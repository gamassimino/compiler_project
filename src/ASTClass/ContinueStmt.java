package ASTClass.ContinueStmt;

import Visitor.ASTVisitor;

public class ContinueStmt extends Statement {
  private Expression expression; // the return expression

  public ContinueStmt(Expression e) {
    this.expression = e;
  }

  public ContinueStmt() {
    this.expression = null;
  }

  public Expression getExpression() {
    return expression;
  }

  public void setExpression(Expression expression) {
    this.expression = expression;
  }

  @Override
  public String toString() {
    if (expression == null) {
      return "continue";
    }
    else {
      return "continue " + expression;
    }
  }

  @Override
  public <T> T accept(ASTVisitor<T> v) {
    return v.visit(this);
  }
}
