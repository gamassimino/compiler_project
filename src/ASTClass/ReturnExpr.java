package ASTClass;

// import Visitor.ASTVisitor;

public class ReturnExpr extends Expression {
  private Expression expression; // the return expression

  public ReturnExpr(Expression e) {
    this.expression = e;
  }

  public ReturnExpr() {
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
      return "return";
    }
    else {
      return "return " + expression;
    }
  }

  // public <T> T accept(ASTVisitor<T> v) {
  //  return v.visit(this);
  // }
}
