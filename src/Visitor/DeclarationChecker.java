package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*;
import Errors.Error;

public class DeclarationChecker implements ASTVisitor<String>{
  Hash hash;
  Hash hash_class;
  Error errors;
  Integer offset;

  public DeclarationChecker(Error er, Hash clases, Integer off){
    hash = new Hash();
    hash_class = clases;
    errors = er;
    offset = off;
  }

  public Integer nextOffset(){
    offset -= 4;
    return offset;
  }

  public Integer getOffset(){
    return offset;
  }

  @Override
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
    // hash_class.insertInLevel(new ClassDecl(expr.getIdName()));

    for (List<FieldDecl> fields_decl : expr.getFieldDecl()) {
      for (FieldDecl field : fields_decl) {
        field.accept(this);
        // hash_class.insertInLevel(field);
      }
    }

    for (MethodDecl statement : expr.getMethodDecl()) {
      statement.accept(this);
      // hash_class.insertInLevel(statement);
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
    return "";
  }

  public String visit(EqualTo expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    return "";
  }

  public String visit(FieldDecl stmt){
    if(hash.searchInLastLevelFD(stmt.getId().toString()) == null){
      if (stmt.getType().toString().equals("integer")|| stmt.getType().toString().equals("bool")||
         stmt.getType().toString().equals("float")){
        stmt.getId().setOffset(nextOffset());
        hash.insertInLevel(stmt);
      }
      else{
        ClassDecl founded = (ClassDecl)hash_class.searchInTableCD(stmt.getType().toString());
        if(founded!= null)
          hash.insertInLevel(new Instance(stmt.getId().toString(), stmt.getType(), founded.getFieldDecl(), founded.getMethodDecl()));
        else
          errors.error2("Class", stmt.getId().toString(),stmt.getId().getLine(),stmt.getId().getColumn());  
      }
    }else
      errors.error2("Identifier", stmt.getId().toString(),stmt.getId().getLine(),stmt.getId().getColumn());
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

    stmt.getStatement().accept(this);

    hash.destroyLevel();
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
    FieldDecl founded = (FieldDecl)hash.searchInTableFD(stmt.toString());
    if(founded != null){
      stmt.setOffset(founded.getId().getOffset());
      stmt.setType(founded.getType());
    }
    else{
      ClassDecl foundedd = (ClassDecl)hash.searchInTableCD(stmt.toString());
      if (foundedd == null)
        errors.error1("Identifier", stmt.toString(),stmt.getLine(),stmt.getColumn());
    }
    return "";
  }

  public String visit(IfStmt stmt){
    stmt.getCondition().accept(this);
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
    return "";
  }

  public String visit(LessOrEq expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    return "";
  }

