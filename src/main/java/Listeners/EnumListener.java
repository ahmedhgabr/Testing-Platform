package Listeners;

import ANTLR.Java8BaseListener;
import ANTLR.Java8Parser;
import Main.Variable;

import java.util.ArrayList;

public class EnumListener extends Java8BaseListener {

    public ArrayList<String> modifier = new ArrayList<>();
    public ArrayList<String> values = new ArrayList<>();


    ///// ENUM HEADER \\\\\\\\\\


    @Override
    public void exitClassModifier(Java8Parser.ClassModifierContext ctx) {
        super.exitClassModifier(ctx);
        modifier.add(ctx.getText());
    }


    //////// VALUES \\\\\\\\\\\\


    @Override
    public void exitEnumConstant(Java8Parser.EnumConstantContext ctx) {
        super.exitEnumConstant(ctx);
        values.add(ctx.getText());
    }
    
}
