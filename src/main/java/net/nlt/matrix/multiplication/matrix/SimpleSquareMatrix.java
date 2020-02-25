package net.nlt.matrix.multiplication.matrix;

import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

public class SimpleSquareMatrix implements SquareMatrix {

    private final double[][] data;
    private final int size;
    private SquareMatrixColumnCache columnCache;
    private boolean columnCacheEnabled = true;

    SimpleSquareMatrix(double[][] data) {
        if (!isMatrixSquare(data)) {
            throw new IllegalArgumentException("Matrix is not square");
        }

        this.data = data;
        this.size = data.length;

        if (columnCacheEnabled) {
            this.columnCache = new SquareMatrixColumnCache(size);
        }
    }

    @Override
    public double get(int row, int col) {
        return data[row][col];
    }

    @Override
    public void set(double value, int row, int col) {
        if (columnCacheEnabled) {
            columnCache.delete(col);
        }
        data[row][col] = value;
    }

    @Override
    public double[] getRow(int row) {
        return data[row];
    }

    @Override
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public double[][] toArray() {
        return data;
    }

    @Override
    public String toString() {
        return deepToString(this.data);
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

    private static class SquareMatrixColumnCache {

        private double[][] columns;
        private boolean[] presence;

        public SquareMatrixColumnCache(int size) {
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

        void delete(int col) {
            this.presence[col] = false;
        }

        void delete() {
            fill(presence, false);
        }
    }
}
