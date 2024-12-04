import java.io.*;
import java.util.*;
import java.util.regex.*;

public class day4 {
    private static int countMatches(String text, String pattern) {
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
    private static int findXMASInRow(String[] grid, String word, String reverseWord) {
        int count = 0;
        for (String row : grid) {
            count += countMatches(row, word); 
            count += countMatches(row, reverseWord); 
        }
        return count;
    }
    private static int findXMASInColumn(String[] grid, int rows, int cols, String word, String reverseWord) {
        int count = 0;
        for (int c = 0; c < cols; c++) {
            StringBuilder col = new StringBuilder();
            for (int r = 0; r < rows; r++) {
                col.append(grid[r].charAt(c));
            }
            String column = col.toString();
            count += countMatches(column, word); 
            count += countMatches(column, reverseWord); 
        }
        return count;
    }
    private static int findXMASInDiagonals(String[] grid, int rows, int cols, String word, String reverseWord) {
        int count = 0;
        for (int r = 0; r <= rows - word.length(); r++) {
            for (int c = 0; c <= cols - word.length(); c++) {
                StringBuilder diagonal = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    diagonal.append(grid[r + i].charAt(c + i));
                }
                String diagonalStr = diagonal.toString();
                count += countMatches(diagonalStr, word); 
                count += countMatches(diagonalStr, reverseWord); 
            }
        }
        for (int r = 0; r <= rows - word.length(); r++) {
            for (int c = word.length() - 1; c < cols; c++) {
                StringBuilder diagonal = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    diagonal.append(grid[r + i].charAt(c - i));
                }
                String diagonalStr = diagonal.toString();
                count += countMatches(diagonalStr, word); 
                count += countMatches(diagonalStr, reverseWord); 
            }
        }

        return count;
    }
    public static String[] readGridFromFile(String filePath) throws IOException {
        List<String> gridList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                gridList.add(line);
            }
        }
        return gridList.toArray(new String[0]);
    }
    public static void main(String[] args) {
        String filePath = "day4.txt"; 
        String[] grid = null;
        try {
            grid = readGridFromFile(filePath);
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
            return;
        }
        String word = "XMAS";
        String reverseWord = new StringBuilder(word).reverse().toString();
        int rows = grid.length;
        int cols = grid[0].length();
        int horizontalCount = findXMASInRow(grid, word, reverseWord);
        int verticalCount = findXMASInColumn(grid, rows, cols, word, reverseWord);
        int diagonalCount = findXMASInDiagonals(grid, rows, cols, word, reverseWord);
        int totalCount = horizontalCount + verticalCount + diagonalCount;
        System.out.println("Total- " + totalCount);
    }
}