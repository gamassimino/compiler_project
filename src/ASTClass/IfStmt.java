package ASTClass;

import Visitor.ASTVisitor;

public class IfStmt extends Statement {
  private Expression condition;
  private Statement ifBlock;
  private Statement elseBlock;

  public IfStmt(Expression cond, Statement ifBl, Integer a_line, Integer a_column) {
    this.condition = cond;
    this.ifBlock = ifBl;
    this.elseBlock = null;
    line = a_line;
    column = a_column;
  }

  public IfStmt(Expression cond, Statement ifBl, Statement elseBl, Integer a_line, Integer a_column) {
    this.condition = cond;
    this.ifBlock = ifBl;
    this.elseBlock = elseBl;
    line = a_line;
    column = a_column;
  }

  public Expression getCondition() {
    return condition;
  }

  public void setCondition(Expression condition) {
    this.condition = condition;
  }

  public Statement getIfBlock() {
    return ifBlock;
  }

  public void setIfBlock(Statement ifBlock) {
    this.ifBlock = ifBlock;
  }

  public Statement getElseBlock() {
    return elseBlock;
  }

  public void setElseBlock(Statement elseBlock) {
    this.elseBlock = elseBlock;
  }

  @Override
  public String toString() {
    String rtn = "if " + condition + '\n' + ifBlock.toString();

    if (elseBlock != null) {
      rtn += "else \n" + elseBlock;
    }

    return rtn;
  }

  public String className(){
    return "IfStmt";
  }
  
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}
