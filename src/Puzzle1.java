import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle1 {
    private static File input = new File("C:\\Users\\Alex\\IdeaProjects\\AdventOfCode2022\\src\\Puzzle1Input.txt");

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(input.toPath());
        List<Long> elves = new ArrayList<>();

        long currMax = 0;

        for (String line: lines) {
            if (line.length() == 0) {
                elves.add(currMax);
                currMax = 0;
                continue;
            }
            long value = Integer.parseInt(line);
            currMax += value;
        }

        elves.sort(Collections.reverseOrder());

        long sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += elves.get(i);
        }
        System.out.println(sum);
    }
}