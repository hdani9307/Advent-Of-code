package advent

import java.io.BufferedReader
import java.io.FileReader

abstract class Day(fileName: String) {

    var lines: MutableList<String> = mutableListOf()

    init {
        BufferedReader(FileReader("/Users/hoffmann.daniel/dev/Advent-Of-code/src/main/resources/2023/$fileName.txt")).use {
            var line = it.readLine()
            do {
                lines.add(line)
                line = it.readLine()
            } while (line != null)
        }
    }

    abstract fun run()
}