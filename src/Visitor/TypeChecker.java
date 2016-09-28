package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java_cup.runtime.*;
import Errors.Error;

public class TypeChecker implements ASTVisitor<String>{
  Hash hash;
  Error error;

  public TypeChecker(Error error){
    hash = new Hash();
    this.error = error;
  }

  public String visit(Expression stmt){
    return "";
  }

  public String visit(AddAssignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("AddAssignment","boolean", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("AddAssignment",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(And stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("And","integers or floats", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("And",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Assignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("Assignment","", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Assignment",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Block expr){
    hash.createLevel();

    for (List<FieldDecl> fields_decl : expr.getFieldDecl()) {
      for (FieldDecl field : fields_decl) {
        field.accept(this);
      }
    }

    for (Statement statement : expr.getStatements()) {
      statement.accept(this);
    }

    hash.destroyLevel();
    return "";
  }

  public String visit(Body expr){
    expr.getBlock().accept(this);
    return "";
  }

  public String visit(BreakStmt expr){
    return "";
  }

  public String visit(ClassDecl expr){
    hash.createLevel();

    for (List<FieldDecl> fields_decl : expr.getFieldDecl()) {
      for (FieldDecl field : fields_decl) {
        field.accept(this);
      }
    }

    for (MethodDecl statement : expr.getMethodDecl()) {
      statement.accept(this);
    }

    hash.destroyLevel();
    return "";
  }

  public String visit(ContinueStmt expr){
    return "";
  }

  public String visit(Divided expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    Type left = getTypeExpression(expr.getLeft());
    Type right = getTypeExpression(expr.getRight());
    if (!expr.supportOp())
      error.error4("Divided","boolean", expr.getLine(), expr.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Divided",left.toString(),right.toString(), expr.getLine(), expr.getColumn());
    return "";
  }

  public String visit(EqualTo expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    Type left = getTypeExpression(expr.getLeft());
    Type right = getTypeExpression(expr.getRight());
    if (!expr.supportOp())
      error.error6("EqualTo","integers, floats, booleans", expr.getLine(), expr.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("EqualTo",left.toString(),right.toString(), expr.getLine(), expr.getColumn());
    return "";
  }

  public String visit(FieldDecl stmt){
    hash.insertInLevel(stmt);
    // stmt.getId().accept(this);
    return "";
  }


  // the for create a innecessary level
  // on the hash, but it's concecuense
  // of the implementation the visitor of block
  // in this case, the first level onl contain
  // the declaration of the integer i for example
  public String visit(ForStmt stmt){
    hash.createLevel();
    stmt.getCondition().accept(this);
    stmt.getStep().accept(this);
    stmt.getStep();
    stmt.getStatement().accept(this);
    System.out.println();
    System.out.println();
    if (!stmt.getCondition().getType().toString().equals("integer")){
      String found = stmt.getCondition().getType().toString();
      error.error3("ForStmt(condition)","integer",found,stmt.getLine(),stmt.getColumn());
    }
    if (!stmt.getStep().getType().toString().equals("integer")){
      String found = stmt.getStep().getType().toString();
      error.error3("ForStmt(step)","integer",found,stmt.getLine(),stmt.getColumn());
    }
    hash.destroyLevel();
    return "";
  }

  public String visit(Greater stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("Greater","boolean", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Greater",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(GreaterOrEq stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("GreaterOrEq","boolean", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("GreaterOrEq",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(IdName stmt){
    if (stmt.getSize() != null){
      if (!stmt.getType().toString().equals("integer"))
        error.error6("IdName","integer",stmt.getLine(),stmt.getColumn());
      if (stmt.getSize().getValue() <= 0)
        error.error9("IdName", stmt.getLine(), stmt.getColumn());
    }
    return "";
  }

  public String visit(IfStmt stmt){
    if(stmt.getIfBlock().className() == "Block")
      stmt.getIfBlock().accept(this);
    else{
      hash.createLevel();

      stmt.getIfBlock().accept(this);

      hash.destroyLevel();
    }
    if(stmt.getElseBlock() != null){
      if(stmt.getElseBlock().className() == "Block")
        stmt.getElseBlock().accept(this);
      else{
        hash.createLevel();

        stmt.getElseBlock().accept(this);

        hash.destroyLevel();
      }
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
    Type left = getTypeExpression(expr.getLeft());
    Type right = getTypeExpression(expr.getRight());
    if (!expr.supportOp())
      error.error4("Less","boolean", expr.getLine(), expr.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Less",left.toString(),right.toString(), expr.getLine(), expr.getColumn());
    return "";
  }

  public String visit(LessOrEq expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    Type left = getTypeExpression(expr.getLeft());
    Type right = getTypeExpression(expr.getRight());
    if (!expr.supportOp())
      error.error4("LessOrEq","boolean", expr.getLine(), expr.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("LessOrEq",left.toString(),right.toString(), expr.getLine(), expr.getColumn());
    return "";
  }

  public String visit(LocationExpr stmt){
    stmt.getId().accept(this);
    if(stmt.getList() != null)
      stmt.getList().accept(this);
    // i don't know what put here
    //visitar
    return "";
  }

  public String visit(LocationStmt stmt){
    // i don't know what put here
    //visitar
    return "";
  }

  public String visit(MethodCallStmt stmt){
    MethodDecl founded = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
    int i = 0;
    List<Expression> list_param_call = stmt.getExpressions();
    if(founded.getParam().getParam().size() != stmt.getExpressions().size())
      error.error8("MethodCallStmt", stmt.getLine(), stmt.getColumn());
    else{
      if (founded.getParam().getParam().size() > 0) {
        for (Pair<Type, IdName> param_of_decl : founded.getParam().getParam()) {
          if(!list_param_call.get(i).getType().toString().equals(param_of_decl.getFst().toString()))
            error.error3("MethodCallStmt",param_of_decl.getFst().toString(),list_param_call.get(i).getType().toString(), stmt.getLine(), stmt.getColumn());
        i++;
        }
      }
    }
    return "";
  }

  public String visit(MethodCallExpr stmt){
    MethodDecl founded = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
    int i = 0;
    List<Expression> list_param_call = stmt.getExpressions();
    if(founded.getParam().getParam().size() != stmt.getExpressions().size())
      error.error8("MethodCallStmt", stmt.getLine(), stmt.getColumn());
    else{
      if (founded.getParam().getParam().size() > 0) {
        for (Pair<Type, IdName> param_of_decl : founded.getParam().getParam()) {
          if(!list_param_call.get(i).getType().toString().equals(param_of_decl.getFst().toString()))
            error.error3("MethodCallStmt",param_of_decl.getFst().toString(),list_param_call.get(i).getType().toString(), stmt.getLine(), stmt.getColumn());
        i++;
        }
      }
    }
    return "";
  }

  // the body create a level and destroy it
  public String visit(MethodDecl stmt){
      hash.insertInLevel(stmt);
      hash.createLevel();
      stmt.getParam().accept(this);
      stmt.getBody().accept(this);
      Block block = stmt.getBody().getBlock();
      for (Statement s: block.getStatements()) {
        if(s.getClass().toString().equals("class ASTClass.ReturnStmt")){
          ReturnStmt st = (ReturnStmt)s;
          if (stmt.getType().toString().equals("void")){
            if (st.getExpression() != null){
              String found = st.getExpression().getType().toString();
              error.error3("MethodDecl(return)",";",found, stmt.getLine(), stmt.getColumn());
            }
          }
          else{
            if (st.getExpression() == null){
              String expec = stmt.getType().toString();
              error.error3("MethodDecl(return)",expec,";", stmt.getLine(), stmt.getColumn());
            }
            else{
              if (!stmt.getType().toString().equals(st.getExpression().getType().toString())){
                String expec = stmt.getType().toString();
                String found = st.getExpression().getType().toString();
                error.error3("MethodDecl(return)",expec,found, stmt.getLine(), stmt.getColumn());
              }
            }
          }
        }
      }
    hash.destroyLevel();
    return "";
  }

  public String visit(Minus stmt){
    if (stmt.getRight() != null) {
      stmt.getLeft().accept(this);
      stmt.getRight().accept(this);
      Type left = getTypeExpression(stmt.getLeft());
      Type right = getTypeExpression(stmt.getRight());
      if (!stmt.supportOp())
      error.error4("Minus","boolean", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Minus",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    }else {
      stmt.getLeft().accept(this);
      Type left = getTypeExpression(stmt.getLeft());
      if (!stmt.supportOp())
        error.error4("Minus","boolean", stmt.getLine(), stmt.getColumn());
    }
    return "";
  }

  public String visit(Navigation stmt){
    if (stmt.getNavigation()!=null)
      error.error7("Navigation", stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Not stmt){
    stmt.getExpr().accept(this);
    Type left = getTypeExpression(stmt.getExpr());
    if (!stmt.supportOp())
      error.error6("Not","boolean",stmt.getLine(),stmt.getColumn());
    return "";
  }

  public String visit(NotEqualTo stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error6("NotEqualTo","integers, floats, booleans", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("NotEqualTo",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Or stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("Or","integers or floats", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Or",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Param stmt){
    for (Pair<Type, IdName> param : stmt.getParam()) {
      IdName id = param.getSnd();
      id.setType(param.getFst());
      hash.insertInLevel(new FieldDecl(param.getFst(), id, id.getLine(), id.getColumn()));
      String param_type = param.getFst().toString();
      if (!param_type.equals("integer") && !param_type.equals("float") && !param_type.equals("boolean"))
        error.error3("Param","integer, float, boolean",param_type,stmt.getLine(),stmt.getColumn());
    }
    return "";
  }

  public String visit(Percentage stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("Percentage","boolean", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Percentage",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Plus stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("Plus","boolean", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Plus",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
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

  public String visit(SubAssignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("SubAssignment","boolean", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("SubAssignment",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Times expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    Type left = getTypeExpression(expr.getLeft());
    Type right = getTypeExpression(expr.getRight());
    if (!expr.supportOp())
      error.error4("Times","boolean", expr.getLine(), expr.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Times",left.toString(),right.toString(), expr.getLine(), expr.getColumn());
    return "";
  }

  public String visit(Type stmt){
    //nada
    return "";
  }

  public String visit(WhileStmt stmt){
    stmt.getCondition().accept(this);
    if(stmt.getStatement().className() == "Block")
      stmt.getStatement().accept(this);
    else{
      hash.createLevel();

      stmt.getStatement().accept(this);

      hash.destroyLevel();
    }
    return "";
  }

  public Type getTypeExpression(Expression expr){
    return expr.getType();
  }

}
