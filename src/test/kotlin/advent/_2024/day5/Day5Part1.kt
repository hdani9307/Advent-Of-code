import advent.helper.readInput
import advent.helper.runMeasured
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class Day5Part1 {
    @Test
    fun run() {
        val lines = readInput("2024/5.txt")
        runMeasured {
            val corrects = mutableListOf<String>()
            val x = mutableMapOf<String, MutableList<String>>()
            val y = mutableMapOf<String, MutableList<String>>()
            for (line in lines) {
                if (line.contains("|")) {
                    val split = line.split("|")
                    val key = split[0].trim()
                    val value = split[1].trim()
                    x.putIfAbsent(key, mutableListOf())
                    x[key]?.add(value)

                    y.putIfAbsent(value, mutableListOf())
                    y[value]?.add(key)
                } else if (line.isEmpty()) {
                    continue
                } else {
                    val split = line.split(",")
                    var correct = true
                    for (i in split.withIndex()) {
                        val value = i.value.trim()
                        val children = x[value]
                        val parents = y[value]
                        for (j in split.withIndex()) {
                            if (j.index < i.index) {
                                if (parents == null) {
                                    continue
                                }
                                if (!parents.contains(j.value)) {
                                    correct = false
                                }
                            }
                            if (j.index > i.index) {
                                if (children == null) {
                                    continue
                                }
                                if (!children.contains(j.value)) {
                                    correct = false
                                }
                            }

                        }
                    }
                    if (correct) {
                        corrects.add(line)
                    }
                }
            }
            var sum = 0
            for (c in corrects) {
                val split = c.split(",")
                val middle = split[(split.size - 1) / 2]
                sum += middle.toInt()
            }
            Assertions.assertEquals(4578, sum)
        }
    }
}