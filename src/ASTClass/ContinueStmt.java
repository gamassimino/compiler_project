package ASTClass;

import Visitor.ASTVisitor;

public class ContinueStmt extends Statement {

  public ContinueStmt() {
  }

  @Override
  public String toString() {
      return "continue ";
  }

  public <T> T accept(ASTVisitor<T> v) {
    return v.visit(this);
  }
}
