package advent._2023.day4;

import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.test.Ignore

@Ignore
fun main() {

    val lines = readInput("2023/4.txt")

    runMeasured {
        var pointSum = 0
        for (line in lines) {
            val split = line.replace(Regex("Card\\s+\\d+: "), "").split(" | ")
            val winning = split[0].split(" ").toMutableList()
            val own = split[1].split(" ").toMutableList()
            winning.retainAll(own)

            winning.removeIf { it.isEmpty() }
            if (winning.isNotEmpty()) {
                var point = 1
                for (i in 0 until winning.size - 1) {
                    point *= 2
                }
                pointSum += point
            }
        }
        println(pointSum)
    }
}