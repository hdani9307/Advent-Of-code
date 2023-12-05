package advent._2023

import advent.Day
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class Day2Part1 : Day("2") {

    val limits = mapOf(
        Pair("red", 12),
        Pair("blue", 14),
        Pair("green", 13)
    )

    override fun run() {
        var sum = 0
        var index = 0;
        BufferedReader(FileReader("/Users/hoffmann.daniel/dev/Advent-Of-code/src/main/resources/2023/2.txt")).use {
            try {
                var line = it.readLine()
                do {
                    ++index
                    val replaced = line.replace("^Game [0-9]*: ".toRegex(), "")

                    //3 blue, 4 red
                    var passed = true;
                    val parts = replaced.split("; ")
                    for (part in parts) {

                        val subParts = part.split(", ")
                        for (subPart in subParts) {
                            //3 blue
                            val (count, color) = subPart.split(" ");
                            if (count.toInt() > limits[color]!!) {
                                println("Game $index is failed")
                                passed = false;
                                break
                            }
                        }
                    }
                    if (passed) {
                        println("Game $index is passed")
                        sum += index;
                    }
                    line = it.readLine()
                } while (line != null)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        println(sum)
    }
}

class Day2Part2 : Day("2") {


    override fun run() {
        var sum = 0
        var index = 0;
        BufferedReader(FileReader("/Users/hoffmann.daniel/dev/Advent-Of-code/src/main/resources/2023/2.txt")).use { it ->
            try {
                var line = it.readLine()
                do {
                    ++index
                    val max = mutableMapOf(
                        Pair("red", 0),
                        Pair("blue", 0),
                        Pair("green", 0)
                    )
                    val game = line.replace("^Game [0-9]*: ".toRegex(), "")
                    val parts = game.split("; ")
                    for (part in parts) {

                        val subParts = part.split(", ")
                        for (subPart in subParts) {
                            val (count, color) = subPart.split(" ");
                            if (count.toInt() > max[color]!!) {
                                max[color] = count.toInt()
                            }
                        }
                    }
                    println(max)
                    val power = max.values.filter { it > 0 }.reduce { acc, i -> acc * i };
                    println("Game $index power is $power")
                    sum += power

                    line = it.readLine()
                } while (line != null)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        println(sum)
    }
}