import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.test.Ignore

@Ignore
fun main() {

    val lines = readInput("2023/4.txt")

    runMeasured {
        val cards = mutableMapOf<Int, Int>()
        for (line in lines.withIndex()) {
            cards[line.index + 1] = 1
        }
        for (card in cards) {
            val numbers = lines[card.key - 1]
            val split = numbers.replace(Regex("Card\\s+\\d+: "), "").split(" | ")
            val winning = split[0].split(" ").toMutableList()
            val own = split[1].split(" ").toMutableList()
            winning.retainAll(own)
            winning.removeIf { it.isEmpty() }

            for (j in 1..winning.size) {
                val cardKey = card.key + j
                cards[cardKey] = cards[cardKey]!! + 1 * card.value
            }

        }
        println(cards.values.reduce { acc, i -> acc + i })
    }
}