package ASTClass;

import Visitor.ASTVisitor;

public class BreakStmt extends Statement {

  public BreakStmt() {
  }

  @Override
  public String toString() {
    return "break ;";
  }

  public String className(){
    return "BreakStmt";
  }
  
  public <T> T accept(ASTVisitor<T> v) {
    return v.visit(this);
  }
}
