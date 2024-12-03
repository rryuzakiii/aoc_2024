import java.util.regex.*;
import java.io.*;

public class day3 {
    public static void main(String[] args) {
        try {
            
            String input = readFile("day3.txt"); 
            if (input.isEmpty()) {
                System.out.println("Input file is empty!");
                return;
            }


            String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);


            int sum = 0;

            
            while (matcher.find()) {
                int x = Integer.parseInt(matcher.group(1)); 
                int y = Integer.parseInt(matcher.group(2)); 
                sum += x * y; 
            }

            
            System.out.println("Sum of all valid mul instructions: " + sum);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers: " + e.getMessage());
        }
    }
    private static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {


                sb.append(line);
            }
        }
        return sb.toString();
    }
}
