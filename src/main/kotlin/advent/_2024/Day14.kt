package advent._2024

import advent.helper.printMatrix
import advent.helper.readInput
import advent.helper.runMeasured


data class Velocity(val x: Int, val y: Int)
data class Robot(var x: Int, var y: Int, val velocity: Velocity)
data class Field(var x: Int, var y: Int, val robots: MutableList<Robot>) {
    override fun toString(): String {
        return if (robots.isEmpty()) "." else "${robots.size}"
    }

    fun toTree(): String {
        return if (robots.isEmpty()) "." else "#"
    }
}

class Day14Part1 {
    private val pattern = "[a-zA-Z:=+]"

    fun solve(fileName: String, height: Int, width: Int, seconds: Int): Int {
        val input = readInput(fileName)

        val robots = mutableListOf<Robot>()
        for (line in input) {
            val (y, x, vY, vX) = line
                .replace(Regex(pattern), "")
                .replace(" ", ",")
                .split(",")
                .map { it.toInt() }
            robots.add(Robot(x, y, Velocity(vX, vY)))
        }

        var sum = 0
        runMeasured {
            val matrix = mutableListOf<MutableList<Field>>()
            for (i in 0 until height) {
                val row = mutableListOf<Field>()
                for (j in 0 until width) {
                    row.add(Field(i, j, mutableListOf()))
                }
                matrix.add(row)
            }
            for (robot in robots) {
                matrix[robot.x][robot.y].robots.add(robot)
            }
            print("Initial state")
            matrix.printMatrix()
            for (second in 0 until seconds) {
                print("After ${second + 1} second:")
                for (robot in robots) {
                    matrix[robot.x][robot.y].robots.remove(robot)

                    robot.x += robot.velocity.x
                    robot.y += robot.velocity.y
                    try {
                        matrix[robot.x][robot.y].robots.add(robot)
                    } catch (e: IndexOutOfBoundsException) {
                        // Teleport
                        if (robot.x < 0) {
                            robot.x = height - 1 + (robot.x + 1)
                        } else if (robot.x >= height) {
                            robot.x = 0 + (robot.x - height)
                        }
                        if (robot.y < 0) {
                            robot.y = width - 1 + (robot.y + 1)
                        } else if (robot.y >= width) {
                            robot.y = 0 + (robot.y - width)
                        }
                        matrix[robot.x][robot.y].robots.add(robot)
                    }
                }
                matrix.printMatrix()
            }

            var first = 0
            var second = 0
            var third = 0
            var fourth = 0

            for (i in matrix.indices) {
                for (j in matrix[i].indices) {
                    if (i < matrix.size / 2) {
                        if (j < matrix[i].size / 2) {
                            first += matrix[i][j].robots.size
                        } else if (j > matrix[i].size / 2) {
                            second += matrix[i][j].robots.size
                        }
                    } else if (i > matrix.size / 2) {
                        if (j < matrix[i].size / 2) {
                            third += matrix[i][j].robots.size
                        } else if (j > matrix[i].size / 2) {
                            fourth += matrix[i][j].robots.size
                        }
                    }
                }
            }
            sum = listOf(first, second, third, fourth)
                .filter { it > 0 }
                .reduce { acc, i -> acc * i }
        }
        return sum
    }
}

class Day14Part2 {
    private val pattern = "[a-zA-Z:=+]"

    fun solve(fileName: String, height: Int, width: Int, seconds: Int): Int {
        val input = readInput(fileName)

        val robots = mutableListOf<Robot>()
        for (line in input) {
            val (y, x, vY, vX) = line
                .replace(Regex(pattern), "")
                .replace(" ", ",")
                .split(",")
                .map { it.toInt() }
            robots.add(Robot(x, y, Velocity(vX, vY)))
        }

        return runMeasured {
            val matrix = mutableListOf<MutableList<Field>>()
            for (i in 0 until height) {
                val row = mutableListOf<Field>()
                for (j in 0 until width) {
                    row.add(Field(i, j, mutableListOf()))
                }
                matrix.add(row)
            }
            for (robot in robots) {
                matrix[robot.x][robot.y].robots.add(robot)
            }
            for (second in 0 until seconds) {
                for (robot in robots) {
                    matrix[robot.x][robot.y].robots.remove(robot)

                    robot.x += robot.velocity.x
                    robot.y += robot.velocity.y
                    try {
                        matrix[robot.x][robot.y].robots.add(robot)
                    } catch (e: IndexOutOfBoundsException) {
                        // Teleport
                        if (robot.x < 0) {
                            robot.x = height - 1 + (robot.x + 1)
                        } else if (robot.x >= height) {
                            robot.x = 0 + (robot.x - height)
                        }
                        if (robot.y < 0) {
                            robot.y = width - 1 + (robot.y + 1)
                        } else if (robot.y >= width) {
                            robot.y = 0 + (robot.y - width)
                        }
                        matrix[robot.x][robot.y].robots.add(robot)
                    }
                }

                for (column in matrix) {
                    val join = column.joinToString("") { it.toTree() }
                    // TODO Modify pattern to other input
                    if (join.contains("###############################")) {
                        println("At $second\n")
                        for (c in matrix) {
                            println(c.joinToString("") { it.toTree() })
                        }
                        return@runMeasured second + 1
                    }
                }
            }
            return@runMeasured 0
        }
    }
}