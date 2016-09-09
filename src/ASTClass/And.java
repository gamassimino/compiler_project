package ASTClass.And;

class And{
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
}