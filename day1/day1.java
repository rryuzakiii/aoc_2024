import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class p1 {

    public int find(int number, int arr[]) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        String filepath = "day1.text";

        p1 p = new p1();

        int counter = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            counter = lines.size();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total lines: " + counter);

        int array1[] = new int[counter];
        int array2[] = new int[counter];

        int k = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));

            for (String line : lines) {
                String[] split = line.trim().split("\\s+");

                if (split.length != 2) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String word = split[0];
                String pos = split[1];

                try {
                    array1[k] = Integer.parseInt(word);
                    array2[k] = Integer.parseInt(pos);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in line: " + line);
                    continue;
                }
                k++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int sum = 0;
        for (int i = 0; i < array1.length; i++) {
            sum += array1[i] * p.find(array1[i], array2);
        }

        System.out.println(sum);
    }
}