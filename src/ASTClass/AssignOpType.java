package src.ASTClass;

import Visitor.ASTVisitor;

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
}
