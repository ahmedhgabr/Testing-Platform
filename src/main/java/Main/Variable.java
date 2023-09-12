package Main;

import java.util.ArrayList;

public class Variable {
    String modifier;
    String type;
    public String name;
    ArrayList<String> usage;

    public Variable(String modifier, String type, String name) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
        usage = new ArrayList<>();
    }


    public void addUsage(String usage) {
        this.usage.add(usage);
    }

    @Override
    public String toString() {
        return type + " " + name;

    }
}
