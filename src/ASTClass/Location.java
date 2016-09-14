package src.ASTClass;

import Visitor.ASTVisitor;

public abstract class Location extends Expression {
	protected IdName id;

	public void setId(IdName id){
		this.id = id;
	}

	public IdName getId(){
		return id;
	}

  @Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}
