package net.nlt.matrix.multiplication;

import net.nlt.matrix.multiplication.matrix.SquareMatrix;

public interface MatrixMath {

    SquareMatrix matmul(double[][] a, double[][] b);

    SquareMatrix matmul(SquareMatrix a, SquareMatrix b);
}
