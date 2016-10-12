package Visitor;

import ASTClass.*;
import java.util.LinkedList;
import java.util.Stack;
import java_cup.runtime.*;
import Assembly.Sentence;

public class IntermediateCode implements ASTVisitor<ExpressionAlgo>{
  private Integer ifcc;
  private Integer forcc;
  private Integer whilecc;
  private Integer gcc;
  private Integer gecc;
  private Integer lcc;
  private Integer lecc;
  private Integer andcc;
  private Integer orcc;
  private Integer eqcc;
  private Integer neqcc;
  private Integer notcc;
  private LinkedList<Sentence> sentence_list;
  private Stack<String> label_stack;

  public IntermediateCode(){
    sentence_list = new LinkedList<Sentence>();
    ifcc = 0;
    forcc = 0;
    whilecc = 0;
    gcc = 0;
    gecc = 0;
    lcc = 0;
    lecc = 0;
    andcc = 0;
    orcc = 0;
    eqcc = 0;
    neqcc = 0;
    notcc = 0;
    label_stack = new Stack<String>();
  }

  public LinkedList<Sentence> getSentences(){
    return sentence_list;
  }

  public LinkedList<Sentence> getSentenceList(){
    return sentence_list;
  }

  public ExpressionAlgo visit(AddAssignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    sentence_list.add(new Sentence("ADD", left, right, left));
    return left;
  }

