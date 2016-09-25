package ASTClass;

import Visitor.ASTVisitor;

public class ContinueStmt extends Statement {

  public ContinueStmt(Integer a_line, Integer a_column) {
    line = a_line;
    column = a_column;
  }

  public ContinueStmt() {
    line = null;
    column = null;
  }

  @Override
  public String toString() {
      return "continue ";
  }

  public String className(){
    return "ContinueStmt";
  }
  
  public <T> T accept(ASTVisitor<T> v) {
    return v.visit(this);
  }
}
