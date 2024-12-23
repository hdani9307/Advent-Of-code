package advent._2024

import org.junit.jupiter.api.Assertions
import kotlin.test.Ignore
import kotlin.test.Test

class Day12 {

    @Test
    fun part1Test() {
        Assertions.assertEquals(1930, Day12Part1().solve("2024/12.sample.txt"))
    }

    @Test
    fun part2Test() {
        Assertions.assertEquals(1206, Day12Part2().solve("2024/12.sample.txt"))
    }

    @Test
    @Ignore
    fun part1() {
        Assertions.assertEquals(1477762, Day12Part1().solve("2024/12.txt"))
    }

    @Test
    @Ignore
    fun part2() {
        Assertions.assertEquals(923480, Day12Part2().solve("2024/12.txt"))
    }
}