package advent._2023

import advent.Day

class Day6Part1 : Day("6") {
    override fun run() {
        val times = lines[0].replace("Time:".toRegex(), "").trimStart()
            .split("  ")
            .filter { it.isNotBlank() }
            .map {
                it.trimStart().trim().toInt()
            }
        val distances = lines[1].replace("Distance:".toRegex(), "").trimStart()
            .split("  ")
            .filter { it.isNotBlank() }
            .map {
                it.trimStart().trim().toInt()
            }
        var result = 1
        for ((index, time) in times.withIndex()) {
            var counter = 0
            for (speed in 0..time) {
                val remainingTravelTime = time - speed
                val distance = speed * remainingTravelTime
                if (distance > distances[index]) {
                    counter++
                }
            }
            result *= counter;
        }
        // 588588 - ok
        println(result)
    }
}

class Day6Part2 : Day("6") {
    override fun run() {
        val time = lines[0].replace("[^0-9]".toRegex(), "")
            .toLong()
        val distance = lines[1].replace("[^0-9]".toRegex(), "")
            .toLong()
        var counter = 0
        for (speed in 0..time) {
            val remainingTravelTime = time - speed
            val d = speed * remainingTravelTime
            if (d > distance) {
                counter++
            }
        }

        // 34655848 - ok
        println(counter)
    }
}