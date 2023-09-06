package Listeners;

import ANTLR.Java8BaseListener;
import ANTLR.Java8Parser;
import Main.Interface;
import Main.Javas;
import Main.Class;


import java.util.ArrayList;

public class mainListener extends Java8BaseListener {

    public static ArrayList<Javas> javas = new ArrayList<>();


    @Override
    public void exitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        super.exitNormalClassDeclaration(ctx);
        String className = ctx.Identifier().getText();
        javas.add(new Class(className));
    }


    @Override
    public void exitNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx) {
        super.exitNormalInterfaceDeclaration(ctx);
        String interfaceName =ctx.Identifier().getText();
        javas.add(new Interface(interfaceName));

    }

    //TODO: enum , annotations
}
