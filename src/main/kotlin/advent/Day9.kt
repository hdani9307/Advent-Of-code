package advent

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import kotlin.math.abs

class Day9 {
    fun run() {

        var head = Pair(0, 0)
        var tail = Pair(0, 0)
        var previousHead: Pair<Int, Int>

        val visited = mutableSetOf(tail)

        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent/src/main/resources/day9.txt")).use {
            try {
                var line = it.readLine()
                do {
                    val split = line.split(" ");
                    val direction = split[0]
                    val steps = split[1].toInt()

                    if (direction == "R") {
                        for (step in 0 until steps) {

                            previousHead = head
                            head = Pair(head.first, head.second + 1)

                            if (abs(head.first - tail.first) >= 2) {
                                tail = previousHead
                                visited.add(tail)
                            }
                            if (abs(head.second - tail.second) >= 2) {
                                tail = previousHead
                                visited.add(tail)
                            }
                        }
                    }
                    if (direction == "L") {
                        for (step in 0 until steps) {
                            previousHead = head
                            head = Pair(head.first, head.second - 1)

                            if (abs(head.first - tail.first) >= 2) {
                                tail = previousHead
                                visited.add(tail)
                            }
                            if (abs(head.second - tail.second) >= 2) {
                                tail = previousHead
                                visited.add(tail)
                            }
                        }
                    }
                    if (direction == "U") {
                        for (step in 0 until steps) {
                            previousHead = head
                            head = Pair(head.first - 1, head.second)

                            if (abs(head.first - tail.first) >= 2) {
                                tail = previousHead
                                visited.add(tail)
                            }
                            if (abs(head.second - tail.second) >= 2) {
                                tail = previousHead
                                visited.add(tail)
                            }

                        }
                    }
                    if (direction == "D") {
                        for (step in 0 until steps) {
                            previousHead = head
                            head = Pair(head.first + 1, head.second)

                            if (abs(head.first - tail.first) >= 2) {
                                tail = previousHead
                                visited.add(tail)
                            }
                            if (abs(head.second - tail.second) >= 2) {
                                tail = previousHead
                                visited.add(tail)
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