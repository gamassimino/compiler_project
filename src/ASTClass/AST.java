package ASTClass;

import Visitor.ASTVisitor;

public abstract class AST {
	protected Integer line;
	protected Integer column;
	
	public Integer getLine() {
		return line;
	}
	
	public void setLine(Integer ln) {
		line = ln;
	}
	
	public Integer getColumn() {
		return column;
	}
	
	public void setColumn(Integer cn) {
		column = cn;
	}
	
	public abstract <T> T accept(ASTVisitor<T> v);
}
