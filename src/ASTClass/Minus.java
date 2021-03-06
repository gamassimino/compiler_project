package ASTClass;

import Visitor.ASTVisitor;

public class Minus extends Expression{
  private Expression left;
  private Expression right;

  public Minus(Expression a_left, Expression a_right, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    left = a_left;
    right = a_right;
  }

  public Minus(Expression a_left, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    left = a_left;
    right = null;
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

  public Type getType(){
    if (right == null)
      return  left.getType();
    return left.getType().toString().equals(right.getType().toString())? left.getType(): new Type("Minus.WrongType.Exception");
  }

  public boolean supportOp(){
    return (left.getType().toString().equals("integer"))||(left.getType().toString().equals("float"));
  }

  public Expression getRight(){
    return right;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}