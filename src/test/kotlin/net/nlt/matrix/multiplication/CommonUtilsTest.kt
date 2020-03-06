package net.nlt.matrix.multiplication

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CommonUtilsTest {

    @Test
    fun testAlignSize() {
        Assertions.assertThat(CommonUtils.nextMultiple(0, 4)).isEqualTo(4)
        Assertions.assertThat(CommonUtils.nextMultiple(3, 4)).isEqualTo(4)
        Assertions.assertThat(CommonUtils.nextMultiple(4, 4)).isEqualTo(4)
        Assertions.assertThat(CommonUtils.nextMultiple(5, 4)).isEqualTo(8)
        Assertions.assertThat(CommonUtils.nextMultiple(6, 4)).isEqualTo(8)
        Assertions.assertThat(CommonUtils.nextMultiple(7, 4)).isEqualTo(8)
        Assertions.assertThat(CommonUtils.nextMultiple(8, 4)).isEqualTo(8)
    }
}