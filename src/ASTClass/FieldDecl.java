package ASTClass;

import java.util.ArrayList;
import java.util.List;
import Visitor.ASTVisitor;

public class FieldDecl extends AST{
  private Type type;
  private IdName id;

  public FieldDecl(Type a_type, IdName a_id, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    type = a_type;
    id = a_id;
  }

  public FieldDecl(IdName a_id, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    type = null;
    id = a_id;
  }

  public void setId(IdName a_id){
    id = a_id;
  }

  public void setType(Type a_type){
    type = a_type;
  }

  public Type getType(){
    return type;
  }

  public IdName getId(){
    return id;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}