package ASTClass;

import Visitor.ASTVisitor;

public class IdName{
  private String id;
  private IntLiteral size;
  private Type type;
  private Integer line;
  private Integer column;

  public IdName(String an_id){
    id = an_id;
    size = null;
    type = null;
  }

  public IdName(String an_id, Integer a_line, Integer a_column){
    id = an_id;
    size = null;
    type = null;
    line = a_line;
    column = a_column;
  }

  public IdName(String an_id, IntLiteral a_size){
    id = an_id;
    size = a_size;
    type= null;
  }

  public IdName(String an_id, IntLiteral a_size, Integer a_line, Integer a_column){
    id = an_id;
    size = a_size;
    type= null;
    line = a_line;
    column = a_column;
  }

  public IdName(String an_id, IntLiteral a_size, Type a_type){
    id = an_id;
    size = a_size;
    type= a_type;
  }

  public void setIdName(String an_id){
    id = an_id;
  }

  public IdName getIdName(){
    return new IdName(id);
  }

  public void setType(Type a_type){
    type = a_type;
  }

  public Type getType(){
    return type;
  }

  public Integer getLine(){
    return line;
  }

  public Integer getColumn(){
    return column;
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