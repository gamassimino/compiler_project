package ASTClass;

import Visitor.ASTVisitor;

public class Expression extends AST {
  protected Expression expr;
  protected Type type;

  public Expression(Expression expr, Type type){
    this.expr = expr;
    this.type = expr.getType();
  }

  public Expression(Expression expr){
    this.expr = expr;
    type = expr.getType();
  }

  public Expression(Type type){
    this.type = type;
  }

  public Type getType() {
    return this.type;
  }

  public void setType(Type t) {
    this.type = t;
  }

  public boolean supportOp(){
    return true;
  }

  public <T> T accept(ASTVisitor<T> v) {
    return null;
  }
}
