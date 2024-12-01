import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class p2 {
    public static void main(String[] args) {
        String filepath = "src/day1/day1.text";  // Ensure this path is correct

        int counter = 0;
        try {
            // Read the lines from the file
            List<String> lines = Files.readAllLines(Paths.get(filepath));

            // Count the number of lines
            counter = lines.size();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total lines: " + counter);

        // Arrays to hold the numbers
        int array1[] = new int[counter];
        int array2[] = new int[counter];

        int k = 0;
        try {
            // Read the lines from the file again
            List<String> lines = Files.readAllLines(Paths.get(filepath));

            for (String line : lines) {
                // Trim leading/trailing spaces and split by one or more spaces
                String[] split = line.trim().split("\\s+");  // Using regex to split by one or more spaces

                // Check if the line has exactly two parts
                if (split.length != 2) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;  // Skip lines that don't have exactly two parts
                }

                String word = split[0];  // First part (word)
                String pos = split[1];   // Second part (pos)

                try {
                    // Convert these parts to integers directly
                    array1[k] = Integer.parseInt(word); // Convert word to int
                    array2[k] = Integer.parseInt(pos);  // Convert pos to int
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in line: " + line);
                    continue;  // Skip invalid lines that can't be parsed to integers
                }

                // Print the arrays for verification
                System.out.println("Left Array: " + array1[k]);
                System.out.println("Right Array: " + array2[k]);

                k++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
