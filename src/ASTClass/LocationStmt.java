package ASTClass;

import Visitor.ASTVisitor;
import java.util.List;

public class LocationStmt extends Statement {
  private IdName id;
  private Navigation list;

  public LocationStmt(IdName an_id){
    id = an_id;
    list = null;
  }

  public LocationStmt(IdName an_id, Navigation a_list){
    id = an_id;
    list = a_list;
  }

  public void setId(IdName id){
    this.id = id;
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

  //@Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }

  @Override
  public String toString(){
    return id.toString();
  }
}
