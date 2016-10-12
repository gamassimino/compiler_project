package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java_cup.runtime.*;

public class AssignOffset implements ASTVisitor<String>{
  Hash hash;
  Hash hash_class;
  Integer offset;

  public AssignOffset(Hash classes, Integer an_offset){
    hash = new Hash();
    hash_class = classes;
    offset = an_offset;
  }

  public Integer nextOffset(){
    offset -= 4;
    return offset;
  }

  public Integer getOffset(){
    return offset;
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

  public Type getTypeExpression(Expression expr){
    return expr.getType();
  }

  public String visit(Instance stmt){
    return "";
  }
  
}
