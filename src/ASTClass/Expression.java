package ASTClass;

import Visitor.ASTVisitor;

public abstract class Expression extends AST {
	protected Expression expr;
	protected Type type;

	public Type getType() {
		return this.type;
	}

	public void setType(Type t) {
		this.type = t;
	}

  public abstract boolean supportOp();

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}
