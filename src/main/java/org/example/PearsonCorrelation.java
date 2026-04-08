package org.example;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class PearsonCorrelation {

    /**
     * Berechnet die Pearson-Korrelation zwischen zwei Arrays
     */
    public static double computePearsonCorrelation(double[] x, double[] y) {
        PearsonsCorrelation pc = new PearsonsCorrelation();
        return pc.correlation(x, y);
    }

    public static double calculateNoLibrary(double[] x, double[] y) {

        if (x.length != y.length) {
            throw new IllegalArgumentException("Arrays müssen gleich lang sein.");
        }

        int n = x.length;

        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;
        double sumY2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumX2 += x[i] * x[i];
            sumY2 += y[i] * y[i];
        }

        double numerator = (n * sumXY) - (sumX * sumY);

        double denominator = Math.sqrt(
                (n * sumX2 - Math.pow(sumX, 2)) *
                        (n * sumY2 - Math.pow(sumY, 2))
        );

        return numerator / denominator;
    }
}
