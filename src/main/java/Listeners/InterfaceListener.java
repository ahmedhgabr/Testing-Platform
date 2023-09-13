package Listeners;

import ANTLR.Java8BaseListener;
import ANTLR.Java8Parser;
import Main.Method;
import Main.Variable;

import java.util.ArrayList;

public class InterfaceListener extends Java8BaseListener {

    public ArrayList<String> modifier = new ArrayList<>();

    public ArrayList<Method> methods = new ArrayList<>();


    ////// INTERFACE HEADER\\\\\\\\\\\\\
    @Override
    public void enterInterfaceModifier(Java8Parser.InterfaceModifierContext ctx) {
        super.enterInterfaceModifier(ctx);
        modifier.add(ctx.getText());
    }


    //////////// METHOD \\\\\\\\\\\\\
    Method method;


    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        super.enterMethodDeclaration(ctx);
        method = new Method();
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

        method.size = 0;
        methods.add(method);
    }

}
