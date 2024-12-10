package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured
import advent.helper.transpose

class Day4Part1 {

    val regexes = listOf(
        Regex("XMAS"),
        Regex("SAMX")
    )

    fun run(): Long {
        val lines = readInput("2024/4.txt")
        val matrix = mutableListOf<MutableList<Char>>()
        var sum = 0L
        runMeasured {

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
        }
        return sum
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
}

class Day4Part2 {
    val regexes = listOf(
        Regex("MAS"),
        Regex("SAM")
    )

    fun run(): Long {
        val lines = readInput("2024/4.txt")
        val matrix = mutableListOf<MutableList<Char>>()
        var sum = 0L
        runMeasured {

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
        }
        return sum
    }
}