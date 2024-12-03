import java.util.regex.*;
import java.io.*;
public class regex{
    public static void main(String [] args){
       
            String input = readFile("day3.txt"); 
            if (input.isEmpty()) {
                System.out.println("Input file is empty!");
                return;
            }


            String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
         boolean found = matcher.find();

         System.out.println("found "+ found);
    }

}