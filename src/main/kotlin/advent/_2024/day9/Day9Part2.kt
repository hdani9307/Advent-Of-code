package advent._2024.day9

import advent.helper.readInput
import advent.helper.runMeasured

class Day9Part2 {
    fun run(): Long {
        var sum = 0L
        val lines = readInput("2024/sample.txt")
        val clusters = mutableListOf<List<String>>()
        runMeasured {
            var index = 0
            for ((i, c) in lines.first().toList().withIndex()) {
                val l = mutableListOf<String>()
                for (j in 0 until c.toString().toInt()) {
                    if (i % 2 == 0) {
                        l.add(index.toString())
                    } else {
                        l.add(".")
                    }
                }
                if (l.isNotEmpty()) {
                    clusters.add(l)
                }
                if (i % 2 == 0) {
                    index++
                }
            }
            println(clusters)

            val compressed = mutableListOf<String>()
            var backIndex = clusters.size

//            for (withIndex in clusters.withIndex()) {
//                if (withIndex.index >= backIndex) {
//                    break
//                }
//                if (withIndex.value == ".") {
//                    var next = clusters[--backIndex]
//                    while (next == ".") {
//                        next = clusters[--backIndex]
//                    }
//                    compressed.add(next)
//                } else {
//                    compressed.add(withIndex.value)
//                }
//            }

            for (withIndex in compressed.withIndex()) {
                if (withIndex.value == ".") {
                    continue
                }
                sum += withIndex.value.toInt() * withIndex.index
            }
        }

        return sum
    }
}