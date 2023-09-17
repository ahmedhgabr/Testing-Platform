package testGenerate;

public class InterfaceTester extends  Tester{

    public InterfaceTester(String outputPath) {
        super(outputPath);
    }


    public void testIsInstanceVariable(String name) {
        writer.writeToFile("public void testClassIsInterface"+name+"() throws Exception\n" +
                "\t{\n" +
                "\t\ttestIsInterface(Class.forName("+ pathV(name)+"));\n" +
                "\t}\n");
    }

}
