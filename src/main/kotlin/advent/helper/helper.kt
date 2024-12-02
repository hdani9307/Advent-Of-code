package advent.helper

import java.io.File
import kotlin.time.Duration
import kotlin.time.measureTimedValue

fun readInput(fileName: String): List<String> {
    val absolutePath = File("").absolutePath
    return File("$absolutePath/src/main/resources/$fileName")
        .bufferedReader()
        .readLines()
}

fun <T> runMeasured(block: () -> T) {
    val (_: T, duration: Duration) = measureTimedValue {
        block()
    }

    println("${duration.inWholeMilliseconds} ms.")
}