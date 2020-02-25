package net.nlt.matrix.multiplication.matrix;

import static java.util.Arrays.deepToString;

public class SimpleSquareMatrix implements SquareMatrix {

    private final double[][] data;
    private final int size;

    SimpleSquareMatrix(double[][] data) {
        if (!isMatrixSquare(data)) {
            throw new IllegalArgumentException("Matrix is not square");
        }
        this.data = data;
        this.size = data.length;
    }

    @Override
    public double get(int row, int col) {
        return data[row][col];
    }

    @Override
    public void set(double value, int row, int col) {
        data[row][col] = value;
    }

    @Override
    public double[] getRow(int row) {
        return data[row];
    }

    @Override
    public double[] getColumn(int col) {
        double[] result = new double[size];
        for (int row = 0; row < size; row++) {
            result[row] = data[row][col];
        }
        return result;
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
}
