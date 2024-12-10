package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured

class Day8Part1 {

    fun run(): Int {
        val lines = readInput("2024/8.txt")
        val matrix = mutableListOf<MutableList<Char>>()
        val uniqueLocations = mutableSetOf<Pair<Int, Int>>()
        runMeasured {
            // Read matrix
            for (line in lines) {
                val row = mutableListOf<Char>()
                for (c in line.toList()) {
                    row.add(c)
                }
                matrix.add(row)
            }
            for ((i, a) in matrix.withIndex()) {
                for ((j, b) in a.withIndex()) {
                    if (b == '.') {
                        continue
                    }
                    for (column in 1..matrix.size) {
                        for (row in 1..a.size) {

                            // Left
                            try {
                                if (matrix[i][j - row] == b) {
                                    uniqueLocations.add(Pair(i, j - row * 2))
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Right
                            try {
                                if (matrix[i][j + row] == b) {
                                    uniqueLocations.add(Pair(i, j + row * 2))
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Top
                            try {
                                if (matrix[i - column][j] == b) {
                                    uniqueLocations.add(Pair(i - column * 2, j))
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Bottom
                            try {
                                if (matrix[i + column][j] == b) {
                                    uniqueLocations.add(Pair(i + column * 2, j))
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Top right
                            try {
                                if (matrix[i + column][j + row] == b) {
                                    uniqueLocations.add(Pair(i + column * 2, j + row * 2))
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Top left
                            try {
                                if (matrix[i + column][j - row] == b) {
                                    uniqueLocations.add(Pair(i + column * 2, j - row * 2))
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Bottom left
                            try {
                                if (matrix[i - column][j - row] == b) {
                                    uniqueLocations.add(Pair((i - column * 2), j - row * 2))
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Bottom right
                            try {
                                if (matrix[i - column][j + row] == b) {
                                    uniqueLocations.add(Pair((i - column * 2), j + row * 2))
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                        }
                    }
                }
            }
        }
        uniqueLocations.removeIf { it.first >= matrix.size || it.second >= matrix[0].size || it.first < 0 || it.second < 0 }

        return uniqueLocations.size
    }
}

class Day8Part2 {

    fun run(): Int {
        val lines = readInput("2024/8.txt")
        val matrix = mutableListOf<MutableList<Char>>()
        val uniqueLocations = mutableSetOf<Pair<Int, Int>>()
        runMeasured {
            // Read matrix
            for (line in lines) {
                val row = mutableListOf<Char>()
                for (c in line.toList()) {
                    row.add(c)
                }
                matrix.add(row)
            }
            for ((i, a) in matrix.withIndex()) {
                for ((j, b) in a.withIndex()) {
                    if (b == '.') {
                        continue
                    }
                    uniqueLocations.add(Pair(i, j))
                    for (column in 1..matrix.size) {
                        for (row in 1..a.size) {

                            // Left
                            try {
                                if (matrix[i][j - row] == b) {
                                    for (m in 0 until a.size) {
                                        uniqueLocations.add(Pair(i, j - row * m))
                                    }
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Right
                            try {
                                if (matrix[i][j + row] == b) {
                                    for (m in 0 until a.size) {
                                        uniqueLocations.add(Pair(i, j + row * m))

                                    }
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Top
                            try {
                                if (matrix[i - column][j] == b) {
                                    for (m in 0 until matrix.size) {
                                        uniqueLocations.add(Pair(i - column * m, j))
                                    }
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Bottom
                            try {
                                if (matrix[i + column][j] == b) {
                                    for (m in 0 until matrix.size) {
                                        uniqueLocations.add(Pair(i + column * m, j))
                                    }
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            val max = a.size.coerceAtLeast(matrix.size)
                            // Top right
                            try {
                                if (matrix[i + column][j + row] == b) {
                                    for (m in 0 until max) {
                                        uniqueLocations.add(Pair(i + column * m, j + row * m))
                                    }
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Top left
                            try {
                                if (matrix[i + column][j - row] == b) {
                                    for (m in 0 until max) {
                                        uniqueLocations.add(Pair(i + column * m, j - row * m))
                                    }
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Bottom left
                            try {
                                if (matrix[i - column][j - row] == b) {
                                    for (m in 0 until max) {
                                        uniqueLocations.add(Pair((i - column * m), j - row * m))
                                    }
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                            // Bottom right
                            try {
                                if (matrix[i - column][j + row] == b) {
                                    for (m in 0 until max) {
                                        uniqueLocations.add(Pair((i - column * m), j + row * m))
                                    }
                                }
                            } catch (e: IndexOutOfBoundsException) {
                                // Ignore
                            }

                        }
                    }
                }
            }
        }
        uniqueLocations.removeIf { it.first >= matrix.size || it.second >= matrix[0].size || it.first < 0 || it.second < 0 }

        return uniqueLocations.size
    }
}