package Visitor;

import ASTClass.*;
import java.util.LinkedList;
import java_cup.runtime.*;
import Assembly.Sentence;

public class IntermediateCode implements ASTVisitor<Expression>{

  LinkedList<Sentence> sentence_list;

  public LinkedList<Sentence> getSentenceList(){
    return sentence_list;
  }

  public Expression visit(Expression stmt){
    return null;
  }

  public Expression visit(AddAssignment stmt){
    sentence_list = new LinkedList<Sentence>();
    return null;
  }

  public Expression visit(And stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("And", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(Assignment stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("Assignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(Block expr){
    return null;
  }

  public Expression visit(Body expr){
    return null;
  }

  public Expression visit(BreakStmt expr){
    return null;
  }

  public Expression visit(ClassDecl expr){
    return null;
  }

  public Expression visit(ContinueStmt expr){
    return null;
  }

  public Expression visit(Divided expr){
    Expression left = expr.getLeft().accept(this);
    Expression right = expr.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("Divided", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(EqualTo expr){
    Expression left = expr.getLeft().accept(this);
    Expression right = expr.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("EqualTo", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(FieldDecl expr){
    return null;
  }

  public Expression visit(ForStmt stmt){
    return null;
  }

  public Expression visit(Greater stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("Greater", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(GreaterOrEq stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("GreaterOrEq", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(IdName stmt){
    return null;
  }

  public Expression visit(IfStmt stmt){
    return null;
  }

  public Expression visit(IntLiteral stmt){
    // Expression t0 = new Expression(stmt.getType());
    // Sentence result = new Sentence("IntLiteral", null, null, t0);
    // sentence_list.add(result);
    return stmt;
  }

  public Expression visit(BoolLiteral stmt){
    // Expression t0 = new Expression(stmt.getType());
    // Sentence result = new Sentence("BoolLiteral", null, null, t0);
    // sentence_list.add(result);
    return stmt;
  }

  public Expression visit(FloatLiteral stmt){
    // Expression t0 = new Expression(stmt.getType());
    // Sentence result = new Sentence("FloatLiteral", null, null, t0);
    // sentence_list.add(result);
    return stmt;
  }

  public Expression visit(Less stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("Less", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(LessOrEq stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("LessOrEq", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(LocationExpr stmt){
    return null;
  }

  public Expression visit(LocationStmt stmt){
    return null;
  }

  public Expression visit(MethodCallStmt stmt){
    return null;
  }

  public Expression visit(MethodCallExpr stmt){
    return null;
  }

  public Expression visit(MethodDecl stmt){
    return null;
  }

  public Expression visit(Minus stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("Minus", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(Navigation stmt){
    return null;
  }

  public Expression visit(Not stmt){
    return null;
  }

  public Expression visit(NotEqualTo stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("NotEqualTo", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(Or stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("Or", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(Param stmt){
    return null;
  }

  public Expression visit(Percentage stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("Percentage", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(Plus stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("Plus", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(Program stmt){
    return null;
  }

  public Expression visit(ReturnStmt stmt){
    return null;
  }

  public Expression visit(ReturnExpr stmt){
    return null;
  }

  public Expression visit(SubAssignment stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("SubAssignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(Times stmt){
    Expression left = stmt.getLeft().accept(this);
    Expression right = stmt.getRight().accept(this);
    Expression t0 = new Expression(left.getType());
    Sentence result = new Sentence("Times", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public Expression visit(Type stmt){
    return null;
  }

  public Expression visit(WhileStmt stmt){
    return null;
  }
}