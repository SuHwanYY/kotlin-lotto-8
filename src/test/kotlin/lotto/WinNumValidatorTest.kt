package lotto

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinNumValidatorTest {

    @Test
    fun `당첨 번호가 6개 콤마 구분이고 1-45 범위면 통과`() {
        val nums = WinNumValidator.parseWinningNumbers("1,2,3,4,5,6")
        assertEquals(listOf(1,2,3,4,5,6), nums.sorted())
    }

    @Test
    fun `숫자 개수가 6개가 아니면 예외`() {
        assertThrows<IllegalArgumentException> { WinNumValidator.parseWinningNumbers("1,2,3,4,5") }
        assertThrows<IllegalArgumentException> { WinNumValidator.parseWinningNumbers("1,2,3,4,5,6,7") }
    }

    @Test
    fun `범위를 벗어나면 예외`() {
        assertThrows<IllegalArgumentException> { WinNumValidator.parseWinningNumbers("0,2,3,4,5,6") }
        assertThrows<IllegalArgumentException> { WinNumValidator.parseWinningNumbers("1,2,3,4,5,46") }
    }

    @Test
    fun `중복이 있으면 예외`() {
        assertThrows<IllegalArgumentException> { WinNumValidator.parseWinningNumbers("1,2,3,3,4,5") }
    }

    @Test
    fun `보너스 번호가 1-45이고 당첨 번호와 중복되지 않으면 통과`() {
        val main = listOf(1,2,3,4,5,6)
        val bonus = WinNumValidator.parseBonusNumber("7", main)
        assertEquals(7, bonus)
    }

    @Test
    fun `보너스 번호가 범위를 벗어나면 예외`() {
        assertThrows<IllegalArgumentException> { WinNumValidator.parseBonusNumber("0", listOf(1,2,3,4,5,6)) }
        assertThrows<IllegalArgumentException> { WinNumValidator.parseBonusNumber("46", listOf(1,2,3,4,5,6)) }
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외`() {
        assertThrows<IllegalArgumentException> { WinNumValidator.parseBonusNumber("6", listOf(1,2,3,4,5,6)) }
    }
}