package lotto

import camp.nextstep.edu.missionutils.Console

fun main() {
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

    // 로또 발행 및 출력
    val tickets = LottoGenerator.createTickets(count)
    tickets.forEach { println(it.values()) }

    println("\n당첨 번호를 입력해 주세요.")
    val winningNumbers = try {
        val rawWinning = Console.readLine()
        WinNumValidator.parseWinningNumbers(rawWinning)
    } catch (e: IllegalArgumentException) {
        println(e.message)
        throw e
    }

    // --- 보너스 번호 입력 ---
    println("\n보너스 번호를 입력해 주세요.")
    val bonusNumber = try {
        val rawBonus = Console.readLine()
        WinNumValidator.parseBonusNumber(rawBonus, winningNumbers)
    } catch (e: IllegalArgumentException) {
        println(e.message)
        throw e
    }

    println("\n입력 확인")
    println("당첨 번호: $winningNumbers")
    println("보너스 번호: $bonusNumber")

}
