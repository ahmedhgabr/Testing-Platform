package Listeners;

import ANTLR.Java8BaseListener;
import ANTLR.Java8Parser;
import Main.Method;
import Main.Variable;

import java.util.ArrayList;

public class ClassListener extends Java8BaseListener {

    public String superClass;
    public ArrayList<String> interfaces = new ArrayList<>();
    public ArrayList<String> modifier = new ArrayList<>();
    public ArrayList<Variable> variables = new ArrayList<>();
    public ArrayList<Method> methods = new ArrayList<>();
    public ArrayList<Method> constructors = new ArrayList<>();


    /////////////CLASS HEADER\\\\\\\\\\\\\\\\\\\\\\


    @Override
    public void exitClassModifier(Java8Parser.ClassModifierContext ctx) {
        super.exitClassModifier(ctx);
        this.modifier.add(ctx.getText());
    }

    @Override
    public void exitSuperclass(Java8Parser.SuperclassContext ctx) {
        super.exitSuperclass(ctx);
        this.superClass = ctx.classType().getText();
    }

    @Override
    public void exitInterfaceType(Java8Parser.InterfaceTypeContext ctx) {
        super.exitInterfaceType(ctx);
        this.interfaces.add(ctx.getText());
    }

    ////////////GLOBAL VARIABLE\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void exitFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {
        super.exitFieldDeclaration(ctx);
        String modifier = (ctx.fieldModifier().isEmpty()) ? "default" : ctx.fieldModifier().get(0).getText();
        String type = ctx.unannType().getText();
        for (Java8Parser.VariableDeclaratorContext var : ctx.variableDeclaratorList().variableDeclarator()) {
            String name = var.variableDeclaratorId().getText();

            variables.add(new Variable(modifier, type, name));
        }
    }


    ////////////METHOD\\\\\\\\\\\\\\\\\\\\

    Method method;
    boolean inMethod;


    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        super.enterMethodDeclaration(ctx);
        method = new Method();
        inMethod = true;
    }

    @Override
    public void exitMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        super.exitMethodDeclarator(ctx);
        String methodName = ctx.Identifier().getText();
        method.name = methodName;
    }

    @Override
    public void exitFormalParameter(Java8Parser.FormalParameterContext ctx) {
        super.exitFormalParameter(ctx);
        method.parameters.add(ctx.getText());
        String name = ctx.variableDeclaratorId().getText();
        String type = ctx.unannType().getText();
        method.variables.put(name, new Variable("private", type, name));
    }


    @Override
    public void exitMethodModifier(Java8Parser.MethodModifierContext ctx) {
        super.exitMethodModifier(ctx);
        method.modifier = ctx.getText();
    }

    @Override
    public void exitMethodHeader(Java8Parser.MethodHeaderContext ctx) {
        super.exitMethodHeader(ctx);
        String returnType = ctx.result().getText();
        method.returnType = returnType;
        method.signature = ctx.getText();

    }


    @Override
    public void exitMethodBody(Java8Parser.MethodBodyContext ctx) {
        super.exitMethodBody(ctx);
        int size;

        try {
            if (ctx.block().blockStatements() == null) {
                methods.get(methods.size() - 1).empty = true;

            } else {
                method.body = ctx.getText();
                size = ctx.block().blockStatements().children.size();
                method.size = size;
            }
        } catch (Exception e) {
            System.out.println("empty");
        }

        methods.add(method);
        inMethod = false;

    }

    //TODO: support static class


    /////////////////CONSTRUCTOR\\\\\\\\\\\\\\\\\\\\\

    @Override
    public void enterConstructorDeclarator(Java8Parser.ConstructorDeclaratorContext ctx) {
        super.enterConstructorDeclarator(ctx);
        method = new Method();
        inMethod = true;
    }

    @Override
    public void exitConstructorBody(Java8Parser.ConstructorBodyContext ctx) {
        super.exitConstructorBody(ctx);
        constructors.add(method);
        inMethod = false;
    }

    /////////////////VARIABLES\\\\\\\\\\\\\\\\\\\\\\\\\\

    @Override
    public void exitLocalVariableDeclaration(Java8Parser.LocalVariableDeclarationContext ctx) {
        super.exitLocalVariableDeclaration(ctx);

        String type = ctx.unannType().getText();
        for (Java8Parser.VariableDeclaratorContext c : ctx.variableDeclaratorList().variableDeclarator()) {
            String name = c.variableDeclaratorId().getText();
            method.variables.putIfAbsent(name, new Variable("private", type, name));
            method.variables.get(name).addUsage(ctx.getText());
        }
    }

    ArrayList<String> tempVar = new ArrayList<>();

    @Override
    public void exitExpressionName(Java8Parser.ExpressionNameContext ctx) {
        super.exitExpressionName(ctx);
        tempVar.add(ctx.getText());
    }

    @Override
    public void exitExpressionStatement(Java8Parser.ExpressionStatementContext ctx) {
        super.exitExpressionStatement(ctx);
        String expression = ctx.getText();

        if (inMethod) {
            for (String varName : tempVar) {
                method.variables.putIfAbsent(varName, new Variable("private", "", varName));
                method.variables.get(varName).addUsage(expression);
            }
        }

    }


    ////////////////////// LOOPS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void exitWhileStatement(Java8Parser.WhileStatementContext ctx) {
        super.enterWhileStatement(ctx);
        String cond = ctx.expression().getText();
        int size = ctx.statement().children.size() - 1;
        if (size == 0) {
            method.Loops.add("Empty");
        } else {
            method.Loops.add(cond);
        }
    }


    @Override
    public void exitBasicForStatement(Java8Parser.BasicForStatementContext ctx) {
        String forInit = ctx.forInit().getText();
        String condition = ctx.expression().getText();
        String update = ctx.forUpdate().getText();
        String bodyDescr = ctx.statement().getText();
        int size = ctx.statement().children.size() - 1;
        if (size == 0) {
            method.Loops.add("Empty");
        } else {
            method.Loops.add(condition);
        }

    }

    @Override
    public void exitEnhancedForStatement(Java8Parser.EnhancedForStatementContext ctx) {
        super.exitEnhancedForStatement(ctx);
        String type = ctx.unannType().getText();
        String update = ctx.expression().getText();
        String bodyDescr = ctx.statement().getText();
        int size = ctx.statement().children.size() - 1;
        if (size == 0) {
            method.Loops.add("Empty");
        } else {
            method.Loops.add(update);
        }

    }


    @Override
    public void exitDoStatement(Java8Parser.DoStatementContext ctx) {
        super.exitDoStatement(ctx);


        String cond = ctx.expression().getText();

//        if (size == 0) {
//            method.Loops.add("Empty");
//        } else {
        method.Loops.add(cond);
//        }

    }

    ////////////////////////CONDITIONS\\\\\\\\\\\\\\\\\\\\\\\\

    @Override
    public void exitIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
        super.exitIfThenElseStatement(ctx);
        String condition = ctx.expression().getText();
        String thenPart = ctx.statementNoShortIf().getText();
        String elsePart = ctx.statement().getText();
        int size = ctx.statement().children.size() - 1;
        if (size == 0) {
            method.Conditions.add("Empty");
        } else {
            method.Conditions.add(condition);
//        method.Conditions.add("!"+condition);
        }
    }

    @Override
    public void exitIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        super.exitIfThenStatement(ctx);
        String cond = ctx.expression().getText();
        String thenPart = ctx.statement().getText();
        int size = ctx.statement().children.size() - 1;
        if (size == 0) {
            method.Conditions.add("Empty");
        } else {
            method.Conditions.add(cond);
        }
    }


    /////////////METHOD INVOCATION\\\\\\\\\\\\\\\\\\

    @Override
    public void exitMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        super.exitMethodInvocation(ctx);
        String output = "";

        try {
            String methodName = ctx.methodName().getText();
            output += methodName;
//            System.out.println(methodUsage);
        } catch (NullPointerException e) {
            if (ctx.typeName() == null) {
                output = "METHOD INVOKED: " + ctx.getText();
            } else {
                String methodName = ctx.typeName().getText() + " . " + ctx.Identifier().getText();
                output = "METHOD INVOKED: " + methodName;
            }

        }
        output += " , arguments : ";
        if (ctx.argumentList() != null) {
            output += ctx.argumentList().getText();
        } else {
            output += "null";
        }
        method.methodsInvoked.add(output);
        method.dependency++;
    }
}
