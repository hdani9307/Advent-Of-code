package advent._2022.day;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day13Java {
    public static void day13() throws IOException {
        try (var reader = new BufferedReader(new FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent//resources/day13.txt"))) {
            String[] packets = reader.lines()
                    .filter(line -> line.startsWith("["))
                    .toArray(String[]::new);

            int answerPartOne = 0;
            List<PDU> broadcast = new ArrayList<>();
            for (int i = 0, pairIdx = 1; i < packets.length - 1; i += 2, pairIdx++) {
                PDU left = new PDU(packets[i]);
                PDU right = new PDU(packets[i + 1]);
                broadcast.addAll(List.of(left, right));
                answerPartOne += left.compareTo(right) > 0 ? pairIdx : 0;
            }
            System.out.println(answerPartOne);

            PDU separator1 = new PDU("[[2]]");
            PDU separator2 = new PDU("[[6]]");
            broadcast.addAll(List.of(separator1, separator2));
            Collections.sort(broadcast);
            Collections.reverse(broadcast);
            int answerPartTwo = (broadcast.indexOf(separator1) + 1) * (broadcast.indexOf(separator2) + 1);
            //System.out.println(answerPartTwo);
        }
    }
}
