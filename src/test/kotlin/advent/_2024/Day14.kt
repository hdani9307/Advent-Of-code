package advent._2024

import org.junit.jupiter.api.Assertions
import kotlin.test.Ignore
import kotlin.test.Test

class Day14 {

    @Test
    fun part1Test() {
        Assertions.assertEquals(12, Day14Part1().solve("2024/14.sample.txt", 7, 11, 100))
    }

    @Test
    @Ignore
    fun part1() {
        Assertions.assertEquals(218619120, Day14Part1().solve("2024/14.txt", 103, 101, 100))
    }

    @Test
    @Ignore
    fun part2() {
        Assertions.assertEquals(
            7055,
            Day14Part2().solve("2024/14.txt", 103, 101, Integer.MAX_VALUE)
        )
    }
}