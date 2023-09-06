package Main;

import java.util.ArrayList;

public class Interface extends Javas{
    String name;
    ArrayList<Method> methods ;

    public Interface(String name) {
        this.name = name;
        methods = new ArrayList<>();
    }
}
