package advent._2023.day2

import advent.helper.readInput
import advent.helper.runMeasured

fun main() {
    val limits = mapOf(
        Pair("red", 12),
        Pair("green", 13),
        Pair("blue", 14)
    )

    var total = 0
    val lines = readInput("2.txt")
    runMeasured {
        for (line in lines) {
            val game = line.split(Regex(": "))
            val gameId = game[0].replace(Regex("Game "), "").toInt()
            val cubes = game[1].split(Regex("; "))
            var possible = true
            for (cube in cubes) {
                val hand = cube.split(Regex(", "))
                for (draws in hand) {
                    val draw = draws.split(Regex(" "))
                    if (draw[0].toInt() > limits[draw[1]]!!) {
                        possible = false
                        break;
                    }
                }
            }
            if (possible) {
                total += gameId
            }
        }
        println(total)
    }
}