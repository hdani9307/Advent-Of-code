package advent._2023

import advent.Day
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class Day1Part1 : Day {
    override fun run() {
        var sum = 0
        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent/src/main/resources/2023/1.txt")).use {
            try {
                var line = it.readLine()
                do {
                    val replaced = line.replace("[^0-9]".toRegex(), "")
                    val first = replaced[0].toString()
                    val last = replaced[replaced.length - 1].toString()
                    val number = first + last
                    sum += number.toInt();
                    line = it.readLine()
                } while (line != null)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        println(sum)
    }
}