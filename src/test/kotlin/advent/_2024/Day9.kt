package advent._2024

import org.junit.jupiter.api.Assertions
import kotlin.test.Ignore
import kotlin.test.Test

class Day9 {

    @Test
    fun part1Sample() {
        Assertions.assertEquals(1928, Day9Part1().run("2024/9.sample.txt"))
    }

    @Test
    fun part2Sample() {
        Assertions.assertEquals(2858, Day9Part2().run("2024/9.sample.txt"))
    }

    @Test
    @Ignore
    fun part1() {
        Assertions.assertEquals(6299243228569, Day9Part1().run("2024/9.txt"))
    }

    @Test
    @Ignore
    fun part2() {
        Assertions.assertEquals(6326952672104, Day9Part2().run("2024/9.txt"))
    }

}