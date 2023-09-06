package Main;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import ANTLR.Java8Lexer;
import ANTLR.Java8Parser;
import Listeners.mainListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {

    static ArrayList<String> filesPath = new ArrayList<>();
    static mainListener MainListener ;

    public static void main(String[] args) {

        String path = takeInput();

        // find all java files in this file
        findFiles(path);

        //main listener to find all classes , interfaces , enums , annotations
        MainListener = new mainListener();
        for (String file: filesPath) {
            isJavas(file);
        }
        //we will use Javas meaning classes , interfaces , annotations , enums
        // , or any java file

        ArrayList<Javas> myJavas = MainListener.javas;
        //here we have all javas Objects with names and paths

        // use more specific listener for each file
        // (class -> class listener - > method listener , interface -> interface listener , etc)

        for (Javas j: myJavas) {
            j.setup();
        }

        // here, we should have all data we need from our input
        // have all classes( names , variables , methods )
        // interfaces (methods)
        //TODO: support enums , annotations


        // empty the output files (delete old output if found)
        String fileP = "output.txt";
        File oldFile = new File(fileP);
        oldFile.delete();

        String fileT = "OutputTest.java";
        File oldFileT = new File(fileT);
        oldFileT.delete();

        String fileC = "outputCheat.java";
        File oldFileC = new File(fileT);
        oldFileC.delete();

        // start generate tests
        for (Javas j: myJavas) {
            j.generateTest(fileT);
        }

        // start cheating output  file
        for (Javas j: myJavas) {
            j.detectCheat(fileC);
        }




    }

    private static String takeInput() {
        //        enter the path of the project
//        System.out.println("Enter src path : ");
//        Scanner sc= new Scanner(System.in);
//        String p = sc.next();

        String p = "C:/Users/Asus/Desktop/myProjects/cheatTry";
        return p;
    }

    private static void findFiles(String path) {
        File directory = new File(path);
        String contents[] = directory.list();
        for (String insidePath : contents) {
            File insidePathF = new File(insidePath);
            if (insidePathF.exists() && insidePathF.isDirectory()) {
                findFiles(path + "/" + insidePath);
            } else if (insidePath.contains(".java")) {
                filesPath.add(path + "/" + insidePath);
            }
        }

    }

    private static void isJavas(String file) {
        Path fileP = Paths.get(file);
        Java8Lexer lexer= null;
        try {
            lexer = new Java8Lexer(CharStreams.fromPath(fileP));
        }catch (IOException e){
            System.out.println("Error in "+ fileP );
        }
        Java8Parser parser = new Java8Parser(new CommonTokenStream(lexer));
        parser.addParseListener(MainListener);
        parser.compilationUnit();
        // add path to the javas
        MainListener.javas.get(MainListener.javas.size()-1).path = file;
    }


}