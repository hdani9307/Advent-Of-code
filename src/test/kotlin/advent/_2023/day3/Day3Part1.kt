package advent._2023.day3

import advent.helper.readInput
import advent.helper.runMeasured

data class Number(val value: String, var abjected: Boolean = false, var touched: Boolean = false)

fun main() {

    val numbers = mutableMapOf<Pair<Int, Int>, Number>()

    val matrix = mutableListOf<MutableList<Char>>()
    val lines = readInput("3.txt")
    runMeasured {
        var currentDigit = ""
        val coordinates = mutableListOf<Pair<Int, Int>>()
        for (i in lines.withIndex()) {
            matrix.add(i.index, mutableListOf())
            for (j in i.value.toList().withIndex()) {
                if (j.value.isDigit()) {
                    coordinates.add(Pair(i.index, j.index))
                    currentDigit += j.value
                } else {
                    val number = Number(currentDigit)
                    for (coordinate in coordinates) {
                        numbers[coordinate] = number
                    }
                    coordinates.clear()
                    currentDigit = ""
                }
                matrix[i.index].add(j.value)
            }
        }
        for (i in matrix.withIndex()) {
            for (j in i.value.withIndex()) {
                if (!j.value.isDigit() && j.value != '.') {
                    // Left and right
                    numbers[Pair(i.index, j.index - 1)]?.let { it.abjected = true }
                    numbers[Pair(i.index, j.index + 1)]?.let { it.abjected = true }

                    // Top left and right
                    numbers[Pair(i.index - 1, j.index - 1)]?.let { it.abjected = true }
                    numbers[Pair(i.index - 1, j.index + 1)]?.let { it.abjected = true }

                    // Bottom left and right
                    numbers[Pair(i.index + 1, j.index - 1)]?.let { it.abjected = true }
                    numbers[Pair(i.index + 1, j.index + 1)]?.let { it.abjected = true }

                    // Top and bottom
                    numbers[Pair(i.index - 1, j.index)]?.let { it.abjected = true }
                    numbers[Pair(i.index + 1, j.index)]?.let { it.abjected = true }
                }
            }
        }
        var sum = 0
        for (number in numbers) {
            if (number.value.touched) {
                continue
            }
            if (number.value.abjected) {
                sum += number.value.value.toInt()
                number.value.touched = true
            }
        }
        println(sum)
    }
}