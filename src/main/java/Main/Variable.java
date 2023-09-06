package Main;

import java.util.ArrayList;

public class Variable {
    String modifier;
    String type;
    String name;
    ArrayList<String> usage ;

    public Variable(String modifier, String type, String name) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
        usage = new ArrayList<>();
    }
}
