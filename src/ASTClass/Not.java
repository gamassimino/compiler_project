package src.ASTClass;

class Not{
  private Expression expr;

  public Not(Expression a_expr){
    expr = a_expr;
  }

  public void setExpr(Expression a_expr){
    expr = a_expr;
  }

  public Expression getExpr(){
    return expr;
  }

  @Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}