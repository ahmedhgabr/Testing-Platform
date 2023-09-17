package Main;

import ANTLR.Java8Lexer;
import ANTLR.Java8Parser;
import Listeners.EnumListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import testGenerate.EnumTester;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Enum extends Javas {

    ArrayList<String> values ;

    public Enum() {
        this.values = new ArrayList<>();
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
        EnumListener listener = new EnumListener();
        parser.addParseListener(listener);
        parser.compilationUnit();

        this.modifier = listener.modifier;
        this.signature = setSignature();
        this.values = listener.values;
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
        EnumTester tester = new EnumTester(outputPath);

        tester.writePath(name, path);

        tester.testIsEnum(name);
        tester.testEnumValues(name, values);

    }
}
