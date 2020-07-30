package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class ExcelReader {


    public static Optional<Workbook> readFile(String fileName) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            return Optional.of(WorkbookFactory.create(file));
        } catch (IOException e) {
            System.err.println("Error while reading " + fileName);
            return Optional.empty();
        }
    }

    public static String readStringCell(Row line, int cellNumber) {
        return Optional.ofNullable(line.getCell(cellNumber)).map(Cell::getStringCellValue).orElse("");
    }

    public static int readNumericCell(Row line, int cellNumber) {
        return (int) line.getCell(cellNumber).getNumericCellValue();
    }

    public static String convertNumericCellToString(Row line, int cellNumber) {
        if (line.getCell(cellNumber).getCellType() == CellType.NUMERIC) {
            return String.valueOf(readNumericCell(line, cellNumber));
        }
        return readStringCell(line, cellNumber);
    }

}
