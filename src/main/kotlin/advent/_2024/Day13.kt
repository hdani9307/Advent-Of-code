package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured

class Day13Part1 {

    val pattern = "[a-zA-Z:=+\\s]"

    fun solve(fileName: String): Int {
        val input = readInput(fileName)

        var sum = 0
        runMeasured {
            var machine = 0
            for (i in 3..input.size step 4) {
                machine++
                val (aX, aY) = input[i - 3].parseToIntList()
                val (bX, bY) = input[i - 2].parseToIntList()
                val (priceX, priceY) = input[i - 1].parseToIntList()

                var lowestCost = Integer.MAX_VALUE
                for (a in IntRange(0, 100)) {
                    for (b in IntRange(0, 100)) {
                        if (a * aX + b * bX == priceX && a * aY + b * bY == priceY) {
                            lowestCost = lowestCost.coerceAtMost(a * 3 + b)
                        }
                    }
                }
                if (lowestCost == Integer.MAX_VALUE) {
                    println("No solution for $machine")
                } else {
                    println("Lowest cost: $lowestCost")
                    sum += lowestCost
                }
            }
        }
        return sum
    }

    private fun String.parseToIntList(): List<Int> {
        return this.replace(Regex(pattern), "").split(",").map { it.toInt() }
    }
}


class Day13Part2 {
    private val pattern = "[a-zA-Z:=+\\s]"

    private val correction = 10_000_000_000_000L

    fun solve(fileName: String): Long {
        val input = readInput(fileName)

        var sum = 0L
        runMeasured {
            var machine = 0
            for (i in 3..input.size step 4) {
                machine++
                val (aX, aY) = input[i - 3].parseToLongList()
                val (bX, bY) = input[i - 2].parseToLongList()
                val (priceX, priceY) = input[i - 1].parseToLongList().map { it + correction }

                val determinant = aX * bY - aY * bX

                if (determinant == 0.0) {
                    println("No solution for $machine")
                } else {
                    val x = (priceX * bY - priceY * bX) / determinant
                    val y = (aX * priceY - aY * priceX) / determinant
                    if (x.isWholeNumber() && y.isWholeNumber()) {
                        sum += x.toLong() * 3 + y.toLong()
                    } else {
                        println("No solution for $machine")
                    }
                }
            }
        }
        return sum
    }

    private fun Double.isWholeNumber(): Boolean {
        return this % 1 == 0.0
    }

    private fun String.parseToLongList(): List<Double> {
        return this.replace(Regex(pattern), "").split(",").map { it.toDouble() }
    }
}
