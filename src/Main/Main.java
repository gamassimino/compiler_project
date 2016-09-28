package Main;

import ASTClass.*;
import java.util.*;
import java_cup.runtime.*;
import CodeGenerated.Parser;
import CodeGenerated.Scanner;
import Visitor.*;
import TableOfHash.Hash;
import Errors.Error;
import Assembly.Sentence;

public class Main{
  public static void main(String args[]) throws Exception {
    ComplexSymbolFactory sf = new ComplexSymbolFactory();
    Program p = (Program) new Parser(new Scanner(new java.io.FileInputStream(args[0]),sf),sf).parse().value;
    Error errors = new Error();
    DeclarationChecker declarationChecker = new DeclarationChecker(errors);
    MainChecker mainChecker = new MainChecker(errors);
    TypeChecker typeChecker = new TypeChecker(errors);
    CycleChecker cycleChecker = new CycleChecker(errors);
    ReturnChecker returnChecker = new ReturnChecker(errors);
    IntermediateCode intermediateCode = new IntermediateCode();


    p.accept(declarationChecker);
    p.accept(typeChecker);
    p.accept(mainChecker);
    p.accept(cycleChecker);
    p.accept(returnChecker);
    for (String error: errors.getErrors()) {
      System.out.println(error);
    }

    p.accept(intermediateCode);
    for (Sentence s : intermediateCode.getSentenceList()) {
        System.out.println(s.toString());
    }
    // System.out.println(p.accept(typeChecker));
  }

}
