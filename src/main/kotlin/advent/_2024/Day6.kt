package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured

data class Tile(
    var visited: Boolean,
    var obstacle: Boolean
)

class Day6Part1 {

    fun run(): Int {
        val down = 'v'
        val up = '^'
        val left = '>'
        val right = '<'
        val lines = readInput("2024/6.txt")
        var visitedCount = 0
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
                        val nextCoordinate =
                            Pair(currentCoordinates.first - 1, currentCoordinates.second)
                        if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                            direction = right
                        } else {
                            matrix[nextCoordinate.first][nextCoordinate.second].visited = true
                            currentCoordinates = nextCoordinate
                        }
                    }
                    if (direction == down) {
                        val nextCoordinate =
                            Pair(currentCoordinates.first + 1, currentCoordinates.second)
                        if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                            direction = left
                        } else {
                            matrix[nextCoordinate.first][nextCoordinate.second].visited = true
                            currentCoordinates = nextCoordinate
                        }
                    }
                    if (direction == left) {
                        val nextCoordinate =
                            Pair(currentCoordinates.first, currentCoordinates.second - 1)
                        if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                            direction = up
                        } else {
                            matrix[nextCoordinate.first][nextCoordinate.second].visited = true
                            currentCoordinates = nextCoordinate
                        }
                    }
                    if (direction == right) {
                        val nextCoordinate =
                            Pair(currentCoordinates.first, currentCoordinates.second + 1)
                        if (matrix[nextCoordinate.first][nextCoordinate.second].obstacle) {
                            direction = down
                        } else {
                            matrix[nextCoordinate.first][nextCoordinate.second].visited = true
                            currentCoordinates = nextCoordinate
                        }
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
                visitedCount = matrix.flatten().count { it.visited }
            }
        }
        return visitedCount
    }
}

class Day6Part2 {

    val down = 'v'
    val up = '^'
    val left = '<'
    val right = '>'

    var startingPosition = Pair(-1, -1)
    var startingDirection = ' '

    val visited = mutableMapOf<Pair<Int, Int>, Int>()

    fun run(): Int {
        val lines = readInput("2024/6.txt")
        var loop = 0
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
        }
        return loop
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