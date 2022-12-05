import org.apache.commons.lang3.Range;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class Puzzle4 {
    private static File input = new File("C:\\Users\\Alex\\IdeaProjects\\AdventOfCode2022\\src\\Puzzle4Input.txt");

    static Range<Integer> toRange(String s) {
        String[] values = s.split("-");
        return Range.between(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

    static void part1(List<String> lines) {
        int count = 0;
        for(String line: lines) {
            String[] pair = line.split(",");
            Range range1 = toRange(pair[0]);
            Range range2 = toRange(pair[1]);

            if (range1.containsRange(range2) || range2.containsRange(range1)) {
                count++;
            }
        }

        System.out.println("Part 1: " + count);
    }

    static void part2(List<String> lines) {
        int count = 0;
        for(String line: lines) {
            String[] pair = line.split(",");
            Range range1 = toRange(pair[0]);
            Range range2 = toRange(pair[1]);

            if (range1.isOverlappedBy(range2) || range2.isOverlappedBy(range1)) {
                count++;
            }
        }

        System.out.println("Part 2: " + count);
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(input.toPath());

        part1(lines);
        part2(lines);
    }
}
