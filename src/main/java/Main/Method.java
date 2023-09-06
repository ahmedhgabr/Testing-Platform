package Main;

import java.util.ArrayList;
import java.util.HashMap;

public class Method implements Comparable{
    public String name;
    public String className;
    public String returnType;

    public String signature ;

    public  String modifier ;
    public int dependency;
    public boolean empty;
    public int size;

    public ArrayList<String> Variables = new ArrayList<>();
    public ArrayList<String> Conditions = new ArrayList<>();
    public ArrayList<String> Loops = new ArrayList<>();
    public ArrayList<String> methodsInvoked = new ArrayList<>();

    public String body ;

    public void setDependency(int dependency) {
        this.dependency = dependency;
    }


    @Override
    public int compareTo(Object o) {
        return this.dependency - ((Method) o).dependency;
    }

    public void analyze() {
    }


    //TODO: compare method
}
