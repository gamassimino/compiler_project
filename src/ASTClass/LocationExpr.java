package ASTClass;

import Visitor.ASTVisitor;
import java.util.List;

public class LocationExpr extends Expression {
	private IdName id;
  private Navigation list;

  public LocationExpr(IdName an_id){
    id = an_id;
    list = null;
  }

  public LocationExpr(IdName an_id, Navigation a_list){
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

  public Type getType(){
    return new Type("LocationExpr");
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }

  @Override
  public String toString(){
    return id.toString();
  }
}
