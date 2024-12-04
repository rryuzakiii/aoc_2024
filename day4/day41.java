import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class day41 {
    final static String inputFile = "day4.txt";

    public static void main(String... args) throws IOException {
        new day41().run();
    }

    void run() throws IOException {
        var lines = Files.readAllLines(Paths.get(inputFile), StandardCharsets.UTF_8);
        var input = parseInput(lines);
        var result = countxmas(input);
        
        System.out.println("x shaped mas " + result);
    }

    private char[][] parseInput(List<String> lines) {
        int rows = lines.size();
        char[][] grid = new char[rows][lines.get(0).length()];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }
        return grid;
    }

    private int countxmas(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 1; row < rows - 1; row++) { 
            for (int col = 1; col < cols - 1; col++) { 
                count += check(grid, row, col);
            }
        }

        return count;
    }

    private int check(char[][] grid, int row, int col) {
        int count = 0;

        if (grid[row][col] == 'A') {
            if (Inbounds(grid, row - 1, col - 1) && Inbounds(grid, row - 1, col + 1) &&
                    Inbounds(grid, row + 1, col - 1) && Inbounds(grid, row + 1, col + 1)) {
                if (grid[row - 1][col - 1] == 'M' && grid[row + 1][col + 1] == 'S' &&
                        grid[row + 1][col - 1] == 'M' && grid[row - 1][col + 1] == 'S') {
                    count++;
                }

                if (grid[row - 1][col - 1] == 'M' && grid[row + 1][col + 1] == 'S' &&
                        grid[row + 1][col - 1] == 'S' && grid[row - 1][col + 1] == 'M') {
                    count++;
                }

                if (grid[row - 1][col - 1] == 'S' && grid[row + 1][col + 1] == 'M' &&
                        grid[row + 1][col - 1] == 'M' && grid[row - 1][col + 1] == 'S') {
                    count++;
                }

                if (grid[row - 1][col - 1] == 'S' && grid[row + 1][col + 1] == 'M' &&
                        grid[row + 1][col - 1] == 'S' && grid[row - 1][col + 1] == 'M') {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean Inbounds(char[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
