package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured
import advent.helper.toMatrix
import java.util.concurrent.atomic.AtomicInteger

class Day12Part1 {

    private val positions = listOf(
        Pair(-1, 0),    // Top
        Pair(1, 0),     // Bottom
        Pair(0, 1),     // Right
        Pair(0, -1)     // Left
    )

    private val visited = mutableSetOf<Pair<Int, Int>>()

    fun solve(fileName: String): Int {
        val matrix = readInput(fileName).toMatrix()

        var sum = 0
        runMeasured {
            for (columnIndex in matrix.indices) {
                for (rowIndex in matrix[columnIndex].indices) {
                    val currentPosition = Pair(columnIndex, rowIndex)
                    if (visited.contains(currentPosition)) {
                        continue
                    }
                    val region = mutableSetOf<Pair<Int, Int>>()
                    val perimeterCounter = AtomicInteger(0)
                    collectRegion(matrix, setOf(currentPosition), region, perimeterCounter)

                    val price = region.size * perimeterCounter.get()

                    sum += price

                    println("${matrix[currentPosition.first][currentPosition.second]} perimeter: ${perimeterCounter.get()} price: $price")
                }
            }

        }
        println("Price: $sum")
        return sum
    }

    private fun collectRegion(
        matrix: List<List<Char>>,
        elements: Set<Pair<Int, Int>>,
        region: MutableSet<Pair<Int, Int>>,
        perimeter: AtomicInteger
    ) {
        val neighbors = mutableSetOf<Pair<Int, Int>>()
        for (element in elements) {
            if (visited.contains(element)) {
                continue
            }
            visited.add(element)
            region.add(element)
            val positionValue = matrix[element.first][element.second]
            for (position in positions) {
                val x = element.first + position.first
                val y = element.second + position.second

                try {
                    if (matrix[x][y] == positionValue) {
                        neighbors.add(Pair(x, y))
                        region.add(Pair(x, y))
                    } else {
                        perimeter.incrementAndGet()
                    }
                } catch (e: IndexOutOfBoundsException) {
                    // Ignore
                    perimeter.incrementAndGet()
                }
            }
        }

        if (neighbors.isEmpty()) {
            return
        } else {
            collectRegion(matrix, neighbors, region, perimeter)
        }
    }
}

class Day12Part2 {

    enum class Orientation(val horizontal: Boolean) {
        TOP(true),
        BOTTOM(true),
        LEFT(false),
        RIGHT(false)
    }

    data class Position(val position: Pair<Int, Int>, val orientation: Orientation)

    private val positions = listOf(
        Position(Pair(-1, 0), Orientation.TOP),
        Position(Pair(1, 0), Orientation.BOTTOM),
        Position(Pair(0, 1), Orientation.RIGHT),
        Position(Pair(0, -1), Orientation.LEFT)
    )

    private val visited = mutableSetOf<Pair<Int, Int>>()

    fun solve(fileName: String): Int {
        val matrix = readInput(fileName).toMatrix()

        var sum = 0
        runMeasured {
            for (columnIndex in matrix.indices) {
                for (rowIndex in matrix[columnIndex].indices) {
                    val currentPosition = Pair(columnIndex, rowIndex)
                    val currentValue = matrix[currentPosition.first][currentPosition.second]
                    if (currentValue == '.') {
                        continue
                    }
                    if (visited.contains(currentPosition)) {
                        continue
                    }
                    val region = mutableSetOf<Pair<Int, Int>>()
                    val perimeters = mutableListOf<Position>()
                    collectRegion(matrix, setOf(currentPosition), region, perimeters)

                    var horizontalSides = 0
                    val horizontalGroup = perimeters.filter { it.orientation.horizontal }
                        .groupBy { it.position.first }
                    for (entry in horizontalGroup) {
                        for (orientationGroup in entry.value.groupBy { it.orientation }) {
                            val list = orientationGroup.value.map { it.position.second }
                            horizontalSides += countGaps(list) + 1
                        }
                    }

                    var verticalSides = 0
                    val verticalGroup = perimeters.filter { !it.orientation.horizontal }
                        .groupBy { it.position.second }
                    for (entry in verticalGroup) {
                        for (orientationGroup in entry.value.groupBy { it.orientation }) {
                            val list = orientationGroup.value.map { it.position.first }
                            verticalSides += countGaps(list) + 1
                        }
                    }

                    val mergedSides = horizontalSides + verticalSides
                    val price = region.size * mergedSides

                    sum += price

                    println("A region of ${matrix[currentPosition.first][currentPosition.second]} plants with price ${region.size} * $mergedSides = $price")
                }
            }

        }
        println("Price: $sum")
        return sum
    }

    private fun countGaps(list: List<Int>): Int {
        if (list.isEmpty()) {
            return 0
        }

        val sortedList = list.sorted()

        var gaps = 0
        for (i in 1 until sortedList.size) {
            if ((sortedList[i] - sortedList[i - 1] - 1) > 0) {
                gaps++
            }
        }

        return gaps
    }

    private fun collectRegion(
        matrix: List<List<Char>>,
        elements: Set<Pair<Int, Int>>,
        region: MutableSet<Pair<Int, Int>>,
        perimeters: MutableList<Position>
    ) {
        val neighbors = mutableSetOf<Pair<Int, Int>>()
        for (element in elements) {
            if (visited.contains(element)) {
                continue
            }
            visited.add(element)
            region.add(element)
            val positionValue = matrix[element.first][element.second]
            for (position in positions) {
                val x = element.first + position.position.first
                val y = element.second + position.position.second

                try {
                    if (matrix[x][y] == positionValue) {
                        neighbors.add(Pair(x, y))
                        region.add(Pair(x, y))
                    } else {
                        perimeters.add(Position(Pair(x, y), position.orientation))
                    }
                } catch (e: IndexOutOfBoundsException) {
                    // Ignore
                    perimeters.add(Position(Pair(x, y), position.orientation))
                }
            }
        }

        if (neighbors.isEmpty()) {
            return
        } else {
            collectRegion(matrix, neighbors, region, perimeters)
        }
    }
}