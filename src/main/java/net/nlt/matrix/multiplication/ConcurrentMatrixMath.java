package net.nlt.matrix.multiplication;

import net.nlt.matrix.multiplication.matrix.SquareMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.nlt.matrix.multiplication.CommonUtils.nextMultiple;
import static net.nlt.matrix.multiplication.SquareMatrices.zeros;

public class ConcurrentMatrixMath extends AbstractMatrixMath {

    private static final Logger LOG = LoggerFactory.getLogger(ConcurrentMatrixMath.class);

    private ForkJoinPool pool;
    private int parallelism;

    public ConcurrentMatrixMath() {
        this.pool = new ForkJoinPool();
        this.parallelism = pool.getParallelism();
    }

    public ConcurrentMatrixMath(int parallelism) {
        this.parallelism = parallelism;
        this.pool = new ForkJoinPool(parallelism);
    }

    @Override
    public SquareMatrix matmul(double[][] a, double[][] b) {
        return matmul(SquareMatrix.of(a), SquareMatrix.of(b));
    }

    @Override
    public SquareMatrix matmul(SquareMatrix a, SquareMatrix b) {
        validate(a, b);
        int size = a.size();

        SquareMatrix result = zeros(size);

        LOG.debug("Starting calculation, parallelism = {}", parallelism);
        pool.invokeAll(split(a, b, result));

        return result;
    }

    private Collection<Callable<Long>> split(SquareMatrix a, SquareMatrix b, SquareMatrix result) {
        int size = a.size();
        int perWorker = calcPerWorker(size);

        List<Callable<Long>> tasks = new ArrayList<>();

        int lo = 0;
        int hi = perWorker;
        for (int i = 0; i < size; i += perWorker) {
            MultiplyWorker task = new MultiplyWorker(a, b, result, lo, hi);
            tasks.add(task);

            lo = hi;
            hi = min(hi + perWorker, size); //min() - avoid ArrayOutOfBoundException
        }

        return tasks;
    }

    private int calcPerWorker(int size) {
        int nextMultiple = nextMultiple(size, parallelism);
        int perWorker = nextMultiple / parallelism;
        return max(perWorker, 1); //max() - to avoid infinite loop when parallelism >= size
    }
}
