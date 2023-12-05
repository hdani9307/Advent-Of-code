package advent._2023

import advent.Day
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class Day1Part1 : Day("2") {
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

class Day1Part2 : Day("2") {

    val numberCorrections = mapOf(
        Pair("oneight", 18),
        Pair("eightwo", 82),
        Pair("twone", 21),
        Pair("threeight", 38),
        Pair("fiveight", 58),
        Pair("sevenine", 79),
        Pair("nineight", 98),
        Pair("eightwo", 82),
        Pair("eighthree", 83),
        Pair("one", 1),
        Pair("two", 2),
        Pair("three", 3),
        Pair("four", 4),
        Pair("five", 5),
        Pair("six", 6),
        Pair("seven", 7),
        Pair("eight", 8),
        Pair("nine", 9)
    );

    override fun run() {
        var sum = 0L
        var index = 1;
        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent/src/main/resources/2023/1.txt")).use {
            try {
                var line = it.readLine()
                do {
                    for (replacement in numberCorrections) {
                        line = line.replace(replacement.key.toRegex(), replacement.value.toString());
                    }
                    val replaced = line.replace("[^0-9]".toRegex(), "")
                    val first = replaced[0].toString()
                    val last = replaced[replaced.length - 1].toString()
                    val number = first + last
                    print(index++)
                    print(". ")
                    println(number)
                    sum += number.toInt();
                    line = it.readLine()
                } while (line != null)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        // 54807 low
        // 54824 ok
        println(sum)
    }
}