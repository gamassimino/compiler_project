package ASTClass;

import Visitor.ASTVisitor;

public class Not extends Expression{
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

  public Type getType(){
    return new Type("boolean");
  }

  public boolean supportOp(){
    return expr.getType().toString().equals("boolean");
  }


  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}