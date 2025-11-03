package lotto

internal object ResultCalculator {

    fun evaluate(tickets: List<Lotto>, winning: List<Int>, bonus: Int): Map<Rank, Int> {
        val winningSet = winning.toSet()
        val counts = mutableMapOf<Rank, Int>()

        tickets.forEach { lotto ->
            val vals = lotto.values()
            val match = vals.count { it in winningSet }
            val bonusMatched = bonus in vals
            val rank = Rank.of(match, bonusMatched) ?: return@forEach
            counts[rank] = (counts[rank] ?: 0) + 1
        }

        // 출력 순서 고정: 3,4,5, 5+보너스, 6
        return linkedMapOf(
            Rank.FIFTH  to (counts[Rank.FIFTH]  ?: 0),
            Rank.FOURTH to (counts[Rank.FOURTH] ?: 0),
            Rank.THIRD  to (counts[Rank.THIRD]  ?: 0),
            Rank.SECOND to (counts[Rank.SECOND] ?: 0),
            Rank.FIRST  to (counts[Rank.FIRST]  ?: 0),
        )
    }

    fun formatPrize(n: Long): String = "%,d".format(n) + "원"

    fun calculateEarningsRate(result: Map<Rank, Int>, purchaseAmount: Int): Double {
        val totalPrize = result.entries.sumOf { (rank, count) ->
            rank.prize * count
        }

        if (purchaseAmount == 0) return 0.0

        val rate = totalPrize.toDouble() / purchaseAmount * 100
        return (rate * 10).toInt() / 10.0  // 소수점 둘째 자리에서 반올림
    }
}