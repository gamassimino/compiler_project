package ASTClass;

import Visitor.ASTVisitor;

public class BreakStmt extends Statement {

  public BreakStmt(Integer a_line, Integer a_column) {
    line = a_line;
    column = a_column;
  }

  public BreakStmt() {
    line = null;
    column = null;
  }

  @Override
  public String toString() {
    return "return ;";
  }

  public String className(){
    return "ReturnStmt";
  }

  public <T> T accept(ASTVisitor<T> v) {
    return v.visit(this);
  }
}
