package Visitor;

import ASTClass.*;
import java.util.LinkedList;
import java_cup.runtime.*;
import Assembly.Sentence;

public class IntermediateCode implements ASTVisitor<ExpressionAlgo>{
  private Integer ifcc = 0;
  private Integer forcc;
  private Integer whilecc;
  private LinkedList<Sentence> sentence_list;

  public IntermediateCode(){
    sentence_list = new LinkedList<Sentence>();
  }
  public LinkedList<Sentence> getSentenceList(){
    return sentence_list;
  }

  public ExpressionAlgo visit(AddAssignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getLeft().getType());
    Sentence result = new Sentence("AddAssignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(And stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getLeft().getType());
    Sentence result = new Sentence("And", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Assignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getLeft().getType());
    Sentence result = new Sentence("Assignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Block expr){
    for (Statement statement : expr.getStatements()) {
      statement.accept(this);
    }
    return null;
  }

  public ExpressionAlgo visit(Body expr){
    expr.getBlock().accept(this);
    return null;
  }

  public ExpressionAlgo visit(BreakStmt expr){
    return null;
  }

  public ExpressionAlgo visit(ClassDecl expr){
    for (MethodDecl method_decl : expr.getMethodDecl()) {
      sentence_list.add(new Sentence("InitMethod"+method_decl.getIdName().toString(), null, null, null));
      method_decl.accept(this);
      sentence_list.add(new Sentence("EndMethod"+method_decl.getIdName().toString(), null, null, null));
    }
    return null;
  }

  public ExpressionAlgo visit(ContinueStmt expr){
    return null;
  }

  public ExpressionAlgo visit(Divided expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Divided", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(EqualTo expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("EqualTo", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(FieldDecl expr){
    return null;
  }

  public ExpressionAlgo visit(ForStmt stmt){
    stmt.getStatement().accept(this);
    return null;
  }

  public ExpressionAlgo visit(Greater stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Greater", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(GreaterOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("GreaterOrEq", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(IdName stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getType());
    return t0;
  }

  public ExpressionAlgo visit(IfStmt stmt){
    ifcc++;
    Sentence result;
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("beginIf"+ifcc), null, null));
    ExpressionAlgo t0 = stmt.getCondition().accept(this);
    if(stmt.getElseBlock() != null){
      sentence_list.add(new Sentence("JMPZ", t0, null, new ExpressionAlgo("beginElse")));
      stmt.getIfBlock().accept(this);
      sentence_list.add(new Sentence("JMP", null, null, new ExpressionAlgo("endIf")));
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("beginElse"+ifcc), null, null));
      stmt.getElseBlock().accept(this);
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("endElse"+ifcc), null, null));
      sentence_list.add(new Sentence("JMP", null, null, new ExpressionAlgo("endIf")));
    }
    else{
      sentence_list.add(new Sentence("JMPZ", t0, null, new ExpressionAlgo("endIf")));
      stmt.getIfBlock().accept(this);
    }
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("endIf"+ifcc), null, null));
    ifcc--;
    return null;
  }

  public ExpressionAlgo visit(IntLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt);
    return t0;
  }

  public ExpressionAlgo visit(BoolLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt);
    return t0;
  }

  public ExpressionAlgo visit(FloatLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt);
    return t0;
  }

  public ExpressionAlgo visit(Less stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Less", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(LessOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("LessOrEq", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(LocationExpr stmt){
    return stmt.getId().accept(this);
  }

  public ExpressionAlgo visit(LocationStmt stmt){
    return null;
  }

  public ExpressionAlgo visit(MethodCallStmt stmt){
    return null;
  }

  public ExpressionAlgo visit(MethodCallExpr stmt){
    return null;
  }

  public ExpressionAlgo visit(MethodDecl stmt){
    stmt.getBody().accept(this);
    return null;
  }

  public ExpressionAlgo visit(Minus stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Minus", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Navigation stmt){
    return null;
  }

  public ExpressionAlgo visit(Not stmt){
    ExpressionAlgo left = stmt.getExpr().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getExpr().getType());
    Sentence result = new Sentence("Not", left, null, t0);
    return t0;
  }

  public ExpressionAlgo visit(NotEqualTo stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("NotEqualTo", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Or stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Or", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Param stmt){
    return null;
  }

  public ExpressionAlgo visit(Percentage stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Percentage", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Plus stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Plus", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Program stmt){
    for (ClassDecl clas : stmt.getClassList()) {
      sentence_list.add(new Sentence("InitClass"+clas.getIdName().toString(), null, null, null));
      clas.accept(this);
      sentence_list.add(new Sentence("EndClass"+clas.getIdName().toString(), null, null, null));
    }
    return null;
  }

  public ExpressionAlgo visit(ReturnStmt stmt){
    Sentence result;
    if (stmt.getExpression() == null)
     result = new Sentence("ReturnStmt", null, null, null);
    else{
      ExpressionAlgo return_value = new ExpressionAlgo(stmt.getExpression());
      result = new Sentence("ReturnStmt", null, null, return_value);
    }
    sentence_list.add(result);
    return null;
  }

  public ExpressionAlgo visit(ReturnExpr stmt){
    // if (stmt.getExpression() != null)
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getExpression());
    Sentence result = new Sentence("ReturnExpr", null, null, t0);
    sentence_list.add(result);
    return null;
  }

  public ExpressionAlgo visit(SubAssignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("SubAssignment", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Times stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    Sentence result = new Sentence("Times", left, right, t0);
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Type stmt){
    return null;
  }

  public ExpressionAlgo visit(WhileStmt stmt){
    whilecc++;

    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("BeginWhile"+whilecc), null, null));

    ExpressionAlgo cond = stmt.getCondition().accept(this);

    sentence_list.add(new Sentence("JMPZ", cond, null, new ExpressionAlgo("EndWhile"+whilecc)));

    stmt.getStatement().accept(this);

    sentence_list.add(new Sentence("JMP", null, null, new ExpressionAlgo("BeginWhile"+whilecc)));

    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("EndWhile"+whilecc), null, null));

    whilecc--;
    return null;
  }
}
