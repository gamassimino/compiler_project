package Visitor;

import ASTClass.*;
import java.util.LinkedList;
import java_cup.runtime.*;
import Assembly.Sentence;

public class IntermediateCode implements ASTVisitor<LocationExpr>{

  LinkedList<Sentence> sentence_list;

  public LocationExpr visit(AddAssignment stmt){
    sentence_list = new LinkedList<Sentence>();
    return null;
  }

  public LocationExpr visit(And stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("And");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("And", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Assignment stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("Assignment");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("Assignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Block expr){
    return null;
  }

  public LocationExpr visit(Body expr){
    return null;
  }

  public LocationExpr visit(BreakStmt expr){
    return null;
  }

  public LocationExpr visit(ClassDecl expr){
    return null;
  }

  public LocationExpr visit(ContinueStmt expr){
    return null;
  }

  public LocationExpr visit(Divided expr){
    LocationExpr left = expr.getLeft().accept(this);
    LocationExpr right = expr.getRight().accept(this);
    IdName name = new IdName("Divided");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("Divided", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(EqualTo expr){
    LocationExpr left = expr.getLeft().accept(this);
    LocationExpr right = expr.getRight().accept(this);
    IdName name = new IdName("EqualTo");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("EqualTo", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(FieldDecl expr){
    return null;
  }

  public LocationExpr visit(ForStmt stmt){
    return null;
  }

  public LocationExpr visit(Greater stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("Greater");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("Greater", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(GreaterOrEq stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("GreaterOrEq");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("GreaterOrEq", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(IdName stmt){
    return null;
  }

  public LocationExpr visit(IfStmt stmt){
    return null;
  }

  public LocationExpr visit(IntLiteral stmt){
    IdName name = new IdName("IntLiteral");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("IntLiteral", null, null, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(BoolLiteral stmt){
    IdName name = new IdName("BoolLiteral");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("BoolLiteral", null, null, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(FloatLiteral stmt){
    IdName name = new IdName("FloatLiteral");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("FloatLiteral", null, null, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Less stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("Less");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("Less", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(LessOrEq stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("LessOrEq");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("LessOrEq", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(LocationExpr stmt){
    return null;
  }

  public LocationExpr visit(LocationStmt stmt){
    return null;
  }

  public LocationExpr visit(MethodCallStmt stmt){
    return null;
  }

  public LocationExpr visit(MethodCallExpr stmt){
    return null;
  }

  public LocationExpr visit(MethodDecl stmt){
    return null;
  }

  public LocationExpr visit(Minus stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("Minus");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("Minus", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Navigation stmt){
    return null;
  }

  public LocationExpr visit(Not stmt){
    return null;
  }

  public LocationExpr visit(NotEqualTo stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("NotEqualTo");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("NotEqualTo", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Or stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("Or");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("Or", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Param stmt){
    return null;
  }

  public LocationExpr visit(Percentage stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("Percentage");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("Percentage", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Plus stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("Plus");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("Plus", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Program stmt){
    return null;
  }

  public LocationExpr visit(ReturnStmt stmt){
    return null;
  }

  public LocationExpr visit(ReturnExpr stmt){
    return null;
  }

  public LocationExpr visit(SubAssignment stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("SubAssignment");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("SubAssignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Times stmt){
    LocationExpr left = stmt.getLeft().accept(this);
    LocationExpr right = stmt.getRight().accept(this);
    IdName name = new IdName("Times");
    LocationExpr t0 = new LocationExpr(name);
    Sentence result = new Sentence("Times", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public LocationExpr visit(Type stmt){
    return null;
  }

  public LocationExpr visit(WhileStmt stmt){
    return null;
  }
}