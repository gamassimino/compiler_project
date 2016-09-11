package src.ASTClass;

public enum Type {
	INTEGER,
	BOOL,
	VOID,
	ID,
	FLOAT;

	@Override
	public String toString() {
		switch(this) {
			case INTEGER:
				return "integer";
			case VOID:
				return "void";
			case FLOAT:
				return "float";
			case ID:
				return "id";
			case BOOL:
				return "int[]";
		}

		return null;
	}
}
