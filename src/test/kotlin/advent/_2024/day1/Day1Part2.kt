import advent.helper.readInput
import advent.helper.runMeasured

fun main() {

    val leftMap = mutableMapOf<Int, Int>()
    val rightMap = mutableMapOf<Int, Int>()
    val lines = readInput("2024/1.txt")

    runMeasured {
        for (line in lines) {
            val splits = line.split("   ")
            val left = splits[0].trim().toInt()
            val right = splits[1].trim().toInt()

            leftMap.putIfAbsent(left, 0)
            rightMap.putIfAbsent(right, 0)

            leftMap[left] = leftMap[left]!! + 1
            rightMap[right] = rightMap[right]!! + 1
        }
        var sum = 0
        for (e in leftMap) {
            if (rightMap.containsKey(e.key)) {
                sum += (e.key * rightMap[e.key]!! * e.value)
            }
        }
        println(sum)
    }
}