package net.nlt.matrix.multiplication

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class SquareMatrixTest {

    private val mm: MatrixMath = ConcurrentMatrixMath()

    @Test
    fun testValidateNotSquareMatrix() {
        val notSquareMatrix = arrayOf(
                doubleArrayOf(1.0),
                doubleArrayOf(0.0, 1.0),
                doubleArrayOf(1.0, 0.0, 1.0)
        )
        assertThrows(IllegalArgumentException::class.java) {
            mm.matmul(notSquareMatrix, notSquareMatrix)
        }
    }
}