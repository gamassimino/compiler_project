package src.ASTClass;

import Visitor.ASTVisitor;

public enum BinOpType {
	PLUS,
	MINUS,
	TIMES,
	DIVIDED,
	PERCENTAGE,
	LESS,
	LESSOREQ,
	GREATER,
	GREATEROREQ,
	EQUALTO,
	NOTEQUALTO,
	AND,
	OR;

	@Override
	public String toString() {
		switch(this) {
			case PLUS:
				return "+";
			case MINUS:
				return "-";
			case TIMES:
				return "*";
			case DIVIDED:
				return "/";
			case PERCENTAGE:
				return "%";
			case LESS:
				return "<";
			case LESSOREQ:
				return "<=";
			case GREATER:
				return ">";
			case GREATEROREQ:
				return ">=";
			case EQUALTO:
				return "==";
			case NOTEQUALTO:
				return "!=";
			case AND:
				return "&&";
			case OR:
				return "||";
		}

		return null;
	}

	@Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}
