package net.nlt.matrix.multiplication;

import static java.lang.Math.random;

public class SquareMatrix {

    private final double[][] data;
    private final int size;
    private SquareMatrixColumnCache columnCache;
    private boolean columnCacheEnabled = true;

    public SquareMatrix(double[][] data) {
        if (!isMatrixSquare(data)) {
            throw new IllegalArgumentException("Matrix is not square");
        }

        this.data = data;
        this.size = data.length;

        if (columnCacheEnabled) {
            this.columnCache = new SquareMatrixColumnCache(size);
        }
    }

    public static SquareMatrix zeros(int size) {
        return new SquareMatrix(new double[size][size]);
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

        return new SquareMatrix(result);
    }

    public static SquareMatrix rand(int size) {
        double[][] result = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = random();
            }
        }

        return new SquareMatrix(result);
    }

    public double get(int row, int col) {
        return data[row][col];
    }

    public void set(double value, int row, int col) {
        data[row][col] = value;
    }

    public double[] getRow(int row) {
        return data[row];
    }

    public double[] getColumn(int col) {
        if (columnCacheEnabled && columnCache.contains(col)) {
            return columnCache.get(col);
        } else {
            double[] result = new double[size];

            for (int row = 0; row < size; row++) {
                result[row] = data[row][col];
            }

            if (columnCacheEnabled) {
                columnCache.put(col, result);
            }

            return result;
        }
    }

    public SquareMatrix transpose() {
        double[][] result = new double[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                result[row][col] = data[col][row];
            }
        }

        return new SquareMatrix(result);
    }

    public int size() {
        return size;
    }

    public double[][] toArray() {
        return data;
    }

    private static class SquareMatrixColumnCache {
        private int size;

        private double[][] columns;
        private boolean[] presence;

        public SquareMatrixColumnCache(int size) {
            this.size = size;
            this.columns = new double[size][size];
            this.presence = new boolean[size];
        }

        void put(int col, double[] data) {
            columns[col] = data;
            presence[col] = true;
        }

        boolean contains(int col) {
            return presence[col];
        }

        double[] get(int col) {
            return columns[col];
        }
    }

    private boolean isMatrixSquare(double[][] matrix) {
        int rowsCount = matrix.length;

        for (double[] row : matrix) {
            if (row.length != rowsCount) {
                return false;
            }
        }

        return true;
    }
}
