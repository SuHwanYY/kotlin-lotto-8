package lotto

internal object PurchaseValidator {
    private const val UNIT = 1_000

    fun validate(raw: String): Int {
        val text = raw.trim()
        require(text.isNotEmpty()) { "[ERROR] 금액을 입력해주세요." }

        val amount = text.toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 금액은 숫자로만 입력해주세요.")

        require(amount > 0) { "[ERROR] 금액은 1,000원 이상의 양수여야 합니다." }
        require(amount % UNIT == 0) { "[ERROR] 금액은 반드시 1,000원 단위여야 합니다." }
        return amount
    }

    fun toCount(amount: Int): Int = amount / UNIT
}