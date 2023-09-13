package Main;

import ANTLR.Java8Lexer;
import ANTLR.Java8Parser;
import Listeners.ClassListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import testGenerate.ClassTester;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Class extends Javas {

    String superClass;
    ArrayList<String> interfaces;

    //global variables
    ArrayList<Variable> variables;
    public ArrayList<Method> methods;
    public ArrayList<Method> constructors;

    public Class(String name) {
        this.name = name;
        this.superClass = " ";
        this.interfaces = new ArrayList<>();
        this.modifier = new ArrayList<>();
        this.signature = "";
        this.variables = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.constructors = new ArrayList<>();
    }

    private boolean isAbstract() {
        return this.signature.contains("abstract");
    }

    @Override
    void analyze() {
        super.analyze();
        Path fileP = Paths.get(this.path);
        Java8Lexer lexer = null;
        try {
            lexer = new Java8Lexer(CharStreams.fromPath(fileP));
        } catch (IOException e) {
            System.out.println("Error in " + fileP);
        }

        Java8Parser parser = new Java8Parser(new CommonTokenStream(lexer));
        ClassListener listener = new ClassListener();
        parser.addParseListener(listener);
        parser.compilationUnit();

        this.superClass = listener.superClass;
        this.interfaces = listener.interfaces;
        this.modifier = listener.modifier;
        this.signature = setSignature();
        this.variables = listener.variables;
        this.methods = listener.methods;
        this.constructors = listener.constructors;
    }

    private String setSignature() {
        String res = "";
        for (String m: this.modifier ) {
            res += m + " ";
        }
        res += "class ";
        res += this.name + " {";
        return res;
    }

    @Override
    void generateTest(String outputPath) {
        super.generateTest(outputPath);

        ClassTester tester = new ClassTester(outputPath);

        tester.writePath(name, path);
        tester.testSuper(name, superClass);
        if (isAbstract()) {
            tester.testIsAbstract(name);
        }
        for (String interfaceName : interfaces) {
            tester.testInterface(name, interfaceName);
        }
        for (Variable v : variables) {
            if (v.modifier.equals("private")) {
                tester.testInstanceVariable(name, v.name);
            }
        }


    }


}
