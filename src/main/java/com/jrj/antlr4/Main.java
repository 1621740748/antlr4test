package com.jrj.antlr4;

//Main.java
import java.io.FileInputStream;
import java.io.InputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
  public static void main(String[] args) throws Exception {
     // create a CharStream thatreads from standard input
     String inputFile = "data.txt";
     InputStream is = System.in;
    
     if ( inputFile!=null ) is = new FileInputStream(Main.class.getResource(inputFile).getFile());
     ANTLRInputStream input = new ANTLRInputStream(is);
    
     calcLexer lexer = new calcLexer(input);
     CommonTokenStream tokens = new CommonTokenStream(lexer);
     calcParser parser = new calcParser(tokens);
     ParseTree tree = parser.prog(); // parse
     EvalVisitor eval = new EvalVisitor();
     eval.visit(tree);
  }
}
