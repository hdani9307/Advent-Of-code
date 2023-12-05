package advent._2023

import advent.Day

class Day5Part1 : Day("5") {

    override fun run() {
        val seeds = lines[0].replace("seeds: ".toRegex(), "")
            .split(" ")
            .map { it.toLong() }
        val seedToSoil = mutableMapOf<Long, Long?>()
        val soilToFertilizer = mutableMapOf<Long, Long?>()
        val fertilizerToWater = mutableMapOf<Long, Long?>()
        val waterToLight = mutableMapOf<Long, Long?>()
        val lightToTemperature = mutableMapOf<Long, Long?>()
        val temperatureToHumidity = mutableMapOf<Long, Long?>()
        val humidityToLocation = mutableMapOf<Long, Long?>()

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
        var currentMap: MutableMap<Long, Long?>? = null;
        for (line in lines) {
            if (line.startsWith("seeds")) {
                continue
            }
            if (line.isNotBlank()) {
                if (line.matches("^[0-9].*".toRegex())) {
                    val split = line.split(" ").map { it.toLong() }
                    val destinationRangeStart = split[0]
                    val sourceRangeStart = split[1]
                    val rangeLength = split[2]
                    for (i in 0 until rangeLength) {
                        currentMap?.set(sourceRangeStart + i, destinationRangeStart + i)
                    }
                } else {
                    if (iterator.hasNext()) {
                        currentMap = iterator.next()
                    }
                }
            }
        }
        var minLocation = Long.MAX_VALUE
        for (seed in seeds) {
            val location = asd(seed, mapList.listIterator())
            minLocation = minLocation.coerceAtMost(location);
        }
        println(minLocation)
    }

    private fun asd(source: Long, searchMapIterator: Iterator<MutableMap<Long, Long?>>): Long {
        if (!searchMapIterator.hasNext()) {
            // TODO
            return source;
        }
        val currentSearchMap = searchMapIterator.next()
        if (!currentSearchMap.containsKey(source)) {
            currentSearchMap[source] = source
        }
        return asd(currentSearchMap[source]!!, searchMapIterator)
    }
}