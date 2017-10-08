package net.nlt.matrix.multiplication;

import java.util.concurrent.RecursiveAction;

import static java.lang.String.format;
import static java.lang.Thread.currentThread;

public class MultiplyWorker extends RecursiveAction {

    private SquareMatrix a;
    private SquareMatrix b;
    private SquareMatrix result;

    private int fromRow;
    private int toRow;

    private final int THRESHOLD = 10;

    public MultiplyWorker(SquareMatrix a, SquareMatrix b, SquareMatrix result, int fromRow, int toRow) {
        this.a = a;
        this.b = b;
        this.result = result;

        this.fromRow = fromRow;
        this.toRow = toRow;
    }

    @Override
    protected void compute() {
        if ((toRow - fromRow) <= THRESHOLD) {
            System.out.println(format("Rows [%d .. %d] calculated by: %s", fromRow, (toRow - 1), currentThread().getName()));
            for (int i = fromRow; i < toRow; i++) {
                for (int j = 0; j < result.size(); j++) {
                    double value = produceRowToColumn(a.getRow(i), b.getColumn(j));
                    result.set(value, i, j);
                }
            }
        } else {
            int mid = (fromRow + toRow) / 2;

            MultiplyWorker left = new MultiplyWorker(a, b, result, fromRow, mid);
            MultiplyWorker right = new MultiplyWorker(a, b, result, mid, toRow);

            invokeAll(left, right);
        }
    }

    private static double produceRowToColumn(double[] row, double[] col) {
        int size = row.length;

        double result = 0;

        for (int i = 0; i < size; i++) {
            result += row[i] * col[i];
        }

        return result;
    }
}
