package factory;

import data.Task;
import data.Zones;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.assertj.core.util.Lists;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

import static utils.ExcelReader.*;

public class Tasks {

    private static final String TACHES_FILE_PATH = "files/Taches.xlsx";
    private static final String AUTRES_FILE_PATH = "files/Autres.xlsx";

    private ArrayList<Task> tasks;
    private PrintStream out;
    private int numberOfLines;

    public Tasks() {
        this.tasks = new ArrayList<>();
        this.out = createFile();
        this.numberOfLines = 0;
    }

    private PrintStream createFile() {
        try {
            return new PrintStream(new FileOutputStream("files/result.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not create output file");
            return null;
        }
    }

    public Integer getTaskPositionById(String taskId) {
        return getTaskById(taskId)
                .map(task -> tasks.indexOf(task))
                .orElse(-1);
    }

    public void printAllTasksFollowing(String taskId) {
        printTaskAndSuccessors(taskId);

        List<String> successors = getTaskById(taskId)
                .map(Task::getSuccessors)
                .orElse(Lists.emptyList());
        for (String successorTaskId : successors) {
            if (numberOfLines == 99) {
                return;
            }
            printAllTasksFollowing(successorTaskId);
        }
    }

    private void printTaskAndSuccessors(String taskId) {
        out.println(taskId + " -> " + printSuccessor(taskId) + " \n");
        numberOfLines++;
    }

    private String printSuccessor(String taskId) {
        return getTaskById(taskId)
                .map(task -> task.getSuccessors())
                .filter(Objects::nonNull)
                .map(Object::toString)
                .orElse("");
    }

    public Optional<Task> getTaskById(String taskId) {
        return this.tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst();
    }

    public int size() {
        return this.tasks.size();
    }

    public Tasks initialize() {

        readFile(TACHES_FILE_PATH).ifPresent(workbook -> {
            Sheet sheetList = workbook.getSheet("liste");
            Sheet sheetRes = workbook.getSheet("res mobile");
            int numberOfTasks = sheetList.getLastRowNum();
            int numberOfTasksRes = sheetRes.getLastRowNum();

            Row line;
            for (int i = 1; i <= numberOfTasks; i++) {
                line = sheetList.getRow(i);

                tasks.add(new Task()
                        .setProcessingTime(readNumericCell(line, 2))
                        .setNumberOfCompagnons(readNumericCell(line, 3))
                        .setId(convertNumericCellToString(line, 0))
                        .setRegroupement(readStringCell(line, 7))
                        .setProfessionNeeded(readStringCell(line, 5)));
            }

            HashMap<String, String> taskByMachine = new HashMap<>();

            for (int t = 1; t <= numberOfTasksRes; t++) {
                line = sheetRes.getRow(t);
                String key = convertNumericCellToString(line, 0);
                String value = readStringCell(line, 1);
                taskByMachine.put(key, value);
            }

            for (Task task : tasks) {
                if (taskByMachine.containsKey(task.getId())) {
                    task.setUsedMachines(taskByMachine.get(task.getId()));
                }
            }

            for (Task task : tasks) {
                retrieveTasks(workbook, "zones inclues")
                        .findByTask(task.getId())
                        .ifPresent(zone -> task.setAreaUsed(zone.getZones()));
            }

            for (Task task : tasks) {
                retrieveTasks(workbook, "zones exclues")
                        .findByTask(task.getId())
                        .ifPresent(zone -> task.setAreaExcluded(zone.getZones()));
            }
        });


        readFile(AUTRES_FILE_PATH).ifPresent(workbook -> {
            for (Task task : tasks) {
                retrieveTasks(workbook, "precedences")
                        .findByTask(task.getId())
                        .ifPresent(zone -> task.setSuccessors(zone.getZones()));
            }
        });

        return this;
    }

    private Zones retrieveTasks(Workbook workbook, String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        Zones mapper = new Zones();
        Row line;

        for (int t = 1; t <= sheet.getLastRowNum(); t++) {
            line = sheet.getRow(t);

            String task = convertNumericCellToString(line, 0);
            String value = convertNumericCellToString(line, 1);

            mapper.findByTask(task)
                    .orElse(mapper.createNewZone(task))
                    .addZone(value);
        }

        return mapper;
    }


}
