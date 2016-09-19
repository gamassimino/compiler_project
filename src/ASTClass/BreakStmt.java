package ASTClass;

import Visitor.ASTVisitor;

public class BreakStmt extends Statement {
  private Expression expression; // the return expression

  public BreakStmt(Expression e) {
    this.expression = e;
  }

  public BreakStmt() {
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
      return "break";
    }
    else {
      return "break " + expression;
    }
  }

  public String className(){
    return "BreakStmt";
  }
  
  public <T> T accept(ASTVisitor<T> v) {
    return v.visit(this);
  }
}
