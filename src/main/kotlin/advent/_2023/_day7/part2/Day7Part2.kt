package advent._2023._day7.part2

import advent.Day
import java.lang.Exception
import java.util.TreeSet
import java.util.concurrent.atomic.AtomicInteger

val cardList = listOf("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J")
    .reversed()

interface Hand {
    fun evaluate(hand: String): Int
}

class FiveOfAKind : Hand {

    private val toRegex: Regex = "^(.)\\1*\$".toRegex()

    override fun evaluate(hand: String): Int {
        return if (toRegex.matches(hand)) {
            6
        } else {
            0
        }
    }
}

class FourOfAKind : Hand {

    override fun evaluate(hand: String): Int {
        val pairs = mutableSetOf<Char>()
        for (c in hand.toCharArray()) {
            if (hand.count { it == c } == 4) {
                pairs.add(c)
            }
        }

        return if (pairs.size == 1) {
            5
        } else {
            0
        }
    }
}

class FullHouse : Hand {

    override fun evaluate(hand: String): Int {
        var _3 = false
        var _2 = false

        for (c in hand.toCharArray()) {
            val count = hand.count { it == c }
            if (count == 3) {
                _3 = true
            }
            if (count == 2) {
                _2 = true
            }
        }

        return if (_3 && _2) {
            4
        } else {
            0
        }
    }
}

class ThreeOfAKind : Hand {

    override fun evaluate(hand: String): Int {
        var _3 = false
        var _2 = 0

        for (c in hand.toCharArray()) {
            val count = hand.count { it == c }
            if (count == 3) {
                _3 = true
            }
            if (count == 2) {
                _2++
            }
        }

        return if (_3 && _2 == 0) {
            3
        } else {
            0
        }
    }
}

class TwoPair : Hand {

    override fun evaluate(hand: String): Int {
        val pairs = mutableSetOf<Char>()
        for (c in hand.toCharArray()) {
            if (hand.count { it == c } == 2) {
                pairs.add(c)
            }
        }

        return if (pairs.size == 2) {
            2
        } else {
            0
        }
    }
}

class OnePair : Hand {

    override fun evaluate(hand: String): Int {
        for (c in hand.toCharArray()) {
            if (hand.count { it == c } == 2) {
                return 1
            }
        }

        return 0
    }
}

class HighCard {
    fun evaluate(hand1: String, hand2: String): Int {
        val toCharArray = hand1.toCharArray()
        for ((index, c) in toCharArray.withIndex()) {
            val score1 = cardList.indexOf(c.toString())
            val score2 = cardList.indexOf(hand2[index].toString())

            if (score1 != score2) {
                return score1.compareTo(score2)
            }
        }
        return 0
    }
}

class HandComparator : Comparator<String> {
    val handScores = listOf(
        FiveOfAKind(),
        FourOfAKind(),
        FullHouse(),
        ThreeOfAKind(),
        TwoPair(),
        OnePair()
    )
    val highCard = HighCard()

    override fun compare(hand1: String?, hand2: String?): Int {
        if (hand1 == null || hand2 == null) {
            throw Exception()
        }
        if (hand1 == hand2) {
            return 0
        }
        val hand1Score = if (hand1.contains("J")) {
            val maxScore = AtomicInteger(0)
            getJokerScore(hand1, maxScore)
            maxScore.get()
        } else {
            handScores.maxOfOrNull { it.evaluate(hand1) } ?: 0
        }
        val hand2Score = if (hand2.contains("J")) {
            val maxScore = AtomicInteger(0)
            getJokerScore(hand2, maxScore)
            maxScore.get()
        } else {
            handScores.maxOfOrNull { it.evaluate(hand2) } ?: 0
        }

        return if (hand1Score != hand2Score) {
            hand1Score.compareTo(hand2Score)
        } else {
            highCard.evaluate(hand1, hand2)
        }
    }

    private fun getJokerScore(hand1: String, maxScore: AtomicInteger) {
        if (!hand1.contains("J")) {
            return
        }
        for ((index, c) in hand1.toCharArray().withIndex()) {
            if (c == 'J') {
                for (s in cardList) {
                    if (s == "J") {
                        continue
                    }
                    val newHand = hand1.substring(0, index) + s + hand1.substring(index + 1)
                    val score = handScores.maxOfOrNull { it.evaluate(newHand) } ?: 0
                    if (score > maxScore.get()) {
                        maxScore.set(score)
                    }
                    if (maxScore.get() == 6) {
                        return
                    }
                    getJokerScore(newHand, maxScore)
                }
            }
        }
    }
}

class Day7Part2 : Day("7") {

    override fun run() {
        val hands = mutableMapOf<String, Int>()

        val rankList = TreeSet(HandComparator())
        for (line in lines) {
            val split = line.split(" ")
            hands[split[0]] = split[1].toInt()
            rankList.add(split[0])
        }
        println(rankList)
        var result = 0L
        for ((index, s) in rankList.withIndex()) {
            result += hands[s]!! * (index + 1)
        }
        // 253300244 - low
        // 253499763 - ok
        println(result)
    }

}