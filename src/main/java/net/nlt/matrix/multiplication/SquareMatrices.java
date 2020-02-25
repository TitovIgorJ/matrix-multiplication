package net.nlt.matrix.multiplication;

import net.nlt.matrix.multiplication.matrix.SquareMatrix;

import static java.lang.Math.random;

public class SquareMatrices {

    public static SquareMatrix zeros(int size) {
        return SquareMatrix.of(new double[size][size]);
    }

    public static SquareMatrix identity(int size) {
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    result[i][j] = 1;
                }
            }
        }
        return SquareMatrix.of(result);
    }

    public static SquareMatrix rand(int size) {
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = random();
            }
        }
        return SquareMatrix.of(result);
    }

    public SquareMatrix transpose(SquareMatrix squareMatrix) {
        int size = squareMatrix.size();
        double[][] result = new double[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                result[row][col] = squareMatrix.get(row, col);
            }
        }

        return SquareMatrix.of(result);
    }
}
