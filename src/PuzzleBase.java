import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class PuzzleBase {
    private static File input = new File("C:\\Users\\Alex\\IdeaProjects\\AdventOfCode2022\\src\\Puzzle4Input.txt");

    static void part1(List<String> lines) {

    }

    static void part2(List<String> lines) {

    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(input.toPath());

        part1(lines);
        part2(lines);
    }
}
