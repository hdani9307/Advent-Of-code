package advent._2022

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class Day13 {

    val visited = mutableSetOf<Int>()

    fun run() {
        readInput()
    }

    private fun readInput() {
        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent/src/main/resources/day13.txt")).use {
            try {
                var rightSum = 0
                var index = 1;
                var line = it.readLine()
                val lines = mutableListOf<String>()
                do {
                    if (line.isBlank()) {
                        try {
                            val line1 = unwrapArray(lines[0], 0)[0] as List<Any>
                            visited.clear()

                            val line2 = unwrapArray(lines[1], 0)[0] as List<Any>
                            visited.clear()

                            val isCorrect = processLine(line1, line2)
                            if (isCorrect) {
                                rightSum += index
                            }
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }

                        index++
                        lines.clear()
                    } else {
                        lines.add(line)
                    }

                    line = it.readLine()
                } while (line != null)

                println(rightSum)
                //1405 low
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun processLine(
        line1: List<Any>,
        line2: List<Any>
    ): Boolean {
        var isCorrect: Boolean? = null
        for (left in line1.withIndex()) {
            val leftVal = left.value
            if (isCorrect == true && line2.size <= left.index) {
                return true
            }
            val rightVal = line2[left.index]

            if (leftVal is Int && rightVal is Int) {
                if (leftVal < rightVal) {
                    isCorrect = true
                } else if (leftVal > rightVal) {
                    isCorrect = false
                    break
                }
            } else if (leftVal is List<*> && rightVal is List<*>) {
                isCorrect = processLine(leftVal as List<Any>, rightVal as List<Any>)
                if (!isCorrect) {
                    break
                }
                // Recursion here
            } else {
                if (leftVal is List<*>) {
                    isCorrect = processLine(leftVal as List<Any>, listOf(rightVal))
                    if (!isCorrect) {
                        break
                    }
                    // Right is int

                } else if (rightVal is List<*>) {
                    // Left is int
                    isCorrect = processLine(listOf(leftVal), rightVal as List<Any>)
                    if (!isCorrect) {
                        break
                    }
                } else {
                    throw RuntimeException("¯\\_(ツ)_/¯")
                }
            }
        }
        return isCorrect ?: true
    }

    private fun unwrapArray(
        line: String,
        from: Int
    ): MutableList<Any> {
        val currentList = mutableListOf<Any>()

        val chars = line.toCharArray()

        var number = ""
        for (i in from until line.length) {
            val c = chars[i]
            if (visited.contains(i)) {
                continue
            }
            if (c == '[') {
                visited.add(i)
                currentList.add(unwrapArray(line, i))
            } else if (c == ']') {
                visited.add(i)
                if (number.isNotBlank()) {
                    currentList.add(Integer.parseInt(number))
                }
                return currentList
            } else if (c == ',') {
                if (number.isNotBlank()) {
                    currentList.add(Integer.parseInt(number))
                    number = ""
                }
            } else {
                visited.add(i)
                number += c
            }
        }

        return currentList
    }
}