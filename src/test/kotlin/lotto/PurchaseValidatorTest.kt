package lotto

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseValidatorTest {

    @Test
    fun `정상 금액 문자열이면 정수로 변환되어 반환한다`() {
        val amount = PurchaseValidator.validate("8000")
        assertEquals(8000, amount)
    }

    @Test
    fun `음수 혹은 0이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { PurchaseValidator.validate("0") }
        assertThrows<IllegalArgumentException> { PurchaseValidator.validate("-1000") }
    }

    @Test
    fun `천원 단위가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { PurchaseValidator.validate("1500") }
        assertThrows<IllegalArgumentException> { PurchaseValidator.validate("999") }
    }

    @Test
    fun `숫자가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { PurchaseValidator.validate("1o00") }
        assertThrows<IllegalArgumentException> { PurchaseValidator.validate("abc") }
        assertThrows<IllegalArgumentException> { PurchaseValidator.validate("") }
    }
}