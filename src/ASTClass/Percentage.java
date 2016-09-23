package ASTClass;

import Visitor.ASTVisitor;

public class Percentage extends Expression{
  private Expression left;
  private Expression right;

  public Percentage(Expression a_left, Expression a_right){
    left = a_left;
    right = a_right;
  }

  public void setLeft(Expression a_left){
    left = a_left;
  }

  public Expression getLeft(){
    return left;
  }

  public void setRight(Expression a_right){
    right = a_right;
  }

  public Expression getRight(){
    return right;
  }

  public Type getType(){
    return left.getType().toString().equals(right.getType().toString())? left.getType(): new Type("Percentage.WrongType.Exception");
  }

  public boolean supportOp(){
    return (left.getType().toString().equals("integer"))||(left.getType().toString().equals("float"));
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}