import javax.xml.stream.events.Characters;
import java.io.File;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Puzzle3 {
    private static File input = new File("C:\\Users\\Alex\\IdeaProjects\\AdventOfCode2022\\src\\Puzzle3Input.txt");

    static Set<Character> findCommonCharacters(String input1, String input2) {
        Set<Character> input1Chars = new HashSet<>();
        Set<Character> commonChars = new HashSet<>();


        for(char c: input1.toCharArray()){
            input1Chars.add(c);
        }

        for(char c: input2.toCharArray()){
            if (input1Chars.contains(c)) {
                commonChars.add(c);
            }
        }
        return commonChars;
    }

    static Set<Character> findCommonCharacters(Set<String> group) {
        Object[] groupMembers = group.toArray();
        Set<Character> partialCommon1 = findCommonCharacters((String) groupMembers[0], (String) groupMembers[1]);
        Set<Character> partialCommon2 = findCommonCharacters((String) groupMembers[1], (String) groupMembers[2]);

        partialCommon1.retainAll(partialCommon2);
        return partialCommon1;
    }

    static int getPriority(char c) {
        if ('a' <= c && c <= 'z') {
            return c - 'a' + 1;
        }
        if ('A' <= c && c <= 'Z') {
            return (c - 'A' + 1) + 26;
        }
        throw new RuntimeException("Unknown Character: " + c);
    }

    static void part1(List<String> lines) {
        int sum = 0;

        for (String l: lines) {
            int midpoint = l.length() / 2;
            String part1 = l.substring(0, midpoint);
            String part2 = l.substring(midpoint, l.length());

            Set<Character> inCommon = findCommonCharacters(part1, part2);

            for (Character c: inCommon) {
                int priority = getPriority(c);
                sum += priority;
                System.out.println("Char: " + c + ", Priority: " + priority);
            }
        }

        System.out.println("Part 1: " + sum);
    }

    static void part2(List<String> lines) {
        Set<String> group = new HashSet<>();

        int sum = 0;
        for (String line: lines) {
            group.add(line);

            if (group.size() == 3) {
                Set<Character> badge = findCommonCharacters(group);

                for (char c: badge) {
                    sum += getPriority(c);
                }
                group.clear();
            }
        }

        System.out.println("Part 2: " + sum);
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(input.toPath());

        part1(lines);
        part2(lines);
    }
}
