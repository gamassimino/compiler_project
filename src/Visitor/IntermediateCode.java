package Visitor;

import ASTClass.*;
import java.util.LinkedList;
import java_cup.runtime.*;
import Assembly.Sentence;

public class IntermediateCode implements ASTVisitor<ExpressionAlgo>{

  LinkedList<Sentence> sentence_list;

  public IntermediateCode(){
    sentence_list = new LinkedList<Sentence>();
  }
  public LinkedList<Sentence> getSentenceList(){
    return sentence_list;
  }

  public ExpressionAlgo visit(AddAssignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getLeft().getType());
    Sentence result = new Sentence("AddAssignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(And stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    BoolLiteral result_value = new BoolLiteral(left.getExpression()+"&&"+right.getExpression());
    ExpressionAlgo t0 = new ExpressionAlgo(result_value);
    Sentence result = new Sentence("And", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Assignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getLeft().getType());
    Sentence result = new Sentence("Assignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Block expr){
    for (Statement statement : expr.getStatements()) {
      statement.accept(this);
    }
    return null;
  }

  public ExpressionAlgo visit(Body expr){
    expr.getBlock().accept(this);
    return null;
  }

  public ExpressionAlgo visit(BreakStmt expr){
    return null;
  }

  public ExpressionAlgo visit(ClassDecl expr){
    for (MethodDecl method_decl : expr.getMethodDecl()) {
      method_decl.accept(this);
    }
    return null;
  }

  public ExpressionAlgo visit(ContinueStmt expr){
    return null;
  }

  public ExpressionAlgo visit(Divided expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Divided", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(EqualTo expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("EqualTo", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(FieldDecl expr){
    return null;
  }

  public ExpressionAlgo visit(ForStmt stmt){
    return null;
  }

  public ExpressionAlgo visit(Greater stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Greater", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(GreaterOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("GreaterOrEq", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(IdName stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getType());
    return t0;
  }

  public ExpressionAlgo visit(IfStmt stmt){
    return null;
  }

  public ExpressionAlgo visit(IntLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt);
    return t0;
  }

  public ExpressionAlgo visit(BoolLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt);
    return t0;
  }

  public ExpressionAlgo visit(FloatLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt);
    return t0;
  }

  public ExpressionAlgo visit(Less stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Less", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(LessOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("LessOrEq", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(LocationExpr stmt){
    return stmt.getId().accept(this);
  }

  public ExpressionAlgo visit(LocationStmt stmt){
    return null;
  }

  public ExpressionAlgo visit(MethodCallStmt stmt){
    return null;
  }

  public ExpressionAlgo visit(MethodCallExpr stmt){
    return null;
  }

  public ExpressionAlgo visit(MethodDecl stmt){
    // stmt.getType().accept(this);
    // stmt.getIdName().accept(this);
    // stmt.getParam().accept(this);
    stmt.getBody().accept(this);
    return null;
  }

  public ExpressionAlgo visit(Minus stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Minus", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Navigation stmt){
    return null;
  }

  public ExpressionAlgo visit(Not stmt){
    return null;
  }

  public ExpressionAlgo visit(NotEqualTo stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("NotEqualTo", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Or stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Or", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Param stmt){
    return null;
  }

  public ExpressionAlgo visit(Percentage stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Percentage", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Plus stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Plus", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Program stmt){
    for (ClassDecl clas : stmt.getClassList()) {
      clas.accept(this);
    }
    return null;
  }

  public ExpressionAlgo visit(ReturnStmt stmt){
    Sentence result = new Sentence("ReturnStmt", null, null, null);
    sentence_list.add(result);
    return null;
  }

  public ExpressionAlgo visit(ReturnExpr stmt){
    // if (stmt.getExpression() != null)
      ExpressionAlgo t0 = new ExpressionAlgo(stmt.getExpression());
    Sentence result = new Sentence("ReturnExpr", null, null, t0);
    sentence_list.add(result);
    return null;
  }

  public ExpressionAlgo visit(SubAssignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("SubAssignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Times stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Times", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Type stmt){
    return null;
  }

  public ExpressionAlgo visit(WhileStmt stmt){
    return null;
  }
}