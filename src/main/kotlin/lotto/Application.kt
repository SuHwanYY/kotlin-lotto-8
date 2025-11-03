package lotto

import camp.nextstep.edu.missionutils.Console

fun main() {
    // TODO: 프로그램 구현
    println("구입금액을 입력해 주세요.")

    // 검증
    val amount = try {
        val raw = Console.readLine()
        PurchaseValidator.validate(raw)
    } catch (e: IllegalArgumentException) {
        // [ERROR]가 출력되어야 하고, 예외가 발생해야 함
        println(e.message)
        throw e
    }

    val count = PurchaseValidator.toCount(amount)
    println()
    println("${count}개를 구매했습니다.")

    // 로또 발행 및 출력
    val tickets = LottoGenerator.createTickets(count)
    tickets.forEach { println(it.values()) }

    // 당첨 번호 입력
    println("\n당첨 번호를 입력해 주세요.")
    val winningNumbers = try {
        val rawWinning = Console.readLine()
        WinNumValidator.parseWinningNumbers(rawWinning)
    } catch (e: IllegalArgumentException) {
        println(e.message)
        throw e
    }

    // 보너스 번호 입력
    println("\n보너스 번호를 입력해 주세요.")
    val bonusNumber = try {
        val rawBonus = Console.readLine()
        WinNumValidator.parseBonusNumber(rawBonus, winningNumbers)
    } catch (e: IllegalArgumentException) {
        println(e.message)
        throw e
    }

    // 당첨 결과 출력
    val result = ResultCalculator.evaluate(tickets, winningNumbers, bonusNumber)

    println("\n당첨 통계")
    println("---")
    println("3개 일치 (${ResultCalculator.formatPrize(Rank.FIFTH.prize)}) - ${result[Rank.FIFTH]}개")
    println("4개 일치 (${ResultCalculator.formatPrize(Rank.FOURTH.prize)}) - ${result[Rank.FOURTH]}개")
    println("5개 일치 (${ResultCalculator.formatPrize(Rank.THIRD.prize)}) - ${result[Rank.THIRD]}개")
    println("5개 일치, 보너스 볼 일치 (${ResultCalculator.formatPrize(Rank.SECOND.prize)}) - ${result[Rank.SECOND]}개")
    println("6개 일치 (${ResultCalculator.formatPrize(Rank.FIRST.prize)}) - ${result[Rank.FIRST]}개")

    // 최종 수익률 계산 및 출력
    val earningRate = ResultCalculator.calculateEarningsRate(result, amount)
    println("총 수익률은 ${earningRate}%입니다.")
}
