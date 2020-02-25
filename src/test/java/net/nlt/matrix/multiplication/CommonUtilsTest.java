package net.nlt.matrix.multiplication;

import org.junit.jupiter.api.Test;

import static net.nlt.matrix.multiplication.CommonUtils.nextMultiple;
import static org.assertj.core.api.Assertions.assertThat;

class CommonUtilsTest {

    @Test
    void testAlignSize() {
        assertThat(nextMultiple(0, 4)).isEqualTo(4);

        assertThat(nextMultiple(3, 4)).isEqualTo(4);
        assertThat(nextMultiple(4, 4)).isEqualTo(4);

        assertThat(nextMultiple(5, 4)).isEqualTo(8);
        assertThat(nextMultiple(6, 4)).isEqualTo(8);
        assertThat(nextMultiple(7, 4)).isEqualTo(8);
        assertThat(nextMultiple(8, 4)).isEqualTo(8);
    }
}
