package lotto

internal object WinNumValidator {
    private const val MIN = 1
    private const val MAX = 45
    private const val REQUIRED_SIZE = 6

    fun parseWinningNumbers(raw: String): List<Int> {
        val parts = raw.split(",").map { it.trim() }
        require(parts.size == REQUIRED_SIZE) {
            "[ERROR] 당첨 번호는 쉼표(,)로 구분된 6개의 숫자여야 합니다."
        }
        val numbers = parts.mapNotNull {
            it.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 당첨 번호는 숫자만 입력해야 합니다.")
        }
        require(numbers.all { it in MIN..MAX }) { "[ERROR] 당첨 번호는 1~45 사이여야 합니다." }
        require(numbers.toSet().size == REQUIRED_SIZE) { "[ERROR] 당첨 번호는 중복될 수 없습니다." }
        return numbers.sorted()
    }

    fun parseBonusNumber(raw: String, winningNumbers: List<Int>): Int {
        val trimmed = raw.trim()
        val bonus = trimmed.toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.")

        require(bonus in MIN..MAX) { "[ERROR] 보너스 번호는 1~45 사이여야 합니다." }
        require(!winningNumbers.contains(bonus)) { "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다." }
        return bonus
    }
}