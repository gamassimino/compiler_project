package ASTClass;

// import Visitor.ASTVisitor;

public class Type {
  private String type;

  public Type(String a_type){
    type = a_type;
  }

  public Type getType(){
    return new Type(type);
  } 

  public void setType(String a_type){
    type = a_type;
  }

  @Override
  public String toString() {
    return type;
  }

  // public <T> T accept(ASTVisitor<T> v) {
  //  return v.visit(this);
  // }
}
