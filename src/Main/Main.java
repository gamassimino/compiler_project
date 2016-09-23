package Main;

import ASTClass.*;
import java.util.*;
import java_cup.runtime.*;
import CodeGenerated.Parser;
import CodeGenerated.Scanner;
import Visitor.*;
import TableOfHash.Hash;
import Errors.Error;

public class Main{
  public static void main(String args[]) throws Exception {
    ComplexSymbolFactory sf = new ComplexSymbolFactory();
    Program p = (Program) new Parser(new Scanner(new java.io.FileInputStream(args[0]),sf),sf).parse().value;
    Error errors = new Error();
    DeclarationChecker declarationChecker = new DeclarationChecker(errors);
    MainChecker mainChecker = new MainChecker();
    TypeChecker typeChecker = new TypeChecker(errors);
    CycleChecker cycleChecker = new CycleChecker();
    ReturnChecker returnChecker = new ReturnChecker();

    p.accept(declarationChecker);
    p.accept(typeChecker);
    for (int i = errors.getErrors().size() -1 ;i>=0 ; i-- ) {
      System.out.println(errors.getErrors().get(i));
    }
    // p.accept(cycleChecker);
    // p.accept(returnChecker);
    // System.out.println(p.accept(typeChecker));
  }

}
