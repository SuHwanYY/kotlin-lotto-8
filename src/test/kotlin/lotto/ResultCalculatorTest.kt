package lotto

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ResultCalculatorTest {

    @Test
    fun `당첨 통계를 올바르게 계산한다`() {
        val tickets = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)), // 6개 일치 -> 1등
            Lotto(listOf(1, 2, 3, 4, 5, 7)), // 5개+보너스 -> 2등 (보너스=7일 때)
            Lotto(listOf(1, 2, 3, 4, 5, 8)), // 5개 -> 3등
            Lotto(listOf(1, 2, 3, 4, 10, 11)), // 4개 -> 4등
            Lotto(listOf(1, 2, 3, 20, 21, 22)), // 3개 -> 5등
            Lotto(listOf(40, 41, 42, 43, 44, 45)) // 0개 -> 미당첨
        )
        val winning = listOf(1, 2, 3, 4, 5, 6)
        val bonus = 7

        val stat = ResultCalculator.evaluate(tickets, winning, bonus)

        assertEquals(1, stat[Rank.FIRST])
        assertEquals(1, stat[Rank.SECOND])
        assertEquals(1, stat[Rank.THIRD])
        assertEquals(1, stat[Rank.FOURTH])
        assertEquals(1, stat[Rank.FIFTH])

        val winners = stat.values.sum()
        val losers = tickets.size - winners
        assertEquals(1, losers) // 미당첨 1장
    }

    @Test
    fun `수익률을 퍼센트 숫자로 계산한다`() {
        // 키 타입은 Rank, 값은 Int
        val stat = mutableMapOf<Rank, Int>().apply {
            this[Rank.FIRST] = 1
            this[Rank.SECOND] = 0
            this[Rank.THIRD] = 0
            this[Rank.FOURTH] = 0
            this[Rank.FIFTH] = 0
        }
        val rate = ResultCalculator.calculateEarningsRate(stat, purchaseAmount = 8000)
        assertEquals(25_000_000.0, rate)
    }
}