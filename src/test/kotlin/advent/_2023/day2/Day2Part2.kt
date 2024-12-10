package advent._2023.day2

import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.test.Ignore

@Ignore
fun main() {

    var sum = 0
    val lines = readInput("2.txt")
    runMeasured {
        for (line in lines) {
            val minimums = mutableMapOf<String, Int>()
            val game = line.split(Regex(": "))
            val cubes = game[1].split(Regex("; "))
            for (cube in cubes) {
                val hand = cube.split(Regex(", "))
                for (draws in hand) {
                    val draw = draws.split(Regex(" "))

                    val count = draw[0].toInt()
                    val color = draw[1]
                    if (minimums.containsKey(color)) {
                        if (count < minimums[color]!!) {
                            continue
                        }
                    }
                    minimums[color] = count
                }
            }
            sum += minimums.values.reduce { acc, i -> acc * i }
        }
        println(sum)
    }
}