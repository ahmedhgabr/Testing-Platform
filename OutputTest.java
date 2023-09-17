import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class Test {
    String BoardPath = "Board";

    public void testConstructorInitialization0Board() throws Exception {
        Object Board0 = Class.forName(BoardPath).getConstructor().newInstance();

        String names = {};

        String values = {};
        testConstructorInitialization(Board0, names, values);
    }

    public void testSetterForInstanceVariableboardExistsInClassBoard() throws Exception {
        testSetterMethodExistsInClass(Class.forName(BoardPath), "setBoard", Class.forName( char[][] Path),false);
    }

    public void testGetterForInstanceVariableboardExistsInClassBoard() throws Exception {
        testGetterMethodExistsInClass(Class.forName(BoardPath), "setBoard", Class.forName( char[][] Path),false);
    }

    public void testSetterForInstanceVariablerowsExistsInClassBoard() throws Exception {
        testSetterMethodExistsInClass(Class.forName(BoardPath), "setRows", Class.forName(int.Class), false);
    }

    public void testGetterForInstanceVariablerowsExistsInClassBoard() throws Exception {
        testGetterMethodExistsInClass(Class.forName(BoardPath), "setRows", Class.forName(int.Class), true);
    }

    public void testSetterForInstanceVariablecolumnsExistsInClassBoard() throws Exception {
        testSetterMethodExistsInClass(Class.forName(BoardPath), "setColumns", Class.forName(int.Class), false);
    }

    public void testGetterForInstanceVariablecolumnsExistsInClassBoard() throws Exception {
        testGetterMethodExistsInClass(Class.forName(BoardPath), "setColumns", Class.forName(int.Class), true);
    }

    String ChaosPath = "Chaos";

    public void testClassicIsSuperClassOfChaos() throws Exception {
        testClassIsSubclass(Class.forName(ChaosPath), Class.forName(ClassicPath));
    }

    public void testConstructorInitialization0Chaos() throws Exception {
        Object Chaos0 = Class.forName(ChaosPath).getConstructor().newInstance();

        String names = {};

        String values = {};
        testConstructorInitialization(Chaos0, names, values);
    }

    String chipsPath = "chips";

    public void testInstanceVariablecolourIsPresentAndIsPrivateInClasschips() throws Exception {
        testInstanceVariableIsPresent(Class.forName(chipsPath), "colour", true);
        testInstanceVariableIsPrivate(Class.forName(chipsPath), "colour");
    }

    public void testConstructorInitialization0chips() throws Exception {
        Object chips0 = Class.forName(chipsPath).getConstructor().newInstance();
        String colour0 = generateRandomString();

        String names = {};

        String values = {};
        testConstructorInitialization(chips0, names, values);
    }

    public void testSetterForInstanceVariablecolourExistsInClasschips() throws Exception {
        testSetterMethodExistsInClass(Class.forName(chipsPath), "setColour", Class.forName(String.Class), false);
    }

    public void testGetterForInstanceVariablecolourExistsInClasschips() throws Exception {
        testGetterMethodExistsInClass(Class.forName(chipsPath), "setColour", Class.forName(String.Class), true);
    }

    String ClassicPath = "Classic";

    public void testInterfaceModeIsImplementedByClassClassic() throws Exception {
        testClassImplementsInterface(Class.forName(ClassicPath), Class.forName(ModePath));
    }

    public void testConstructorInitialization0Classic() throws Exception {
        Object Classic0 = Class.forName(ClassicPath).getConstructor().newInstance();

        String names = {};

        String values = {};
        testConstructorInitialization(Classic0, names, values);
    }

    public void testSetterForInstanceVariableplayer1ExistsInClassClassic() throws Exception {
        testSetterMethodExistsInClass(Class.forName(ClassicPath), "setPlayer1", Class.forName(PlayerPath), false);
    }

    public void testGetterForInstanceVariableplayer1ExistsInClassClassic() throws Exception {
        testGetterMethodExistsInClass(Class.forName(ClassicPath), "setPlayer1", Class.forName(PlayerPath), true);
    }

    public void testSetterForInstanceVariableplayer2ExistsInClassClassic() throws Exception {
        testSetterMethodExistsInClass(Class.forName(ClassicPath), "setPlayer2", Class.forName(PlayerPath), false);
    }

    public void testGetterForInstanceVariableplayer2ExistsInClassClassic() throws Exception {
        testGetterMethodExistsInClass(Class.forName(ClassicPath), "setPlayer2", Class.forName(PlayerPath), true);
    }

    public void testSetterForInstanceVariablecurrentPlayerExistsInClassClassic() throws Exception {
        testSetterMethodExistsInClass(Class.forName(ClassicPath), "setCurrentPlayer", Class.forName(PlayerPath), true);
    }

    public void testGetterForInstanceVariablecurrentPlayerExistsInClassClassic() throws Exception {
        testGetterMethodExistsInClass(Class.forName(ClassicPath), "setCurrentPlayer", Class.forName(PlayerPath), true);
    }

    public void testSetterForInstanceVariableboardExistsInClassClassic() throws Exception {
        testSetterMethodExistsInClass(Class.forName(ClassicPath), "setBoard", Class.forName(BoardPath), false);
    }

    public void testGetterForInstanceVariableboardExistsInClassClassic() throws Exception {
        testGetterMethodExistsInClass(Class.forName(ClassicPath), "setBoard", Class.forName(BoardPath), true);
    }

    public void testSetterForInstanceVariablescannerExistsInClassClassic() throws Exception {
        testSetterMethodExistsInClass(Class.forName(ClassicPath), "setScanner", Class.forName(ScannerPath), false);
    }

    public void testGetterForInstanceVariablescannerExistsInClassClassic() throws Exception {
        testGetterMethodExistsInClass(Class.forName(ClassicPath), "setScanner", Class.forName(ScannerPath), false);
    }

    String MainPath = "Main";

    public void testSetterForInstanceVariableclassesExistsInClassMain() throws Exception {
        testSetterMethodExistsInClass(Class.forName(MainPath), "setClasses", Class.forName(List < String > Path), false);
    }

    public void testGetterForInstanceVariableclassesExistsInClassMain() throws Exception {
        testGetterMethodExistsInClass(Class.forName(MainPath), "setClasses", Class.forName(List < String > Path), true);
    }

    String ModePath = "Mode";

    public void testClassIsInterfaceMode() throws Exception {
        testIsInterface(Class.forName(ModePath));
    }

    String PlayerPath = "Player";

    public void testInstanceVariablenameIsPresentAndIsPrivateInClassPlayer() throws Exception {
        testInstanceVariableIsPresent(Class.forName(PlayerPath), "name", true);
        testInstanceVariableIsPrivate(Class.forName(PlayerPath), "name");
    }

    public void testInstanceVariablechipIsPresentAndIsPrivateInClassPlayer() throws Exception {
        testInstanceVariableIsPresent(Class.forName(PlayerPath), "chip", true);
        testInstanceVariableIsPrivate(Class.forName(PlayerPath), "chip");
    }

    public void testConstructorInitialization0Player() throws Exception {
        Object Player0 = Class.forName(PlayerPath).getConstructor().newInstance();
        String name0 = generateRandomString();
        String colour1 = generateRandomString();

        String names = {"name", "colour"};

        String values = {name0, colour1};
        testConstructorInitialization(Player0, names, values);
    }

    public void testSetterForInstanceVariablenameExistsInClassPlayer() throws Exception {
        testSetterMethodExistsInClass(Class.forName(PlayerPath), "setName", Class.forName(String.Class), false);
    }

    public void testGetterForInstanceVariablenameExistsInClassPlayer() throws Exception {
        testGetterMethodExistsInClass(Class.forName(PlayerPath), "setName", Class.forName(String.Class), true);
    }

    public void testSetterForInstanceVariablechipExistsInClassPlayer() throws Exception {
        testSetterMethodExistsInClass(Class.forName(PlayerPath), "setChip", Class.forName(chipsPath), false);
    }

    public void testGetterForInstanceVariablechipExistsInClassPlayer() throws Exception {
        testGetterMethodExistsInClass(Class.forName(PlayerPath), "setChip", Class.forName(chipsPath), true);
    }

    String testPath = "test";


    // ############################################# Helper methods
    private Object giveMeRandom(String type) {
        Random random = new Random();

        switch (type) {
            case "boolean":
                return random.nextBoolean();
            case "byte":
                byte[] randomByte = new byte[1];
                random.nextBytes(randomByte);
                return randomByte;
            case "short":
                return (short) random.nextInt();
            case "int":
                return random.nextInt();
            case "long":
                return random.nextLong();
            case "float":
                return random.nextFloat();
            case "double":
                return random.nextDouble();
            case "char":
                return generateRandomChar();
            case "String":
                return generateRandomString();
            default:
                mock();

        }
        return null;
    }


    private static char generateRandomChar() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }

    private Byte randomByte() {
        Random random = new Random();
        byte[] randomByte = new byte[1];
        random.nextBytes(randomByte);
        return randomByte[0];
    }

    private static String generateRandomString() {
        int length = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    private boolean testConstructorInitializationWithRandom(Object createdObject, String[] names, Object[] values) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {
        boolean res = true;
        for (int i = 0; i < names.length; i++) {
            Field f = null;
            Class curr = createdObject.getClass();
            String currName = names[i];
            Object currValue = values[i];
            while (f == null) {
                if (curr == Object.class)
                    fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + currName + "\".");
                try {
                    f = curr.getDeclaredField(currName);
                } catch (NoSuchFieldException e) {
                    curr = curr.getSuperclass();
                }
            }
            f.setAccessible(true);
            res = res && currValue.equals(f.get(createdObject));
        }
        return res;
    }

    private void testEnumValues(Class aClass, String[] value) throws ClassNotFoundException {
        for (int i = 0; i < value.length; i++) {
            try {
                Enum.valueOf((Class<Enum>) aClass, value[i]);
            } catch (IllegalArgumentException e) {
                fail(aClass.getSimpleName() + " enum can be " + value[i]);
            }
        }
    }

    private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar) throws SecurityException {
        boolean thrown = false;
        try {
            aClass.getDeclaredField(varName);
        } catch (NoSuchFieldException e) {
            thrown = true;
        }
        if (implementedVar) {
            assertFalse("There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".", thrown);
        } else {
            assertTrue("The instance variable \"" + varName + "\" should not be declared in class " + aClass.getSimpleName() + ".", thrown);
        }
    }

    private void testInstanceVariableIsPrivate(Class aClass, String varName) throws NoSuchFieldException, SecurityException {
        Field f = aClass.getDeclaredField(varName);
        assertEquals("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " should not be accessed outside that class.", 2, f.getModifiers());
    }

    private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType, boolean writeVariable) {
        Method m = null;
        boolean found = true;
        try {
            m = aClass.getDeclaredMethod(methodName);
        } catch (Exception e) {
            found = false;
        }
        String varName = "";
        if (returnedType == boolean.class)
            varName = methodName.substring(2);
        else
            varName = methodName.substring(3);
        varName = Character.toLowerCase(varName.charAt(0))
                + varName.substring(1);
        if (writeVariable) {
            assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is a READ variable.", found);
            assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.", m.getReturnType()
                    .isAssignableFrom(returnedType));
        } else {
            assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is not a READ variable.", found);
        }
    }

    private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType, boolean writeVariable) {
        Method[] methods = aClass.getDeclaredMethods();
        String varName = methodName.substring(3);
        varName = Character.toLowerCase(varName.charAt(0))
                + varName.substring(1);
        if (writeVariable) {
            assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is a WRITE variable.", containsMethodName(methods, methodName));
        } else {
            assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is not a WRITE variable.", containsMethodName(methods, methodName));
            return;
        }
        Method m = null;
        boolean found = true;
        try {
            m = aClass.getDeclaredMethod(methodName, inputType);
        } catch (NoSuchMethodException e) {
            found = false;
        }
        assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one " + inputType.getSimpleName() + " parameter.", found);
        assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".", m.getReturnType().equals(Void.TYPE));
    }

    private static boolean containsMethodName(Method[] methods, String name) {
        for (Method method : methods) {
            if (method.getName().equals(name))
                return true;
        }
        return false;
    }

    private void testConstructorExists(Class aClass, Class[] inputs) {
        boolean thrown = false;
        try {
            aClass.getConstructor(inputs);
        } catch (NoSuchMethodException e) {
            thrown = true;
        }
        if (inputs.length > 0) {
            String msg = "";
            int i = 0;
            do {
                msg += inputs[i].getSimpleName() + " and ";
                i++;
            } while (i < inputs.length);
            msg = msg.substring(0, msg.length() - 4);
            assertFalse("Missing constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "") + " in " + aClass.getSimpleName() + " class.", thrown);
        } else
            assertFalse("Missing constructor with zero parameters in " + aClass.getSimpleName() + " class.", thrown);
    }

    private void testClassIsAbstract(Class aClass) {
        assertTrue("You should not be able to create new instances from " + aClass.getSimpleName() + " class.", Modifier.isAbstract(aClass.getModifiers()));
    }

    private void testIsInterface(Class aClass) {
        assertEquals(aClass.getName() + " should be an Interface", aClass.isInterface(), true);
    }

    private void testIsEnum(Class aClass) {
        assertEquals(aClass.getName() + " should be an Enum", aClass.isEnum(), true);
    }

    private void testClassIsSubclass(Class subClass, Class superClass) {
        assertEquals(subClass.getSimpleName() + " class should be a subclass from " + superClass.getSimpleName() + ".", superClass, subClass.getSuperclass());
    }

    private void testConstructorInitializationException(Object createdObject, String message) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        Class throwableClass = Throwable.class;
        Field messageFiled = throwableClass.getDeclaredField("detailMessage");
        messageFiled.setAccessible(true);
        assertEquals("The Exception constructor of the " + createdObject.getClass().getSimpleName() + " class should initialize the message correctly.", message, messageFiled.get(createdObject));
    }

    private void testConstructorInitialization(Object createdObject, String[] names, Object[] values) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {
        for (int i = 0; i < names.length; i++) {
            Field f = null;
            Class curr = createdObject.getClass();
            String currName = names[i];
            Object currValue = values[i];
            while (f == null) {
                if (curr == Object.class)
                    fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + currName + "\".");
                try {
                    f = curr.getDeclaredField(currName);
                } catch (NoSuchFieldException e) {
                    curr = curr.getSuperclass();
                }
            }
            f.setAccessible(true);
            assertEquals("The constructor of the " + createdObject.getClass().getSimpleName() + " class should initialize the instance variable \"" + currName + "\" correctly.", currValue, f.get(createdObject));
        }
    }

    private void testGetterLogic(Object createdObject, String name, Object value) throws Exception {
        Field f = null;
        Class curr = createdObject.getClass();
        while (f == null) {
            if (curr == Object.class)
                fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + name + "\".");
            try {
                f = curr.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                curr = curr.getSuperclass();
            }
        }
        f.setAccessible(true);
        f.set(createdObject, value);
        Character c = name.charAt(0);
        String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());
        if (value.getClass().equals(Boolean.class))
            methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());
        Method m = createdObject.getClass().getMethod(methodName);
        assertEquals("The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName() + " should return the correct value of variable \"" + name + "\".", value, m.invoke(createdObject));
    }

    private void testSetterLogic(Object createdObject, String name, Object valueIn, Object valueOut, Class type) throws Exception {
        Field f = null;
        Class curr = createdObject.getClass();
        while (f == null) {

            if (curr == Object.class)
                fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + name + "\".");
            try {
                f = curr.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                curr = curr.getSuperclass();
            }
        }
        f.setAccessible(true);
        Character c = name.charAt(0);
        String methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
        Method m = createdObject.getClass().getMethod(methodName, type);
        m.invoke(createdObject, valueIn);
        assertEquals("The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName() + " should set the correct value of variable \"" + name + "\".", valueOut, f.get(createdObject));
    }

    private void testClassImplementsInterface(Class aClass, Class interfaceName) {
        Class[] interfaces = aClass.getInterfaces();
        boolean implement = false;
        for (Class i : interfaces) {
            if (i.toString().equals(interfaceName.toString()))
                implement = true;
        }
        assertTrue(aClass.getSimpleName() + " class should implement " + interfaceName.getSimpleName() + " interface.", implement);
    }

}

