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

    for (FieldDecl field_decl : expr.getExpressions()) {
      field_decl().accept(this);
    }

    for (Statement statement : expr.getExpressions()) {
      statement().accept(this);
    }

    hash.destroyLevel();
  }

  public void visit(Body expr){
    expr.getBlock().accept(this);
    //elBody
  }

  public void visit(BreakStmt expr){
    if(pr.getExpression() != null)
      expr.getExpression().accept(this);
    //nada
  }

  public void visit(ClassDecl expr){
    hash.createLevel();

    for (FieldDecl field_decl_list : expr.getFieldDecl()) {
      field_decl().accept(this);
    }

    for (MethodDecl statement : expr.getMethodDecl()) {
      statement().accept(this);
    }

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
    hash.insertInLevel(stmt);
    // continue visit ?
    //insertar
  }

  public void visit(ForStmt stmt){
    hash.createLevel();

    stmt.getCondition().accept(this);
    stmt.getStep().accept(this);
    stmt.getStatement().accept(this);

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
    if(stmt.getElseBlock() != null){
      hash.createLevel();



      hash.destroyLevel();
    }
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
    hash.createLevel();
    stmt.getParam().accept(this);
    stmt.getBody().accept(this);
    hash.destroyLevel();
    //nose
  }

  public void visit(Minus stmt){
    stmt.getLeft().accept(this);
    if(stmt.getRight() !=)
    stmt.getRight().accept(this);
  }

  public void visit(Navigation stmt){
    //nada
  }

  public void visit(Not stmt){
    stmt.getExpr().accept(this);
  }

  public void visit(NotEqualTo stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
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
    for (ClassDecl class_decl : stmt.getClassDecl()) {
      class.accept(this);
    }
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

    stmt.getCondition.accept(this);
    stmt.getStatement.accept(this);

    hash.destroyLevel();
  }
}
