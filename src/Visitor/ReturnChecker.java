package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*;
import Errors.Error;

public class ReturnChecker implements ASTVisitor<String>{
  boolean return_flag;
  Error error;

  public ReturnChecker(Error error){
    return_flag = false;
    this.error = error;
  }

  @Override
  public String visit(AddAssignment stmt){
    return "";
  }

  public String visit(And stmt){
    //visitar
    return "";
  }

  public String visit(Assignment stmt){
    return "";
  }

  public String visit(Block expr){

    for (Statement statement : expr.getStatements()) {
      statement.accept(this);
    }

    return "";
  }

  public String visit(Body expr){
    if(expr.getBlock() != null)
      expr.getBlock().accept(this);
    else
      return_flag = true;
    //elBody
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
    stmt.getStatement().accept(this);
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
    stmt.getIfBlock().accept(this);
    if(stmt.getElseBlock() != null)
      stmt.getElseBlock().accept(this);
    return "";
  }

  public String visit(IntLiteral stmt){
    //nada
    return "";
  }

  public String visit(FloatLiteral stmt){
    //nada
    return "";
  }

  public String visit(BoolLiteral stmt){
    //nada
    return "";
  }

  public String visit(Less expr){
    return "";
  }

  public String visit(LessOrEq expr){
    return "";
  }

  public String visit(Literal stmt){
    //nada
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

  // the body create a level and destroy it
  public String visit(MethodDecl stmt){
    return_flag = false;
    stmt.getBody().accept(this);
    if (!return_flag)
      error.error10("MethodDecl", stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Minus stmt){
    return "";
  }

  public String visit(Navigation stmt){
    // i don't know what put here
    // nada
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
    expr.accept(this);
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
    for (ClassDecl class_decl : stmt.getClassList()) {
      class_decl.accept(this);
    }
    //nada
    return "";
  }

  public String visit(ReturnExpr stmt){
    return_flag = true;
    return "";
  }

  public String visit(ReturnStmt stmt){
    return_flag = true;
    return "";
  }

  public String visit(SubAssignment stmt){
    return "";
  }

  public String visit(Times expr){
    return "";
  }

  public String visit(Type stmt){
    //nada
    return "";
  }

  public String visit(WhileStmt stmt){
    stmt.getStatement().accept(this);
    return "";
  }
  
  public String visit(Instance stmt){
    return "";
  }

}
