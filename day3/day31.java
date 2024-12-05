import java.util.regex.*;
import java.nio.file.*;
import java.io.IOException;

public class day31 {
    public static void main(String[] args) throws IOException {
        String memory = Files.readString(Path.of("input.dat"));

        Pattern instructionPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = instructionPattern.matcher(memory);

        boolean enabled = true;
        int totalSum = 0;

        while (matcher.find()) {
            String match = matcher.group();
            if (match.startsWith("mul")) {
                if (enabled) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    totalSum += x * y;
                }
            } else if (match.equals("do()")) {
                enabled = true;
            } else if (match.equals("don't()")) {
                enabled = false;
            }
        }

        System.out.println(totalSum);
    }
}
