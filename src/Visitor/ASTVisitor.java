package Visitor;

import Visitor.ASTClass.*;

// Abstract visitor
public interface ASTVisitor<T> {
// visit statements
	T visit(AddAssignment stmt);
	T visit(And stmt);
	T visit(Assignment stmt);
	T visit(AssignOpType stmt);
	T visit(AssignStmt stmt);
	T visit(AST stmt);
	T visit(BinOpExpr expr);;
	T visit(BinOpType expr);;
	T visit(Block expr);;
	T visit(Body expr);;
	T visit(BreakStmt expr);;
	T visit(ClassDecl expr);;
	T visit(ContinueStmt expr);;
	T visit(Divided expr);;
	T visit(EqualTo expr);;
	T visit(Expression expr);;
	T visit(FieldDecl expr);;
	T visit(ForStmt stmt);
	T visit(Greater stmt);
	T visit(GreaterOrEq stmt);
	T visit(IdName stmt);
	T visit(IfStmt stmt);
	T visit(IntLiteral stmt);
	T visit(Less stmt);
	T visit(LessOrEq stmt);
	T visit(Literal stmt);
	T visit(Location stmt);
	T visit(LocationAux stmt);
	T visit(MethodCall stmt);
	T visit(MethodDecl stmt);
	T visit(Minus stmt);
	T visit(Navigation stmt);
	T visit(Not stmt);
	T visit(NotEqualTo stmt);
	T visit(Or stmt);
	T visit(Pair stmt); // it's no needed right?
	T visit(Param stmt);
	T visit(ParamList stmt);
	T visit(Percentage stmt);
	T visit(Plus stmt);
	T visit(Program stmt);
	T visit(ReturnStmt stmt);
	T visit(Statement stmt);
	T visit(SubAssignment stmt);
	T visit(Times stmt);
	T visit(Type stmt);
	T visit(VarLocation loc);
	T visit(WhileStmt loc);
	T visit(Stmt stmt);// what it's ?
	
// visit expressions
	
// visit literals	

// visit locations	
}
