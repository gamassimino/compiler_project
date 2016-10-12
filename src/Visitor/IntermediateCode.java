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
  private Integer offset;
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
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("ADDQ", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVL", left, new ExpressionAlgo("EAX", "record"), null));
    return left;
  }

  public ExpressionAlgo visit(And stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    andcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultAnd"+andcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("CMPL", right, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultAnd"+andcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endAnd"+andcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultAnd"+andcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endAnd"+andcc,"label"), null, null));
    return t0;
  }

  public ExpressionAlgo visit(Assignment stmt){
    ExpressionAlgo left = new ExpressionAlgo(stmt.getLeft().getOffset().toString(),"offset");
    String right;
    if (stmt.getRight() instanceof Literal){
      right = stmt.getRight().toString();
      sentence_list.add(new Sentence("MOVL", left, new ExpressionAlgo(right,"value"), null));
    }
    else{
      right = stmt.getRight().getOffset().toString();
      sentence_list.add(new Sentence("MOVL", left, new ExpressionAlgo(right,"offset"), null));
    }
    return left;
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
      sentence_list.add(new Sentence("Break", new ExpressionAlgo("_EndWhile"+whilecc,"label"), null, null));
    if (label_stack.peek().toString() == "_EndFor"+forcc)
      sentence_list.add(new Sentence("Break", new ExpressionAlgo("_EndFor"+forcc,"label"), null, null));
    return null;
  }

  public ExpressionAlgo visit(ClassDecl expr){
    for (MethodDecl method_decl : expr.getMethodDecl()) {
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_InitMethod"+method_decl.getIdName().toString(),"label"), null, null));
      method_decl.accept(this);
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_EndMethod"+method_decl.getIdName().toString(),"label"), null, null));
    }
    return null;
  }

  public ExpressionAlgo visit(ContinueStmt expr){
    String top = label_stack.peek();
    label_stack.pop();
    if (label_stack.peek().toString() == "_BeginWhile"+whilecc)
      sentence_list.add(new Sentence("Continue", new ExpressionAlgo("_BeginWhile"+whilecc,"label"), null, null));
    if (label_stack.peek().toString() == "_BeginFor"+forcc)
      sentence_list.add(new Sentence("Continue", new ExpressionAlgo("_BeginFor"+forcc,"label"), null, null));
    label_stack.push(top);
    return null;
  }

  public ExpressionAlgo visit(Divided expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("DIVQ", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("EAX", "record"), null));
    return t0;
  }

  public ExpressionAlgo visit(EqualTo expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    eqcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultEqual"+eqcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endEqual"+eqcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultEqual"+eqcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endEqual"+eqcc,"label"), null, null));
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
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_beginFor"+forcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), condition, null));
    sentence_list.add(new Sentence("CMPL", i, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JZ", new ExpressionAlgo("_endFor"+forcc,"label"), null, null));
    stmt.getStatement().accept(this);
    sentence_list.add(new Sentence("INC", i, null, null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_beginFor"+forcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endFor"+forcc,"label"), null, null));
    label_stack.pop();
    label_stack.pop();
    return null;
  }

  public ExpressionAlgo visit(Greater stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    gcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JG", new ExpressionAlgo("_compare_result_greater"+gcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("end_greater"+gcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_greater"+gcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("end_greater"+gcc,"label"), null, null));
    return t0;
  }

  public ExpressionAlgo visit(GreaterOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    gecc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JGE", new ExpressionAlgo("_compare_result_greaterE"+gecc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_end_greaterE"+gecc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_greaterE"+gecc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_end_greaterE"+gecc,"label"), null, null));
    return t0;
  }

  public ExpressionAlgo visit(IdName stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getOffset().toString(),"offset");
    return t0;
  }

  public ExpressionAlgo visit(IfStmt stmt){
    ifcc++;
    Sentence result;
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_beginIf"+ifcc,"label"), null, null));
    ExpressionAlgo t0 = stmt.getCondition().accept(this);
    if(stmt.getElseBlock() != null){
      sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
      sentence_list.add(new Sentence("CMPL", t0, new ExpressionAlgo("EAX", "record"), null));
      sentence_list.add(new Sentence("JZ", new ExpressionAlgo("_beginElse"+ifcc,"label"), null, null));
      stmt.getIfBlock().accept(this);
      sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endIf","label"), null, null));
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_beginElse"+ifcc,"label"), null, null));
      stmt.getElseBlock().accept(this);
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endElse"+ifcc,"label"), null, null));
      sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endIf","label"), null, null));
    }
    else{
      sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
      sentence_list.add(new Sentence("CMPL", t0, new ExpressionAlgo("EAX", "record"), null));
      sentence_list.add(new Sentence("JZ", new ExpressionAlgo("_endIf"+ifcc,"label"), null, null));
      stmt.getIfBlock().accept(this);
    }
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endIf"+ifcc,"label"), null, null));
    return null;
  }

  public ExpressionAlgo visit(IntLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getRawValue().toString(),"value");
    return t0;
  }

  public ExpressionAlgo visit(BoolLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getRawValue().toString(),"value");
    return t0;
  }

  public ExpressionAlgo visit(FloatLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getRawValue().toString(),"value");
    return t0;
  }

  public ExpressionAlgo visit(Less stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    lcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JL", new ExpressionAlgo("_compare_result_less"+lcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_end_less"+lcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_less"+lcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_end_less"+lcc,"label"), null, null));
    return t0;
  }

  public ExpressionAlgo visit(LessOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    lecc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JLE", new ExpressionAlgo("_compare_result_lessE"+lecc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_end_lessE"+lecc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_lessE"+lecc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_end_lessE"+lecc,"label"), null, null));
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
    //=====================================================================
    //=====================================================================
    //=====================================================================
    //seria un label o un value el retorno?
    //=====================================================================
    //=====================================================================
    //=====================================================================
    ExpressionAlgo name = new ExpressionAlgo(stmt.getIdName().toString(),"label");
    ExpressionAlgo result = new ExpressionAlgo("result","value");
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
    ExpressionAlgo name = new ExpressionAlgo(stmt.getIdName().toString(),"label");
    sentence_list.add(new Sentence("CALL", name, null, null));
    for (Expression param : stmt.getExpressions()) {
      sentence_list.add(new Sentence("POPQ", null, null, null));
    }
    return null;
  }

  public ExpressionAlgo visit(MethodDecl stmt){
    sentence_list.add(new Sentence("PUSHQ", new ExpressionAlgo("RBP","record"), null, null));
    sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RBP","record"), new ExpressionAlgo("RSP","record"), null));
    sentence_list.add(new Sentence("SUBQ", new ExpressionAlgo("4","value"), null, null));
    stmt.getBody().accept(this);
    sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RSP","record"), new ExpressionAlgo("RBP","record"), null));
    sentence_list.add(new Sentence("POPQ", new ExpressionAlgo("RBP","record"), null, null));
    sentence_list.add(new Sentence("RETQ", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(Minus stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right;
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    if (stmt.getRight() != null){
      right = stmt.getRight().accept(this);
      sentence_list.add(new Sentence("SUBQ", left, right, t0));
    }
    else{
      sentence_list.add(new Sentence("MUL", left, new ExpressionAlgo("-1","value"), t0));
    }
    return t0;
  }

  public ExpressionAlgo visit(Navigation stmt){
    return null;
  }

  public ExpressionAlgo visit(Not stmt){
    ExpressionAlgo left = stmt.getExpr().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    notcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_notCondition"+notcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_notEnd"+notcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_notCondition"+notcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_notEnd"+notcc,"label"), null, null));
    return t0;
  }

  public ExpressionAlgo visit(NotEqualTo stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    neqcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JNE", new ExpressionAlgo("_resultNotEqual"+neqcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endNotEqual"+neqcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultNotEqual"+neqcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endNotEqual"+neqcc,"label"), null, null));
    return t0;
  }

  public ExpressionAlgo visit(Or stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    orcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("CMPL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultOr"+orcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("CMPL", right, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultOr"+orcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endOr"+orcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultOr"+orcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endOr"+orcc,"label"), null, null));
    return t0;
  }

  public ExpressionAlgo visit(Param stmt){
    return null;
  }

  public ExpressionAlgo visit(Percentage stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    Sentence result = new Sentence("Percentage", left, right, t0);
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("DIVQ", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("EBX","label"), null));
    sentence_list.add(result);
    return t0;
  }

  public ExpressionAlgo visit(Plus stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("ADDQ", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("EAX", "record"), null));
    return t0;
  }

  public ExpressionAlgo visit(Program stmt){
    for (ClassDecl clas : stmt.getClassList()) {
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_InitClass"+clas.getIdName().toString(),"label"), null, null));
      clas.accept(this);
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_EndClass"+clas.getIdName().toString(),"label"), null, null));
    }
    return null;
  }

  public ExpressionAlgo visit(ReturnStmt stmt){
    Sentence result;
    if (stmt.getExpression() == null)
     result = new Sentence("ReturnStmt", null, null, null);
    else{
      ExpressionAlgo return_value = new ExpressionAlgo(stmt.getExpression().toString(),"value");
      result = new Sentence("ReturnStmt", return_value, null, null);
    }
    sentence_list.add(result);
    return null;
  }

  public ExpressionAlgo visit(ReturnExpr stmt){
    ExpressionAlgo return_value = new ExpressionAlgo(stmt.getExpression().toString(),"value");
    Sentence result = new Sentence("ReturnExpr", return_value, null, null);
    sentence_list.add(result);
    return null;
  }

  public ExpressionAlgo visit(SubAssignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("SUBQ", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVL", left, new ExpressionAlgo("EAX", "record"), null));
    return left;
  }

  public ExpressionAlgo visit(Times stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    offset -= 4;
    ExpressionAlgo t0 = new ExpressionAlgo(offset.toString(),"offset");
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("MULQ", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("EAX", "record"), null));
    return t0;
  }

  public ExpressionAlgo visit(Type stmt){
    return null;
  }

  public ExpressionAlgo visit(WhileStmt stmt){
    whilecc++;
    label_stack.push("_BeginWhile"+whilecc);
    label_stack.push("_EndWhile"+whilecc);
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_BeginWhile"+whilecc,"label"), null, null));
    ExpressionAlgo cond = stmt.getCondition().accept(this);
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("CMPL", cond, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JZ", new ExpressionAlgo("_EndWhile"+whilecc,"label"), null, null));
    stmt.getStatement().accept(this);
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_BeginWhile"+whilecc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_EndWhile"+whilecc,"label"), null, null));
    label_stack.pop();
    label_stack.pop();
    return null;
  }

  public ExpressionAlgo visit(Instance stmt){
    return null;
  }
}