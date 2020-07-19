package constructor;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import factory.Affectation;
import factory.Compagnon;
import factory.Tache;

public class Excel_reader {

	/**
	 * Cr?e la liste des compagnons dans l'instance
	 * @return
	 */
	public static ArrayList<Compagnon> createCompagnonsList() {

		ArrayList<Compagnon> result = new ArrayList<Compagnon>();
		
		try {
			
			//Instanciation de la liste des types horaires
			FileInputStream file = new FileInputStream("files/Compagnons.xlsx");
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet("type h");
			
			int nb_of_compagnons = sheet.getLastRowNum();
			Row line;
			
			for(int i = 1; i <= nb_of_compagnons; i++) {
				line = sheet.getRow(i);
				result.add(new Compagnon(i, (int) line.getCell(1).getNumericCellValue(), "", ""));
			}
			
			//Maintenant on se charge du m?tier
			sheet = workbook.getSheet("metier");
			for(int i = 1; i <= nb_of_compagnons; i++) {
				line = sheet.getRow(i);
				result.get(i-1).setProfession(line.getCell(1).getStringCellValue());
			}
			
			//Maintenant on se charge de l'?quipe
			sheet = workbook.getSheet("equipe");
			for(int i = 1; i <= nb_of_compagnons; i++) {
				line = sheet.getRow(i);
				result.get(i-1).setEquipe(line.getCell(1).getStringCellValue());
			}
			
			workbook.close();
			file.close();
			
		} catch (Exception e){
			System.out.println("Cr?ation de la liste de compagnons impossible.");
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * Instanciation des informations li?es aux t?ches de l'instance
	 * 		Liste des informations de chaque oclone dans la feuille "liste" :
	 * 		1 : ID
	 * 		2 : D?signation ( = ID )
	 * 		3 : Temps de fabrication
	 * 		4 : Nb de Compagnons
	 * 		5 : Type horaire
	 * 		6 : M?tier
	 * 		7 : Interruption impossible
	 * 		8 : Regroupement de t?ches
	 * 		9 : Poste
	 * 		10 : Equipe
	 * 		11 : Avion
	 *		12 : Debut
	 * 		13 : Fin
	 * @return la liste des t?ches pr?sentes
	 */
	public static ArrayList<Tache> createTasksList() {

		ArrayList<Tache> result = new ArrayList<Tache>();
		
		try {
			
			//Cr?ation de la liste des t?ches
			FileInputStream file = new FileInputStream("files/Taches.xlsx");
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet("liste");
			
			int nb_of_tasks = sheet.getLastRowNum();
			String converted_ID, regroup;
			Row line;
			
			for(int i = 1; i <= nb_of_tasks; i++) {
				line = sheet.getRow(i);
				
				//On v?rifie que l'ID soit bien un String
				if(line.getCell(0).getCellType() == CellType.NUMERIC)
					converted_ID = String.valueOf(
								(int) line.getCell(0).getNumericCellValue());
				else
					converted_ID = line.getCell(0).getStringCellValue();
				
				//On v?rifie que la case de regroupement ne soit pas "null"
				if(line.getCell(7) == null)
					regroup = "";
				else
					regroup = line.getCell(7).getStringCellValue();
				
				result.add(new Tache((int) line.getCell(2).getNumericCellValue(), 	//processing time
						(int) line.getCell(3).getNumericCellValue(), 				//Nb compagnons
						converted_ID, 												//ID
						"", 														//Machine utilis?e
						regroup,  													//Regroupement
						line.getCell(5).getStringCellValue(), 						//M?tier n?cessaire
						null, 														//zones utilis?es
						null, 														//zones exclues 
						null)); 													//successeurs
			}
			
			//On met ? jour les informations sur les ressources mobiles
			sheet = workbook.getSheet("res mobile");
			nb_of_tasks = sheet.getLastRowNum();
			HashMap<String, String> task_mach = new HashMap<String, String>();
			
			for(int t=0; t<nb_of_tasks; t++) {
				line = sheet.getRow(t+1);
				String key = String.valueOf((int) line.getCell(0).getNumericCellValue());
				String value = line.getCell(1).getStringCellValue();
				task_mach.put(key, value);
			}
			
			for(int t=0; t<result.size(); t++)
				if(task_mach.containsKey(result.get(t).getID()))
					result.get(t).setM_uses(task_mach.get(result.get(t).getID()));
			
			//On met ? jour les informations sur les zones inclues
			HashMap<String, ArrayList<String>> z_inclues = recup_taches(workbook, "zones inclues");
			
			for(int t=0; t<result.size(); t++)
				if(z_inclues.containsKey(result.get(t).getID()))
					result.get(t).setA_used(z_inclues.get(result.get(t).getID()));
			
			//De m?me avec les zones exclues
			HashMap<String, ArrayList<String>> z_exclues = recup_taches(workbook, "zones exclues");
			
			for(int t=0; t<result.size(); t++)
				if(z_exclues.containsKey(result.get(t).getID()))
					result.get(t).setA_excluded(z_exclues.get(result.get(t).getID()));
			
			//On s'occupe des successeurs
			file = new FileInputStream("files/Autres.xlsx");
			workbook = WorkbookFactory.create(file);
			HashMap<String, ArrayList<String>> succ = recup_taches(workbook, "precedences");
			
			for(int t=0; t<result.size(); t++)
				if(succ.containsKey(result.get(t).getID()))
					result.get(t).setSuccessors(succ.get(result.get(t).getID()));			
			
			workbook.close();
			file.close();
			
		} catch (Exception e){
			System.out.println("Cr?ation de la liste de t?ches impossible.");
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * R?cup?re les zones associ?es ? chaque t?che dans la feuille "sheetName"
	 * @param workbook
	 * @param sheetName
	 * @return
	 */
	private static HashMap<String, ArrayList<String>> recup_taches(Workbook workbook, String sheetName){
		
		Sheet sheet = workbook.getSheet(sheetName);
		int nb_of_tasks = sheet.getLastRowNum();
		
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		Row line;
		
		for(int t=0; t<nb_of_tasks; t++) {
			ArrayList<String> zones = new ArrayList<String>();
			line = sheet.getRow(t+1);
			
			String key;
			if(line.getCell(0).getCellType() == CellType.NUMERIC)
				key = String.valueOf((int) line.getCell(0).getNumericCellValue());
			else
				key = line.getCell(0).getStringCellValue();
			
			String value;
			if(line.getCell(1).getCellType() == CellType.NUMERIC)
				value = String.valueOf((int) line.getCell(1).getNumericCellValue());
			else
				value = line.getCell(1).getStringCellValue();
			
			if(result.containsKey(key)) {
				zones = result.get(key);
				zones.add(value);
				result.replace(key, zones);
			} else {
				zones.add(value);
				result.put(key, zones);
			}
		}
		
		return result;
	}

	
	/**
	 * R?cup?re les anciennes affectations et date de d?part/fin de chaque t?che
	 * @return
	 */
	public static ArrayList<Affectation> getAffectations() {

		ArrayList<Affectation> result = new ArrayList<Affectation>();
		
		try {
			
			//Cr?ation de la liste des t?ches
			FileInputStream file = new FileInputStream("files/Taches.xlsx");
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet("liste");
			
			int nb_of_tasks = sheet.getLastRowNum();
			String id = "";
			Row line;
			
			for(int t = 0; t < nb_of_tasks; t++) {
				line = sheet.getRow(t+1);
				if(line.getCell(0).getCellType() == CellType.NUMERIC)
					id = String.valueOf((int) line.getCell(0).getNumericCellValue());
				else
					id = line.getCell(0).getStringCellValue();
				result.add(
						new Affectation(id, 0, 									//Task id and compagnon id
								(int) line.getCell(11).getNumericCellValue(), 	//Start
								(int) line.getCell(12).getNumericCellValue()));	//End
			}
			
			workbook.close();
			file.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erreur dans la r?cup?ration des affectations");
		}
		
		return result;
	}


}
