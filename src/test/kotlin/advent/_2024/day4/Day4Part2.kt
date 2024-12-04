package advent._2024.day4

import advent.helper.readInput
import advent.helper.runMeasured
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class Day4Part2 {
    val regexes = listOf(
        Regex("MAS"),
        Regex("SAM")
    )

    @Test
    fun run() {
        val lines = readInput("2024/4.txt")
        val matrix = mutableListOf<MutableList<Char>>()
        runMeasured {
            var sum = 0

            // Read matrix
            for (line in lines) {
                val row = mutableListOf<Char>()
                for (c in line.toList()) {
                    row.add(c)
                }
                matrix.add(row)
            }
            for ((i, chars) in matrix.withIndex()) {
                for ((j, c) in chars.withIndex()) {
                    if (c == 'A') {
                        try {
                            val diagonal1 = "${matrix[i - 1][j + 1]}A${matrix[i + 1][j - 1]}"
                            val diagonal2 = "${matrix[i - 1][j - 1]}A${matrix[i + 1][j + 1]}"

                            val matches1 = regexes.map { it.findAll(diagonal1) }.map { it.count() }
                                .reduce { acc, k -> acc + k }
                            val matches2 = regexes.map { it.findAll(diagonal2) }.map { it.count() }
                                .reduce { acc, l -> acc + l }

                            if (matches1 == 1 && matches2 == 1) {
                                sum++
                            }
                        } catch (e: IndexOutOfBoundsException) {
                            // Ignore
                        }
                    }
                }
            }

            println(sum)
            Assertions.assertEquals(2041, sum)
        }
    }
}