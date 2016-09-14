package Visitor;

import ASTClass.*;

public class PrintAST implements ASTVisitor<String>{
  String visit(AddAssignment stmt){
   return stmt.getId().accept(this)+" += "+stmt.getExpr().acept(this);
  }

  String visit(And stmt){
    return stmt.getLeft().accept(this)+" && "+stmt.getRight().accept(this);
  }

  String visit(Assignment stmt){
    return stmt.getLeft().accept(this)+" = "+stmt.getRight().accept(this);
  }

  String visit(AssignOpType stmt){
    return stmt.toString();
  }

  String visit(AST stmt){
    return stmt.toString();
  }

  String visit(BinOpExpr expr){
    return expr.getLeftOperand().accept(this)+expr.getOperator().accept(this)+expr.getRightOperand().accept(this);
  }

  String visit(BinOpType expr){
    return expr.toString();
  }

  String visit(Block expr){
    return expr.getStatements().accept(this)+expr.getFieldDecl().accept(this);
  }

  String visit(Body expr){
    return expr.getBlock().accept(this);
  }

  String visit(BreakStmt expr){
    return "break "+expr.getExpression().accept(this);
  }

  String visit(ClassDecl expr){
    return "class "+expr.getIdName().accept(this)+"{"+expr.getFieldDecl().accept(this)+expr.getMethodDecl.accept(this)+" }";
  }

  String visit(ContinueStmt expr){
    return "continue "+expr.getExpression().accept(this);
  }

  String visit(Divided expr){
    return expr.getLeft().accept(this)+" / "+expr.getRight().accept(this);
  }

  String visit(EqualTo expr){
    return expr.getLeft().accept(this)+" == "+expr.getRight().accept(this);
  }

  // String visit(Expression expr){
  //   return stmt.toString();
  // }

  String visit(FieldDecl stmt){
    return stmt.getType().accept(this)+" "+stmt.getListId().accept(this);
  }

  String visit(ForStmt stmt){
    return stmt.getIdName().accept(this)
            +"("+stmt.getCondition().accept(this)+";"+stmt.getStep().accept(this)+")"
            +stmt.getStatement().accept(this);
  }

  String visit(Greater stmt){
    return stmt.getLeft().accept(this)+" > "+stmt.getRight().accept(this);
  }

  String visit(GreaterOrEq stmt){
    return stmt.getLeft().accept(this)+" >= "+stmt.getRight().accept(this);
  }

  String visit(IdName stmt){
    return stmt.toString();
  }

  String visit(IfStmt stmt){
    String flag = "if "+stmt.getCondition().accept(this)+"{"+stmt.getIfBlock().accept(this)+"}";
    if (stmt.setElseBlock() == null)
      return flag;
    else
      return flag+"else{"+stmt.setElseBlock().accept(this)+"}";
  }

  String visit(IntLiteral stmt){
    return stmt.toString();
  }

  String visit(Less expr){
    return stmt.getLeft().accept(this)+" < "+stmt.getRight().accept(this);
  }

  String visit(LessOrEq expr){
    return stmt.getLeft().accept(this)+" <= "+stmt.getRight().accept(this);
  }

  String visit(Literal stmt){
    return stmt.toString();
  }

  String visit(Location stmt){
    return stmt.getId().accept(this);
  }

  String visit(LocationAux stmt){
    return stmt.toString();
  }

  String visit(MethodCall stmt){
    return stmt.getIdName().accept(this)+stmt.getNavigation().accept(this)+stmt.getExpressions().accept(this);
  }

  String visit(MethodDecl stmt){
    return stmt.getType().accept(this)+stmt.getIdName().accept(this)+"("
            +stmt.getParam().accept(this)+"){"+stmt.getBody().accept(this)+"}";
  }

  String visit(Minus stmt){
    return stmt.toString();
  }

  String visit(Navigation stmt){
    return stmt.getIdName().accept(this)+"."+stmt.getNavigation().accept(this);
  }

  String visit(Not stmt){
    return stmt.getExpr().accept(this);
  }

  String visit(NotEqualTo stmt){
    return stmt.getLeft().accept(this)+" != "+stmt.getRight().accept(this);
  }

  String visit(Or stmt){
    return stmt.getLeft().accept(this)+" || "+stmt.getRight().accept(this);
  }

  String visit(Pair stmt){
  return "<"+stmt.getFst().accept(this)+","+stmt.getSnd().accept(this)+">";
  }
   // it's no needed right?
  String visit(Param stmt){
    return stmt.toString();
  }

  String visit(ParamList stmt){
    return stmt.toString();
  }

  String visit(Percentage stmt){
    return stmt.getLeft().accept(this)+" % "+stmt.getRight().accept(this);
  }

  String visit(Plus stmt){
    return stmt.getLeft().accept(this)+" + "+stmt.getRight().accept(this);
  }

  String visit(Program stmt){
    return stmt.getClassList().accept(this);
  }

  String visit(ReturnStmt stmt){
    return "return "+stmt.getExpression().accept(this);
  }

  // String visit(Statement stmt){
  //   return stmt.toString();
  // }

  String visit(SubAssignment expr){
    return stmt.getId().accept(this)+" -= "+stmt.getExpr().acept(this);
  }

  String visit(Times expr){
    return stmt.getLeft().accept(this)+" * "+stmt.getRight().accept(this);
  }

  String visit(Type stmt){
    return stmt.toString();
  }

  String visit(VarLocation loc){
    return loc.toString();
  }

  String visit(WhileStmt stmt){
    return "while ("+stmt.getCondition().accept(this)+")"+stmt.getStatement().accept(this);
  }

  // String visit(Stmt stmt){
  // return stmt.toString();
  // }
  // what it's ?
}
