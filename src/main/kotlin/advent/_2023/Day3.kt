package advent._2023

import advent.Day
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.lang.Exception
import java.lang.NumberFormatException

class Day3Part1 : Day("3") {
    val matrix = mutableListOf<MutableList<String>>()
    override fun run() {
        val numbers = mutableListOf<Int>()
        for (line in lines) {
            val matrixLine = mutableListOf<String>()
            for (c in line.toCharArray()) {
                matrixLine.add(c.toString())
            }
            matrix.add(matrixLine);
        }

        val elementBuffer = mutableListOf<Pair<Int, String>>()
        var haveSymbol = false
        for ((i, line) in matrix.withIndex()) {
            for ((j, element) in line.withIndex()) {
                val isNumber = element.matches("[0-9]".toRegex())
                if (!isNumber) {
                    if (haveSymbol) {
                        val number = elementBuffer.map { it.second }.joinToString(separator = "").toInt()
                        numbers.add(number)
                    }

                    haveSymbol = false;
                    elementBuffer.clear()
                } else {
                    if (!haveSymbol) {

                        val topIndex = i - 1
                        val bottomIndex = i + 1;
                        val leftIndex = j - 1
                        val rightIndex = j + 1

                        println("--------------------------")
                        // Diagonal cases
                        if (checkForSymbol(topIndex, leftIndex)) {
                            haveSymbol = true;
                        }
                        if (checkForSymbol(topIndex, rightIndex)) {
                            haveSymbol = true;
                        }
                        if (checkForSymbol(bottomIndex, leftIndex)) {
                            haveSymbol = true;
                        }
                        if (checkForSymbol(bottomIndex, rightIndex)) {
                            haveSymbol = true;
                        }

                        // Inline cases
                        if (checkForSymbol(i, leftIndex)) {
                            haveSymbol = true;
                        }
                        if (checkForSymbol(i, rightIndex)) {
                            haveSymbol = true;
                        }

                        // Top case
                        if (checkForSymbol(topIndex, j)) {
                            haveSymbol = true;
                        }
                        // Bottom case
                        if (checkForSymbol(bottomIndex, j)) {
                            haveSymbol = true;
                        }
                    }

                    elementBuffer.add(Pair(j, element))

                }
            }
        }

        // 10241831 high
        // 512736 low
        // 557705 ok
        println(numbers.reduce { acc, i -> acc + i })
    }

    private fun checkForSymbol(i: Int, j: Int): Boolean {
        try {
            val element = matrix[i][j]
            if (element == ".") {
                return false
            }
            element.toInt()
            println("$i and $j is a number, returning false")
            return false
        } catch (e: NumberFormatException) {
            println("$i and $j is a symbol: ${matrix[i][j]}, returning true")
            return true
        } catch (e: Exception) {
            println("$i and $j out of bounds, returning false")
            return false
        }
    }
}

class Day3Part2 : Day("3") {

    val matrix = mutableListOf<MutableList<String>>()
    val gearMap = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
    override fun run() {
        for (line in lines) {
            val matrixLine = mutableListOf<String>()
            for (c in line.toCharArray()) {
                matrixLine.add(c.toString())
            }
            matrix.add(matrixLine);
        }


        val elementBuffer = mutableListOf<Pair<Int, String>>()
        val gearSymbols = mutableSetOf<Pair<Int, Int>>()
        var haveSymbol = false
        for ((i, line) in matrix.withIndex()) {
            for ((j, element) in line.withIndex()) {
                val isNumber = element.matches("[0-9]".toRegex())
                if (!isNumber) {
                    if (haveSymbol) {
                        val number = elementBuffer.map { it.second }.joinToString(separator = "").toInt()

                        for (gearSymbol in gearSymbols) {
                            if (gearMap.containsKey(gearSymbol)) {
                                gearMap[gearSymbol]!!.add(number)
                            } else {
                                gearMap[gearSymbol] = mutableListOf(number)
                            }
                        }
                    }

                    gearSymbols.clear()
                    haveSymbol = false;
                    elementBuffer.clear()
                } else {
                    if (!haveSymbol) {

                        val topIndex = i - 1
                        val bottomIndex = i + 1;
                        val leftIndex = j - 1
                        val rightIndex = j + 1

                        println("--------------------------")

                        // Diagonal cases
                        if (checkForSymbol(topIndex, leftIndex, gearSymbols)) {
                            haveSymbol = true;
                        }
                        if (checkForSymbol(topIndex, rightIndex, gearSymbols)) {
                            haveSymbol = true;
                        }
                        if (checkForSymbol(bottomIndex, leftIndex, gearSymbols)) {
                            haveSymbol = true;
                        }
                        if (checkForSymbol(bottomIndex, rightIndex, gearSymbols)) {
                            haveSymbol = true;
                        }

                        // Inline cases
                        if (checkForSymbol(i, leftIndex, gearSymbols)) {
                            haveSymbol = true;
                        }
                        if (checkForSymbol(i, rightIndex, gearSymbols)) {
                            haveSymbol = true;
                        }

                        // Top case
                        if (checkForSymbol(topIndex, j, gearSymbols)) {
                            haveSymbol = true;
                        }
                        // Bottom case
                        if (checkForSymbol(bottomIndex, j, gearSymbols)) {
                            haveSymbol = true;
                        }
                    }

                    elementBuffer.add(Pair(j, element))
                }
            }
        }

        var sum = 0;
        for (value in gearMap.values) {
            if (value.size == 2) {
                val mult = value.reduce { acc, i -> acc * i }
                sum += mult;
            } else {
                println()
            }
        }

        // 84266818 - ok
        println(sum)
    }


    private fun checkForSymbol(i: Int, j: Int, gearSymbols: MutableSet<Pair<Int, Int>>): Boolean {
        try {
            val element = matrix[i][j]
            if (element == ".") {
                return false
            }
            if (element == "*") {
                val key = Pair(i, j)
                gearSymbols.add(key)
            }
            element.toInt()
            println("$i and $j is a number, returning false")
            return false
        } catch (e: NumberFormatException) {
            println("$i and $j is a symbol: ${matrix[i][j]}, returning true")
            return true
        } catch (e: Exception) {
            println("$i and $j out of bounds, returning false")
            return false
        }
    }
}