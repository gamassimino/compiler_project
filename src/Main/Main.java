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
    DeclarationChecker declarationChecker = new DeclarationChecker();
    MainChecker mainChecker = new MainChecker();
    TypeChecker typeChecker = new TypeChecker();
    CycleChecker cycleChecker = new CycleChecker();

    p.accept(declarationChecker);
    p.accept(cycleChecker);
    System.out.println(p.accept(typeChecker));
  }

  public void syntax_error(Symbol sym){
    // Mute legacy Error Printing
  }
}