  public ExpressionAlgo visit(And stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getLeft().getType());
    andcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultAnd"+andcc), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("CMPL", right, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultAnd"+andcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endAnd"+andcc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultAnd"+andcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endAnd"+andcc), null, null));
    return t0;
  }

  public ExpressionAlgo visit(Assignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    sentence_list.add(new Sentence("MOVL", right, left, null));
    return right;
  }

  public ExpressionAlgo visit(Block expr){
    for (Statement statement : expr.getStatements()) {
      statement.accept(this);
    }
    return null;
  }

  public ExpressionAlgo visit(Body expr){
    if (expr.getBlock() != null)
      expr.getBlock().accept(this);
    return null;
  }

  public ExpressionAlgo visit(BreakStmt expr){
    if (label_stack.peek().toString() == "_EndWhile"+whilecc)
      sentence_list.add(new Sentence("Break", new ExpressionAlgo("_EndWhile"+whilecc), null, null));
    else{
      if (label_stack.peek().toString() == "_EndFor"+forcc)
        sentence_list.add(new Sentence("Break", new ExpressionAlgo("_EndFor"+whilecc), null, null));
      else{
        sentence_list.add(new Sentence("Break", null, null, null));
      }
    }
    return null;
  }

  public ExpressionAlgo visit(ClassDecl expr){
    for (MethodDecl method_decl : expr.getMethodDecl()) {
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_InitMethod"+method_decl.getIdName().toString()), null, null));
      method_decl.accept(this);
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_EndMethod"+method_decl.getIdName().toString()), null, null));
    }
    return null;
  }

  public ExpressionAlgo visit(ContinueStmt expr){
    String top = label_stack.peek();
    label_stack.pop();
    if (label_stack.peek().toString() == "_BeginWhile"+whilecc)
      sentence_list.add(new Sentence("Continue", new ExpressionAlgo("_BeginWhile"+whilecc), null, null));
    else{
      if (label_stack.peek().toString() == "_BeginFor"+forcc)
        sentence_list.add(new Sentence("Continue", new ExpressionAlgo("_BeginFor"+whilecc), null, null));
      else{
        sentence_list.add(new Sentence("Continue", null, null, null));
      }
    }
    label_stack.push(top);
    return null;
  }

  public ExpressionAlgo visit(Divided expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    sentence_list.add(new Sentence("DIV", left, right, t0));
    return t0;
  }

  public ExpressionAlgo visit(EqualTo expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    eqcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultEqual"+eqcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endEqual"+eqcc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultEqual"+eqcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endEqual"+eqcc), null, null));
    return t0;
  }

  public ExpressionAlgo visit(FieldDecl expr){
    expr.getId().accept(this);
    return null;
  }

  public ExpressionAlgo visit(ForStmt stmt){
    forcc++;
    label_stack.push("_BeginFor"+forcc);
    label_stack.push("_EndFor"+forcc);
    ExpressionAlgo i = stmt.getIdName().accept(this);
    ExpressionAlgo condition = stmt.getCondition().accept(this);
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_beginFor"+forcc), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), condition, null));
    sentence_list.add(new Sentence("CMPL", i, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JZ", new ExpressionAlgo("_endFor"+forcc), null, null));
    stmt.getStatement().accept(this);
    sentence_list.add(new Sentence("INC", i, null, null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_beginFor"+forcc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endFor"+forcc), null, null));
    label_stack.pop();
    label_stack.pop();
    return null;
  }

  public ExpressionAlgo visit(Greater stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    gcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JG", new ExpressionAlgo("_compare_result_greater"+gcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("end_greater"+gcc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_greater"+gcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("end_greater"+gcc), null, null));
    return t0;
  }

  public ExpressionAlgo visit(GreaterOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    gecc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JGE", new ExpressionAlgo("_compare_result_greaterE"+gecc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_end_greaterE"+gecc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_greaterE"+gecc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_end_greaterE"+gecc), null, null));
    return t0;
  }

  public ExpressionAlgo visit(IdName stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getType());
    return t0;
  }

  public ExpressionAlgo visit(IfStmt stmt){
    ifcc++;
    Sentence result;
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_beginIf"+ifcc), null, null));
    ExpressionAlgo t0 = stmt.getCondition().accept(this);
    if(stmt.getElseBlock() != null){
      sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), new ExpressionAlgo("1"), null));
      sentence_list.add(new Sentence("CMPL", t0, new ExpressionAlgo("EAX"), null));
      sentence_list.add(new Sentence("JZ", new ExpressionAlgo("_beginElse"+ifcc), null, null));
      stmt.getIfBlock().accept(this);
      sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endIf"), null, null));
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_beginElse"+ifcc), null, null));
      stmt.getElseBlock().accept(this);
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endElse"+ifcc), null, null));
      sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endIf"), null, null));
    }
    else{
      sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), new ExpressionAlgo("1"), null));
      sentence_list.add(new Sentence("CMPL", t0, new ExpressionAlgo("EAX"), null));
      sentence_list.add(new Sentence("JZ", new ExpressionAlgo("_endIf"+ifcc), null, null));
      stmt.getIfBlock().accept(this);
    }
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endIf"+ifcc), null, null));
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
    lcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JL", new ExpressionAlgo("_compare_result_less"+lcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_end_less"+lcc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_less"+lcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_end_less"+lcc), null, null));
    return t0;
  }

  public ExpressionAlgo visit(LessOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    lecc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JLE", new ExpressionAlgo("_compare_result_lessE"+lecc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_end_lessE"+lecc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_lessE"+lecc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_end_lessE"+lecc), null, null));
    return t0;
  }

  public ExpressionAlgo visit(LocationExpr stmt){
    return stmt.getId().accept(this);
  }

  public ExpressionAlgo visit(LocationStmt stmt){
    return null;
  }

  public ExpressionAlgo visit(MethodCallStmt stmt){
    LinkedList<Expression> param_list = new LinkedList<Expression>();
    for (Expression param : stmt.getExpressions()) {
      param_list.addFirst(param);
    }
    for (Expression param : param_list) {
      ExpressionAlgo t0 = param.accept(this);
      sentence_list.add(new Sentence("PUSHQ", t0, null, null));
    }
    ExpressionAlgo name = new ExpressionAlgo(stmt.getIdName().toString());
    ExpressionAlgo result = new ExpressionAlgo("result");
    sentence_list.add(new Sentence("CALL", name, result, null));
    for (Expression param : stmt.getExpressions()) {
      sentence_list.add(new Sentence("POPQ", null, null, null));
    }
    return null;
  }

  public ExpressionAlgo visit(MethodCallExpr stmt){
    LinkedList<Expression> param_list = new LinkedList<Expression>();
    for (Expression param : stmt.getExpressions()) {
      param_list.addFirst(param);
    }
    for (Expression param : param_list) {
      ExpressionAlgo t0 = param.accept(this);
      sentence_list.add(new Sentence("PUSHQ", t0, null, null));
    }
    ExpressionAlgo name = new ExpressionAlgo(stmt.getIdName().toString());
    sentence_list.add(new Sentence("CALL", name, null, null));
    for (Expression param : stmt.getExpressions()) {
      sentence_list.add(new Sentence("POPQ", null, null, null));
    }
    return null;
  }

  public ExpressionAlgo visit(MethodDecl stmt){
    sentence_list.add(new Sentence("PUSHQ", new ExpressionAlgo("RBP"), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("RBP"), new ExpressionAlgo("RSP"), null));
    sentence_list.add(new Sentence("SUBQ", new ExpressionAlgo("4"), null, null));
    stmt.getBody().accept(this);
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("RSP"), new ExpressionAlgo("RBP"), null));
    sentence_list.add(new Sentence("POPQ", new ExpressionAlgo("RBP"), null, null));
    sentence_list.add(new Sentence("RETQ", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(Minus stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right;
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    if (stmt.getRight() != null){
      right = stmt.getRight().accept(this);
      sentence_list.add(new Sentence("SUBQ", left, right, t0));
    }
    else{
      sentence_list.add(new Sentence("MUL", left, new ExpressionAlgo("-1"), t0));
    }
    return t0;
  }

  public ExpressionAlgo visit(Navigation stmt){
    return null;
  }

  public ExpressionAlgo visit(Not stmt){
    ExpressionAlgo left = stmt.getExpr().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getExpr().getType());
    notcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_notCondition"+notcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_notEnd"+notcc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_notCondition"+notcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_notEnd"+notcc), null, null));
    return t0;
  }

  public ExpressionAlgo visit(NotEqualTo stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    neqcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JNE", new ExpressionAlgo("_resultNotEqual"+neqcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endNotEqual"+neqcc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultNotEqual"+neqcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endNotEqual"+neqcc), null, null));
    return t0;
  }

  public ExpressionAlgo visit(Or stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    orcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultOr"+orcc), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("CMPL", right, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultOr"+orcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endOr"+orcc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultOr"+orcc), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endOr"+orcc), null, null));
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
    sentence_list.add(new Sentence("ADD", left, right, t0));
    return t0;
  }

  public ExpressionAlgo visit(Program stmt){
    for (ClassDecl clas : stmt.getClassList()) {
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_InitClass"+clas.getIdName().toString()), null, null));
      clas.accept(this);
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_EndClass"+clas.getIdName().toString()), null, null));
    }
    return null;
  }

  public ExpressionAlgo visit(ReturnStmt stmt){
    Sentence result;
    if (stmt.getExpression() == null)
     result = new Sentence("ReturnStmt", null, null, null);
    else{
      ExpressionAlgo return_value = new ExpressionAlgo(stmt.getExpression());
      result = new Sentence("ReturnStmt", return_value, null, null);
    }
    sentence_list.add(result);
    return null;
  }

  public ExpressionAlgo visit(ReturnExpr stmt){
    ExpressionAlgo return_value = new ExpressionAlgo(stmt.getExpression());
    Sentence result = new Sentence("ReturnExpr", return_value, null, null);
    sentence_list.add(result);
    return null;
  }

  public ExpressionAlgo visit(SubAssignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    sentence_list.add(new Sentence("SUBQ", left, right, left));
    return left;
  }

  public ExpressionAlgo visit(Times stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(left.getType());
    sentence_list.add(new Sentence("MUL", left, right, t0));
    return t0;
  }

  public ExpressionAlgo visit(Type stmt){
    return null;
  }

  public ExpressionAlgo visit(WhileStmt stmt){
    whilecc++;
    label_stack.push("_BeginWhile"+whilecc);
    label_stack.push("_EndWhile"+whilecc);
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_BeginWhile"+whilecc), null, null));
    ExpressionAlgo cond = stmt.getCondition().accept(this);
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX"), new ExpressionAlgo("1"), null));
    sentence_list.add(new Sentence("CMPL", cond, new ExpressionAlgo("EAX"), null));
    sentence_list.add(new Sentence("JZ", new ExpressionAlgo("_EndWhile"+whilecc), null, null));
    stmt.getStatement().accept(this);
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_BeginWhile"+whilecc), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_EndWhile"+whilecc), null, null));
    label_stack.pop();
    label_stack.pop();
    return null;
  }

  public ExpressionAlgo visit(Instance stmt){
    return null;
  }
}