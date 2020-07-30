package factory;

import data.Affectation;
import data.Compagnon;
import data.Task;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Instance {

	/**
	 * Represents the complete instance and all its
	 * aspects in respect to the input data, but also
	 * the data related to the first scheduling and
	 * therefore the data used for rescheduling
	 */
	
	//Input data
	int nbAreas;
	ArrayList<String> A; 						//Liste des zones
	HashMap<String, Integer> machines, workers; //Maps du nombre de travailleurs/machines par profession/type
	
	ArrayList<Task> tasks_list;
	ArrayList<Compagnon> workers_list;
	
	//Data for rescheduling
	int actual_date;							//Start date for rescheduling
	ArrayList<Affectation> previous_couples;	//All couples that won't change and maintain their start time
	ArrayList<String> to_reschedule;			//List of all tasks ids to reschedule
	ArrayList<Integer> old_end;					//Previous ending time for every task
	int[][] old_aff;							//Previous affectations for each task
	
	/**
	 * On se sert des param�tres pour initialiser les informations manquantes
	 * @param workers_list
	 * @param tasks_list
	 * @param previous_couples
	 */
	public Instance(ArrayList<Compagnon> workers_list, ArrayList<Task> tasks_list,
			ArrayList<Affectation> previous_couples, int actual_date) {
		
		this.workers_list = workers_list;
		this.tasks_list = tasks_list;
		this.previous_couples = previous_couples;
		this.actual_date = actual_date;

		try {
			//NbAreas peut �tre initialis� sans eux dans u npremier temps
			FileInputStream file = new FileInputStream("files/Autres.xlsx");
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet("zones");
			nbAreas = sheet.getLastRowNum();
			Row line;
			
			//Initialisation de la liste des zones
			A = new ArrayList<String>();
			for(int m = 0; m < nbAreas; m++) {
				line = sheet.getRow(m+1);
				String zone = line.getCell(0).getStringCellValue();
				if(!A.contains(zone))
					A.add(zone);
			}
			
			int height;
			
			//Initialisation liste professions et nombre de compagnons par prof
			sheet = workbook.getSheet("metiers");
			height = sheet.getLastRowNum();
			workers = new HashMap<String, Integer>();
			for(int h = 0; h < height; h++) {
				line = sheet.getRow(h+1);
				String key = line.getCell(0).getStringCellValue();
				int value = (int) line.getCell(2).getNumericCellValue();
				workers.put(key, value);
			}
			
			//Initialisation liste machines et leur nombre
			sheet = workbook.getSheet("res mobiles");
			height = sheet.getLastRowNum();
			machines = new HashMap<String, Integer>();
			for(int h = 0; h < height; h++) {
				line = sheet.getRow(h+1);
				String key = line.getCell(0).getStringCellValue();
				machines.put(key, 1);
			}
			
			//Initialisation des anciennes dates de fin
			old_end = new ArrayList<Integer>();
			for(Affectation aff : previous_couples)
				old_end.add(aff.getEnd());
			
			//Initialisation des anciennes affectations
			old_aff = new int[tasks_list.size()][workers_list.size()];
			file = new FileInputStream("files/Compagnons.xlsx");
			workbook = WorkbookFactory.create(file);
			sheet = workbook.getSheet("taches");
			height = sheet.getLastRowNum();
			for(int i = 0; i < height; i++) {
				line = sheet.getRow(i+1);
				//On r�cup�re les IDs du compagnon et e la t�che
				int c_id = (int) line.getCell(0).getNumericCellValue();
				String t_id;
				if(line.getCell(1).getCellType() == CellType.NUMERIC)
					t_id = String.valueOf(
								(int) line.getCell(1).getNumericCellValue());
				else
					t_id = line.getCell(1).getStringCellValue();
				
				//On cherhcer leurs positions dans leur liste respective
				int comp_pos = -1, tas_pos = -1;
				for(int t = 0; t < tasks_list.size(); t++) 
					if(tasks_list.get(t).getId().equals(t_id))
						tas_pos = t;
				
				for(int c = 0; c < workers_list.size(); c++)
					if(workers_list.get(c).getId() == c_id)
						comp_pos = c;
				
				old_aff[tas_pos][comp_pos] = 1;

			}
			
			//On fini par d�finir la liste des t�ches � r�affecter
			//TODO Old_school version, � modifier pour coller aux diff�rentes r�gles
			//TODO Idea : mettre une pop up qui dit 'voici ce qui a �t� selectionn�, c'est bon?
			to_reschedule = new ArrayList<String>();
			for(Affectation aff : previous_couples)
				if(aff.getStart() >= actual_date)
					to_reschedule.add(aff.getTaskId());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur dans la cr�ation de l'instance");
		}
	}

	public int getNbAreas() {
		return nbAreas;
	}

	public void setNbAreas(int nbAreas) {
		this.nbAreas = nbAreas;
	}

	public ArrayList<String> getA() {
		return A;
	}

	public void setA(ArrayList<String> a) {
		A = a;
	}

	public HashMap<String, Integer> getMachines() {
		return machines;
	}

	public void setMachines(HashMap<String, Integer> machines) {
		this.machines = machines;
	}

	public HashMap<String, Integer> getWorkers() {
		return workers;
	}

	public void setWorkers(HashMap<String, Integer> workers) {
		this.workers = workers;
	}

	public ArrayList<Task> getTasks_list() {
		return tasks_list;
	}

	public void setTasks_list(ArrayList<Task> tasks_list) {
		this.tasks_list = tasks_list;
	}

	public ArrayList<Compagnon> getWorkers_list() {
		return workers_list;
	}

	public void setWorkers_list(ArrayList<Compagnon> workers_list) {
		this.workers_list = workers_list;
	}

	public int getActual_date() {
		return actual_date;
	}

	public void setActual_date(int actual_date) {
		this.actual_date = actual_date;
	}

	public ArrayList<Affectation> getFixed_couples() {
		return previous_couples;
	}

	public void setFixed_couples(ArrayList<Affectation> fixed_couples) {
		this.previous_couples = fixed_couples;
	}

	public ArrayList<String> getTo_reschedule() {
		return to_reschedule;
	}

	public void setTo_reschedule(ArrayList<String> to_reschedule) {
		this.to_reschedule = to_reschedule;
	}

	public ArrayList<Integer> getOld_end() {
		return old_end;
	}

	public void setOld_end(ArrayList<Integer> old_end) {
		this.old_end = old_end;
	}

	public int[][] getOld_aff() {
		return old_aff;
	}

	public void setOld_aff(int[][] old_aff) {
		this.old_aff = old_aff;
	}
		
}
