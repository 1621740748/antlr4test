package com.jrj.antlr4;
import java.util.HashMap;
import java.util.Map;

import com.jrj.antlr4.calcParser.AddSubContext;
import com.jrj.antlr4.calcParser.AssignContext;
import com.jrj.antlr4.calcParser.BlankContext;
import com.jrj.antlr4.calcParser.IdContext;
import com.jrj.antlr4.calcParser.IntContext;
import com.jrj.antlr4.calcParser.MulDivContext;
import com.jrj.antlr4.calcParser.ParensContext;
import com.jrj.antlr4.calcParser.PrintExprContext;
import com.jrj.antlr4.calcParser.ProgContext;
 
 
public class EvalVisitor extends calcBaseVisitor<Integer>{
    Map<String, Integer> memory = new HashMap<String, Integer>();

	@Override
	public Integer visitParens(ParensContext ctx) {
		return visit(ctx.expr());
	}

	@Override
	public Integer visitBlank(BlankContext ctx) {
		return super.visitBlank(ctx);
	}

	@Override
	public Integer visitMulDiv(MulDivContext ctx) {
        int left=visit(ctx.expr(0));
        int right=visit(ctx.expr(1));
        if(ctx.op.getType()==calcParser.MUL){
        	return left*right;
        }
		return left/right;
	}

	@Override
	public Integer visitAddSub(AddSubContext ctx) {
        int left=visit(ctx.expr(0));
        int right=visit(ctx.expr(1));
        if(ctx.op.getType()==calcParser.ADD){
        	return left+right;
        }
		return left-right;
	}

	@Override
	public Integer visitId(IdContext ctx) {
        String id=ctx.ID().getText();
        if(memory.containsKey(id)){
        	return memory.get(id);
        }
        return 0;
	}

	@Override
	public Integer visitProg(ProgContext ctx) {
		return super.visitProg(ctx);
	}

	@Override
	public Integer visitInt(IntContext ctx) {
		return Integer.valueOf(ctx.INT().getText());
	}

	@Override
	public Integer visitPrintExpr(PrintExprContext ctx) {
		Integer value=visit(ctx.expr());
		System.out.println(value);
		return 0;
	}

	@Override
	public Integer visitAssign(AssignContext ctx) {
		String id=ctx.ID().getText();
		int value=visit(ctx.expr());
		memory.put(id, value);
		System.out.println(value);;
		return value;
	}
 
}
