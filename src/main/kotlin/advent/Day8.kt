package advent

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException


class Day8 {

    fun run() {
        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent/src/main/resources/day8.txt")).use {
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

            var counter = 0

            for ((i, column) in matrix.withIndex()) {
                for ((j, value) in column.withIndex()) {
                    if (i == 0 || i == matrix.size - 1) {
                        counter++;
                        continue;
                    }
                    if (j == 0 || j == matrix[i].size - 1) {
                        counter++;
                        continue;
                    }
                    if (isVisible(matrix, i, j, value)) {
                        counter++;
                    }
                }
            }

            println(counter)
        }
    }

    private fun isVisible(matrix: MutableList<MutableList<Int>>, rowIndex: Int, columnIndex: Int, value: Int): Boolean {
        val row = matrix[rowIndex]

        var before = row.subList(0, columnIndex)
        var after = row.subList(columnIndex + 1, row.size)

        if (before.all { it < value }) {
            return true
        }

        if (after.all { it < value }) {
            return true
        }

        val column = mutableListOf<Int>()
        for (withIndex in matrix.withIndex()) {
            column.add(matrix[withIndex.index][columnIndex])
        }
        before = column.subList(0, rowIndex)
        after = column.subList(rowIndex + 1, column.size)

        if (before.all { it < value }) {
            return true
        }

        if (after.all { it < value }) {
            return true
        }

        return false
    }
}
