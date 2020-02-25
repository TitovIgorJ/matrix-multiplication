package net.nlt.matrix.multiplication.matrix;

public interface SquareMatrix {

    double get(int row, int col);

    void set(double value, int row, int col);

    double[] getRow(int row);

    double[] getColumn(int col);

    int size();

    double[][] toArray();

    static SquareMatrix of(double[][] data) {
        return new SquareMatrixCachingDecorator(new SimpleSquareMatrix(data));
    }
}
