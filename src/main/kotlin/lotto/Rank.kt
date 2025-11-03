package lotto

enum class Rank(
    val matchCount: Int,
    val prize: Long,
    val needsBonus: Boolean
) {
    FIRST(6, 2_000_000_000L, false),
    SECOND(5,   30_000_000L,  true),
    THIRD(5,    1_500_000L,   false),
    FOURTH(4,      50_000L,   false),
    FIFTH(3,       5_000L,    false);

    companion object {
        fun of(match: Int, bonusMatched: Boolean): Rank? =
            when {
                match == 6 -> FIRST
                match == 5 && bonusMatched -> SECOND
                match == 5 -> THIRD
                match == 4 -> FOURTH
                match == 3 -> FIFTH
                else -> null
            }
    }
}
