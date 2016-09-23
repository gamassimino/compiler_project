package ASTClass;

import Visitor.ASTVisitor;

public class SubAssignment  extends Statement{
  private Expression left;
  private Expression right;

  public SubAssignment(Expression a_left, Expression a_right){
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

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }

  public boolean supportOp(){
    if (!left.getType().toString().equals(right.getType().toString()))
      return false;
    return (left.getType().toString().equals("integer"))||(left.getType().toString().equals("float"));
  }
  
  public String className(){
    return "SubAssignment";
  }

  @Override
  public String toString(){
    return left.toString()+" -= "+right.toString();
  }
}