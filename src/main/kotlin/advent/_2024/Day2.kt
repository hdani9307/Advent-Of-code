package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.math.absoluteValue

class Day2Part1 {

    fun run(): Long {
        val lines = readInput("2024/2.txt")
        var safeSum = 0L
        runMeasured {
            for (line in lines) {
                var rootDecreasing = false
                val levels = line.split(" ")

                var isSafe = false
                for (i in 0..<levels.size - 1) {
                    val currentLevel = levels[i].toInt()
                    val nextLevel = levels[i + 1].toInt()
                    val difference = currentLevel - nextLevel
                    if (i == 0) {
                        rootDecreasing = nextLevel < currentLevel
                    }
                    val decreasing = nextLevel < currentLevel
                    if (rootDecreasing == decreasing && (difference.absoluteValue in 1..3)) {
                        isSafe = true
                    } else {
                        isSafe = false
                        break
                    }
                }
                if (isSafe) {
                    safeSum++
                }
            }
        }
        return safeSum
    }
}

class Day2Part2 {

    fun run(): Long {
        val lines = readInput("2024/2.txt")
        var safeSum = 0L

        runMeasured {
            for (line in lines) {
                val levels = line.split(" ")
                var safe = isSafe(levels)

                if (!safe) {
                    for ((index, _) in levels.withIndex()) {
                        val subList = levels.toMutableList()
                        subList.removeAt(index)
                        safe = isSafe(subList)
                        if (safe) {
                            break
                        }
                    }
                }

                if (safe) {
                    safeSum++
                }
            }
        }
        return safeSum
    }

    private fun isSafe(levels: List<String>): Boolean {
        val rootDecreasing = levels[1].toInt() < levels[0].toInt()
        for (i in 0..<levels.size - 1) {
            val currentLevel = levels[i].toInt()
            val nextLevel = levels[i + 1].toInt()
            val difference = currentLevel - nextLevel

            val decreasing = nextLevel < currentLevel
            if (rootDecreasing != decreasing) {
                return false
            }
            if (difference.absoluteValue !in 1..3) {
                return false
            }
        }
        return true
    }
}