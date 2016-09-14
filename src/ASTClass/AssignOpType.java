package src.ASTClass;

public enum AssignOpType {
	ADDASSIGNMENT,
	SUBASSIGNMENT,
	ASSIGNMENT;

	@Override
	public String toString() {
		switch(this) {
			case ADDASSIGNMENT:
				return "+=";
			case SUBASSIGNMENT:
				return "-=";
			case ASSIGNMENT:
				return "=";
		}

		return null;
	}

	@Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}
