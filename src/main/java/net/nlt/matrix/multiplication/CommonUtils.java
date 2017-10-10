package net.nlt.matrix.multiplication;

class CommonUtils {

    private CommonUtils() {
    }

    static int nextMultiple(int num, int divide) {
        if (num < divide) {
            return divide;
        }

        int remainder = num % divide;
        return remainder == 0
                ? num
                : num + (divide - remainder);
    }
}
