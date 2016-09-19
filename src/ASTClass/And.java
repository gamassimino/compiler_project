package ASTClass;

import Visitor.ASTVisitor;

public class And extends Expression{
  private Expression left;
  private Expression right;

  public And(Expression a_left, Expression a_right){
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
    return new Type("And");
  }

  public boolean supportOp(){
    return (left.getType().toString()=="integer")||(left.getType().toString()=="float")||(left.getType().toString()=="boolean");
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}