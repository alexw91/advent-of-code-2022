import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle5 {
    private static File input = new File("C:\\Users\\Alex\\IdeaProjects\\AdventOfCode2022\\src\\Puzzle5Input.txt");

    public static class Move {
        public int count;
        public int from;
        public int to;

        public Move() {
        }
    }

    static char[] parseRow(String s) {
//        System.out.println("String s: " + s);

        char[] asciiArtRow = s.toCharArray();
        char[] parsedRow = new char[9];

        int index = 0;
        for(int i = 1; i < asciiArtRow.length; i += 4) {
//            System.out.println("i: " + i + ", index: " + index + ", ascii[i]: " + asciiArtRow[i]);
            parsedRow[index++] = asciiArtRow[i];
        }

        return parsedRow;
    }

    static Stack<Character>[] initalizeStacks(List<String> lines) {
        Stack<Character>[] stacks = new Stack[9];

        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new Stack<>();
        }

        Stack<String> rows = new Stack<>();

        for (String line: lines) {
            if (line.startsWith(" 1   2   3   4   5   6   7   8   9")){
                break;
            }
            rows.push(line);
        }

        while (rows.size() > 0) {
            String nextRow = rows.pop();
            char[] parsedRow = parseRow(nextRow);

            for(int i = 0; i < parsedRow.length; i++) {
                char c = parsedRow[i];
                if (c != ' ') {
                    stacks[i].push(c);
                }
            }
        }

        return stacks;
    }

    static String moveRegex = "move ([0-9]+) from ([0-9]+) to ([0-9]+)";
    static Pattern movePattern = Pattern.compile(moveRegex);

    static List<Move> parseMoves(List<String> lines) {
        List<Move> moves = new ArrayList<>();

        for (String line: lines) {
            if (!line.startsWith("move ")) {
                continue;
            }

            Matcher matcher = movePattern.matcher(line);

            if (matcher.find()) {
//                System.out.println("Group[0]: " + matcher.group(0));
//                System.out.println("Group[1]: " + matcher.group(1));
//                System.out.println("Group[2]: " + matcher.group(2));
//                System.out.println("Group[3]: " + matcher.group(3));

                Move move = new Move();
                move.count = Integer.parseInt(matcher.group(1));
                move.from = Integer.parseInt(matcher.group(2));
                move.to = Integer.parseInt(matcher.group(3));

                moves.add(move);
            } else {
                throw new RuntimeException("No move found for line: " + line);
            }
        }

        return moves;
    }


    static void part1(List<String> lines) {
        Stack<Character>[] stacks = initalizeStacks(lines);
        List<Move> moves = parseMoves(lines);

        for (Move m: moves) {
            for (int i = 0; i < m.count; i++) {
                stacks[m.to-1].push(stacks[m.from-1].pop());
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Stack<Character> s: stacks) {
            builder.append(s.pop());
        }

        System.out.println("Part 1: " + builder.toString());
    }

    static void part2(List<String> lines) {
        Stack<Character>[] stacks = initalizeStacks(lines);
        List<Move> moves = parseMoves(lines);

        for (Move m: moves) {
            Stack<Character> temp = new Stack<>();
            for (int i = 0; i < m.count; i++) {
                temp.push(stacks[m.from-1].pop());
            }
            for (int i = 0; i < m.count; i++) {
                stacks[m.to-1].push(temp.pop());
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Stack<Character> s: stacks) {
            builder.append(s.pop());
        }

        System.out.println("Part 2: " + builder.toString());
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(input.toPath());

        part1(lines);
        part2(lines);
    }
}
