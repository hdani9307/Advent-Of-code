package advent._2024.day3

import advent.helper.readInput
import advent.helper.runMeasured
import org.junit.jupiter.api.Test

class Day3Part2 {

    @Test
    fun main() {
        val lines = readInput("2024/3.txt")
        runMeasured {
            var sum = 0

            val dontDoIt = "don't()"
            val doIt = "do()"

            val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)")
            val matches = regex.findAll(lines[0])
            var enabled = true
            for (match in matches) {
                val value = match.value
                if (value == doIt) {
                    enabled = true
                    continue
                } else if (value == dontDoIt) {
                    enabled = false
                    continue
                }

                if (enabled) {

                    val stripped = match.value
                        .replace(Regex("mul\\("), "")
                        .replace(")", "")
                    val split = stripped.split(",")
                    sum += split[0].trim().toInt() * split[1].trim().toInt()
                }
            }

            println(sum)
        }
    }
}