package advent._2023

import advent.Day
import java.util.PriorityQueue

class Day4Part1 : Day("4") {
    override fun run() {
        var sum = 0
        for (line in lines) {
            var score: Int? = null
            val replaced = line.replace("^Card.*[0-9]*: ".toRegex(), "")
            val (winning, youHave) = replaced.split(" | ")
            val winningInt = mutableListOf<Int>()
            val youHaveInt = mutableListOf<Int>()
            for (win in winning.trim().split(" ")) {
                if (win.isNotEmpty()) {
                    winningInt.add(win.trim().toInt());
                }
            }
            for (have in youHave.trim().split(" ")) {
                if (have.isNotEmpty()) {
                    youHaveInt.add(have.trim().toInt());
                }
            }

            for (win in winningInt) {
                if (youHaveInt.contains(win)) {
                    if (score == null) {
                        score = 1;
                    } else {
                        score *= 2;
                    }
                }
            }
            sum += score ?: 0;
        }
        // 106724 - high
        // 26443 - ok
        println(sum)
    }
}

class CustomerComparator {

    companion object : Comparator<String> {

        override fun compare(a: String, b: String): Int {
            val regex = "^Card +(\\d+): ".toRegex()
            val (groupNumberA) = regex.find(a)!!.destructured
            val (groupNumberB) = regex.find(b)!!.destructured

            return groupNumberA.toInt().compareTo(groupNumberB.toInt())
        }
    }
}

class Day4Part2 : Day("4") {
    override fun run() {
        val queue = PriorityQueue(CustomerComparator)
        for (line in lines) {
            queue.add(line)
        }
        var conter = 0;
        while (!queue.isEmpty()) {
            val regex = "^Card +(\\d+): ".toRegex()
            val line = queue.poll()
            val (groupNumber) = regex.find(line)!!.destructured
            val replaced = line.replace("^Card.*[0-9]*: ".toRegex(), "")
            val (winning, youHave) = replaced.split(" | ")
            val youHaveInt = toIntList(youHave)
            val winningInt = toIntList(winning)

            var wins = 0
            for (win in winningInt) {
                if (youHaveInt.contains(win)) {
                    wins++
                }
            }
           // println("Card $groupNumber will ad")
           // println("-------------------")
            for (i in groupNumber.toInt()..groupNumber.toInt() - 1 + wins) {
               // println(lines[i])
                queue.add(lines[i])
                conter++
            }
           // println("-------------------")
        }

        println(conter + lines.size)
    }

    private fun toIntList(youHave: String): MutableList<Int> {
        val youHaveInt = mutableListOf<Int>()
        for (have in youHave.trim().split(" ")) {
            if (have.isNotEmpty()) {
                youHaveInt.add(have.trim().toInt());
            }
        }
        return youHaveInt
    }
}