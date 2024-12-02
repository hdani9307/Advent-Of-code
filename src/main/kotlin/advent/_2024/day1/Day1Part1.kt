import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.math.absoluteValue

fun main() {

    val lines = readInput("2024/1.txt")

    runMeasured {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        for (line in lines) {
            val splits = line.split("   ")
            left.add(splits[0].trim().toInt())
            right.add(splits[1].trim().toInt())
        }
        left.sort()
        right.sort()

        var sum = 0
        for ((index, i) in left.withIndex()) {
            val abs = (i - right[index]).absoluteValue
            sum += abs
        }
        println(sum)
    }
}