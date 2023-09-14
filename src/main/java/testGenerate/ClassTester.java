package testGenerate;

public class ClassTester extends Tester {


    public ClassTester(String outputPath) {
        super(outputPath);
    }


    // write class path as String in test
    public void writePath(String name, String path) {
        writer.writeToFile("String " + pathV(name) + " = " + "\"" + path + "\" ;\n");
    }

    private String pathV(String name) { // use this method so the variable name is the same every time
        return name + "Path";
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

        String t = "int Integer String Arraylist  List  boolean  Boolean";
        String type = (t.contains(varType) ? varType + ".Class" : pathV(varType));
        writer.writeToFile("\tpublic void testSetterForInstanceVariable" + varName + "ExistsInClass" + className + "()throws Exception { \n" +
                "\t\t testSetterMethodExistsInClass(Class.forName(" + pathV(className) + "), " + methodName + ", Class.forName(" + type + ")," + writeVariable + "); }\n");
    }

    public void testGetter(String className, String methodName, String varName, String varType, boolean writeVariable) {

        String t = "int Integer String Arraylist  List  boolean  Boolean";
        String type = (t.contains(varType) ? varType + ".Class" : pathV(varType));
        writer.writeToFile("\tpublic void testGetterForInstanceVariable" + varName + "ExistsInClass" + className + "()throws Exception { \n" +
                "\t\t testGetterMethodExistsInClass(Class.forName(" + pathV(className) + "), " + methodName + ", Class.forName(" + type + ")," + writeVariable + "); }\n");
    }


    ///////////////////////////////






    //////////////////////////////

    private String[] inputsToString(String[] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("int") || inputs[i].equals("String")) {
                inputs[i] += ".Class ";
            }
        }
        return inputs;
    }


}
