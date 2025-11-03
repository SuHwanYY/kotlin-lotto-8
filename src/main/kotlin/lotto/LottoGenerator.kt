package lotto

import camp.nextstep.edu.missionutils.Randoms

internal object LottoGenerator {

    fun createTickets(count: Int): List<Lotto> {
        return List(count) { createOneTicket() }
    }

    private fun createOneTicket(): Lotto {
        val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
            .sorted()  // 오름차순 정렬
        return Lotto(numbers)
    }
}