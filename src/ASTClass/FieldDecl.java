package ASTClass;

import java.util.ArrayList;
import java.util.List;
import Visitor.ASTVisitor;

public class FieldDecl extends AST{
  private Type type;
  private IdName list_id;

  public FieldDecl(Type a_type, IdName a_list_id){
    type = a_type;
    list_id = a_list_id;
  }

  public void setListId(IdName a_list_id){
    list_id = a_list_id;
  }

  public void setType(Type a_type){
    type = a_type;
  }

  public Type getType(){
    return type;
  }

  public List<IdName> getListId(){
    return (list_id == null) ? new ArrayList<IdName>() : list_id;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}