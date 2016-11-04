package Main;

import ASTClass.*;
import java.util.*;
import java_cup.runtime.*;
import CodeGenerated.Parser;
import CodeGenerated.Scanner;
import Visitor.*;
import TableOfHash.Hash;
import TableOfHash.Heap;
import TableOfHash.InstanceOffset;
import Errors.Error;
import Assembly.*;

public class Main{
  public static void main(String args[]) throws Exception {
    LinkedList<Pair<String,Integer>> methodNameOffset = new LinkedList<Pair<String,Integer>>();
    Error errors = new Error();
    Hash classes = new Hash();
    Heap heap = new Heap();
    Hash instances = new Hash();
    InstanceOffset insOff = new InstanceOffset();
    Integer offset = new Integer(0);

    ComplexSymbolFactory sf = new ComplexSymbolFactory();
    Program p = (Program) new Parser(new Scanner(new java.io.FileInputStream(args[0]),sf),sf).parse().value;
    DeclarationChecker declarationChecker = new DeclarationChecker(errors, classes, insOff, offset, methodNameOffset);
    TypeChecker typeChecker = new TypeChecker(errors, classes, offset);
    MainChecker mainChecker = new MainChecker(errors);
    CycleChecker cycleChecker = new CycleChecker(errors);
    ReturnChecker returnChecker = new ReturnChecker(errors);
    IntermediateCode intermediateCode = new IntermediateCode(offset, methodNameOffset);
    ConstantOptimizer constantOptimizer = new ConstantOptimizer();
    RecordOptimizer recordOptimizer = new RecordOptimizer();

    p.accept(declarationChecker);
    offset = declarationChecker.getOffset();
    heap = declarationChecker.getHeap();

    instances = declarationChecker.getHashInstance();
    intermediateCode.setHashInstance(instances);

    typeChecker.setOffset(offset);
    if(errors.getErrors().size() == 0)
      p.accept(typeChecker);

      offset = typeChecker.getOffset();
    if(errors.getErrors().size() == 0)
      p.accept(mainChecker);

    if(errors.getErrors().size() == 0)
      p.accept(cycleChecker);

    if(errors.getErrors().size() == 0)
      p.accept(returnChecker);

    // run the optimizer of code

    if(errors.getErrors().size() == 0)
      p.accept(constantOptimizer);

    if(errors.getErrors().size() == 0)
      p.accept(recordOptimizer);

    intermediateCode.setOffset(offset);
    intermediateCode.setHeap(heap);
    if(errors.getErrors().size() == 0)
      p.accept(intermediateCode);

    for (String error: errors.getErrors()) {
      System.out.println(error);
    }

    intermediateCode.setInstanceOffset(insOff);
    if(errors.getErrors().size() == 0)
      AsmGenerator.writeAssembler(intermediateCode.getSentenceList());
  }

}
