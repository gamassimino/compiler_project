package ASTClass;

import Visitor.ASTVisitor;
import java.util.List;

public class LocationStmt extends Expression {
  private IdName id;
  private Navigation list;

  public LocationStmt(IdName an_id){
    id = an_id;
  }

  public LocationStmt(IdName an_id, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    id = an_id;
    list = null;
  }

  public LocationStmt(IdName an_id, Navigation a_list, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    id = an_id;
    list = a_list;
  }

  public void setId(IdName id){
    this.id = id;
  }

  public void setOffsetLocation(){
    this.setOffset(id.getOffset());
  }

  public IdName getId(){
    return id;
  }

  public void setList(Navigation list){
    this.list = list;
  }

  public Navigation getList(){
    return list;
  }

  public Type getType(){
    if (list == null)
      return id.getType();
    else
      return list.getIdName().getType();
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }

  @Override
  public String toString(){
    return id.toString();
  }
}
