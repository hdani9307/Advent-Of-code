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
        return stones.size
    }
}

class Day11Part2 {

    fun solve(fileName: String, blinks: Int): Long {
        val stoneMap = mutableMapOf<Long, Long>()

        readInput(fileName)[0].split(" ")
            .map { stoneMap.put(it.toLong(), 1) }
            .toMutableList()

        runMeasured {
            for (blink in 0 until blinks) {
                val nextStones = mutableMapOf<Long, Long>()

                val iter = stoneMap.iterator()
                while (iter.hasNext()) {
                    val e = iter.next()
                    if (e.key == 0L) {
                        nextStones.putIfAbsent(1, 0)
                        nextStones.computeIfPresent(1) { _, v -> v + e.value }
                    } else if (e.key.toString().length % 2 == 0) {
                        val valueString = e.key.toString()
                        val stone1 = valueString.substring(0, valueString.length / 2)
                        val stone2 =
                            valueString.substring(valueString.length / 2, valueString.length)

                        nextStones.putIfAbsent(stone1.toLong(), 0)
                        nextStones.computeIfPresent(stone1.toLong()) { _, v -> v + e.value }

                        nextStones.putIfAbsent(stone2.toLong(), 0)
                        nextStones.computeIfPresent(stone2.toLong()) { _, v -> v + e.value }
                    } else {
                        val result = e.key * 2024

                        nextStones.putIfAbsent(result, 0)
                        nextStones.computeIfPresent(result) { _, v -> v + e.value }
                    }
                }
                stoneMap.clear()
                stoneMap.putAll(nextStones)
            }
        }
        return stoneMap.values.reduce { acc, l -> acc + l }
    }

}