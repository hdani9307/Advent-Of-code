package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured
import java.util.*

class Day11Part1 {

    fun solve(fileName: String, blinks: Int): Int {
        val stones = readInput(fileName)[0].split(" ")
            .map { it.toLong() }
            .toMutableList()

        runMeasured {
            for (blink in 0 until blinks) {
                runMeasured {
                    println("Blink $blink")

                    val stoneQueue = LinkedList<Long>()
                    stoneQueue.addAll(stones)

                    var index = 0
                    while (stoneQueue.peek() != null) {
                        val stone = stoneQueue.pop()
                        if (stone == 0L) {
                            stones[index] = 1
                        } else if (stone.toString().length % 2 == 0) {
                            val valueString = stone.toString()
                            val stone1 = valueString.substring(0, valueString.length / 2)
                            val stone2 =
                                valueString.substring(valueString.length / 2, valueString.length)

                            stones[index] = stone1.toLong()
                            stones.add(++index, stone2.toLong())
                        } else {
                            stones[index] = stone * 2024
                        }
                        index++
                    }
                }
            }
        }
        return stones.size
    }
}

class Day11Part2 {
    fun solve(fileName: String, blinks: Int): Int {
        val stones = readInput(fileName)[0].split(" ")
            .map { it.toLong() }
            .toMutableList()

        runMeasured {
            for (blink in 0 until blinks) {
                runMeasured {
                    println("Blink $blink")

                    val stoneQueue = LinkedList<Long>()
                    stoneQueue.addAll(stones)

                    var index = 0
                    while (stoneQueue.peek() != null) {
                        val stone = stoneQueue.pop()
                        if (stone == 0L) {
                            stones[index] = 1
                        } else if (stone.toString().length % 2 == 0) {
                            val valueString = stone.toString()
                            val stone1 = valueString.substring(0, valueString.length / 2)
                            val stone2 =
                                valueString.substring(valueString.length / 2, valueString.length)

                            stones[index] = stone1.toLong()
                            stones.add(++index, stone2.toLong())
                        } else {
                            stones[index] = stone * 2024
                        }
                        index++
                    }
                }
            }
        }
        return stones.size
    }

}