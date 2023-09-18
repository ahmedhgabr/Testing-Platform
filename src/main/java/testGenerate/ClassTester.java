package testGenerate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class ClassTester extends Tester {


    public ClassTester(String outputPath) {
        super(outputPath);
    }


    public void testIsAbstract(String name) {
        writer.writeToFile("@Test\npublic void testClassIsAbstract" + name + "() throws Exception{ testClassIsAbstract(Class.forName(" + pathV(name) + ")); }\n");
    }


    public void testSuper(String name, String superName) {
        if (superName == null) return;
        writer.writeToFile("@Test\npublic void test" + superName + "IsSuperClassOf" + name + "() throws Exception\n" +
                "\t{\n" +
                "\t\ttestClassIsSubclass(Class.forName(" + pathV(name) + "), Class.forName(" + (superName.equals("Exception") ? "\"java.lang.Exception\"" : pathV(superName)) + "));\n" +
                "\t}");
    }

    public void testInterface(String name, String interfaceName) {
        interfaceName = (interfaceName.equals("Comparable") ? "\"Comparable\"" : pathV(interfaceName));
        writer.writeToFile("@Test\npublic void testInterface" + interfaceName + "IsImplementedByClass" + name + "() throws Exception{\n" +
                "\t\ttestClassImplementsInterface(Class.forName(" + pathV(name) + "), Class.forName(" + interfaceName + "));\n" +
                "\t}");
    }


    public void testInstanceVariable(String name, String variable) {
        writer.writeToFile("@Test\npublic void testInstanceVariable" + variable + "IsPresentAndIsPrivateInClass" + name + "() throws Exception\n" +
                "\t{\n" +
                "\t\ttestInstanceVariableIsPresent(Class.forName(" + pathV(name) + "), \"" + variable + "\", true);\n" +
                "\t\ttestInstanceVariableIsPrivate(Class.forName(" + pathV(name) + "), \"" + variable + "\");\n" +
                "\t}");
    }


    public void testConstructor(String name, ArrayList<String> inputs, int n) {

        writer.writeToFile("@Test\npublic void testConstructor" + n + name + "() throws Exception\n" +
                "\t{\n" +
                "\t\tClass[] inputs = {" + inputsToString(inputs) + "};\n" +
                "\t\ttestConstructorExists(Class.forName(" + pathV(name) + "), inputs);\n" +
                "\t}");
    }


    /////////////////////
    public void testSetter(String className, String methodName, String varName, String varType, boolean writeVariable) {

        String type = getTypeClass(varType);
        writer.writeToFile("@Test\n\tpublic void testSetterForInstanceVariable" + varName + "ExistsInClass" + className + "()throws Exception { \n" +
                "\t\t testSetterMethodExistsInClass(Class.forName(" + pathV(className) + "), " + "\"" + methodName + "\"" + ", " + type + "," + writeVariable + "); }\n");
    }

    public void testGetter(String className, String methodName, String varName, String varType, boolean writeVariable) {

        String type = getTypeClass(varType);
        writer.writeToFile("@Test\n\tpublic void testGetterForInstanceVariable" + varName + "ExistsInClass" + className + "()throws Exception { \n" +
                "\t\t testGetterMethodExistsInClass(Class.forName(" + pathV(className) + "), " + "\"" + methodName + "\"" + ", " + type + "," + writeVariable + "); }\n");
    }


    private boolean isMyType(String type) {
//        String t = "int Integer String boolean Boolean double Double List Arraylist Set HashSet HashMap [] Stack Queue LinkedList";

        switch (type) {
            case "boolean":
            case "Boolean":
            case "byte":
            case "Byte":
            case "short":
            case "Short":
            case "int":
            case "Integer":
            case "long":
            case "Long":
            case "float":
            case "Float":
            case "double":
            case "Double":
            case "char":
            case "Character":
            case "String":
                return true;
            default:
                return (type.contains("[") || type.contains("<"));
        }
    }

    private String getTypeClass(String varType) {
        if (isMyType(varType)) {
            if (varType.contains("[")) {
                varType = varType.substring(0, varType.indexOf("[")).trim();
                return "Class.forName(" + pathV(varType) + ")";
            } else if (varType.contains("<")) {
                varType = varType.substring(0, varType.indexOf("<")).trim();
            }
            return varType + ".class";
        } else {
            return "Class.forName(" + pathV(varType) + ")";
        }
    }


    public void testConstructorInitialization(String className, ArrayList<String> varNames, ArrayList<String> varType, int n) {

        String varInit = "Random random = new Random();\n";
        ArrayList<String> valuesNames = new ArrayList<>();
        for (int i = 0; i < varNames.size(); i++) {
            varInit += varType.get(i) + " " + varNames.get(i) + i + " = " + giveMeRandom(varType.get(i)) + ";\n";
            valuesNames.add(varNames.get(i) + i);
        }

        String names = "";
        String values = "";
        names += "\nString[] names = {";
        values += "\nObject[] values = {";
        if (varNames.size() > 1) {
            names += "\"" + varNames.get(0) + "\"";
            values += valuesNames.get(0);
            for (int i = 1; i < varNames.size(); i++) {
                names += " , " + "\"" + varNames.get(i) + "\"";
                values += " , " + valuesNames.get(i);
            }
        }
        names += " };\n";
        values += " };\n";

        writer.writeToFile("@Test\npublic void testConstructorInitialization" + n + className + "() throws Exception\n" +
                "\t{\n" +
                "Object " + className + n + " =  Class.forName(" + className + "Path).getConstructor().newInstance(); \n" +
                varInit + names + values +
                "testConstructorInitialization(" + className + n + ", names, values);\n" +
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

    private String inputsToString(ArrayList<String> inputs) {
        String res = "";
        for (int i = 0; i < inputs.size(); i++) {
            res +=getTypeClass(inputs.get(i) ) +" ";
            if(i != inputs.size()-1)
                res += ",";
        }
        return res;
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
                return "Class.forName(" + pathV(type) + ").getConstructor().newInstance();";
        }
    }


}
