package lotto

import kotlin.math.round

internal object ResultCalculator {

    fun evaluate(tickets: List<Lotto>, winning: List<Int>, bonus: Int): Map<Rank, Int> {
        val winSet = winning.toSet()

        // 모든 Rank를 0으로 초기화 (출력 시 null 방지)
        val counts = linkedMapOf(
            Rank.FIFTH to 0,
            Rank.FOURTH to 0,
            Rank.THIRD to 0,
            Rank.SECOND to 0,
            Rank.FIRST to 0
        )

        tickets.forEach { lotto ->
            val values = lotto.values()
            val match = values.count { it in winSet }
            val bonusMatched = bonus in values
            Rank.of(match, bonusMatched)?.let { r ->
                counts[r] = counts.getValue(r) + 1
            }
        }
        return counts
    }

    fun calculateEarningsRate(result: Map<Rank, Int>, purchaseAmount: Int): Double {
        if (purchaseAmount == 0) return 0.0
        val totalPrize = result.entries.sumOf { (rank, cnt) -> rank.prize * cnt }
        val rate = totalPrize.toDouble() / purchaseAmount * 100
        // 소수점 둘째 자리 반올림 → 소수 첫째 자리까지 표기 (예: 62.5)
        return round(rate * 10) / 10.0
    }

    fun formatPrize(n: Long): String = "%,d원".format(n)
}