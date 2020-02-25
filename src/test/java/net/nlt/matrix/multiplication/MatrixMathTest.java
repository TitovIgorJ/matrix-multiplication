package net.nlt.matrix.multiplication;

import net.nlt.matrix.multiplication.matrix.SquareMatrix;
import org.junit.jupiter.api.Test;

import static net.nlt.matrix.multiplication.SquareMatrices.identity;
import static net.nlt.matrix.multiplication.SquareMatrices.rand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatrixMathTest {

    private final MatrixMath mm = new ConcurrentMatrixMath();

    @Test
    void testMatmul() {
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
    void testWithIdentityMatrix() {
        SquareMatrix m1 = rand(10);
        SquareMatrix identityM = identity(10);

        assertThat(mm.matmul(m1, identityM).toArray()).isEqualTo(m1.toArray());
    }

    @Test
    void testBigMatrix() {
        SquareMatrix a = rand(98);
        SquareMatrix b = identity(98);

        SquareMatrix c = mm.matmul(a, b);

        assertThat(c.toArray()).isEqualTo(a.toArray());
    }

    @Test
    void testValidateMatrixDifferentSize() {
        double[][] a = {
                {1, 1},
                {1, 1}
        };

        double[][] b = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        assertThrows(IllegalArgumentException.class, () -> mm.matmul(a, b));
    }
}
