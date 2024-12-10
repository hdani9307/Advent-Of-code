package advent._2023.day3

import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.test.Ignore

data class Gear(val value: String)

@Ignore
fun main() {

    val numbers = mutableMapOf<Pair<Int, Int>, Gear>()

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
                    val number = Gear(currentDigit)
                    for (coordinate in coordinates) {
                        numbers[coordinate] = number
                    }
                    coordinates.clear()
                    currentDigit = ""
                }
                matrix[i.index].add(j.value)
            }
        }
        var sum = 0
        for (i in matrix.withIndex()) {
            for (j in i.value.withIndex()) {
                if (j.value == '*') {
                    val gears = mutableSetOf<Gear>()
                    // Left and right
                    numbers[Pair(i.index, j.index - 1)]?.let { gears.add(it) }
                    numbers[Pair(i.index, j.index + 1)]?.let { gears.add(it) }

                    // Top left and right
                    numbers[Pair(i.index - 1, j.index - 1)]?.let { gears.add(it) }
                    numbers[Pair(i.index - 1, j.index + 1)]?.let { gears.add(it) }

                    // Bottom left and right
                    numbers[Pair(i.index + 1, j.index - 1)]?.let { gears.add(it) }
                    numbers[Pair(i.index + 1, j.index + 1)]?.let { gears.add(it) }

                    // Top and bottom
                    numbers[Pair(i.index - 1, j.index)]?.let { gears.add(it) }
                    numbers[Pair(i.index + 1, j.index)]?.let { gears.add(it) }

                    if (gears.size == 2) {
                        val iterator = gears.iterator()
                        sum += iterator.next().value.toInt() * iterator.next().value.toInt()
                    }
                }
            }
        }
        //84266818
        println(sum)
    }
}