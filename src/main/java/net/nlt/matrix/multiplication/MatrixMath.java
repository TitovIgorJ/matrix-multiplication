package net.nlt.matrix.multiplication;

import java.util.concurrent.ForkJoinPool;

import static java.lang.String.format;
import static net.nlt.matrix.multiplication.SquareMatrix.zeros;

public class MatrixMath {

    private ForkJoinPool pool;

    public MatrixMath() {
        this.pool = new ForkJoinPool();
    }

    public MatrixMath(int parallelism) {
        this.pool = new ForkJoinPool(parallelism);
    }

    public SquareMatrix matmul(double[][] a, double[][] b) {
        return matmul(new SquareMatrix(a), new SquareMatrix(b));
    }

    public SquareMatrix matmul(SquareMatrix a, SquareMatrix b) {
        validate(a, b);
        int size = a.size();

        SquareMatrix result = zeros(size);

        MultiplyWorker task = new MultiplyWorker(a, b, result, 0, size);
        pool.invoke(task);

        return result;
    }

    private static void validate(SquareMatrix a, SquareMatrix b) {
        if (a.size() != b.size()) {
            throw new IllegalArgumentException(format("Size must be equal (a = %d, b = %d)", a.size(), b.size()));
        }
    }
}
