package net.nlt.matrix.multiplication;

import static java.lang.Math.random;

public class SquareMatrices {

    static SquareMatrix zeros(int size) {
        return new SimpleSquareMatrix(new double[size][size]);
    }

    static SquareMatrix identity(int size) {
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    result[i][j] = 1;
                }
            }
        }
        return new SimpleSquareMatrix(result);
    }

    static SquareMatrix rand(int size) {
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = random();
            }
        }
        return new SimpleSquareMatrix(result);
    }
}
