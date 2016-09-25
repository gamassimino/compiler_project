package ASTClass;

import Visitor.ASTVisitor;

public class ReturnStmt extends Statement {
  private Expression expression; // the return expression

  public ReturnStmt(Expression e, Integer a_line, Integer a_column) {
    this.expression = e;
    line = a_line;
    column = a_column;
  }

  public ReturnStmt(Integer a_line, Integer a_column) {
    this.expression = null;
    line = a_line;
    column = a_column;
  }

  public ReturnStmt() {
    this.expression = null;
    line = null;
    column = null;
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

  public String className(){
    return "ReturnStmt";
  }
  
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}
