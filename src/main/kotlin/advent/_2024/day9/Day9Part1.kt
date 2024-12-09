package advent._2024.day9

import advent.helper.readInput
import advent.helper.runMeasured

class Day9Part1 {
    fun run(): Long {
        var sum = 0L
        val lines = readInput("2024/9.txt")
        val expanded = mutableListOf<String>()
        runMeasured {
            var index = 0
            for ((i, c) in lines.first().toList().withIndex()) {
                for (j in 0 until c.toString().toInt()) {
                    if (i % 2 == 0) {
                        expanded.add(index.toString())
                    } else {
                        expanded.add(".")
                    }
                }
                if (i % 2 == 0) {
                    index++
                }
            }

            val compressed = mutableListOf<String>()
            var backIndex = expanded.size

            for (withIndex in expanded.withIndex()) {
                if (withIndex.index >= backIndex) {
                    break
                }
                if (withIndex.value == ".") {
                    var next = expanded[--backIndex]
                    while (next == ".") {
                        next = expanded[--backIndex]
                    }
                    compressed.add(next)
                } else {
                    compressed.add(withIndex.value)
                }
            }

            for (withIndex in compressed.withIndex()) {
                sum += withIndex.value.toInt() * withIndex.index
            }
        }

        return sum
    }
}