package advent._2022

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day9 {
    fun run() {
        val rope = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until 10) {
            rope.add(Pair(0, 0))
        }

        val visited = mutableSetOf(rope[0])

        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent//resources/day9.txt")).use {
            try {
                var line = it.readLine()
                do {
                    val split = line.split(" ");
                    val direction = split[0]
                    val steps = split[1].toInt()
                    for (step in 0 until steps) {
                        var head = rope[0]
                        if (direction == "R") {
                            head = Pair(head.first, head.second + 1)
                        }
                        if (direction == "L") {
                            head = Pair(head.first, head.second - 1)
                        }
                        if (direction == "U") {
                            head = Pair(head.first - 1, head.second)
                        }
                        if (direction == "D") {
                            head = Pair(head.first + 1, head.second)
                        }
                        rope[0] = head

                        for (knot in rope.subList(0, rope.size - 1).withIndex()) {
                            val diff = Pair(
                                rope[knot.index].first - rope[knot.index + 1].first,
                                rope[knot.index].second - rope[knot.index + 1].second,
                            );

                            if (diff.first.absoluteValue > 1 || diff.second.absoluteValue > 1) {
                                val pair = Pair(
                                    rope[knot.index + 1].first + diff.first.sign,
                                    rope[knot.index + 1].second + diff.second.sign
                                )
                                rope[knot.index + 1] = pair
                                if (knot.index + 1 == 9) {
                                    visited.add(rope[knot.index + 1])
                                }
                            }
                        }
                    }

                    line = it.readLine()
                } while (line != null)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        println(visited.size)
    }
}