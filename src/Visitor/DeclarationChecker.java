package Visitor;

import ASTClass.*;
import TableOfHash.Hash;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*;
import Errors.Error;

public class DeclarationChecker implements ASTVisitor<String>{
  Hash hash;
  Error errors;

  public DeclarationChecker(Error er){
    hash = new Hash();
    errors = er;
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
    //visitar
    return "";
  }

  public String visit(Assignment stmt){
    stmt.getLeft().accept(this);
    stmt.getRight().accept(this);
    FieldDecl left = (FieldDecl)hash.searchInTableFD(stmt.getLeft().toString());
    FieldDecl right = (FieldDecl)hash.searchInTableFD(stmt.getRight().toString());
    if (left != null && right != null){
      if (!left.getType().toString().equals(right.getType().toString()))
        errors.error1(left.getId().toString(), right.getId().toString(), "Assignment");
        // System.out.println(left.getId().toString()+" aren't the same type "+right.getId().toString());
    }
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
    //elBody
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
    //nada
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
      hash.insertInLevel(stmt);
      // System.out.println("IDENTIFIER NO REPEATED");
    }else{
      // System.out.println("THE IDENTIFIER IS ALLREADY DECLARED");
    }
    // continue visit ?
    //insertar
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
      // System.out.println("Identifier "+stmt.toString()+" founded");
      stmt.setType(founded.getType());
    }
    else
      System.out.println("Identifier "+stmt.toString()+" not founded");
    // referenciar
    //nada
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
    return "";
  }

  public String visit(LessOrEq expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
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
    stmt.getId().accept(this);
    if(stmt.getList() != null)
      stmt.getList().accept(this);
    // i don't know what put here
    //visitar
    return "";
  }

  public String visit(MethodCallStmt stmt){
   MethodDecl founded = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
    if(founded != null){
      // System.out.println("Method "+stmt.getIdName().toString()+" founded");
      stmt.setIdName(founded.getIdName());
      // stmt.setType(founded.getType());
    }
    else
      System.out.println("Method "+stmt.getIdName().toString()+" not founded");
    // i don't know what put here
    //nada
    return "";
  }

  public String visit(MethodCallExpr stmt){
   MethodDecl founded = (MethodDecl)hash.searchInTableMD(stmt.getIdName().toString());
    if(founded != null){
      // System.out.println("Method "+stmt.getIdName().toString()+" founded");
      stmt.setIdName(founded.getIdName());
      // stmt.setType(founded.getType());
    }
    else
      System.out.println("Method "+stmt.getIdName().toString()+" not founded");
    // i don't know what put here
    //nada
    return "";
  }

  // the body create a level and destroy it
  public String visit(MethodDecl stmt){
    if(hash.searchInLastLevelMD(stmt.getIdName().toString()) == null){
      hash.insertInLevel(stmt);
      hash.createLevel();
      stmt.getParam().accept(this);
      stmt.getBody().accept(this);
      hash.destroyLevel();
    }else{
      System.out.println("THIS METHOD IS ALLREADY DECLARED");
    }

    //nose
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
    // i don't know what put here
    // nada
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
      hash.insertInLevel(new FieldDecl(param.getFst(), id));
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
    return "";
  }

  public String visit(Times expr){
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
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

}
