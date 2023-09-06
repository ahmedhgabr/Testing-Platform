package Main;

import ANTLR.Java8Lexer;
import ANTLR.Java8Parser;
import Listeners.classListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Class extends Javas {

    //global variables
    ArrayList<String> variables;
    ArrayList<Method> methods ;

    public Class(String name) {
        this.name = name;
        this.variables = new ArrayList<>();
        this.methods = new ArrayList<>();
    }


    @Override
    void setup() {
        super.setup();
        Path fileP = Paths.get(this.path);
        Java8Lexer lexer= null;
        try {
            lexer = new Java8Lexer(CharStreams.fromPath(fileP));
        }catch (IOException e){
            System.out.println("Error in "+ fileP );
        }
        Java8Parser parser = new Java8Parser(new CommonTokenStream(lexer));
        classListener listener = new classListener();
        parser.addParseListener(listener);
        parser.compilationUnit();
        this.variables = listener.variables ;
        this.methods = listener.methods ;

        for (Method myMethod: this.methods ) {
            myMethod.analyze();
        }

    }


}
