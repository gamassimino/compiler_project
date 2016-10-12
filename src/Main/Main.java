package Main;

import ASTClass.*;
import java.util.*;
import java_cup.runtime.*;
import CodeGenerated.Parser;
import CodeGenerated.Scanner;
import Visitor.*;
import TableOfHash.Hash;
import Errors.Error;
import Assembly.*;

public class Main{
  public static void main(String args[]) throws Exception {
    Error errors = new Error();
    Hash classes = new Hash();
    Integer offset = new Integer(0);
    ComplexSymbolFactory sf = new ComplexSymbolFactory();
    Program p = (Program) new Parser(new Scanner(new java.io.FileInputStream(args[0]),sf),sf).parse().value;
    DeclarationChecker declarationChecker = new DeclarationChecker(errors, classes);
    TypeChecker typeChecker = new TypeChecker(errors, classes, offset);
    MainChecker mainChecker = new MainChecker(errors);
    CycleChecker cycleChecker = new CycleChecker(errors);
    ReturnChecker returnChecker = new ReturnChecker(errors);
    IntermediateCode intermediateCode = new IntermediateCode();
    // AssignOffset assignOffset = new AssignOffset(classes, offset);

    p.accept(declarationChecker);
    if(errors.getErrors().size() == 0)
      p.accept(typeChecker);
    if(errors.getErrors().size() == 0)
      p.accept(mainChecker);
    if(errors.getErrors().size() == 0)
      p.accept(cycleChecker);
    if(errors.getErrors().size() == 0)
      p.accept(returnChecker);
    if(errors.getErrors().size() == 0)
      p.accept(intermediateCode);
    // if(errors.getErrors().size() == 0)
    //   p.accept(assignOffset);

    for (String error: errors.getErrors()) {
      System.out.println(error);
    }

    AsmGenerator.writeAssembler(intermediateCode.getSentenceList());
  }

}
