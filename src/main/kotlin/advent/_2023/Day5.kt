package advent._2023

import advent.Day
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Objects
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong


class Day5Part1 : Day("5") {

    override fun run() {
        val seeds = lines[0].replace("seeds: ".toRegex(), "")
            .split(" ")
            .map { it.toLong() }
        val seedToSoil = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val soilToFertilizer = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val fertilizerToWater = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val waterToLight = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val lightToTemperature = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val temperatureToHumidity = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val humidityToLocation = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()

        val mapList = listOf(
            seedToSoil,
            soilToFertilizer,
            fertilizerToWater,
            waterToLight,
            lightToTemperature,
            temperatureToHumidity,
            humidityToLocation
        )
        val iterator = mapList.listIterator()
        var currentMap: MutableMap<Pair<Long, Long>, Pair<Long, Long>?>? = null;
        for (line in lines) {
            if (line.startsWith("seeds")) {
                continue
            }
            if (line.isNotBlank()) {
                if (line.matches("^[0-9].*".toRegex())) {
                    val split = line.split(" ").map { it.toLong() }
                    val destinationRangeStart = split[0]
                    val sourceRangeStart = split[1]
                    val rangeLength = split[2] - 1
                    currentMap?.set(
                        Pair(sourceRangeStart, sourceRangeStart + rangeLength),
                        Pair(destinationRangeStart, destinationRangeStart + rangeLength)
                    )
                } else {
                    if (iterator.hasNext()) {
                        currentMap = iterator.next()
                    }
                }
            }
        }
        var minLocation = Long.MAX_VALUE
        for (seed in seeds) {
            val location = searchHeight(seed, mapList.listIterator())
            minLocation = minLocation.coerceAtMost(location);
        }
        // 662197086
        println(minLocation)
    }

    private fun searchHeight(
        source: Long,
        searchMapIterator: ListIterator<MutableMap<Pair<Long, Long>, Pair<Long, Long>?>>
    ): Long {
        if (!searchMapIterator.hasNext()) {
            // TODO
            return source;
        }
        val currentSearchMap = searchMapIterator.next()

        // containsKey
        var startRange: Pair<Long, Long>? = null;
        var destinationRange: Pair<Long, Long>? = null;

        for (entry in currentSearchMap.entries) {
            if (entry.key.first <= source && source <= entry.key.second) {
                startRange = entry.key
                destinationRange = entry.value
                break
            }
        }
        if (startRange == null) {
            startRange = Pair(source, source)
            destinationRange = Pair(source, source)
        }
        val diff = source - startRange.first
        val newSource = destinationRange!!.first + diff
        return searchHeight(newSource, searchMapIterator)
    }
}

class Day5Part2 : Day("5") {

    val lock = Object()

    override fun run() {
        val tmp = lines[0].replace("seeds: ".toRegex(), "")
            .split(" ")
            .map { it.toLong() }

        val seeds = mutableListOf<Pair<Long, Long>>()
        for (i in 1..tmp.size step 2) {
            seeds.add(Pair(tmp[i - 1], tmp[i - 1] + tmp[i]))
        }
        val seedToSoil = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val soilToFertilizer = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val fertilizerToWater = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val waterToLight = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val lightToTemperature = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val temperatureToHumidity = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()
        val humidityToLocation = mutableMapOf<Pair<Long, Long>, Pair<Long, Long>?>()

        val mapList = listOf(
            seedToSoil,
            soilToFertilizer,
            fertilizerToWater,
            waterToLight,
            lightToTemperature,
            temperatureToHumidity,
            humidityToLocation
        )
        val iterator = mapList.listIterator()
        var currentMap: MutableMap<Pair<Long, Long>, Pair<Long, Long>?>? = null;
        for (line in lines) {
            if (line.startsWith("seeds")) {
                continue
            }
            if (line.isNotBlank()) {
                if (line.matches("^[0-9].*".toRegex())) {
                    val split = line.split(" ").map { it.toLong() }
                    val destinationRangeStart = split[0]
                    val sourceRangeStart = split[1]
                    val rangeLength = split[2] - 1
                    currentMap?.set(
                        Pair(sourceRangeStart, sourceRangeStart + rangeLength),
                        Pair(destinationRangeStart, destinationRangeStart + rangeLength)
                    )
                } else {
                    if (iterator.hasNext()) {
                        currentMap = iterator.next()
                    }
                }
            }
        }
        var minLocation = Long.MAX_VALUE
        for ((index, seed) in seeds.withIndex()) {
            println("Processing seed $index")
            for (i in seed.first until seed.second) {
                val location = searchHeight(i, mapList.listIterator())
                minLocation = minLocation.coerceAtMost(location);
            }
            println("$index finished, currentMin: $minLocation")
        }

        //52510810 - high
        //52510809 - ok
        println(minLocation)

    }

    private fun searchHeight(
        source: Long,
        searchMapIterator: ListIterator<MutableMap<Pair<Long, Long>, Pair<Long, Long>?>>
    ): Long {
        if (!searchMapIterator.hasNext()) {
            // TODO
            return source;
        }
        val currentSearchMap = searchMapIterator.next()

        // containsKey
        var startRange: Pair<Long, Long>? = null;
        var destinationRange: Pair<Long, Long>? = null;

        for (entry in currentSearchMap.entries) {
            if (entry.key.first <= source && source <= entry.key.second) {
                startRange = entry.key
                destinationRange = entry.value
                break
            }
        }
        if (startRange == null) {
            startRange = Pair(source, source)
            destinationRange = Pair(source, source)
        }
        val diff = source - startRange.first
        val newSource = destinationRange!!.first + diff
        return searchHeight(newSource, searchMapIterator)
    }
}