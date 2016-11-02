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
  Hash hash_class;
  Error error;
  Integer offset;

  public TypeChecker(Error error, Hash classes, Integer an_offset){
    hash = new Hash();
    hash_class = classes;
    this.error = error;
    offset = an_offset;
  }

  public Integer nextOffset(){
    offset -= 8;
    return offset;
  }

  public Integer getOffset(){
    return offset;
  }

  public void setOffset(Integer off){
    offset = off;
  }

  public String visit(AddAssignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error4("AddAssignment","bool", stmt.getLine(), stmt.getColumn());
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
    if(expr.getBlock() != null)
      expr.getBlock().accept(this);
    return "";
  }

  public String visit(BreakStmt expr){
    return "";
  }

  public String visit(ClassDecl expr){
    hash.createLevel();
    hash_class.createLevel();

    for (List<FieldDecl> fields_decl : expr.getFieldDecl()) {
      for (FieldDecl field : fields_decl) {
        field.accept(this);
      }
    }

    for (MethodDecl statement : expr.getMethodDecl()) {
      statement.accept(this);
    }
    hash_class.insertInLevel(new ClassDecl(expr.getIdName(),expr.getFieldDecl(),expr.getMethodDecl(),expr.getLine(),expr.getColumn()));

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
      error.error4("Divided","bool", expr.getLine(), expr.getColumn());
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
      error.error6("EqualTo","integers, floats, bools", expr.getLine(), expr.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("EqualTo",left.toString(),right.toString(), expr.getLine(), expr.getColumn());
    return "";
  }

  public String visit(FieldDecl stmt){
    if(hash.searchInLastLevelFD(stmt.getId().toString()) == null){
      if (stmt.getType().toString().equals("integer")|| stmt.getType().toString().equals("bool")||
         stmt.getType().toString().equals("float"))
        hash.insertInLevel(stmt);
      else{
        ClassDecl founded = (ClassDecl)hash_class.searchInTableCD(stmt.getType().toString());
        if(founded!= null)
          hash.insertInLevel(new Instance(stmt.getId().toString(), stmt.getType(), founded.getFieldDecl(), founded.getMethodDecl()));
      }
    }
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
      error.error4("Greater","bool", stmt.getLine(), stmt.getColumn());
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
      error.error4("GreaterOrEq","bool", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("GreaterOrEq",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(IdName stmt){
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
    Type left = getTypeExpression(expr.getLeft());
    Type right = getTypeExpression(expr.getRight());
    if (!expr.supportOp())
      error.error4("Less","bool", expr.getLine(), expr.getColumn());
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
      error.error4("LessOrEq","bool", expr.getLine(), expr.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("LessOrEq",left.toString(),right.toString(), expr.getLine(), expr.getColumn());
    return "";
  }

  public String visit(LocationExpr stmt){
    boolean founded = false;
    if (stmt.getList() != null){
      Instance i = (Instance)hash.searchInTableI(stmt.getId().toString());
      if(i.getFieldDecl().size() > 0){
        for (List<FieldDecl> fields_decl : i.getFieldDecl()) {
          if(fields_decl != null){
            for (FieldDecl field : fields_decl) {
              if (stmt.getList().getIdName().toString().equals(field.getId().toString())){
                stmt.getList().getIdName().accept(this);
                founded = true;
                break;
              }
            }
            if (founded)
              break;
          }
        }
      }
    }else
      stmt.getId().accept(this);
    return "";
  }

  public String visit(LocationStmt stmt){

    boolean founded = false;
    if (stmt.getList() != null){
      Instance i = (Instance)hash.searchInTableI(stmt.getId().toString());
      if(i.getFieldDecl().size() > 0){
        for (List<FieldDecl> fields_decl : i.getFieldDecl()) {
          if(fields_decl != null){
            for (FieldDecl field : fields_decl) {
              if (stmt.getList().getIdName().toString().equals(field.getId().toString())){
                stmt.getList().getIdName().accept(this);
                founded = true;
                break;
              }
            }
            if (founded)
              break;
          }
        }
      }
    }else
      stmt.getId().accept(this);
    return "";
  }

  public String visit(MethodCallStmt stmt){
    MethodDecl founded = null;
    if (stmt.getNavigation() != null){
      Instance i = (Instance)hash.searchInTableI(stmt.getIdName().toString());
      for (MethodDecl method : i.getMethodDecl()) {
        if (stmt.getNavigation().getIdName().toString().equals(method.getIdName().toString())){
          founded = method;
          break;
        }
      }
    }else{
      MethodDecl method_founded = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
      if(method_founded != null){
        founded = method_founded;
      }
    }

    List<Expression> list_param_call = stmt.getExpressions();
    int i = list_param_call.size()-1;
    if(founded.getParam().getParam().size() != stmt.getExpressions().size()){
      error.error8("MethodCallStmt", stmt.getLine(), stmt.getColumn());
    }
    else{
      if (founded.getParam().getParam().size() > 0) {
        for (Pair<Type, IdName> param_of_decl : founded.getParam().getParam()) {
          if(!list_param_call.get(i).getType().toString().equals(param_of_decl.getFst().toString()))
            error.error3("MethodCallStmt",param_of_decl.getFst().toString(),list_param_call.get(i).getType().toString(), stmt.getLine(), stmt.getColumn());
        i--;
        }
      }
    }
    return "";
  }

  public String visit(MethodCallExpr stmt){
  MethodDecl founded = null;
    if (stmt.getNavigation() != null){
      Instance i = (Instance)hash.searchInTableI(stmt.getIdName().toString());
      for (MethodDecl method : i.getMethodDecl()) {
        if (stmt.getNavigation().getIdName().toString().equals(method.getIdName().toString())){
          founded = method;
          break;
        }
      }
    }else{
      MethodDecl method_founded = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
      if(method_founded != null){
        founded = method_founded;
      }
    }

    List<Expression> list_param_call = stmt.getExpressions();
    int i = list_param_call.size()-1;
    if(founded.getParam().getParam().size() != stmt.getExpressions().size()){
      error.error8("MethodCallExpr", stmt.getLine(), stmt.getColumn());
    }
    else{
      if (founded.getParam().getParam().size() > 0) {
        for (Pair<Type, IdName> param_of_decl : founded.getParam().getParam()) {
          if(!list_param_call.get(i).getType().toString().equals(param_of_decl.getFst().toString()))
            error.error3("MethodCallExpr",param_of_decl.getFst().toString(),list_param_call.get(i).getType().toString(), stmt.getLine(), stmt.getColumn());
        i--;
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
      if(stmt.getBody().getBlock() != null ){
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
      error.error4("Minus","bool", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Minus",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    }else {
      stmt.getLeft().accept(this);
      Type left = getTypeExpression(stmt.getLeft());
      if (!stmt.supportOp())
        error.error4("Minus","bool", stmt.getLine(), stmt.getColumn());
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
      error.error6("Not","bool",stmt.getLine(),stmt.getColumn());
    return "";
  }

  public String visit(NotEqualTo stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    Type left = getTypeExpression(stmt.getLeft());
    Type right = getTypeExpression(stmt.getRight());
    if (!stmt.supportOp())
      error.error6("NotEqualTo","integers, floats, bools", stmt.getLine(), stmt.getColumn());
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
      if (!param_type.equals("integer") && !param_type.equals("float") && !param_type.equals("bool"))
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
      error.error4("Percentage","bool", stmt.getLine(), stmt.getColumn());
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
      error.error4("Plus","bool", stmt.getLine(), stmt.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Plus",left.toString(),right.toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Program stmt){
    for (ClassDecl class_decl : stmt.getClassList()) {
      class_decl.accept(this);
    }
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
      error.error4("SubAssignment","bool", stmt.getLine(), stmt.getColumn());
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
      error.error4("Times","bool", expr.getLine(), expr.getColumn());
    if (!left.toString().equals(right.toString()))
      error.error3("Times",left.toString(),right.toString(), expr.getLine(), expr.getColumn());
    return "";
  }

  public String visit(Type stmt){
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

  public String visit(Instance stmt){
    return "";
  }

}
