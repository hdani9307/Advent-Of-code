package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.math.absoluteValue

class Day1Part1 {

    fun run(): Long {

        val lines = readInput("2024/1.txt")

        var sum = 0L
        runMeasured {
            val left = mutableListOf<Int>()
            val right = mutableListOf<Int>()
            for (line in lines) {
                val splits = line.split("   ")
                left.add(splits[0].trim().toInt())
                right.add(splits[1].trim().toInt())
            }
            left.sort()
            right.sort()

            for ((index, i) in left.withIndex()) {
                val abs = (i - right[index]).absoluteValue
                sum += abs
            }
        }
        return sum
    }
}

class Day1Part2 {

    fun run(): Long {

        val leftMap = mutableMapOf<Int, Int>()
        val rightMap = mutableMapOf<Int, Int>()
        val lines = readInput("2024/1.txt")

        var sum = 0L
        runMeasured {
            for (line in lines) {
                val splits = line.split("   ")
                val left = splits[0].trim().toInt()
                val right = splits[1].trim().toInt()

                leftMap.putIfAbsent(left, 0)
                rightMap.putIfAbsent(right, 0)

                leftMap[left] = leftMap[left]!! + 1
                rightMap[right] = rightMap[right]!! + 1
            }
            for (e in leftMap) {
                if (rightMap.containsKey(e.key)) {
                    sum += (e.key * rightMap[e.key]!! * e.value)
                }
            }
        }
        return sum
    }
}
