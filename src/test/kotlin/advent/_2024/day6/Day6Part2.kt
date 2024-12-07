package advent._2024.day6

import advent.helper.readInput
import advent.helper.runMeasured
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class Day6Part2 {

    val down = 'v'
    val up = '^'
    val left = '<'
    val right = '>'

    var startingPosition = Pair(-1, -1)
    var startingDirection = ' '

    val visited = mutableMapOf<Pair<Int, Int>, Int>()

    @Test
    fun run() {

        val lines = readInput("2024/6.txt")
        runMeasured {
            val matrix = mutableListOf<MutableList<Tile>>()
            for ((i, line) in lines.withIndex()) {
                val row = mutableListOf<Tile>()
                for ((j, c) in line.toList().withIndex()) {
                    val currentPosition = c != '.' && c != '#'
                    row.add(Tile(currentPosition, c == '#'))
                    if (currentPosition) {
                        startingDirection = c
                        startingPosition = Pair(i, j)
                    }
                }
                matrix.add(row)
            }
            var loop = 0
            for ((i, value) in matrix.withIndex()) {
                for ((j, v) in value.withIndex()) {
                    if (v.obstacle || (startingPosition.first == i && startingPosition.second == j)) {
                        continue
                    }
                    v.obstacle = true
                    try {
                        visited.clear()
                        findLoop(startingDirection, startingPosition, matrix)
                        loop++
                    } catch (e: IndexOutOfBoundsException) {
                        // Ignore
                    } finally {
                        v.obstacle = false
                    }
                }
            }

            println(loop)
            Assertions.assertEquals(1618, loop)
        }
    }

    private fun findLoop(
        startDirection: Char,
        startCoordinates: Pair<Int, Int>,
        matrix: MutableList<MutableList<Tile>>
    ) {
        var direction = startDirection
        var currentCoordinates = startCoordinates
        while (true) {
            visited.putIfAbsent(currentCoordinates, 0)
            visited.computeIfPresent(currentCoordinates) { _, u -> u + 1 }

            if (visited.getOrDefault(currentCoordinates, 0) > 3) {
                return
            }
            if (direction == up) {
                val nextCoordinate = Pair(currentCoordinates.first - 1, currentCoordinates.second)
                if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                    direction = right
                } else {
                    currentCoordinates = nextCoordinate
                }
            } else if (direction == down) {
                val nextCoordinate = Pair(currentCoordinates.first + 1, currentCoordinates.second)
                if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                    direction = left
                } else {
                    currentCoordinates = nextCoordinate
                }
            } else if (direction == left) {
                val nextCoordinate = Pair(currentCoordinates.first, currentCoordinates.second - 1)

                if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                    direction = up
                } else {
                    currentCoordinates = nextCoordinate
                }
            } else if (direction == right) {
                val nextCoordinate = Pair(currentCoordinates.first, currentCoordinates.second + 1)
                if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                    direction = down
                } else {
                    currentCoordinates = nextCoordinate
                }
            }
        }
    }
}