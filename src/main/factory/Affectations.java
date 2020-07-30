package factory;

import data.Affectation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;

import static utils.ExcelReader.*;

public class Affectations {

    public static final String TACHES_FILE_PATH = "files/Taches.xlsx";
    private ArrayList<Affectation> affectations;

    public Affectations() {
        this.affectations = new ArrayList<>();
    }

    public ArrayList<Affectation> initialize() {
        readFile(TACHES_FILE_PATH).ifPresent(workbook -> {
            Sheet sheet = workbook.getSheet("liste");

            int numberOfTasks = sheet.getLastRowNum();
            Row line;

            for (int t = 1; t <= numberOfTasks; t++) {
                line = sheet.getRow(t);
                affectations.add(
                        Affectation.newAffectation()
                                .taskId(convertNumericCellToString(line, 0))
                                .compagnonId(0)
                                .start(readNumericCell(line, 11))
                                .end(readNumericCell(line, 12))
                                .build());
            }
        });
        return affectations;

    }
}
