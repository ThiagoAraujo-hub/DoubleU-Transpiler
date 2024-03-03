package handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class FileHandler {
    private static final String filename = "src/resources/Program.java";

    public static void createFile() {
        try {
            File file = new File(filename);

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File '" + file.getName() + "' already exists.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String code) {
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(filename);

            fw.write(code);
            fw.close();

            System.out.println("Successfully wrote to the file '" + file.getName() + "'.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
