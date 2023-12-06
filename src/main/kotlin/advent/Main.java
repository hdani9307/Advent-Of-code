package advent;


import advent._2023.*;

public class Main {
    public static void main(String[] args) {
        var now = System.currentTimeMillis();
        try {
            new Day6Part2().run();
        } finally {
            var end = System.currentTimeMillis() - now;
            System.out.println("Run sec " + end / 1000d);
        }
    }
}
