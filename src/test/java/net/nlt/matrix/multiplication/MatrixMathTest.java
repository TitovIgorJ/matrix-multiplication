package net.nlt.matrix.multiplication;

import net.nlt.matrix.multiplication.matrix.SquareMatrix;
import org.junit.Before;
import org.junit.Test;

import static net.nlt.matrix.multiplication.SquareMatrices.identity;
import static net.nlt.matrix.multiplication.SquareMatrices.rand;
import static org.assertj.core.api.Assertions.assertThat;

public class MatrixMathTest {

    private MatrixMath mm;

    @Before
    public void setup() throws Exception {
        mm = new ConcurrentMatrixMath();
    }

    @Test
    public void testMatmul() throws Exception {
        double[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        double[][] matrixB = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        };

        double[][] expectedResult = {
                {4, 2, 4},
                {10, 5, 10},
                {16, 8, 16}
        };

        assertThat(mm.matmul(matrixA, matrixB).toArray()).isEqualTo(expectedResult);
    }

    @Test
    public void testWithIdentityMatrix() throws Exception {
        SquareMatrix m1 = rand(10);
        SquareMatrix identityM = identity(10);

        assertThat(mm.matmul(m1, identityM).toArray()).isEqualTo(m1.toArray());
    }

    @Test
    public void testBigMatrix() throws Exception {
        SquareMatrix a = rand(98);
        SquareMatrix b = identity(98);

        SquareMatrix c = mm.matmul(a, b);

        assertThat(c.toArray()).isEqualTo(a.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateMatrixDifferentSize() throws Exception {
        double[][] a = {
                {1, 1},
                {1, 1}
        };

        double[][] b = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        mm.matmul(a, b);
    }
}