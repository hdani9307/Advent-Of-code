import advent.helper.readInput
import advent.helper.runMeasured
import kotlin.math.absoluteValue

fun main() {
    val lines = readInput("2024/2.txt")

    runMeasured {
        var safeSum = 0
        for (line in lines) {
            val levels = line.split(" ")
            var safe = isSafe(levels)

            if (!safe) {
                for ((index, _) in levels.withIndex()) {
                    val subList = levels.toMutableList()
                    subList.removeAt(index)
                    safe = isSafe(subList)
                    if (safe) {
                        break
                    }
                }
            }

            if (safe) {
                safeSum++
            }
        }
        println(safeSum)
    }

}

private fun isSafe(levels: List<String>): Boolean {
    val rootDecreasing = levels[1].toInt() < levels[0].toInt()
    for (i in 0..<levels.size - 1) {
        val currentLevel = levels[i].toInt()
        val nextLevel = levels[i + 1].toInt()
        val difference = currentLevel - nextLevel

        val decreasing = nextLevel < currentLevel
        if (rootDecreasing != decreasing) {
            return false
        }
        if (difference.absoluteValue !in 1..3) {
            return false
        }
    }
    return true
}