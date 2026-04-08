package org.example;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.io.IOException;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/Bachelorarbeit.xlsx";

        ExcelReader excelReader = new ExcelReader(filePath);

        // 1. Zeitstempel 2. realer Eindruck 3. Wahrnehmung erhöhren 4. proffesionel und modern 5. Vertr 6. vorst. 7. v

        double[] spalte1 = excelReader.getColumn(3);
        double[] spalte2 = excelReader.getColumn(5);

        PearsonsCorrelation pearsonsCorrelation = new PearsonsCorrelation();

        double personCorrelation = pearsonsCorrelation.correlation(spalte1,spalte2);

        System.out.println(personCorrelation);
    }

    private static double durchschnitt(double[] spalte) {
        return Arrays.stream(spalte).sum() / spalte.length;
    }


}