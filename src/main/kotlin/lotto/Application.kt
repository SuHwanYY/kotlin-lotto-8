package lotto

import camp.nextstep.edu.missionutils.Console

fun main() {
    // 구입 금액 입력 (잘못 입력하면 [ERROR] 출력 후 금액부터 다시 받기)
    println("구입금액을 입력해 주세요.")
    val amount = readAmountWithRetry()

    // 로또 발행
    val count = PurchaseValidator.toCount(amount)
    println()
    println("${count}개를 구매했습니다.")
    val tickets = LottoGenerator.createTickets(count)
    tickets.forEach { println(it.values()) }

    // 당첨 번호 입력 (잘못 입력하면 [ERROR] 출력 후 당첨 번호부터 다시 받기)
    println("\n당첨 번호를 입력해 주세요.")
    val winningNumbers = readWinningNumbersWithRetry()

    // 보너스 번호 입력 (잘못 입력하면 [ERROR] 출력 후 보너스 번호부터 다시 받기)
    println("\n보너스 번호를 입력해 주세요.")
    val bonusNumber = readBonusNumberWithRetry(winningNumbers)

    // 당첨 통계/수익률 출력
    val result = ResultCalculator.evaluate(tickets, winningNumbers, bonusNumber)

    println("\n당첨 통계")
    println("---")
    println("3개 일치 (${ResultCalculator.formatPrize(Rank.FIFTH.prize)}) - ${result[Rank.FIFTH]}개")
    println("4개 일치 (${ResultCalculator.formatPrize(Rank.FOURTH.prize)}) - ${result[Rank.FOURTH]}개")
    println("5개 일치 (${ResultCalculator.formatPrize(Rank.THIRD.prize)}) - ${result[Rank.THIRD]}개")
    println("5개 일치, 보너스 볼 일치 (${ResultCalculator.formatPrize(Rank.SECOND.prize)}) - ${result[Rank.SECOND]}개")
    println("6개 일치 (${ResultCalculator.formatPrize(Rank.FIRST.prize)}) - ${result[Rank.FIRST]}개")

    val earningRate = ResultCalculator.calculateEarningsRate(result, amount)
    println("총 수익률은 ${earningRate}%입니다.")
}

// 금액 입력 재시도
private fun readAmountWithRetry(): Int {
    while (true) {
        try {
            val raw = Console.readLine()
            return PurchaseValidator.validate(raw)
        } catch (e: IllegalArgumentException) {
            println(ensureErrorPrefix(e.message))
            // 금액부터 다시 입력
        }
    }
}

// 당첨 번호 입력 재시도
private fun readWinningNumbersWithRetry(): List<Int> {
    while (true) {
        try {
            val raw = Console.readLine()
            return WinNumValidator.parseWinningNumbers(raw)
        } catch (e: IllegalArgumentException) {
            println(ensureErrorPrefix(e.message))
            // 당첨 번호부터 다시 입력
        }
    }
}

// 보너스 번호 입력 재시도
private fun readBonusNumberWithRetry(winningNumbers: List<Int>): Int {
    while (true) {
        try {
            val raw = Console.readLine()
            return WinNumValidator.parseBonusNumber(raw, winningNumbers)
        } catch (e: IllegalArgumentException) {
            println(ensureErrorPrefix(e.message))
            // 보너스 번호부터 다시 입력
        }
    }
}

// 메시지가 [ERROR]로 시작하도록 보정
private fun ensureErrorPrefix(msg: String?): String {
    val core = msg?.removePrefix("[ERROR]")?.trim().orEmpty()
    return if (core.isEmpty()) "[ERROR] 잘못된 입력입니다." else "[ERROR] $core"
}