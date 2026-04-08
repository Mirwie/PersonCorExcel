package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    private final String filePath;
    List<List<Double>> daten = new ArrayList<>();

    public ExcelReader(String filePath) {
        this.filePath = filePath;
        try {
            this.daten = readAllRows();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<Double>> readAllRows() throws IOException {

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                List<Double> zeile = new ArrayList<>();
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        zeile.add(cell.getNumericCellValue());
                    }
                }
                if (!zeile.isEmpty()) {
                    daten.add(zeile);
                }
            }
        }
        return daten;
    }

    public double[] getColumn(int columnIndex) {
        List<Double> column = new ArrayList<>();
        if (daten == null) {
            try {
                readAllRows();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (List<Double> row : daten) {
            column.add(row.get(columnIndex));
        }

        return column.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
