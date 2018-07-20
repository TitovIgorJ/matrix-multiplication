package net.nlt.matrix.multiplication;

class CommonUtils {

    private CommonUtils() {
    }

    static int nextMultiple(int number, int divider) {
        if (number < divider) {
            return divider;
        }

        int remainder = number % divider;
        return remainder == 0
                ? number
                : number + (divider - remainder);
    }
}
