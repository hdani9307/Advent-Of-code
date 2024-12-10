package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured

class Day3Part1 {

    fun run(): Long {
        val lines = readInput("2024/3.txt")
        var sum = 0L
        runMeasured {
            val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
            val matches = regex.findAll(lines[0])
            for (match in matches) {
                val stripped = match.value
                    .replace(Regex("mul\\("), "")
                    .replace(")", "")
                val split = stripped.split(",")
                sum += split[0].trim().toInt() * split[1].trim().toInt()
            }
        }
        return sum
    }
}

class Day3Part2 {

    fun run(): Long {
        val lines = readInput("2024/3.txt")
        var sum = 0L
        runMeasured {

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
        }
        return sum
    }
}