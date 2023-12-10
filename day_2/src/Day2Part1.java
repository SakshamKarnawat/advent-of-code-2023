import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day2Part1 {
    // * Color limits:
    static int redLimit = 12;
    static int greenLimit = 13;
    static int blueLimit = 14;

    public static void main(String[] args) throws Exception {
        // * Using AtomicInteger (can't use a non final variable in a lambda function)
        AtomicInteger gameIDTotal = new AtomicInteger(0);
        try (Stream<String> lines = Files.lines(Paths.get("src/input.txt"), Charset.defaultCharset())) {
            // * Iterating over each line:
            lines.forEachOrdered(line -> {
                // * Red, green and blue counters:
                int red = 0;
                int green = 0;
                int blue = 0;

                // * Splitting the line:
                String[] st = line.split(":");
                String gameIDString = st[0].replaceAll(" ", "").split("Game")[1];
                String[] sets = st[1].split(";");

                // * Reading game ID:
                int gameID = Integer.parseInt(gameIDString);

                // * Iterating over sets of a game:
                for (int i = 0; i < sets.length; i++) {
                    // * Trimming a set:
                    sets[i] = sets[i].trim();

                    // * Defining patterns to match --> [int] red, green and blue
                    Pattern redPattern = Pattern.compile("\\b\\d+ red\\b");
                    Pattern greenPattern = Pattern.compile("\\b\\d+ green\\b");
                    Pattern bluePattern = Pattern.compile("\\b\\d+ blue\\b");

                    // * Matching a set with the patterns:
                    Matcher redMatcher = redPattern.matcher(sets[i]);
                    Matcher greenMatcher = greenPattern.matcher(sets[i]);
                    Matcher blueMatcher = bluePattern.matcher(sets[i]);

                    // * Initialising red, green and blue counters:
                    while (redMatcher.find()) {
                        red = Integer.parseInt(redMatcher.group(0).split("red")[0].trim());
                    }
                    while (greenMatcher.find()) {
                        green = Integer.parseInt(greenMatcher.group(0).split("green")[0].trim());
                    }
                    while (blueMatcher.find()) {
                        blue = Integer.parseInt(blueMatcher.group(0).split("blue")[0].trim());
                    }

                    // * If any set of a game exceeds color limit, skip that game ID:
                    if (red > redLimit || green > greenLimit || blue > blueLimit) {
                        return;
                    }

                }
                // * Since no set of that game exceeded color limit, add that game ID to total:
                gameIDTotal.set(gameIDTotal.get() + gameID);
            });
            // * Print total sum of valid gameIDs:
            System.out.printf("Total of gameIDs is: %d\n", gameIDTotal.get());
        }
    }
}
