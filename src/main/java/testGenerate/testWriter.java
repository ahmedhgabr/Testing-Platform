package testGenerate;

import java.io.FileWriter;
import java.io.IOException;

public class testWriter {

    private String filePath;

    public testWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String content) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(content + "\n");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

}
