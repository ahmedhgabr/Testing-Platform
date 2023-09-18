package testGenerate;

import java.util.ArrayList;

public class EnumTester extends Tester {


    public EnumTester(String outputPath) {
        super(outputPath);
    }

    public void testIsEnum(String className) {
        writer.writeToFile("@Test\n" +
                "public void testClassIsEnum" + className + "() throws Exception\n" +
                "\t{\n" +
                "testIsEnum(Class.forName(" + className + "Path));\n" +
                "\t}");
    }

    public void testEnumValues(String className, ArrayList<String> values){
        String in = "" ;

            in += "\""+ values.get(0) + "\"" ;
            for (int i = 1; i < values.size(); i++) {
                in += " , " + "\""+values.get(i) + "\"" ;
            }

        in += " };\n";
        writer.writeToFile("@Test\npublic void testEnumValues" + className + "() throws Exception\n" +
                "\t{\n" +
                "String [] inputs = {" + in + "\n" +
                "testEnumValues(Class.forName(" + className +"Path),inputs);\n" +
                "\t}");
    }



}
