package lotto

import camp.nextstep.edu.missionutils.Randoms

internal object LottoGenerator {

    fun createTickets(count: Int): List<Lotto> =
        (1..count).map {
            val nums = Randoms.pickUniqueNumbersInRange(1, 45, 6).sorted()
            Lotto(nums)
        }
}