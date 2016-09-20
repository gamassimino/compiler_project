package Visitor;

import ASTClass.*;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*;

public class PrintAST implements ASTVisitor<String>{

  public PrintAST(){

  }
  @Override
  public String visit(AddAssignment stmt){
    return stmt.getLeft().accept(this)+" += "+stmt.getRight().accept(this);
  }

  public String visit(And stmt){
    return stmt.getLeft().accept(this)+" && "+stmt.getRight().accept(this);
  }

  public String visit(Assignment stmt){
    return stmt.getLeft().accept(this)+" = "+stmt.getRight().accept(this);
  }

  public String visit(Block expr){
    String flag = "";
    for (List<FieldDecl> fields_decl : expr.getFieldDecl()) {
      for (FieldDecl field : fields_decl ) {
        flag += field.accept(this);
      }
    }

    for (Statement statement : expr.getStatements()) {
      flag += statement.accept(this)+";\n";
    }
    return flag;
  }

  public String visit(Body expr){
    return expr.getBlock().accept(this);
  }

  public String visit(BreakStmt expr){
    return "break "+expr.getExpression().accept(this);
  }

  public String visit(ClassDecl expr){
    String flag =  "class "+expr.getIdName().accept(this)+"{\n";
    for (List<FieldDecl> fields_decl : expr.getFieldDecl()) {
      for (FieldDecl field : fields_decl ) {
        flag += field.accept(this);
      }
    }
    for (MethodDecl method_decl : expr.getMethodDecl()) {
      flag += method_decl.accept(this);
    }
    return flag += "}\n";
  }

  public String visit(ContinueStmt expr){
    return "continue ";
  }

  public String visit(Divided expr){
    return expr.getLeft().accept(this)+" / "+expr.getRight().accept(this);
  }

  public String visit(EqualTo expr){
    return expr.getLeft().accept(this)+" == "+expr.getRight().accept(this);
  }

  public String visit(FieldDecl stmt){
    String flag = "  "+stmt.getType().accept(this)+" ";
    flag += stmt.getId().accept(this);
    // if (stmt.getId().getSize() != null)
    //   flag += "["+stmt.getId().getSize().toString()+"]";
    return flag+=";\n";
  }

  public String visit(ForStmt stmt){
    return stmt.getIdName().accept(this)
            +"("+stmt.getCondition().accept(this)+";"+stmt.getStep().accept(this)+")"
            +stmt.getStatement().accept(this);
  }

  public String visit(Greater stmt){
    return stmt.getLeft().accept(this)+" > "+stmt.getRight().accept(this);
  }

  public String visit(GreaterOrEq stmt){
    return stmt.getLeft().accept(this)+" >= "+stmt.getRight().accept(this);
  }

  public String visit(IdName stmt){
    return stmt.toString();
  }

  public String visit(IfStmt stmt){
    String flag = "  if ("+stmt.getCondition().accept(this)+"){\n    "
      +stmt.getIfBlock().accept(this)+"}\n";
    if (stmt.getElseBlock() == null)
      return flag;
    else
      return flag+"  else{\n  "+stmt.getElseBlock().accept(this)+"  }\n";
  }

  public String visit(IntLiteral stmt){
    return stmt.toString();
  }

  public String visit(FloatLiteral stmt){
    return stmt.toString();
  }

  public String visit(BoolLiteral stmt){
    return stmt.toString();
  }

  public String visit(Less expr){
    return expr.getLeft().accept(this)+" < "+expr.getRight().accept(this);
  }

  public String visit(LessOrEq expr){
    return expr.getLeft().accept(this)+" <= "+expr.getRight().accept(this);
  }

  public String visit(Literal stmt){
    return stmt.getType().accept(this);
  }

  public String visit(LocationExpr stmt){
    return stmt.getId().accept(this);
  }

  public String visit(LocationStmt stmt){
    return stmt.getId().accept(this);
  }

  public String visit(MethodCallStmt stmt){
    String flag = stmt.getIdName().accept(this)+stmt.getNavigation().accept(this);
    for (Expression expr : stmt.getExpressions()) {
      flag += expr.accept(this);
    }
    return flag;
  }

  public String visit(MethodCallExpr stmt){
    String flag =stmt.getIdName().accept(this);
    if (stmt.getNavigation() != null)
      flag += stmt.getNavigation().accept(this);
    flag += "(";
    if (stmt.getExpressions() != null) {
      for (Expression expr : stmt.getExpressions()) {
        flag += expr.accept(this);
      }
    }
    flag += ")";
    return flag;
  }

  public String visit(MethodDecl stmt){
    return "  "+stmt.getType().accept(this)+" "+stmt.getIdName().accept(this)+"("
            +stmt.getParam().accept(this)+"){\n"+stmt.getBody().accept(this)+"  }\n";
  }

  public String visit(Minus stmt){
    return stmt.getLeft().accept(this)+" - "+stmt.getRight().accept(this);
  }

  public String visit(Navigation stmt){
    String flag = "."+stmt.getIdName().accept(this);
    if (stmt.getNavigation() != null)
      flag += stmt.getNavigation().accept(this);
    return flag;
  }

  public String visit(Not stmt){
    return "!("+stmt.getExpr().accept(this)+")";
  }

  public String visit(NotEqualTo stmt){
    return stmt.getLeft().accept(this)+" != "+stmt.getRight().accept(this);
  }

  public String visit(Or stmt){
    return stmt.getLeft().accept(this)+" || "+stmt.getRight().accept(this);
  }

  public String visit(Expression expr){
    return "";
  }

   // it's no needed right?
  public String visit(Param stmt){
    int size = stmt.getParam().size();
    String flag = "";
    for (Pair<Type, IdName> param : stmt.getParam()) {
      flag = param.getFst().toString()+" "+param.getSnd().toString()+flag;
      if(--size>0) flag = ", "+flag;
    }
    return flag;
  }

  public String visit(Percentage stmt){
    return stmt.getLeft().accept(this)+" % "+stmt.getRight().accept(this);
  }

  public String visit(Plus stmt){
    return stmt.getLeft().accept(this)+" + "+stmt.getRight().accept(this);
  }

  public String visit(Program stmt){
    String flag = "";
    for (ClassDecl clas : stmt.getClassList()) {
      flag += clas.accept(this);
    }
    return flag;
  }

  public String visit(ReturnStmt stmt){
    return "  return "+stmt.getExpression().accept(this)+";";
  }

  public String visit(SubAssignment expr){
    return expr.getLeft().accept(this)+" -= "+expr.getRight().accept(this);
  }

  public String visit(Times expr){
    return expr.getLeft().accept(this)+" * "+expr.getRight().accept(this);
  }

  public String visit(Type stmt){
    return stmt.toString();
  }

  public String visit(WhileStmt stmt){
    return "while ("+stmt.getCondition().accept(this)+")"+stmt.getStatement().accept(this);
  }
}
