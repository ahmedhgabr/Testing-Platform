package Listeners;

import ANTLR.Java8BaseListener;
import ANTLR.Java8Parser;
import Main.Method;
import Main.Variable;

import java.util.ArrayList;

public class classListener extends Java8BaseListener {

    public ArrayList<Variable> variables;
    public ArrayList<Method> methods ;

    @Override
    public void exitFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {
        super.exitFieldDeclaration(ctx);
        String modifier = (ctx.fieldModifier().isEmpty())? "default" : ctx.fieldModifier().get(0).getText();
        String type = ctx.unannType().getText();
        String name = ctx.variableDeclaratorList().getText();
        variables.add(new Variable(modifier,type,name));
    }




    //TODO: support static class
}
