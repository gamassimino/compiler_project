package Visitor;

import ASTClass.*;

public class PrintAST<T> implements ASTVisitor<T>{
  T visit(AddAssignment stmt){
    return stmt.toString();
  }

  T visit(And stmt){
    return stmt.toString();
  }

  T visit(Assignment stmt){
    return stmt.toString();
  }

  T visit(AssignOpType stmt){
    return stmt.toString();
  }

  T visit(AssignStmt stmt){
    return stmt.toString();
  }

  T visit(AST stmt){
    return stmt.toString();
  }

  T visit(BinOpExpr expr){
    return expr.toString();
  }

  T visit(BinOpType expr){
    return expr.toString();
  }

  T visit(Block expr){
    return expr.toString();
  }

  T visit(Body expr){
    return expr.toString();
  }

  T visit(BreakStmt expr){
    return expr.toString();
  }

  T visit(ClassDecl expr){
    return expr.toString();
  }

  T visit(ContinueStmt expr){
    return expr.toString();
  }

  T visit(Divided expr){
    return expr.toString();
  }

  T visit(EqualTo expr){
    return expr.toString();
  }

  // T visit(Expression expr){
  //   return stmt.toString();
  // }

  T visit(FieldDecl stmt){
    return stmt.toString();
  }

  T visit(ForStmt stmt){
    return stmt.toString();
  }

  T visit(Greater stmt){
    return stmt.toString();
  }

  T visit(GreaterOrEq stmt){
    return stmt.toString();
  }

  T visit(IdName stmt){
    return stmt.toString();
  }

  T visit(IfStmt stmt){
    return stmt.toString();
  }

  T visit(IntLiteral stmt){
    return stmt.toString();
  }

  T visit(Less expr){
    return expr.toString();
  }

  T visit(LessOrEq expr){
    return expr.toString();
  }

  T visit(Literal stmt){
    return stmt.toString();
  }

  T visit(Location stmt){
    return stmt.toString();
  }

  T visit(LocationAux stmt){
    return stmt.toString();
  }

  T visit(MethodCall stmt){
    return stmt.toString();
  }

  T visit(MethodDecl stmt){
    return stmt.toString();
  }

  T visit(Minus stmt){
    return stmt.toString();
  }

  T visit(Navigation stmt){
    return stmt.toString();
  }

  T visit(Not stmt){
    return stmt.toString();
  }

  T visit(NotEqualTo stmt){
    return stmt.toString();
  }

  T visit(Or stmt){
    return stmt.toString();
  }

  T visit(Pair stmt){
  return stmt.toString();
  }
   // it's no needed right?
  T visit(Param stmt){
    return stmt.toString();
  }

  T visit(ParamList stmt){
    return stmt.toString();
  }

  T visit(Percentage stmt){
    return stmt.toString();
  }

  T visit(Plus stmt){
    return stmt.toString();
  }

  T visit(Program stmt){
    return stmt.toString();
  }

  T visit(ReturnStmt stmt){
    return stmt.toString();
  }

  // T visit(Statement stmt){
  //   return stmt.toString();
  // }

  T visit(SubAssignment expr){
    return expr.toString();
  }

  T visit(Times expr){
    return expr.toString();
  }

  T visit(Type stmt){
    return stmt.toString();
  }

  T visit(VarLocation loc){
    return loc.toString();
  }

  T visit(WhileStmt stmt){
    return stmt.toString();
  }

  // T visit(Stmt stmt){
  // return stmt.toString();
  // }
  // what it's ?
}
