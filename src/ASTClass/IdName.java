package ASTClass;

import Visitor.ASTVisitor;

public class IdName{
  private String id;
  private IntLiteral size;

  public IdName(String an_id){
    id = an_id;
    size = null;
  }

  public IdName(String an_id, IntLiteral a_size){
    id = an_id;
    size = a_size;
  }

  public void setIdName(String an_id){
    id = an_id;
  }

  public IdName getIdName(){
    return new IdName(id);
  }

  public void setSize(IntLiteral a_size){
    size = a_size;
  }

  public IntLiteral getSize(){
    return size;
  }

  public String toString(){
    return id;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}