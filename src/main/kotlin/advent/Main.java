package advent;


import advent._2023._day7.part1.Day7Part1;
import advent._2023._day7.part2.Day7Part2;

public class Main {
    public static void main(String[] args) {
        var now = System.currentTimeMillis();
        try {
            new Day7Part2().run();
        } finally {
            var end = System.currentTimeMillis() - now;
            System.out.println("Run sec " + end / 1000d);
        }
    }
}
