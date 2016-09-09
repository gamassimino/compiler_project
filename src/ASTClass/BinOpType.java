package ASTClass.BinOpType;

public enum BinOpType {
	PLUS, // Arithmetic
	MINUS,
	TIMES,
	DIVIDED,
	PERCENTAGE,
	LESS, // Relational
	LESSOREQ,
	GREATER,
	GREATEROREQ,
	EQUALTO, // Equal
	NOTEQUALTO,
	AND, // Conditional
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
}
