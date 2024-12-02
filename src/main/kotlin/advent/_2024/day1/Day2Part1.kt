import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.math.absoluteValue

fun main() {

    val lines = readInput("2024/2.txt")

    runMeasured {
        var safeSum = 0
        for (line in lines) {
            var rootDecreasing = false
            val levels = line.split(" ")

            var isSafe = false
            for (i in 0..<levels.size - 1) {
                val currentLevel = levels[i].toInt()
                val nextLevel = levels[i + 1].toInt()
                val difference = currentLevel - nextLevel
                if (i == 0) {
                    rootDecreasing = nextLevel < currentLevel
                }
                val decreasing = nextLevel < currentLevel
                if (rootDecreasing == decreasing && (difference.absoluteValue in 1..3)) {
                    isSafe = true
                } else {
                    isSafe = false
                    break
                }
            }
            if (isSafe) {
                safeSum++
            }
        }
        println(safeSum)
    }
}