package advent._2024

import org.junit.jupiter.api.Assertions
import kotlin.test.Ignore
import kotlin.test.Test

class Day13 {

    @Test
    fun part1Test() {
        Assertions.assertEquals(480, Day13Part1().solve("2024/13.sample.txt"))
    }

    @Test
    fun part2Test() {
        Assertions.assertEquals(875318608908, Day13Part2().solve("2024/13.sample.txt"))
    }

    @Test
    @Ignore
    fun part1() {
        Assertions.assertEquals(37901, Day13Part1().solve("2024/13.txt"))
    }

    @Test
    @Ignore
    fun part2() {
        Assertions.assertEquals(77407675412647, Day13Part2().solve("2024/13.txt"))
    }
}