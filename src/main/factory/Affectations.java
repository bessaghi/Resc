package factory;

import data.Affectation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static utils.ExcelReader.*;

public class Affectations {

    public static final String TACHES_FILE_PATH = "files/Taches.xlsx";
    private ArrayList<Affectation> affectations;

    public Affectations() {
        this.affectations = new ArrayList<>();
    }

    public Optional<Affectation> getAffectationById(String taskId) {
        return affectations.stream()
                .filter(affectation -> taskId.equals(affectation.getTaskId()))
                .findFirst();
    }

    public Affectations initialize() {
        readFile(TACHES_FILE_PATH).ifPresent(workbook -> {
            Sheet sheet = workbook.getSheet("liste");

            int numberOfTasks = sheet.getLastRowNum();
            Row line;

            for (int t = 1; t <= numberOfTasks; t++) {
                line = sheet.getRow(t);
                affectations.add(
                        new Affectation()
                                .setTaskId(convertNumericCellToString(line, 0))
                                .setCompagnonId(0)
                                .setStart(readNumericCell(line, 11))
                                .setEnd(readNumericCell(line, 12))
                );
            }
        });
        return this;

    }

    public List<String> tasksToReschedule(int actualStartDateForRescheduling) {
        return affectations.stream()
                .filter(affectation -> affectation.getStart() >= actualStartDateForRescheduling)
                .map(Affectation::getTaskId)
                .collect(Collectors.toList());
    }

    public List<Integer> previousEndingTime() {
        return affectations.stream()
                .map(Affectation::getEnd)
                .collect(Collectors.toList());
    }
}
