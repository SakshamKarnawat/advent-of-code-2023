import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Day1Part1 {
    public static void main(String[] args) throws Exception {

        AtomicInteger sum = new AtomicInteger(0);
        try (Stream<String> lines = Files.lines(Paths.get("src/input.txt"), Charset.defaultCharset())) {
            lines.forEachOrdered(line -> {

                char[] charArray = line.toCharArray();
                int left = 0;
                int right = 0;

                for (char c : charArray) {
                    if (Character.isDigit(c)) {
                        left = Character.getNumericValue(c);
                        break;
                    }
                }

                for (int i = charArray.length - 1; i >= 0; i--) {
                    if (Character.isDigit(charArray[i])) {
                        right = Character.getNumericValue(charArray[i]);
                        break;
                    }
                }
                sum.set(sum.get() + (left * 10) + right);
            });
        } catch (Exception e) {
            System.out.println("Error reading the file");
        } finally {
            System.out.printf("Sum is: %d\n", sum.get());
        }
    }
}
