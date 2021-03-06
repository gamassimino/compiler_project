package ASTClass;


public abstract class Expression extends AST {
  protected Expression expr;
  protected Type type;

  public Type getType() {
    return this.type;
  }

  public void setType(Type t) {
    this.type = t;
  }

  public Expression getExpression(){
    return this.expr;
  }

  public boolean supportOp(){
    return true;
  }

}
