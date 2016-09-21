package Main;

import ASTClass.*;
import java.util.*;
import java_cup.runtime.*;
import CodeGenerated.Parser;
import CodeGenerated.Scanner;
import Visitor.*;
import TableOfHash.Hash;

public class Main{
  public static void main(String args[]) throws Exception {
    ComplexSymbolFactory sf = new ComplexSymbolFactory();
    Program p = (Program) new Parser(new Scanner(new java.io.FileInputStream(args[0]),sf),sf).parse().value;
    TypeChecker pr = new TypeChecker();
    DeclarationChecker prr = new DeclarationChecker();
    // for (ClassDecl cl : p.getClassList()) {
    //   PrintAST pr = new PrintAST();
    //   System.out.println(cl.accept(pr));
    // }
    p.accept(prr);
    System.out.println(p.accept(pr));
  }

  public void syntax_error(Symbol sym){
    // Mute legacy Error Printing
  }
}
