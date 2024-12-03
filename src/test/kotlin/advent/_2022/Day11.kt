package advent._2022

class Day11 {
    fun run() {
        var monkeys = listOf(
            Monkey(
                items = mutableListOf(52, 78, 79, 63, 51, 94),
                divisor = 5,
                testTrueThrow = 1,
                testFalseThrow = 6,
            ) {
                it * 13uL
            },
            Monkey(
                items = mutableListOf(77, 94, 70, 83, 53),
                divisor = 7,
                testTrueThrow = 5,
                testFalseThrow = 3,
            ) {
                it + 3uL
            },
            Monkey(
                items = mutableListOf(98, 50, 76),
                divisor = 13,
                testTrueThrow = 0,
                testFalseThrow = 6,
            ) {
                it * it
            },
            Monkey(
                items = mutableListOf(92, 91, 61, 75, 99, 63, 84, 69),
                divisor = 11,
                testTrueThrow = 5,
                testFalseThrow = 7,
            ) {
                it + 5uL
            },
            Monkey(
                items = mutableListOf(51, 53, 83, 52),
                divisor = 3,
                testTrueThrow = 2,
                testFalseThrow = 0,
            ) {
                it + 7uL
            },
            Monkey(
                items = mutableListOf(76, 76),
                divisor = 2,
                testTrueThrow = 4,
                testFalseThrow = 7,
            ) {
                it + 4uL
            },
            Monkey(
                items = mutableListOf(75, 59, 93, 69, 76, 96, 65),
                divisor = 17,
                testTrueThrow = 1,
                testFalseThrow = 3,
            ) {
                it * 19uL
            },
            Monkey(
                items = mutableListOf(89),
                divisor = 19,
                testTrueThrow = 2,
                testFalseThrow = 4,
            ) {
                it + 2uL
            }
        )
        var commonMultiply = monkeys.map { it.divisor }.reduce { a, b -> a * b }.toULong()
        for (round in 0 until 10000) {
            for (monkey in monkeys) {
                val listIterator = monkey.items2.listIterator()
                for (item in listIterator) {
                    monkey.inspections++
                    var worryLevel = monkey.operation(item)
                    worryLevel %= commonMultiply
                    if (worryLevel.mod(monkey.divisor.toULong()) == 0uL) {
                        monkeys[monkey.testTrueThrow].items2.add(worryLevel)
                    } else {
                        monkeys[monkey.testFalseThrow].items2.add(worryLevel)
                    }
                    listIterator.remove()
                }
            }
        }
        for (monkey in monkeys.withIndex()) {
            println("Monkey ${monkey.index + 1} inspected items ${monkey.value.inspections} times.")
        }
        val top2Monkey = monkeys.sortedBy { it.inspections }.reversed().subList(0, 2)
        println(top2Monkey)
        println(top2Monkey[0].inspections * top2Monkey[1].inspections)
        //2067283968 wrong
        //14952185856
    }
}

data class Monkey(
    val items: MutableList<Long>,
    val divisor: Int,
    val testTrueThrow: Int,
    val testFalseThrow: Int,
    var inspections: ULong = 0uL,
    val operation: (ULong) -> ULong
) {
    val items2 = items.map { it.toULong() }.toMutableList()
}