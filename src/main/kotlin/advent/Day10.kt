package advent

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class Day10 {
    fun run() {
        var queue = mutableListOf<String>()
        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent/src/main/resources/day10.txt")).use {
            try {
                var line = it.readLine()
                do {
                    if (line != "noop") {
                        queue.add("noop")
                    }
                    queue.add(line)

                    line = it.readLine()
                } while (line != null)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        var index = 1
        var x = 1
        val signals = mutableListOf<Int>()
        for (operation in queue.iterator()) {
            signals.add(x * index)
            if (operation != "noop") {
                x += operation.split(" ")[1].toInt()
            }
            index++
        }
        //14840
        //14760
        println(signals)
        println(signals[19])
        println(signals[59])
        println(signals[99])
        println(signals[139])
        println(signals[179])
        println(signals[219])
        println(signals[19] + signals[59] + signals[99] + signals[139] + signals[179] + signals[219])
        //13140
    }
}