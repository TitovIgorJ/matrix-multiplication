package net.nlt.matrix.multiplication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SquareMatrixTest {

    private final MatrixMath mm = new ConcurrentMatrixMath();

    @Test
    void testValidateNotSquareMatrix() {
        double[][] notSquareMatrix = {
                {1},
                {0, 1},
                {1, 0, 1}
        };
        assertThrows(IllegalArgumentException.class, () -> mm.matmul(notSquareMatrix, notSquareMatrix));
    }
}
