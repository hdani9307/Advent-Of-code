package advent._2022

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException


class Day8 {

    fun run() {
        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent//resources/day8.txt")).use {
            val matrix = mutableListOf<MutableList<Int>>()
            try {
                var i = 0
                var line = it.readLine()
                do {
                    matrix.add(mutableListOf())
                    line.toCharArray().forEach { c ->
                        matrix[i].add(Integer.parseInt(c.toString()));
                    }

                    i++;
                    line = it.readLine()
                } while (line != null)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            var topScore = 0

            for ((i, column) in matrix.withIndex()) {
                for ((j, value) in column.withIndex()) {
                    val score = calculateScore(matrix, i, j, value)
                    if (score > topScore) {
                        topScore = score
                    }
                }
            }

            println(topScore)
        }
    }

    private fun calculateScore(
        matrix: MutableList<MutableList<Int>>,
        rowIndex: Int,
        columnIndex: Int,
        value: Int
    ): Int {
        val row = matrix[rowIndex]

        var left = 0
        var right = 0
        var top = 0
        var down = 0

        var before = row.subList(0, columnIndex)
        var after = row.subList(columnIndex + 1, row.size)

        for (i in before.reversed()) {
            if (i < value) {
                left++
            } else {
                left++
                break
            }
        }
        for (i in after) {
            if (i < value) {
                right++
            } else {
                right++
                break
            }
        }

        val column = mutableListOf<Int>()
        for (withIndex in matrix.withIndex()) {
            column.add(matrix[withIndex.index][columnIndex])
        }
        before = column.subList(0, rowIndex)
        after = column.subList(rowIndex + 1, column.size)

        for (i in before.reversed()) {
            if (i < value) {
                top++
            } else {
                top++
                break
            }
        }

        for (i in after) {
            if (i < value) {
                down++
            } else {
                down++
                break
            }
        }

        //199272
        return left * right * top * down
    }
}
