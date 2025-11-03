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

    // 3) 개수 출력 (다음 단계에서 실제 발행 붙일 예정)
    val count = PurchaseValidator.toCount(amount)
    println("${count}개를 구매했습니다.")
}
