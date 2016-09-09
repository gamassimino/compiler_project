package ASTClass.AssignOpType;

public enum AssignOpType {
	ADDASSIGNMENT,
	SUBASSIGNMENT,
	EQUAL;

	@Override
	public String toString() {
		switch(this) {
			case ADDASSIGNMENT:
				return "+=";
			case SUBASSIGNMENT:
				return "-=";
			case EQUAL:
				return "=";
		}

		return null;
	}
}
