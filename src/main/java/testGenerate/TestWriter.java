package testGenerate;

import java.io.FileWriter;
import java.io.IOException;

public class TestWriter {

    private String filePath;

    public TestWriter(String filePath) {
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
