import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class Puzzle2 {
    private static File input = new File("C:\\Users\\Alex\\IdeaProjects\\AdventOfCode2022\\src\\Puzzle2Input.txt");

    enum Choice {
        Rock(1),
        Paper(2),
        Scissors(3);

        private int score;

        Choice(int score) {
            this.score = score;
        }

        static public Choice toChoice(String s) {
            char c = s.toLowerCase().charAt(0);
            if (c == 'a' || c == 'x') {
                return Rock;
            } else if (c == 'b' || c == 'y') {
                return Paper;
            } else if (c == 'c' || c == 'z') {
                return Scissors;
            }

            throw new RuntimeException("Unknown Input: " + s);
        }

        static public Choice fromScore(int score) {
            for (Choice c: Choice.values()) {
                if (c.getScore() == score) {
                    return c;
                }
            }
            throw new RuntimeException("Unknown Input: " + score);
        }

        public int getScore() {
            return score;
        }
    }

    enum Result {
        Lose(0),
        Draw(3),
        Win(6);

        private int score;

        Result(int score) {
            this.score = score;
        }

        static public Result toResult(String s) {
            char c = s.toLowerCase().charAt(0);
            if (c == 'a' || c == 'x') {
                return Lose;
            } else if (c == 'b' || c == 'y') {
                return Draw;
            } else if (c == 'c' || c == 'z') {
                return Win;
            }

            throw new RuntimeException("Unknown Input: " + s);
        }

        public int getScore(){
            return score;
        }
    }

    private static Result[][] lookuptable = {
                    /*  Rock,        Paper,      Scissors */
        /* Rock */     {Result.Draw, Result.Win, Result.Lose},
        /* Paper */    {Result.Lose, Result.Draw, Result.Win},
        /* Scissors */ {Result.Win, Result.Lose, Result.Draw},
    };

    static Result battle(Choice theirs, Choice ours) {
        return lookuptable[theirs.getScore()-1][ours.getScore()-1];
    }

    static Choice decide(Choice theirs, Result result) {
        Result[] options = lookuptable[theirs.getScore() - 1];

        for (int i = 1; i <= 3; i++) {
            if (options[i-1].equals(result)) {
                return Choice.fromScore(i);
            }
        }

        throw new RuntimeException("Unknown Input: theirs: " + theirs.name() + ", result: " + result.name());
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(input.toPath());

        int totalScore = 0;

        for (String line: lines) {
            String[] choices = line.split(" ");
            Choice theirs = Choice.toChoice(choices[0]);
            Result r = Result.toResult(choices[1]);
            //Choice ours = Choice.toChoice(choices[1]);
            Choice ours = decide(theirs, r);

            totalScore += (ours.getScore() + r.getScore());
        }

        System.out.println("Total: " + totalScore);
    }
}
