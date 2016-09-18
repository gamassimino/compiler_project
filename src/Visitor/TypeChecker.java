package Visitor;

import ASTClass.*;
import TableOfHash;
import java.util.ArrayList;
import java_cup.runtime.*;

public class TypeChecker implements ASTVisitor<void>{

  public TypeChecker(){
  }

  @Override
  public void visit(AddAssignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
  }

  public void visit(And stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
  }

  public void visit(Assignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
  }

  public void visit(Block expr){
    hash.createLevel();
    for (Statement statement : expr.getExpressions()) {
      
    }
    hash.destroyLevel();
  }

  public void visit(Body expr){
    //elBody
  }

  public void visit(BreakStmt expr){
    //nada
  }

  public void visit(ClassDecl expr){
    hash.createLevel();
    hash.destroyLevel();

  }

  public void visit(ContinueStmt expr){
    //nada
  }

  public void visit(Divided expr){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
  }

  public void visit(EqualTo expr){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
  }

  public void visit(FieldDecl stmt){
    //insertar
  }

  public void visit(ForStmt stmt){
    hash.createLevel();
    hash.destroyLevel();
  }

  public void visit(Greater stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
  }

  public void visit(GreaterOrEq stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
  }

  public void visit(IdName stmt){
    //referenciar 
    //nada
  }

  public void visit(IfStmt stmt){
    hash.createLevel();
    hash.destroyLevel();
  }

  public void visit(IntLiteral stmt){
    //nada
  }

  public void visit(FloatLiteral stmt){
    //nada
  }

  public void visit(BoolLiteral stmt){
    //nada
  }

  public void visit(Less expr){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
  }

  public void visit(LessOrEq expr){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
  }

  public void visit(Literal stmt){
    //nada
  }

  public void visit(LocationExpr stmt){
    //visitar
  }

  public void visit(LocationStmt stmt){
    //visitar
  }

  public void visit(MethodCallStmt stmt){
    //nada
  }

  public void visit(MethodCallExpr stmt){
    //nada
  }

  public void visit(MethodDecl stmt){
    //nose
  }

  public void visit(Minus stmt){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
  }

  public void visit(Navigation stmt){
    //nada
  }

  public void visit(Not stmt){
    stmt.getExpr().accept(this);
  }

  public void visit(NotEqualTo stmt){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
  }

  public void visit(Or stmt){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
  }

  public void visit(Expression expr){
    expr.accept(this);
  }

  public void visit(Param stmt){
    hash.searchInLavel(stmt);
  }

  public void visit(Percentage stmt){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
  }

  public void visit(Plus stmt){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
  }

  public void visit(Program stmt){
    //nada
  }

  public void visit(ReturnExpr stmt){
    if (stmt.getExpression() != null)
      stmt.getExpression().accept(this);
  }

  public void visit(ReturnStmt stmt){
    if (stmt.getExpression() != null)
      stmt.getExpression().accept(this);
  }

  public void visit(SubAssignment expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
  }

  public void visit(Times expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
  }

  public void visit(Type stmt){
    //nada
  }

  public void visit(WhileStmt stmt){
    hash.createLevel();
    hash.destroyLevel();
  }
}
