package advent._2022

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class Day10 {
    fun run() {
        val matrix = mutableListOf<String>()
        var queue = mutableListOf<String>()
        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent//resources/day10.txt")).use {
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
        var index = 0

        var pixLeft = 0
        var pix0 = 1
        var pixRight = 2

        //  If the sprite is positioned such that one of its three pixels is the pixel currently being drawn,
        //  the screen produces a lit pixel (#); otherwise,
        //  the screen leaves the pixel dark (.).
        for (operation in queue.iterator()) {

            if (operation == "noop") {
                //begin executing addx 15 or noop
            }

            //CRT draws pixel in position
            if (index == pixLeft || index == pix0 || index == pixRight) {
                matrix.add("#")
            } else {
                matrix.add(".")
            }

            if (operation != "noop") {
                pix0 += operation.split(" ")[1].toInt()
                pixLeft = pix0 - 1
                pixRight = pix0 + 1
            }

            index++
            if(index % 40 == 0){
                index = 0
            }
        }

        for (x in 0 until 40 * 6) {
            print(matrix[x])
            if ((x + 1) % 40 == 0) {
                println()
            }
        }
        //##..##..##..##..##..##..##..##..##..##..
        //###...###...###...###...###...###...###.
        //####....####....####....####....####....
        //#####.....#####.....#####.....#####.....
        //######......######......######......####
        //#######.......#######.......#######.....

        //##..##..##..##..##..##..##..##..##..##..
        //###...###...###...###...###...###...###.
        //####....####....####....####....####....
        //#####.....#####.....#####.....#####.....
        //######......######......######......####
        //#######.......#######.......#######.....
    }
}