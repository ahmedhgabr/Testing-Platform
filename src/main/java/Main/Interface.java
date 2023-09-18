package Main;

import ANTLR.Java8Lexer;
import ANTLR.Java8Parser;
import Listeners.InterfaceListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import testGenerate.InterfaceTester;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Interface extends Javas{
    ArrayList<Method> methods ;

    public Interface(String name) {
        this.name = name;
        methods = new ArrayList<>();
    }

    private String setSignature() {
        String res = "";
        for (String m: this.modifier ) {
            res += m + " ";
        }
        res += "interface ";
        res += this.name + " {";
        return res;
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
        InterfaceListener listener = new InterfaceListener();
        parser.addParseListener(listener);
        parser.compilationUnit();


        this.modifier = listener.modifier;
        this.signature = setSignature();
        this.methods = listener.methods;
    }


    @Override
    void generateTest(String outputPath) {
        super.generateTest(outputPath);

        InterfaceTester tester = new InterfaceTester(outputPath);

        tester.writePath(name, path);

        tester.testIsInterface(name);

    }


}
