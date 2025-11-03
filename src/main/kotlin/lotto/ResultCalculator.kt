package lotto

internal object ResultCalculator {

    fun evaluate(tickets: List<Lotto>, winning: List<Int>, bonus: Int): Map<Rank, Int> {
        val winSet = winning.toSet()
        val counts = mutableMapOf<Rank, Int>()
        tickets.forEach { accumulate(counts, it, winSet, bonus) }
        return ordered(counts)
    }

    private fun accumulate(
        counts: MutableMap<Rank, Int>,
        lotto: Lotto,
        winSet: Set<Int>,
        bonus: Int
    ) {
        val values = lotto.values()
        val match = values.count { it in winSet }
        val bonusMatched = bonus in values
        Rank.of(match, bonusMatched)?.let { rank ->
            counts[rank] = (counts[rank] ?: 0) + 1
        }
    }

    private fun ordered(counts: Map<Rank, Int>): Map<Rank, Int> = linkedMapOf(
        Rank.FIFTH  to (counts[Rank.FIFTH]  ?: 0),
        Rank.FOURTH to (counts[Rank.FOURTH] ?: 0),
        Rank.THIRD  to (counts[Rank.THIRD]  ?: 0),
        Rank.SECOND to (counts[Rank.SECOND] ?: 0),
        Rank.FIRST  to (counts[Rank.FIRST]  ?: 0),
    )

    fun calculateEarningsRate(result: Map<Rank, Int>, purchaseAmount: Int): Double {
        if (purchaseAmount == 0) return 0.0
        val totalPrize = result.entries.sumOf { (rank, cnt) -> rank.prize * cnt }
        val rate = totalPrize.toDouble() / purchaseAmount * 100
        return (rate * 10).toInt() / 10.0  // 둘째 자리 반올림 → 한 자리 표시
    }

    fun formatPrize(n: Long): String = "%,d원".format(n)
}