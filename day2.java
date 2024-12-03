import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class day2 {

    // Check if a report is safe
    public static boolean isSafe(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            int diff = Math.abs(report.get(i + 1) - report.get(i));
            if (diff < 1 || diff > 3) {
                return false; // Adjacent levels' difference is invalid
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

        return isIncreasing || isDecreasing; // Must be monotonic
    }

    // Check if a report can be made safe by removing one level
    public static boolean canBeMadeSafe(List<Integer> report) {
        // If the report is already safe
        if (isSafe(report)) {
            return true;
        }

        // Try removing each level one at a time
        for (int i = 0; i < report.size(); i++) {
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i); // Remove the i-th level
            if (isSafe(modifiedReport)) {
                return true; // If any modification makes it safe
            }
        }

        return false; // None of the modifications made it safe
    }

    // Count the number of safe reports considering the Problem Dampener
    public static int countSafeReportsWithDampener(List<String> data) {
        int safeCount = 0;

        for (String line : data) {
            List<Integer> report = new ArrayList<>();
            for (String num : line.split("\\s+")) {
                report.add(Integer.parseInt(num)); // Parse numbers
            }

            // Check if the report is safe or can be made safe
            if (canBeMadeSafe(report)) {
                safeCount++;
            }
        }

        return safeCount;
    }

    public static void main(String[] args) {
        String filepath = "day2.txt";

        try {
            List<String> inputData = Files.readAllLines(Paths.get(filepath));
            int safeCount = countSafeReportsWithDampener(inputData);
            System.out.println("Number of safe reports: " + safeCount);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
