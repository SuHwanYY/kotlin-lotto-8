package lotto

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "[ERROR] 로또 번호는 6개여야 합니다." }
    }

    // 외부에서 읽을 수 있도록 방어적 복사로 반환
    fun values(): List<Int> = numbers.toList()
}
