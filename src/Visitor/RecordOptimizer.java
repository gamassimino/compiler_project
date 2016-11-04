package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java_cup.runtime.*;
public class RecordOptimizer implements ASTVisitor<String>{
  private Hash hash;
  private Error errors;
  private LinkedList<Integer> callList;

  public RecordOptimizer(){
    hash = new Hash();
    callList = new LinkedList<Integer> ();
    callList.add(6);
  }

  public String visit(AddAssignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    return "";
  }

  public String visit(And stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    return "";
  }

  public String visit(Assignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
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
    for (MethodDecl statement : expr.getMethodDecl()) {
      statement.accept(this);
    }
    return "";
  }

  public String visit(ContinueStmt expr){
    return "";
  }

  public String visit(Divided expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    return "";
  }

  public String visit(EqualTo expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    return "";
  }

  public String visit(FieldDecl stmt){
    return "";
  }

  public String visit(ForStmt stmt){
    return "";
  }

  public String visit(Greater stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    return "";
  }

  public String visit(GreaterOrEq stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
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
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    return "";
  }

  public String visit(LessOrEq expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    return "";
  }

  public String visit(LocationExpr stmt){
    return "";
  }

  public String visit(LocationStmt stmt){
    return "";
  }

  public String visit(MethodCallStmt stmt){
    stmt.setAttrNum(callList.getLast());
    callList.add(stmt.getExpressions().size());
    MethodDecl md = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
    if (md != null) {
      md.accept(this);
    }
    callList.removeLast();
    return "";
  }

  public String visit(MethodCallExpr stmt){
    stmt.setAttrNum(callList.getLast());
    callList.add(stmt.getExpressions().size());
    MethodDecl md = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
    if (md != null) {
      md.accept(this);
    }
    callList.removeLast();
    return "";
  }

  public String visit(MethodDecl stmt){
    hash.insertInLevel(stmt);
    if (stmt.getBody() != null) {
      if (stmt.getBody().getBlock() != null) {
        for (Statement st : stmt.getBody().getBlock().getStatements()) {
          st.accept(this);
        } 
      }
    }
    return "";
  }

  public String visit(Minus stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    return "";
  }

  public String visit(Navigation stmt){
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

  public String visit(Param stmt){
    return "";
  }

  public String visit(Percentage stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    return "";
  }

  public String visit(Plus stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    return "";
  }

  public String visit(Program stmt){
    hash.createLevel();
    for (ClassDecl class_decl : stmt.getClassList()) {
      class_decl.accept(this);
    }
    hash.destroyLevel();
    return "";
  }

  public String visit(ReturnExpr stmt){
    return "";
  }

  public String visit(ReturnStmt stmt){
    return "";
  }

  public String visit(SubAssignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    return "";
  }

  public String visit(Times expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    return "";
  }

  public String visit(Type stmt){
    return "";
  }

  public String visit(WhileStmt stmt){
    return "";
  }

  public String visit(Instance stmt){
    return "";
  }
}
