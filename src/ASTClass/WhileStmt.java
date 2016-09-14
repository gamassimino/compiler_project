package src.ASTClass;

import Visitor.ASTVisitor;

public class WhileStmt extends Statement {
  private Expression condition;
  private Statement statement;
  
  public WhileStmt(Expression cond, Statement a_statement) {
    this.condition = cond;
    this.statement = a_statement;
  }

  public Expression getCondition() {
    return condition;
  }

  public void setCondition(Expression condition) {
    this.condition = condition;
  }

  public Statement getStatement() {
    return statement;
  }

  public void setStatement(Statement a_statement) {
    this.statement = a_statement;
  }
  
  @Override
  public String toString() {
    String rtn = "while " + condition + '\n';
    
    rtn += statement.toString();

    return rtn;
  }

  // @Override
  // public <T> T accept(ASTVisitor<T> v) {
  //  return v.visit(this);
  // }
}
