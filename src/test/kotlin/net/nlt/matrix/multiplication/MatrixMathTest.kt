package net.nlt.matrix.multiplication

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class MatrixMathTest {

    private val mm: MatrixMath = ConcurrentMatrixMath()

    @Test
    fun simpleTest() {
        val matrixA = arrayOf(
                doubleArrayOf(1.0, 2.0, 3.0),
                doubleArrayOf(4.0, 5.0, 6.0),
                doubleArrayOf(7.0, 8.0, 9.0)
        )
        val matrixB = arrayOf(
                doubleArrayOf(1.0, 0.0, 1.0),
                doubleArrayOf(0.0, 1.0, 0.0),
                doubleArrayOf(1.0, 0.0, 1.0)
        )
        val expectedResult = arrayOf(
                doubleArrayOf(4.0, 2.0, 4.0),
                doubleArrayOf(10.0, 5.0, 10.0),
                doubleArrayOf(16.0, 8.0, 16.0)
        )
        assertThat(mm.matmul(matrixA, matrixB).toArray()).isEqualTo(expectedResult)
    }

    @Test
    fun testWithIdentityMatrix() {
        val m1 = SquareMatrices.rand(10)
        val identityM = SquareMatrices.identity(10)
        assertThat(mm.matmul(m1, identityM).toArray()).isEqualTo(m1.toArray())
    }

    @Test
    fun testBigMatrix() {
        val a = SquareMatrices.rand(98)
        val b = SquareMatrices.identity(98)
        val c = mm.matmul(a, b)
        assertThat(c.toArray()).isEqualTo(a.toArray())
    }

    @Test
    fun testValidateMatrixDifferentSize() {
        val a = arrayOf(
                doubleArrayOf(1.0, 1.0),
                doubleArrayOf(1.0, 1.0)
        )
        val b = arrayOf(
                doubleArrayOf(1.0, 1.0, 1.0),
                doubleArrayOf(1.0, 1.0, 1.0),
                doubleArrayOf(1.0, 1.0, 1.0)
        )
        assertThrows(IllegalArgumentException::class.java) {
            mm.matmul(a, b)
        }
    }
}