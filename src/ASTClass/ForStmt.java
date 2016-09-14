package src.ASTClass;

import Visitor.ASTVisitor;

public class ForStmt extends Statement {
  private IdName id;
  private Expression condition;
  private Expression step;
  private Statement statement;
  
  public ForStmt(IdName an_id, Expression cond, Expression a_step, Statement st) {
    this.id = an_id;
    this.condition = cond;
    this.step = a_step;
    this.statement = st;
  }

  public IdName getIdName() {
    return id;
  }

  public void setIdName(IdName a_id) {
    this.id = a_id;
  }

  public Expression getCondition() {
    return condition;
  }

  public void setCondition(Expression a_condition) {
    this.condition = condition;
  }

  public Expression getStep() {
    return step;
  }

  public void setStep(Expression a_step) {
    this.step = a_step;
  }

  public Statement getStatement() {
    return statement;
  }

  public void setStatement(Statement a_statement) {
    this.statement = a_statement;
  }
  
  @Override
  public String toString() {
    String rtn = "for " + condition.toString()+ " ; " + step.toString() + '\n' + statement.toString();
    return rtn;
  }

  // @Override
  // public <T> T accept(ASTVisitor<T> v) {
  //   return v.visit(this);
  // }
}
