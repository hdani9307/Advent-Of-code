package advent._2024

import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class Day11 {

    @Test
    fun part1Test() {
        Assertions.assertEquals(55312, Day11Part1().solve("2024/11.sample.txt", 25))
    }


    @Test
    fun part1() {
        Assertions.assertEquals(203228, Day11Part1().solve("2024/11.txt", 25))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(1483, Day11Part1().solve("2024/11.txt", 75))
    }
}