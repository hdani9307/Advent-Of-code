package advent._2023.day1

import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.test.Ignore
import kotlin.time.ExperimentalTime

@Ignore
fun main() {
    val lines = readInput("1.txt")
    runMeasured {
        var sum = 0
        for (line in lines) {
            val numbers = line.replace(Regex("\\D"), "").trim().toCharArray()
            sum += (numbers[0].toString() + numbers[numbers.size - 1]).toInt()
        }
        println(sum)
    }
}
