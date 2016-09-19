package ASTClass;

import java.util.ArrayList;
import java.util.List;
import Visitor.ASTVisitor;

public class FieldDecl extends AST{
  private Type type;
  private IdName id;

  public FieldDeclExpr(Type a_type, IdName a_id){
    type = a_type;
    id = a_id;
  }  

  public FieldDeclExpr(IdName a_id){
    type = null;
    id = a_id;
  }

  public void setId(IdName a_id){
    list_id = a_id;
  }

  public void setType(Type a_type){
    type = a_type;
  }

  public Type getType(){
    return type;
  }

  public getId(){
    return id;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}