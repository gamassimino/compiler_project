package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java_cup.runtime.*;

public class TypeChecker implements ASTVisitor<String>{
  Hash hash;

  public TypeChecker(){
    hash = new Hash();
  }


  public String visit(AddAssignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      System.out.println("Incompatible types of operands");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
    return "";
  }

  public String visit(And stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      System.out.println("this operator don't suport Float or Integers");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
    return "";
  }

  public String visit(Assignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      System.out.println("Incompatible types of operands");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
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
      System.out.println("this operator don't suport booleans");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
    return "";
  }

  public String visit(EqualTo expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    LocationExpr left = (LocationExpr)expr.getLeft();
    LocationExpr right = (LocationExpr)expr.getRight();
    if (!left.getId().getType().toString().equals(right.getId().getType().toString()))
      System.out.println("aren't the same type");
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
    if (!stmt.getCondition().getType().toString().equals("integer")
      || !stmt.getStep().getType().toString().equals("integer"))
      System.out.println("condition and step must be integer");
    hash.destroyLevel();
    return "";
  }

  public String visit(Greater stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      System.out.println("this operator don't suport booleans");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
    return "";
  }

  public String visit(GreaterOrEq stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      System.out.println("this operator don't suport booleans");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
    return "";
  }

  public String visit(IdName stmt){
    if (stmt.getSize() != null){
      if (!stmt.getType().toString().equals("integer"))
        System.out.println("the arrays must be of integers");
      if (stmt.getSize().getValue() <= 0)
        System.out.println("array size must be greater than zero");
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
      System.out.println("this operator don't suport booleans");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
    return "";
  }

  public String visit(LessOrEq expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    Type left = getTypeExpression(expr.getLeft());
    Type right = getTypeExpression(expr.getRight());
    if (!expr.supportOp())
      System.out.println("this operator don't suport booleans");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
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
      System.out.print("cantidad de parametros incorrecto");
    else{
      if (founded.getParam().getParam().size() > 0) {
        for (Pair<Type, IdName> param_of_decl : founded.getParam().getParam()) {
          if(!list_param_call.get(i).getType().toString().equals(param_of_decl.getFst().toString()))
            System.out.print("el tipo "+param_of_decl.getFst().toString()+" no machea con el tipo "+list_param_call.get(i).getType().toString()+" usado en la invocacion del metodo "+stmt.getIdName().toString());
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
      System.out.print("cantidad de parametros incorrecto");
    else{
      if (founded.getParam().getParam().size() > 0) {
        for (Pair<Type, IdName> param_of_decl : founded.getParam().getParam()) {
          if(!list_param_call.get(i).getType().toString().equals(param_of_decl.getFst().toString()))
            System.out.print("el tipo "+param_of_decl.getFst().toString()+" no machea con el tipo "+list_param_call.get(i).getType().toString()+" usado en la invocacion del metodo "+stmt.getIdName().toString());
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
            if (st.getExpression() != null)
              System.out.println("the method musn't return nothing");
          }
          else{
            if (st.getExpression() == null)
              System.out.println("method must return some");
            else{
              if (!stmt.getType().toString().equals(st.getExpression().getType().toString()))
                System.out.println("the type of the method and the return value are diferents");
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
        System.out.println("this operator don't suport booleans");
      if (!left.toString().equals(right.toString()))
        System.out.println("aren't the same type");
    }else {
      stmt.getLeft().accept(this);
      Type left = getTypeExpression(stmt.getLeft());
      if (!stmt.supportOp())
        System.out.println("this operator don't suport booleans");
    }
    return "";
  }

  public String visit(Navigation stmt){
    if (stmt.getNavigation()!=null)
      System.out.println("you can only use one navigation");
    return "";
  }

  public String visit(Not stmt){
    stmt.getExpr().accept(this);
    Type left = getTypeExpression(stmt.getExpr());
    if (!stmt.supportOp())
      System.out.println("this operator only support booleans");
    return "";
  }

  public String visit(NotEqualTo stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    LocationExpr left = (LocationExpr)stmt.getLeft();
    LocationExpr right = (LocationExpr)stmt.getRight();
    if (left.getId().getType().toString().equals(right.getId().getType().toString()))
      System.out.println("are the same type");
    return "";
  }

  public String visit(Or stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      System.out.println("this operator don't suport Float or Integers");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
    return "";
  }

  public String visit(Param stmt){
    for (Pair<Type, IdName> param : stmt.getParam()) {
      IdName id = param.getSnd();
      id.setType(param.getFst());
      hash.insertInLevel(new FieldDecl(param.getFst(), id));
    }
    return "";
  }

  public String visit(Percentage stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      System.out.println("this operator don't suport booleans");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
    return "";
  }

  public String visit(Plus stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      System.out.println("this operator don't suport booleans");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
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
      System.out.println("Incompatible types of operands");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
    return "";
  }

  public String visit(Times expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    Type left = getTypeExpression(expr.getLeft());
    Type right = getTypeExpression(expr.getRight());
    if (!expr.supportOp())
      System.out.println("this operator don't suport booleans");
    if (!left.toString().equals(right.toString()))
      System.out.println("aren't the same type");
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
