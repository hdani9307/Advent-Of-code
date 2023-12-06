package advent;


import advent._2023.*;

public class Main {
    public static void main(String[] args) {
        var now = System.currentTimeMillis();
        try {

            new Day5Part2().run();
        } catch (Exception e) {

        } finally {
            var end = System.currentTimeMillis() - now;
            System.out.println("Run sec " + end / 1000d);
        }
    }
}
