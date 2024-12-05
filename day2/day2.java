import java.util.*;
import java.nio.file.*;
import java.io.IOException;


public class p10 {

    public static boolean isSafe(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            int diff = Math.abs(report.get(i + 1) - report.get(i));
            if (diff < 1 || diff > 3) {
                return false;
            }
        }


        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i) >= report.get(i + 1)) {
                isIncreasing = false;
            }
            if (report.get(i) <= report.get(i + 1)) {
                isDecreasing = false;
            }
        }

        return isIncreasing || isDecreasing;
    }


    public static int countSafeReports(List<String> data) {
        int safeCount = 0;

        for (String line : data) {
            List<Integer> report = new ArrayList<>();
            for (String num : line.split("\\s+")) {
                report.add(Integer.parseInt(num));
            }

            if (isSafe(report)) {
                safeCount++;
            }
        }

        return safeCount;
    }

    public static void main(String[] args) {
        String filepath = "src/day2/day2";

        try {
            List<String> inputData = Files.readAllLines(Paths.get(filepath));

            int safeCount = countSafeReports(inputData);
            System.out.println("Number of safe reports: " + safeCount);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}