  public String visit(LocationExpr stmt){
    if(stmt.getList() != null && stmt.getList().getNavigation() != null)
      errors.error15(stmt.getId().toString(), stmt.getLine(), stmt.getColumn());
    else{
      // ClassDecl founded = (ClassDecl)hash_class.searchInTableCD(stmt.getId().getType().toString());
      // if(founded != null && hash_class.searchInTableAD(founded.getIdName().toString(), stmt.getList().getIdName.toString()))
      boolean founded = false;
      String class_name = "";
      if (stmt.getList() != null){
        Instance i = (Instance)hash.searchInTableI(stmt.getId().toString());
        class_name = i.getType().toString();
        if(i.getFieldDecl().size() > 0){
          for (List<FieldDecl> fields_decl : i.getFieldDecl()) {
            if(fields_decl!= null){
              for (FieldDecl field : fields_decl) {
                if (stmt.getList().getIdName().toString().equals(field.getId().toString())){
                  stmt.getList().setIdName(field.getId());
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
        
        // System.out.println("###################");
      if (stmt.getList() != null && !founded)
        errors.error13(class_name, stmt.getList().getIdName().toString(), stmt.getLine(), stmt.getColumn());
    }
    return "";
  }

  public String visit(LocationStmt stmt){
    if(stmt.getList() != null && stmt.getList().getNavigation() != null)
      errors.error15(stmt.getId().toString(), stmt.getLine(), stmt.getColumn());
    else{
      boolean founded = false;
      String class_name = "";
      if (stmt.getList() != null){
        Instance i = (Instance)hash.searchInTableI(stmt.getId().toString());
        class_name = i.getType().toString();
        if(i.getFieldDecl().size() > 0){
          for (List<FieldDecl> fields_decl : i.getFieldDecl()) {
            if(fields_decl!= null){
              for (FieldDecl field : fields_decl) {
                if (stmt.getList().getIdName().toString().equals(field.getId().toString())){
                  stmt.getList().setIdName(field.getId());
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
        
        // System.out.println("###################");
      if (stmt.getList() != null && !founded)
        errors.error13(class_name, stmt.getList().getIdName().toString(), stmt.getLine(), stmt.getColumn());
    }
    return "";
  }

  public String visit(MethodCallStmt stmt){
    if(stmt.getNavigation() != null && stmt.getNavigation().getNavigation() != null)
      errors.error15(stmt.getIdName().toString(), stmt.getLine(), stmt.getColumn());
    else{
      String class_name = "";
      boolean founded = false;
      if (stmt.getNavigation() != null){
        Instance i = (Instance)hash.searchInTableI(stmt.getIdName().toString());
        class_name = i.getType().toString();
        for (MethodDecl method : i.getMethodDecl()) {
          if (stmt.getNavigation().getIdName().toString().equals(method.getIdName().toString())){
            stmt.getNavigation().setIdName(method.getIdName());
            founded = true;
            for (Expression expr: stmt.getExpressions()) {
              expr.accept(this);      
            }      
            break;
          }
        }
      }else{
        MethodDecl method_founded = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
        if(method_founded != null){
          stmt.setIdName(method_founded.getIdName());
          for (Expression expr: stmt.getExpressions()) {
            expr.accept(this);    
          }      
        }
        else
          errors.error1("Method", stmt.getIdName().toString(), stmt.getLine(), stmt.getColumn());
      }

      if(!founded && stmt.getNavigation() != null)
        errors.error14(class_name, stmt.getNavigation().getIdName().toString(), stmt.getLine(), stmt.getColumn());
    }
    return "";
  }

  public String visit(MethodCallExpr stmt){
    if(stmt.getNavigation() != null && stmt.getNavigation().getNavigation() != null)
      errors.error15(stmt.getIdName().toString(), stmt.getLine(), stmt.getColumn());
    else{
      String class_name = "";
      boolean founded = false;
      if (stmt.getNavigation() != null){
        Instance i = (Instance)hash.searchInTableI(stmt.getIdName().toString());
        class_name = i.getType().toString();
        for (MethodDecl method : i.getMethodDecl()) {
          if (stmt.getNavigation().getIdName().toString().equals(method.getIdName().toString())){
            stmt.getNavigation().setIdName(method.getIdName());
            founded = true;
            for (Expression expr: stmt.getExpressions()) {
              expr.accept(this);      
            }      
            break;
          }
        }
      }else{
        MethodDecl method_founded = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
        if(method_founded != null){
          stmt.setIdName(method_founded.getIdName());
          for (Expression expr: stmt.getExpressions()) {
            expr.accept(this);    
          }      
        }
        else
          errors.error1("Method", stmt.getIdName().toString(), stmt.getLine(), stmt.getColumn());
      }

      if(!founded && stmt.getNavigation() != null)
        errors.error14(class_name, stmt.getNavigation().getIdName().toString(), stmt.getLine(), stmt.getColumn());
    }
    return "";
  }

  // the body create a level and destroy it
  public String visit(MethodDecl stmt){
    if(hash.searchInLastLevelMD(stmt.getIdName().toString()) == null){
      hash.insertInLevel(stmt);
      hash.createLevel();
      stmt.getParam().accept(this);
      if (stmt.getBody().getBlock() != null)
        stmt.getBody().accept(this);
      hash.destroyLevel();
    }else
      errors.error2("Method", stmt.getIdName().toString(), stmt.getLine(), stmt.getColumn());
    return "";
  }

  public String visit(Minus stmt){
    stmt.getLeft().accept(this);
    if(stmt.getRight() != null){
      stmt.getRight().accept(this);
    }
    else{
    }
    return "";
  }

  public String visit(Navigation stmt){
    stmt.getIdName().accept(this);
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

  // need the new implementation of FieldDecl
  public String visit(Param stmt){
    for (Pair<Type, IdName> param : stmt.getParam()) {
      IdName id = param.getSnd();
      id.setType(param.getFst());
      hash.insertInLevel(new FieldDecl(param.getFst(), id, id.getLine(), id.getColumn()));
    }
    // search the param ?
    // declare a new identifie on the table ?
    // bind this identifier with the id on the call?
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

  public String visit(Instance stmt){
    return "";
  }


}
