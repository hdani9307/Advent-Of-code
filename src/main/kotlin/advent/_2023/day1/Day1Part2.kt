package advent._2023.day1

import advent.helper.readInput
import advent.helper.runMeasured

val corrections = mapOf(
    Pair("one", "1"),
    Pair("two", "2"),
    Pair("three", "3"),
    Pair("four", "4"),
    Pair("five", "5"),
    Pair("six", "6"),
    Pair("seven", "7"),
    Pair("eight", "8"),
    Pair("nine", "9")
);
val initials = listOf("o", "t", "f", "s", "e", "n")

fun main() {

    val lines = readInput("1.txt")
    runMeasured {
        var sum = 0
        for (line in lines) {
            val numbers = mutableListOf<String>()
            for (a in line.toList().withIndex()) {
                if (a.value.isDigit()) {
                    numbers.add(a.value.toString())
                } else {
                    findNumber(line.toList(), a.index, a.value.toString())?.let {
                        numbers.add(it)
                    }
                }
            }
            sum += (numbers[0] + numbers[numbers.size - 1]).toInt()
        }
        println(sum)
    }
}

private fun findNumber(line: List<Char>, index: Int, current: String): String? {
    if (current.length == 1 && !initials.contains(current)) {
        return null
    }
    if (corrections.containsKey(current)) {
        return corrections[current]
    } else {
        if (index + 1 >= line.size) {
            return null
        }
        val next = line[index + 1]
        if (next.isDigit()) {
            return null
        }
        return findNumber(line, index + 1, current + next)
    }
}