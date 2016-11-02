package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*;
import Errors.Error;

public class CycleChecker implements ASTVisitor<String>{
  Integer cycle;
  Error error;

  public CycleChecker(Error error){
    cycle = 0;
    this.error = error;
  }

  @Override
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

    for (Statement statement : expr.getStatements()) {
      statement.accept(this);
    }

    return "";
  }

  public String visit(Body expr){
    if(expr.getBlock() != null)
      expr.getBlock().accept(this);
    return "";
  }

  public String visit(BreakStmt expr){
    if (cycle == 0)
      error.error11("BreakStmt", expr.getLine(), expr.getColumn());
    return "";
  }

  public String visit(ClassDecl expr){

    for (MethodDecl statement : expr.getMethodDecl()) {
      statement.accept(this);
    }

    return "";
  }

  public String visit(ContinueStmt expr){
    if (cycle == 0)
      error.error12("ContinueStmt", expr.getLine(), expr.getColumn());
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
    cycle++;
    stmt.getStatement().accept(this);
    cycle--;
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
    cycle++;
    stmt.getIfBlock().accept(this);
    if(stmt.getElseBlock() != null)
      stmt.getElseBlock().accept(this);
    cycle--;
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
    stmt.getBody().accept(this);
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
    return "";
  }

  public String visit(ReturnExpr stmt){
    if (stmt.getExpression() != null)
      stmt.getExpression().accept(this);
    return "";
  }

  public String visit(ReturnStmt stmt){
    if (stmt.getExpression() != null)
      stmt.getExpression().accept(this);
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
    cycle++;
    stmt.getStatement().accept(this);
    cycle--;
    return "";
  }

  public String visit(Instance stmt){
    return "";
  }
}
