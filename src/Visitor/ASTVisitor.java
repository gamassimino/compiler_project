package Visitor;

import ASTClass.*;

// Abstract visitor
public interface ASTVisitor<T> {
// visit statements
  public T visit(AddAssignment stmt);
  public T visit(And stmt);
  public T visit(Assignment stmt);
  public T visit(Block expr);
  public T visit(Body expr);
  public T visit(BreakStmt expr);
  public T visit(ClassDecl expr);
  public T visit(ContinueStmt expr);
  public T visit(Divided expr);
  public T visit(EqualTo expr);
  public T visit(FieldDecl expr);
  public T visit(ForStmt stmt);
  public T visit(Greater stmt);
  public T visit(GreaterOrEq stmt);
  public T visit(IdName stmt);
  public T visit(IfStmt stmt);
  public T visit(IntLiteral stmt);
  public T visit(BoolLiteral stmt);
  public T visit(FloatLiteral stmt);
  public T visit(Less stmt);
  public T visit(LessOrEq stmt);
  public T visit(LocationExpr stmt);
  public T visit(LocationStmt stmt);
  public T visit(MethodCallStmt stmt);
  public T visit(MethodCallExpr stmt);
  public T visit(MethodDecl stmt);
  public T visit(Minus stmt);
  public T visit(Navigation stmt);
  public T visit(Not stmt);
  public T visit(NotEqualTo stmt);
  public T visit(Or stmt);
  public T visit(Param stmt);
  public T visit(Percentage stmt);
  public T visit(Plus stmt);
  public T visit(Program stmt);
  public T visit(ReturnStmt stmt);
  public T visit(ReturnExpr stmt);
  public T visit(SubAssignment stmt);
  public T visit(Times stmt);
  public T visit(Type stmt);
  public T visit(WhileStmt stmt);
  public T visit(Instance stmt);
}
