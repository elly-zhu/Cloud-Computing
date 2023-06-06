import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GenerateRandomCoordinates {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("How many number of (x, y) pairs to create:"); // we use 1000000 to test
        int RandomNumCount = input.nextInt();

        System.out.println("What's the radius?"); // we use 200 to test
        int radius = input.nextInt();

        int diameter = radius * 2;
        input.close();

        try {
            String currentPath = System.getProperty("user.dir");
            String filePath = currentPath + "/dataset";
            System.out.println("filePath = " + filePath);

            // it creates file input
            File file = new File(filePath + "/RandomCoordinates.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }

            // Prepare input data
            FileWriter writer = new FileWriter(file);
            BufferedWriter buffer = new BufferedWriter(writer);

            for (int i = 0; i < RandomNumCount; i++) {
                int xvalue = (int) (Math.random() * diameter);
                int yvalue = (int) (Math.random() * diameter);
                buffer.write("(" + xvalue + "," + yvalue + ") ");
            }

            // send the data into the file
            buffer.flush();

            // closing the write after pushing the data inside the .txt file
            buffer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
