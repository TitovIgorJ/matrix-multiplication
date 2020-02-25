package net.nlt.matrix.multiplication.matrix;

import static java.util.Arrays.fill;

public class SquareMatrixCachingDecorator implements SquareMatrix {

    private SquareMatrix squareMatrixDelegate;
    private SquareMatrixColumnCache columnCache;

    public SquareMatrixCachingDecorator(SquareMatrix delegate) {
        this.squareMatrixDelegate = delegate;
        columnCache = new SquareMatrixColumnCache(delegate.size());
    }

    @Override
    public double get(int row, int col) {
        return squareMatrixDelegate.get(row, col);
    }

    @Override
    public void set(double value, int row, int col) {
        columnCache.delete(col);
        squareMatrixDelegate.set(value, row, col);
    }

    @Override
    public double[] getRow(int row) {
        return squareMatrixDelegate.getRow(row);
    }

    @Override
    public double[] getColumn(int col) {
        if (columnCache.contains(col)) {
            return columnCache.get(col);
        } else {
            double[] result = squareMatrixDelegate.getColumn(col);
            columnCache.put(col, result);
            return result;
        }
    }

    @Override
    public int size() {
        return squareMatrixDelegate.size();
    }

    @Override
    public double[][] toArray() {
        return squareMatrixDelegate.toArray();
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
