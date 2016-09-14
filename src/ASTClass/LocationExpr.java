package ASTClass;

import Visitor.ASTVisitor;

public class LocationExpr extends Expression {
	private IdName id;
  private List<Navigation> list;

  public LocationExpr(IdName an_id){
    id = an_id;
    list = null;
  }

  public LocationExpr(IdName an_id, List<Navigation> a_list){
    id = an_id;
    list = a_list;
  }

	public void setId(IdName id){
		this.id = id;
	}

	public IdName getId(){
		return id;
	}

  public void setList(List<Navigation> list){
    this.list = list;
  }

  public IdName getList(){
    return list;
  }

  // @Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}
