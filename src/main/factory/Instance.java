package factory;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.ExcelReader.*;

public class Instance {

    /**
     * Represents the complete instance and all its
     * aspects in respect to the input data, but also
     * the data related to the first scheduling and
     * therefore the data used for rescheduling
     */

    private int numberOfAreas;
    private Set<String> zones;
    private HashMap<String, Integer> workersByMachine;
    private HashMap<String, Integer> professionByType;

    private Tasks tasks;
    private Compagnons compagnons;

    //Data for rescheduling
    private int actualStartDateForRescheduling;
    private Affectations previousCouplesMaintainingStartTime;
    private List<String> tasksToReschedule;
    private List<Integer> previousEndingTime;
    private int[][] previousAffectations;

    /**
     * On se sert des param�tres pour initialiser les informations manquantes
     *
     * @param compagnons
     * @param tasks
     * @param previousCouplesMaintainingStartTime
     */
    public Instance(Compagnons compagnons,
                    Tasks tasks,
                    Affectations previousCouplesMaintainingStartTime,
                    int actualStartDateForRescheduling) {
        this.zones = new HashSet<>();
        this.professionByType = new HashMap<>();
        this.compagnons = compagnons;
        this.tasks = tasks;
        this.previousCouplesMaintainingStartTime = previousCouplesMaintainingStartTime;
        this.actualStartDateForRescheduling = actualStartDateForRescheduling;
    }

    public void initialize() {
        readFile("files/Autres.xlsx")
                .ifPresent(workbook -> {
                    initializeZones(workbook.getSheet("zones"));
                    mapCompanonsNumberToProfession(workbook.getSheet("metiers"));
                    mapNumberToMachines(workbook.getSheet("res mobiles"));
                });
        previousEndingTime = previousCouplesMaintainingStartTime.previousEndingTime();
        previousAffectations = new int[tasks.size()][compagnons.size()];
		mapPreviousAffectations();
		tasksToReschedule = previousCouplesMaintainingStartTime.tasksToReschedule(actualStartDateForRescheduling);

    }

	private void mapPreviousAffectations() {
		readFile("files/Compagnons.xlsx")
				.ifPresent(workbook -> {
					Sheet sheet = workbook.getSheet("taches");
					Row line;
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						line = sheet.getRow(i);
						int compagnonId = readNumericCell(line, 0);
						String taskId = convertNumericCellToString(line, 1);

						int taskPosition = tasks.getTaskById(taskId);
						int compagnionPosition = compagnons.getCompagnonPositionById(compagnonId);

						if (taskPosition > -1 && compagnionPosition > -1) {
							previousAffectations[taskPosition][compagnionPosition] = 1;
						}
					}
				});
	}

	private void mapNumberToMachines(Sheet sheet) {
        Row line;
        int height = sheet.getLastRowNum();
        workersByMachine = new HashMap<>();
        for (int h = 1; h <= height; h++) {
            line = sheet.getRow(h);
            String key = readStringCell(line, 0);
            workersByMachine.put(key, 1);
        }
    }

    private void mapCompanonsNumberToProfession(Sheet sheet) {
        Row line;
        int height = sheet.getLastRowNum();
        for (int h = 1; h <= height; h++) {
            line = sheet.getRow(h);
            String key = readStringCell(line, 0);
            int value = readNumericCell(line, 2);
            professionByType.put(key, value);
        }
    }

    private void initializeZones(Sheet sheet) {
        numberOfAreas = sheet.getLastRowNum();
        Row line;

        //Initialisation de la liste des zones
        for (int m = 1; m <= numberOfAreas; m++) {
            line = sheet.getRow(m);
            String zone = readStringCell(line, 0);
            zones.add(zone);
        }
    }

}
