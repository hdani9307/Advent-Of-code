package advent._2023._day7.part1

import advent.Day
import java.lang.Exception
import java.util.TreeSet

val cardList = listOf("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2")
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
        for (handScore in handScores) {
            val hand1Score = handScore.evaluate(hand1)
            val hand2Score = handScore.evaluate(hand2)
            if (hand1Score != hand2Score) {
                return hand1Score.compareTo(hand2Score)
            }
        }
        return highCard.evaluate(hand1, hand2)
    }
}

class Day7Part1 : Day("7") {

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
        // 252156670 - low
        // 252656917
        println(result)
    }

}