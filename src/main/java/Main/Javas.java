package Main;

import java.util.ArrayList;

public abstract class Javas {

    String path;

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getModifier() {
        return modifier;
    }

    public String getSignature() {
        return signature;
    }

    String name;
    ArrayList<String> modifier;
    String signature;
    String creationString;

    void analyze() {

    }

    void generateTest(String outputPath ) {

    }




}
