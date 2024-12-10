package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured

class Day5Part1 {

    fun run(): Long {
        val lines = readInput("2024/5.txt")
        var sum = 0L
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
                        if (!correct) {
                            break
                        }
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
            for (c in corrects) {
                val split = c.split(",")
                val middle = split[(split.size - 1) / 2]
                sum += middle.toInt()
            }
        }
        return sum
    }
}

class Day5Part2 {

    fun run(): Long {
        val lines = readInput("2024/5.txt")
        var sum = 0L
        runMeasured {
            val notCorrects = mutableListOf<String>()
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
                        if (!correct) {
                            break
                        }
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
                    if (!correct) {
                        notCorrects.add(line)
                    }
                }
            }
            val ordered = mutableListOf<List<String>>()
            for (notCorrect in notCorrects) {
                notCorrect.split(",").sortedWith(Comparator { o1, o2 ->
                    val child = x[o1]?.contains(o2) ?: false
                    return@Comparator if (child) {
                        1
                    } else {
                        -1
                    }
                }).also { ordered.add(it) }
            }

            for (list in ordered) {
                val middle = list[(list.size - 1) / 2]
                sum += middle.toInt()
            }
        }
        return sum
    }
}