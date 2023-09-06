package Listeners;

import ANTLR.Java8BaseListener;
import ANTLR.Java8Parser;
import Main.Method;

import java.util.ArrayList;

public class methodListener extends Java8BaseListener {

    Method method;

    public methodListener() {
        this.method = new Method();
    }


    @Override
    public void exitMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        super.exitMethodDeclarator(ctx);

        String methodName = ctx.Identifier().getText();
        method.name = methodName;
    }

    @Override
    public void exitMethodHeader(Java8Parser.MethodHeaderContext ctx) {
        super.exitMethodHeader(ctx);

        method.returnType = ctx.result().getText();
    }


    @Override
    public void exitMethodBody(Java8Parser.MethodBodyContext ctx) {
        super.exitMethodBody(ctx);
        int size = 0;


        try {
            if (ctx.block().blockStatements() == null) {
                method.empty = true;
            } else {
                size = ctx.block().blockStatements().children.size();
                method.size = size;
            }
        } catch (Exception e) {
            // this is interface should not be here
            System.out.println("This listener is for class not interface");
        }
    }


    /////////////////VARIABLES\\\\\\\\\\\\\\\\\\\\\\\\\\

    @Override
    public void exitVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {
        super.exitVariableDeclaratorId(ctx);

//        variables.add(ctx.Identifier().getText());
//        ArrayList<String> temp = new ArrayList<>();
//        String varID = (variables.lastIndexOf(ctx.Identifier().getText()) != -1) ? ctx.Identifier().getText() + variables.lastIndexOf(ctx.Identifier().getText()) : ctx.Identifier().getText() + ".";
//        variableUsage.put(varID, temp);
//        fw.writeToFile(spaceAppend(space) + "VARIABLE NAME: " + ctx.Identifier() + ",ID : " + varID);
        method.Variables.putIfAbsent(ctx.Identifier().getText(), new ArrayList<>());
    }


    String tempVar;

    @Override
    public void exitExpressionName(Java8Parser.ExpressionNameContext ctx) {
        super.exitExpressionName(ctx);
        tempVar = ctx.Identifier().getText();
    }

    @Override
    public void exitExpressionStatement(Java8Parser.ExpressionStatementContext ctx) {
        super.exitExpressionStatement(ctx);
        String expression = ctx.getText();

//        for (String var : varTemp) {
//            String varID = (variables.lastIndexOf(var) != -1) ? var + variables.lastIndexOf(var) : var + ".";
//            if (variableUsage.get(varID) == null) {
//                ArrayList<String> temp = new ArrayList<>();
//                variableUsage.put(varID, temp);
//
//            }
//            variableUsage.get(varID).add(expression);
//        }

        method.Variables.get(tempVar).add(expression);
    }

    @Override
    public void exitAssignment(Java8Parser.AssignmentContext ctx) {
        super.exitAssignment(ctx);
        method.Variables.get(ctx.leftHandSide().getText()).add("=" + ctx.expression().getText());

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
