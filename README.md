# Advent of Code

![Tests](https://github.com/hdani9307/Advent-of-Code/actions/workflows/github-actions.yml/badge.svg)

If you are looking for clean code it is not your place ¯\\_(ツ)_/¯

## Template for new classes

```kotlin

import advent.helper.readInput
import advent.helper.runMeasured
import advent.helper.toMatrix

class DayXPartX {
    fun solve(fileName: String): Int {
        val input = readInput(fileName)

        var sum = 0
        runMeasured {

        }
        return sum
    }
}
```

## Template for test

```kotlin

import org.junit.jupiter.api.Assertions
import kotlin.test.Ignore
import kotlin.test.Test

class DayX {

    @Test
    fun part1Test() {
        Assertions.assertEquals(0, DayXPart1().solve("2024/X.sample.txt"))
    }

    @Test
    fun part2Test() {
        Assertions.assertEquals(0, DayXPart2().solve("2024/X.sample.txt"))
    }

    @Test
    @Ignore
    fun part1() {
        Assertions.assertEquals(0, DayXPart1().solve("2024/X.txt"))
    }

    @Test
    @Ignore
    fun part2() {
        Assertions.assertEquals(0, DayXPart2().solve("2024/X.txt"))
    }
}
```