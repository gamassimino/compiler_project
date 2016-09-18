package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java_cup.runtime.*;

public class TypeChecker implements ASTVisitor<String>{
  Hash hash;
  
  public TypeChecker(){
    hash = new Hash();
  }

  @Override
  public String visit(AddAssignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    if(stmt.getLeft().getType().toString() != stmt.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    //visitar
    return "";
  }

  public String visit(And stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    //visitar
    return "";
  }

  public String visit(Assignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    if(stmt.getLeft().getType().toString() != stmt.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    //visitar
    return "";
  }

  public String visit(Block expr){
    hash.createLevel();

    for (FieldDecl field_decl : expr.getFieldDecl()) {
      field_decl.accept(this);
    }

    for (Statement statement : expr.getStatements()) {
      statement.accept(this);
    }

    hash.destroyLevel();
    return "";
  }

  public String visit(Body expr){
    expr.getBlock().accept(this);
    //elBody
    return "";
  }

  public String visit(BreakStmt expr){
    if(expr.getExpression() != null)
      expr.getExpression().accept(this);
    //nada
    return "";
  }

  public String visit(ClassDecl expr){
    hash.createLevel();

    for (FieldDecl field_decl : expr.getFieldDecl()) {
      field_decl.accept(this);
    }

    for (MethodDecl statement : expr.getMethodDecl()) {
      statement.accept(this);
    }

    hash.destroyLevel();
    return "";
  }

  public String visit(ContinueStmt expr){
    //nada
    return "";
  }

  public String visit(Divided expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    if(expr.getLeft().getType().toString() != expr.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    //visitar
    return "";
  }

  public String visit(EqualTo expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    if(expr.getLeft().getType().toString() != expr.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    //visitar
    return "";
  }

  public String visit(FieldDecl stmt){
    hash.insertInLevel(stmt);
    // continue visit ?
    //insertar
    return "";
  }

  public String visit(ForStmt stmt){
    hash.createLevel();

    stmt.getCondition().accept(this);
    stmt.getStep().accept(this);
    stmt.getStatement().accept(this);

    hash.destroyLevel();
    return "";
  }

  public String visit(Greater stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    if(stmt.getLeft().getType().toString() != stmt.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    //visitar
    return "";
  }

  public String visit(GreaterOrEq stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    if(stmt.getLeft().getType().toString() != stmt.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    //visitar
    return "";
  }

  public String visit(IdName stmt){
    //referenciar 
    //nada
    return "";
  }

  public String visit(IfStmt stmt){
    hash.createLevel();



    hash.destroyLevel();
    if(stmt.getElseBlock() != null){
      hash.createLevel();



      hash.destroyLevel();
    }
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
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    if(expr.getLeft().getType().toString() != expr.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    //visitar
    return "";
  }

  public String visit(LessOrEq expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    if(expr.getLeft().getType().toString() != expr.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    //visitar
    return "";
  }

  public String visit(Literal stmt){
    //nada
    return "";
  }

  public String visit(LocationExpr stmt){
    //visitar
    return "";
  }

  public String visit(LocationStmt stmt){
    //visitar
    return "";
  }

  public String visit(MethodCallStmt stmt){
    //nada
    return "";
  }

  public String visit(MethodCallExpr stmt){
    //nada
    return "";
  }

  public String visit(MethodDecl stmt){
    hash.createLevel();
    stmt.getParam().accept(this);
    stmt.getBody().accept(this);
    hash.destroyLevel();
    //nose
    return "";
  }

  public String visit(Minus stmt){
    stmt.getLeft().accept(this);
    if(stmt.getRight() != null){
      stmt.getRight().accept(this);
    }
    else{
      if(stmt.getLeft().getType().toString() != stmt.getRight().getType().toString())
        System.out.println("NO ES DEL MISMO TIPOO");
    }
    return "";
  }

  public String visit(Navigation stmt){
    //nada
    return "";
  }

  public String visit(Not stmt){
    stmt.getExpr().accept(this);
    return "";
  }

  public String visit(NotEqualTo stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    return "";
  }

  public String visit(Or stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    return "";
  }

  public String visit(Expression expr){
    expr.accept(this);
    return "";
  }

  public String visit(Param stmt){
    hash.searchInLevel(stmt);
    return "";
  }

  public String visit(Percentage stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    if(stmt.getLeft().getType().toString() != stmt.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    return "";
  }

  public String visit(Plus stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    if(stmt.getLeft().getType().toString() != stmt.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
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
    if (stmt.getExpression() != null)
      stmt.getExpression().accept(this);
    return "";
  }

  public String visit(ReturnStmt stmt){
    if (stmt.getExpression() != null)
      stmt.getExpression().accept(this);
    return "";
  }

  public String visit(SubAssignment expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    if(expr.getLeft().getType().toString() != expr.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    return "";
  }

  public String visit(Times expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    if(expr.getLeft().getType().toString() != expr.getRight().getType().toString())
      System.out.println("NO ES DEL MISMO TIPOO");
    return "";
  }

  public String visit(Type stmt){
    //nada
    return "";
  }

  public String visit(WhileStmt stmt){
    hash.createLevel();

    stmt.getCondition().accept(this);
    stmt.getStatement().accept(this);

    hash.destroyLevel();
    return "";
  }
}
