package ASTClass;

import Visitor.ASTVisitor;
import java.util.List;

public class LocationStmt extends Statement {
  private IdName id;
  private Navigation list;

  public LocationStmt(IdName an_id, Integer a_line, Integer a_column){
    id = an_id;
    list = null;
    line = a_line;
    column = a_column;
  }

  public LocationStmt(IdName an_id, Navigation a_list, Integer a_line, Integer a_column){
    id = an_id;
    list = a_list;
    line = a_line;
    column = a_column;
  }

  public void setId(IdName id){
    this.id = id;
  }

  public IdName getId(){
    return id;
  }

  public Type getType(){
    if (list == null)
      return id.getType();
    else
      return list.getIdName().getType();
  }

  public void setList(Navigation list){
    this.list = list;
  }

  public Navigation getList(){
    return list;
  }

  //@Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }

  public String className(){
    return "LocationStmt";
  }

  @Override
  public String toString(){
    return id.toString();
  }
}
