package factory;

import data.Compagnon;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;

import static utils.ExcelReader.*;

public class Compagnons {

    public static final String COMPAGNONS_FILE_PATH = "files/Compagnons.xlsx";

    private ArrayList<Compagnon> compagnons;

    public Compagnons() {
        this.compagnons = new ArrayList<>();
    }


    public Integer getCompagnonPositionById(int compagnonId) {
        return this.compagnons.stream()
                .filter(compagnon -> compagnon.getId() == compagnonId)
                .findFirst()
                .map(compagnon -> compagnons.indexOf(compagnon))
                .orElse(-1);
    }

    public Compagnons initialize() {
        readFile(COMPAGNONS_FILE_PATH).ifPresent(workbook -> {
            Sheet sheetTypeH = workbook.getSheet("type h");
            Sheet sheetMetier = workbook.getSheet("metier");
            Sheet sheetEquipe = workbook.getSheet("equipe");

            Row lineH;
            Row lineM;
            Row lineE;

            for (int i = 1; i <= sheetTypeH.getLastRowNum(); i++) {
                lineH = sheetTypeH.getRow(i);
                lineM = sheetMetier.getRow(i);
                lineE = sheetEquipe.getRow(i);

                compagnons.add(Compagnon.newCompagnon().id(i)
                        .typeH(readNumericCell(lineH, 1))
                        .profession(readStringCell(lineM, 1))
                        .equipe(readStringCell(lineE, 1))
                        .build());
            }
        });

        return this;
    }

    public int size() {
        return this.compagnons.size();
    }
}
