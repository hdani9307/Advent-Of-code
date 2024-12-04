package advent._2024.day4

import advent.helper.readInput
import advent.helper.runMeasured
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class Day4Part1 {

    val regexes = listOf(
        Regex("XMAS"),
        Regex("SAMX")
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

            // Normal matrix
            for (chars in matrix) {
                sum += findXmas(chars)
            }
            println("---")

            // Transposed
            for (chars in matrix.transpose()) {
                sum += findXmas(chars)
            }
            println("---")


            // Transposed by 45 degree
            for (entry in transpose45Degree(matrix)) {
                sum += findXmas(entry.value)
            }
            println("---")

            // Transposed by -45 degree
            for (entry in transposeMinus45Degree(matrix)) {
                sum += findXmas(entry.value)
            }
            println("---")

            println(sum)
            Assertions.assertEquals(2575, sum)
        }
    }

    private fun findXmas(charList: List<Char>): Int {
        val line = charList.map { it.toString() }.reduce { acc, c -> acc + c }
        val matches = regexes.map { it.findAll(line) }
        return matches.map { it.count() }
            .reduce { acc, i -> acc + i }
            .also { println(it) }
    }

    private fun transpose45Degree(matrix: List<List<Char>>): Map<Int, List<Char>> {
        val transposed = mutableMapOf<Int, MutableList<Char>>()
        for ((i, chars) in matrix.withIndex()) {
            for ((j, c) in chars.withIndex()) {
                transposed.putIfAbsent(i + j, mutableListOf())
                transposed[i + j]!!.add(c)
            }
        }
        return transposed
    }

    private fun transposeMinus45Degree(matrix: List<List<Char>>): Map<Int, List<Char>> {
        val transposed = mutableMapOf<Int, MutableList<Char>>()
        for ((i, chars) in matrix.withIndex()) {
            for ((j, c) in chars.withIndex()) {
                transposed.putIfAbsent(i - j, mutableListOf())
                transposed[i - j]!!.add(c)
            }
        }
        return transposed
    }

    fun <T> List<List<T>>.transpose(): List<List<T>> {
        return (this[0].indices).map { i -> (this.indices).map { j -> this[j][i] } }
    }
}
