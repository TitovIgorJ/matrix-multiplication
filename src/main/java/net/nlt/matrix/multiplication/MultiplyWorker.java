package net.nlt.matrix.multiplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveAction;

import static java.lang.String.format;

public class MultiplyWorker extends RecursiveAction {

    private static final Logger LOG = LoggerFactory.getLogger(MultiplyWorker.class);

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
            for (int i = fromRow; i < toRow; i++) {
                for (int j = 0; j < result.size(); j++) {
                    double value = produceRowToColumn(a.getRow(i), b.getColumn(j));
                    result.set(value, i, j);
                }
            }
            LOG.debug(format(": rows [%d .. %d] calculated", fromRow, (toRow - 1)));
        } else {
            int mid = (fromRow + toRow) / 2;

            MultiplyWorker left = new MultiplyWorker(a, b, result, fromRow, mid);
            MultiplyWorker right = new MultiplyWorker(a, b, result, mid, toRow);

            invokeAll(left, right);
        }
    }

    private double produceRowToColumn(double[] row, double[] col) {
        int size = row.length;

        double result = 0;

        for (int i = 0; i < size; i++) {
            result += row[i] * col[i];
        }

        return result;
    }
}
