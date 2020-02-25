package net.nlt.matrix.multiplication;

import net.nlt.matrix.multiplication.matrix.SquareMatrix;

import static java.lang.String.format;

abstract class AbstractMatrixMath implements MatrixMath {

    static void validate(SquareMatrix a, SquareMatrix b) {
        if (a.size() != b.size()) {
            throw new IllegalArgumentException(format("Size must be equal (a = %d, b = %d)", a.size(), b.size()));
        }
    }
}
