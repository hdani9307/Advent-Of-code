package advent._2024.day6

import advent.helper.readInput
import advent.helper.runMeasured
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

data class Tile(
    var visited: Boolean,
    var obstacle: Boolean
)

class Day6Part1 {

    @Test
    fun run() {
        val down = 'v'
        val up = '^'
        val left = '>'
        val right = '<'
        val lines = readInput("2024/6.txt")
        runMeasured {
            val matrix = mutableListOf<MutableList<Tile>>()
            // Read matrix
            var direction = ' '
            var currentCoordinates: Pair<Int, Int> = Pair(-1, -1)
            for ((i, line) in lines.withIndex()) {
                val row = mutableListOf<Tile>()
                for ((j, c) in line.toList().withIndex()) {
                    val currentPosition = c != '.' && c != '#'
                    row.add(Tile(currentPosition, c == '#'))
                    if (currentPosition) {
                        direction = c
                        currentCoordinates = Pair(i, j)
                    }
                }
                matrix.add(row)
            }
            try {
                while (true) {
                    if (direction == up) {
                        val nextCoordinate = Pair(currentCoordinates.first - 1, currentCoordinates.second)
                        if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                            direction = right
                        } else {
                            matrix[nextCoordinate.first][nextCoordinate.second].visited = true
                            currentCoordinates = nextCoordinate
                        }
                    }
                    if (direction == down) {
                        val nextCoordinate = Pair(currentCoordinates.first + 1, currentCoordinates.second)
                        if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                            direction = left
                        } else {
                            matrix[nextCoordinate.first][nextCoordinate.second].visited = true
                            currentCoordinates = nextCoordinate
                        }
                    }
                    if (direction == left) {
                        val nextCoordinate = Pair(currentCoordinates.first, currentCoordinates.second - 1)
                        if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                            direction = up
                        } else {
                            matrix[nextCoordinate.first][nextCoordinate.second].visited = true
                            currentCoordinates = nextCoordinate
                        }
                    }
                    if (direction == right) {
                        val nextCoordinate = Pair(currentCoordinates.first, currentCoordinates.second + 1)
                        if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                            direction = down
                        } else {
                            matrix[nextCoordinate.first][nextCoordinate.second].visited = true
                            currentCoordinates = nextCoordinate
                        }
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
                val visitedCount = matrix.flatten().count { it.visited }
                println(visitedCount)
                Assertions.assertEquals(4778, visitedCount)
            }
        }
    }
}