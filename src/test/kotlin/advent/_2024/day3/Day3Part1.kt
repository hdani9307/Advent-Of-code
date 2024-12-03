package advent._2024.day3

import advent.helper.readInput
import advent.helper.runMeasured

fun main() {
    val lines = readInput("2024/3.txt")
    runMeasured {
        var sum = 0
        val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
        val matches = regex.findAll(lines[0])
        for (match in matches) {
            val stripped = match.value
                .replace(Regex("mul\\("), "")
                .replace(")", "")
            val split = stripped.split(",")
            sum += split[0].trim().toInt() * split[1].trim().toInt()
        }

        println(sum)
    }
}