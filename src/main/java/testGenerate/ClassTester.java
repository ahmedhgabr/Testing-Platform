package testGenerate;

import java.util.ArrayList;
import java.util.Random;

public class ClassTester extends Tester {


    public ClassTester(String outputPath) {
        super(outputPath);
    }


    public void testIsAbstract(String name) {
        writer.writeToFile("public void testClassIsAbstract" + name + "() throws Exception{ testClassIsAbstract(Class.forName(" + pathV(name) + ")); }\n");
    }


    public void testSuper(String name, String superName) {
        if (superName == null) return;
        writer.writeToFile("public void test" + superName + "IsSuperClassOf" + name + "() throws Exception\n" +
                "\t{\n" +
                "\t\ttestClassIsSubclass(Class.forName(" + pathV(name) + "), Class.forName(" + pathV(superName) + "));\n" +
                "\t}");
    }

    public void testInterface(String name, String interfaceName) {
        writer.writeToFile("public void testInterface" + interfaceName + "IsImplementedByClass" + name + "() throws Exception{\n" +
                "\t\ttestClassImplementsInterface(Class.forName(" + pathV(name) + "), Class.forName(" + pathV(interfaceName) + "));\n" +
                "\t}");
    }


    public void testInstanceVariable(String name, String variable) {
        writer.writeToFile("public void testInstanceVariable" + variable + "IsPresentAndIsPrivateInClass" + name + "() throws Exception\n" +
                "\t{\n" +
                "\t\ttestInstanceVariableIsPresent(Class.forName(" + pathV(name) + "), \"" + variable + "\", true);\n" +
                "\t\ttestInstanceVariableIsPrivate(Class.forName(" + pathV(name) + "), \"" + variable + "\");\n" +
                "\t}");
    }


    public void testConstructor(String name, String[] inputs, int n) {
        writer.writeToFile("public void testConstructor" + n + name + "() throws Exception\n" +
                "\t{\n" +
                "\t\tClass[] inputs = {" + inputsToString(inputs) + ")};\n" +
                "\t\ttestConstructorExists(Class.forName(cardPath), inputs);\n" +
                "\t}");
    }


    /////////////////////
    public void testSetter(String className, String methodName, String varName, String varType, boolean writeVariable) {


        String type = (isMyType(varType) ? varType + ".Class" : pathV(varType));
        writer.writeToFile("\tpublic void testSetterForInstanceVariable" + varName + "ExistsInClass" + className + "()throws Exception { \n" +
                "\t\t testSetterMethodExistsInClass(Class.forName(" + pathV(className) + "), " + "\"" + methodName +"\"" + ", Class.forName(" + type + ")," + writeVariable + "); }\n");
    }

    public void testGetter(String className, String methodName, String varName, String varType, boolean writeVariable) {

        String type = (isMyType(varType) ? varType + ".Class" : pathV(varType));
        writer.writeToFile("\tpublic void testGetterForInstanceVariable" + varName + "ExistsInClass" + className + "()throws Exception { \n" +
                "\t\t testGetterMethodExistsInClass(Class.forName(" + pathV(className) + "), " +"\""+ methodName +"\""+ ", Class.forName(" + type + ")," + writeVariable + "); }\n");
    }


    private boolean isMyType(String type) {
        String t = "int Integer String Arraylist  List  boolean  Boolean double Double";
        return t.contains(type);
    }


    public void testConstructorInitialization(String className, ArrayList<String> varNames, ArrayList<String> varType, int n) {

        String varInit = "";
        ArrayList<String> valuesNames = new ArrayList<>();
        for (int i = 0; i < varNames.size(); i++) {
            varInit += varType.get(i) + " " + varNames.get(i) + i + " = " + giveMeRandom(varType.get(i)) + ";\n";
            valuesNames.add(varNames.get(i) + i);
        }

        String names = "";
        String values = "";
        names += "\nString names = {" ;
        values += "\nString values = {" ;
        if (varNames.size() > 1) {
            names += "\""+ varNames.get(0) + "\"" ;
            values += valuesNames.get(0);
            for (int i = 1; i < varNames.size(); i++) {
                names += " , " + "\""+varNames.get(i) + "\"" ;
                values += " , " + valuesNames.get(i);
            }
        }
        names += " };\n";
        values += " };\n";

        writer.writeToFile("public void testConstructorInitialization" + n + className + "() throws Exception\n" +
                "\t{\n" +
                "Object " + className + n + " =  Class.forName(" + className + "Path).getConstructor().newInstance(); \n" +
                varInit + names + values +
                "testConstructorInitialization(" + className + n+", names, values);\n" +
                "\t}");
    }


    ///////////////////////////////


//    public void setterLogic(String className, String varName, String varType) {
//
//        String className2 = (isMyType(varType) ? varType + ".Class" : pathV(varType));//class of the variable
//        writer.writeToFile("public void testSetterLogicForInstanceVariable" + varName  + "InClass" + className + "() throws Exception\n" +
//                "\t{\n" +
//                "Object " + className2 + " =  Class.forName(" + className2 + " Path).getConstructor().newInstance(); \n" +
//                varType + varType.trim() + 01 + " = 01; \n" +
//                "\t\ttestSetterLogic(" + className2 + "," + varName + " , varType, varType," + varType + ");\n" +
//                "\t}");
//    }
//
//    public void getterLogic(String className, String varName, String varType) {
//
//        String className2 = (isMyType(varType) ? varType + ".Class" : pathV(varType)); //class of the variable
//        writer.writeToFile("public void testGetterLogicForInstanceVariable" + varName + "InClass" + className + "() throws Exception\n" +
//                "\t{\n" +
//                "Object " + className2 + " =  Class.forName(" + className2 + " Path).getConstructor().newInstance(); \n" +
//                varType + varType.trim() + 01 + " = 01; \n" +
//                "\t\ttestGetterLogic(" + className2 + "," + varName + " , varType);\n" +
//                "\t}");
//    }


    //////////////////////////////

    private String[] inputsToString(String[] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("int") || inputs[i].equals("String")) {
                inputs[i] += ".Class ";
            }
        }
        return inputs;
    }


    private String giveMeRandom(String type) {
        Random random = new Random();

        switch (type) {
            case "boolean":
                return "random.nextBoolean()";
            case "byte":
                return "randomByte()";
            case "short":
                return "(short) random.nextInt()";
            case "int":
                return "random.nextInt()";
            case "long":
                return "random.nextLong()";
            case "float":
                return "random.nextFloat()";
            case "double":
                return "random.nextDouble()";
            case "char":
                return "generateRandomChar()";
            case "String":
                return "generateRandomString()";
            default:
                return "Class.forName(" + type + ").getConstructor().newInstance();";
        }
    }


}
