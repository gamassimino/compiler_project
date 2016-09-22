package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java_cup.runtime.*;

public class MainChecker implements ASTVisitor<String>{
  Hash hash;

  public MainChecker(){
    hash = new Hash();
  }


  public String visit(AddAssignment stmt){
    return "";
  }

  public String visit(And stmt){
    return "";
  }

  public String visit(Assignment stmt){
    return "";
  }

  public String visit(Block expr){
    return "";
  }

  public String visit(Body expr){
    return "";
  }

  public String visit(BreakStmt expr){
    return "";
  }

  public String visit(ClassDecl expr){
    for (MethodDecl statement : expr.getMethodDecl()) {
      statement.accept(this);
    }
    return "";
  }

  public String visit(ContinueStmt expr){
    return "";
  }

  public String visit(Divided expr){
    return "";
  }

  public String visit(EqualTo expr){
    return "";
  }

  public String visit(FieldDecl stmt){
    return "";
  }

  public String visit(ForStmt stmt){
    return "";
  }

  public String visit(Greater stmt){
    return "";
  }

  public String visit(GreaterOrEq stmt){
    return "";
  }

  public String visit(IdName stmt){
    return "";
  }

  public String visit(IfStmt stmt){
    return "";
  }

  public String visit(IntLiteral stmt){
    return "";
  }

  public String visit(FloatLiteral stmt){
    return "";
  }

  public String visit(BoolLiteral stmt){
    return "";
  }

  public String visit(Less expr){
    return "";
  }

  public String visit(LessOrEq expr){
    return "";
  }

  public String visit(Literal stmt){
    return "";
  }

  public String visit(LocationExpr stmt){
    return "";
  }

  public String visit(LocationStmt stmt){
    return "";
  }

  public String visit(MethodCallStmt stmt){
    return "";
  }

  public String visit(MethodCallExpr stmt){
    return "";
  }

  public String visit(MethodDecl stmt){
    hash.insertInLevel(stmt);
    return "";
  }

  public String visit(Minus stmt){
    return "";
  }

  public String visit(Navigation stmt){
    return "";
  }

  public String visit(Not stmt){
    return "";
  }

  public String visit(NotEqualTo stmt){
    return "";
  }

  public String visit(Or stmt){
    return "";
  }

  public String visit(Expression expr){
    return "";
  }

  // need the new implementation of FieldDecl
  public String visit(Param stmt){
    return "";
  }

  public String visit(Percentage stmt){
    return "";
  }

  public String visit(Plus stmt){
    return "";
  }

  public String visit(Program stmt){
    hash.createLevel();
    for (ClassDecl class_decl : stmt.getClassList()) {
      if(hash.searchInLastLevelCD(class_decl.getIdName().toString()) == null){
        hash.insertInLevel(class_decl);
        if(class_decl.getIdName().toString().equals("main")){
          class_decl.accept(this);
          if(hash.searchInTableMD("main") == null)
            System.out.println("Metodo Main no definido");
        }
      }else
        System.out.println("Clase Repetida");
    }
    if(hash.searchInLastLevelCD("main") == null)
      System.out.println("Clase Main no definida");
    hash.destroyLevel();
    return "";
  }

  public String visit(ReturnExpr stmt){
    return "";
  }

  public String visit(ReturnStmt stmt){
    return "";
  }

  public String visit(SubAssignment stmt){
    return "";
  }

  public String visit(Times expr){
    return "";
  }

  public String visit(Type stmt){
    return "";
  }

  public String visit(WhileStmt stmt){
    return "";
  }

}
