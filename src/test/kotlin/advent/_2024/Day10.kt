package advent._2024

import org.junit.jupiter.api.Assertions
import kotlin.test.Ignore
import kotlin.test.Test

class Day10 {

    @Test
    fun part1Test() {
        Assertions.assertEquals(36, Day10Part1().run("2024/10.sample.txt"))
    }

    @Test
    fun part2Test() {
        Assertions.assertEquals(81, Day10Part2().run("2024/10.sample.txt"))
    }

    @Test
    @Ignore
    fun part1() {
        Assertions.assertEquals(624, Day10Part1().run("2024/10.txt"))
    }

    @Test
    @Ignore
    fun part2() {
        Assertions.assertEquals(1483, Day10Part2().run("2024/10.txt"))
    }
}