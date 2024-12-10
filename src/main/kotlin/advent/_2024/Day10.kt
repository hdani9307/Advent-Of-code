package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured
import java.util.concurrent.atomic.AtomicLong

class Day10Part1 {

    data class Node(val position: Pair<Int, Int>, val value: Int, val children: MutableList<Node>)

    val matrix = mutableListOf<MutableList<Char>>()

    private val positions = listOf(
        Pair(-1, 0),    // Top
        Pair(1, 0),     // Bottom
        Pair(0, 1),     // Right
        Pair(0, -1)     // Left
    )

    fun run(): Long {
        var sum = 0L
        val lines = readInput("2024/10.txt")
        val headNodes = mutableListOf<Node>()

        runMeasured {
            // Read matrix
            for ((i, line) in lines.withIndex()) {
                val row = mutableListOf<Char>()
                for ((j, c) in line.toList().withIndex()) {
                    row.add(c)
                    if (c == '0') {
                        headNodes.add(Node(Pair(i, j), 0, mutableListOf()))
                    }
                }
                matrix.add(row)
            }

            for (headNode in headNodes) {
                val accumulator = mutableSetOf<Pair<Int, Int>>()
                buildTree(listOf(headNode), accumulator)
                sum += accumulator.size
            }
        }

        return sum
    }

    private fun buildTree(nodes: List<Node>, accumulator: MutableSet<Pair<Int, Int>>) {
        for (node in nodes) {
            if (node.value == 9) {
                accumulator.add(node.position)
            } else {
                for (position in positions) {
                    val x = node.position.first + position.first
                    val y = node.position.second + position.second
                    if (checkPosition(x, y, node.value)) {
                        node.children.add(
                            Node(
                                Pair(x, y),
                                matrix[x][y].toString().toInt(),
                                mutableListOf()
                            )
                        )
                    }
                }
            }
            buildTree(node.children, accumulator)
        }
    }

    private fun checkPosition(x: Int, y: Int, startValue: Int): Boolean {
        return try {
            val position = matrix[x][y]
            if (position.isDigit()) {
                position.toString().toInt() == startValue + 1
            } else {
                false
            }
        } catch (e: IndexOutOfBoundsException) {
            false
        }
    }
}

class Day10Part2 {

    data class Node(val position: Pair<Int, Int>, val value: Int, val children: MutableList<Node>)

    val matrix = mutableListOf<MutableList<Char>>()

    private val positions = listOf(
        Pair(-1, 0),    // Top
        Pair(1, 0),     // Bottom
        Pair(0, 1),     // Right
        Pair(0, -1)     // Left
    )

    fun run(): Long {
        val sum = AtomicLong(0)
        val lines = readInput("2024/10.txt")
        val headNodes = mutableListOf<Node>()

        runMeasured {
            // Read matrix
            for ((i, line) in lines.withIndex()) {
                val row = mutableListOf<Char>()
                for ((j, c) in line.toList().withIndex()) {
                    row.add(c)
                    if (c == '0') {
                        headNodes.add(Node(Pair(i, j), 0, mutableListOf()))
                    }
                }
                matrix.add(row)
            }

            for (headNode in headNodes) {
                buildTree(listOf(headNode), sum)
            }
        }
        return sum.toLong()
    }

    private fun buildTree(nodes: List<Node>, accumulator: AtomicLong) {
        for (node in nodes) {
            if (node.value == 9) {
                accumulator.incrementAndGet()
            } else {
                for (position in positions) {
                    val x = node.position.first + position.first
                    val y = node.position.second + position.second
                    if (checkPosition(x, y, node.value)) {
                        node.children.add(
                            Node(
                                Pair(x, y),
                                matrix[x][y].toString().toInt(),
                                mutableListOf()
                            )
                        )
                    }
                }
            }
            buildTree(node.children, accumulator)
        }
    }

    private fun checkPosition(x: Int, y: Int, startValue: Int): Boolean {
        return try {
            val position = matrix[x][y]
            if (position.isDigit()) {
                position.toString().toInt() == startValue + 1
            } else {
                false
            }
        } catch (e: IndexOutOfBoundsException) {
            false
        }
    }
}