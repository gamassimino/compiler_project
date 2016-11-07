package Visitor;

import ASTClass.*;
import java.util.LinkedList;
import java.util.Stack;
import java_cup.runtime.*;
import Assembly.Sentence;
import TableOfHash.InstanceOffset;
import TableOfHash.*;
import java.lang.Math;

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
  private LinkedList<Pair<String,Integer>> restore_values;
  private Stack<String> label_stack;
  private Integer offset;
  private LinkedList<Pair<String,Integer>> list;
  private String className;
  private String methodName;
  private InstanceOffset insOff;
  private Hash instance;
  private Heap heap;

  public IntermediateCode(Integer off, LinkedList<Pair<String,Integer>> a_list){
    className = "";
    methodName = "";
    sentence_list = new LinkedList<Sentence>();
    list = a_list;
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
    offset = off;
    restore_values = new LinkedList<Pair<String,Integer>>();
  }

  public Integer search(String name){
    for (Pair<String,Integer> pair : list) {
      if(pair.getFst().equals(name))
        return pair.getSnd();
    }
    return 0;
  }

  public Integer nextOffset(){
    offset -= 8;
    return offset;
  }

  public Integer getOffset(){
    return offset;
  }

  public void setOffset(Integer off){
    offset = off;
  }

  public void setHeap(Heap a_heap){
    heap = a_heap;
  }

  public void setHashInstance(Hash a_ins){
    instance = a_ins;
  }

  public void setInstanceOffset(InstanceOffset a_insOff){
    insOff = a_insOff;
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
    sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RAX", "record"), left, null));
    sentence_list.add(new Sentence("ADDQ", new ExpressionAlgo("RAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVQ", left, new ExpressionAlgo("RAX", "record"), null));
    sentence_list.add(new Sentence("", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(And stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    andcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultAnd"+andcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultAnd"+andcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endAnd"+andcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultAnd"+andcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endAnd"+andcc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(Assignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    String record;
    String mov;
    if (left.getType().equals("record") && left.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      left.setValue(left.getValue().replace("R","E"));
    }
    if (right.getType().equals("record") && right.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      right.setValue(right.getValue().replace("R","E"));
    }
    // if is variable or param set recoord to record=RAX and mov=MOVQ
    // if(right.getType().equals("record")){
    //   record = "RAX";
    //   mov = "MOVQ";
    // }else{
    //   record = "EAX";
    //   mov = "MOVL";
    // }

    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVL", left, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("", null, null, null));
    return null;
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
    if (label_stack.peek().toString().equals("_EndWhile"+whilecc))
      sentence_list.add(new Sentence("Break", new ExpressionAlgo("_EndWhile"+whilecc,"label"), null, null));
    if (label_stack.peek().toString().equals("_EndFor"+forcc))
      sentence_list.add(new Sentence("Break", new ExpressionAlgo("_EndFor"+forcc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(ClassDecl expr){
    className = expr.getIdName().toString();
    for (MethodDecl method_decl : expr.getMethodDecl()) {
      if (method_decl.getBody().getBlock() != null) {
        if(method_decl.getIdName().toString().equals("main"))
          sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_main","label"), null, null));
        sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_InitMethod"+method_decl.getIdName().toString(),"label"), null, null));
        method_decl.accept(this);
        sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_EndMethod"+method_decl.getIdName().toString(),"label"), null, null));
      }
    }
    return null;
  }

  public ExpressionAlgo visit(ContinueStmt expr){
    String top = label_stack.peek();
    label_stack.pop();
    if (label_stack.peek().toString().equals("_BeginWhile"+whilecc))
      sentence_list.add(new Sentence("Continue", new ExpressionAlgo("_BeginWhile"+whilecc,"label"), null, null));
    if (label_stack.peek().toString().equals("_BeginFor"+forcc))
      sentence_list.add(new Sentence("Continue", new ExpressionAlgo("_BeginFor"+forcc,"label"), null, null));
    label_stack.push(top);
    return null;
  }

  public ExpressionAlgo visit(Divided expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");

    if (left.getType().equals("record") && left.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      left.setValue(left.getValue().replace("R","E"));
    }
    if (right.getType().equals("record") && right.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      right.setValue(right.getValue().replace("R","E"));
    }
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EBX", "record"), right, null));
    sentence_list.add(new Sentence("CLTD", null, null, null));
    sentence_list.add(new Sentence("IDIVL", new ExpressionAlgo("EBX", "record"), null, null));
    sentence_list.add(new Sentence("MOVQ", t0, new ExpressionAlgo("RAX", "record"), null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(EqualTo expr){
    ExpressionAlgo left = expr.getLeft().accept(this);
    ExpressionAlgo right = expr.getRight().accept(this);

    if (left.getType().equals("record") && left.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      left.setValue(left.getValue().replace("R","E"));
    }
    if (right.getType().equals("record") && right.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      right.setValue(right.getValue().replace("R","E"));
    }
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    eqcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultEqual"+eqcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endEqual"+eqcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultEqual"+eqcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endEqual"+eqcc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(FieldDecl expr){
    return null;
  }

  public ExpressionAlgo visit(ForStmt stmt){
    forcc++;
    int for_end = forcc;
    label_stack.push("_BeginFor"+forcc);
    label_stack.push("_EndFor"+forcc);
    ExpressionAlgo i = stmt.getIdName().accept(this);
    ExpressionAlgo step = stmt.getStep().accept(this);
    ExpressionAlgo condition = stmt.getCondition().accept(this);
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), condition, null));
    sentence_list.add(new Sentence("MOVL", i, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_beginFor"+for_end,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), i, null));
    sentence_list.add(new Sentence("CMPL", step, new ExpressionAlgo("EAX", "record"), null));
    sentence_list.add(new Sentence("JZ", new ExpressionAlgo("_endFor"+for_end,"label"), null, null));
    stmt.getStatement().accept(this);
    sentence_list.add(new Sentence("INCL", i, null, null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_beginFor"+for_end,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endFor"+for_end,"label"), null, null));
    label_stack.pop();
    label_stack.pop();
    sentence_list.add(new Sentence("", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(Greater stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    gcc++;

    if (left.getType().equals("record") && left.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      left.setValue(left.getValue().replace("R","E"));
    }
    if (right.getType().equals("record") && right.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      right.setValue(right.getValue().replace("R","E"));
    }
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("JL", new ExpressionAlgo("_compare_result_greater"+gcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("end_greater"+gcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_greater"+gcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("end_greater"+gcc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(GreaterOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    gecc++;

    if (left.getType().equals("record") && left.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      left.setValue(left.getValue().replace("R","E"));
    }
    if (right.getType().equals("record") && right.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      right.setValue(right.getValue().replace("R","E"));
    }
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("JLE", new ExpressionAlgo("_compare_result_greaterE"+gecc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_end_greaterE"+gecc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_greaterE"+gecc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_end_greaterE"+gecc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(IdName stmt){
    ExpressionAlgo t0;

    if (stmt.getRecord()!= null)
      t0 = new ExpressionAlgo(stmt.getRecord(), "record");
    else
      t0 = new ExpressionAlgo(stmt.getOffset().toString(), "offset");

    if (stmt.getSize() != null) {
      ExpressionAlgo expOffset = stmt.getSize().accept(this);
      sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("ECX", "record"), expOffset, null));
      return (new ExpressionAlgo(t0.getValue(), "array"));
    }
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(IfStmt stmt){
    ifcc++;
    int if_end = ifcc;
    Sentence result;
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_beginIf"+if_end,"label"), null, null));
    ExpressionAlgo t0 = stmt.getCondition().accept(this);
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
    if (t0.getValue() == "true"){
      sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1", "value"), null));
    }else{
      if (t0.getValue() == "false"){
        sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("0", "value"), null));
      }
      else{
        sentence_list.add(new Sentence("CMPL", t0, new ExpressionAlgo("EAX", "record"), null));
      }
    }
    if(stmt.getElseBlock() != null){
      sentence_list.add(new Sentence("JNE", new ExpressionAlgo("_beginElse"+if_end,"label"), null, null));
      stmt.getIfBlock().accept(this);
      sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endIf"+if_end,"label"), null, null));
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_beginElse"+if_end,"label"), null, null));
      stmt.getElseBlock().accept(this);
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endElse"+if_end,"label"), null, null));
      sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endIf"+if_end,"label"), null, null));
    }
    else{
      sentence_list.add(new Sentence("JNE", new ExpressionAlgo("_endIf"+if_end,"label"), null, null));
      stmt.getIfBlock().accept(this);
    }
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endIf"+if_end,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(IntLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getRawValue().toString(),"value");
    return t0;
  }

  public ExpressionAlgo visit(BoolLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getIntValue(),"value");
    return t0;
  }

  public ExpressionAlgo visit(FloatLiteral stmt){
    ExpressionAlgo t0 = new ExpressionAlgo(stmt.getRawValue().toString(),"value");
    return t0;
  }

  public ExpressionAlgo visit(Less stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    lcc++;

    if (left.getType().equals("record") && left.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      left.setValue(left.getValue().replace("R","E"));
    }
    if (right.getType().equals("record") && right.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      right.setValue(right.getValue().replace("R","E"));
    }
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("JG", new ExpressionAlgo("_compare_result_less"+lcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_end_less"+lcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_less"+lcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_end_less"+lcc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(LessOrEq stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    lecc++;
    if (left.getType().equals("record") && left.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      left.setValue(left.getValue().replace("R","E"));
    }
    if (right.getType().equals("record") && right.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      right.setValue(right.getValue().replace("R","E"));
    }
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("JGE", new ExpressionAlgo("_compare_result_lessE"+lecc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_end_lessE"+lecc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_compare_result_lessE"+lecc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_end_lessE"+lecc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(LocationExpr stmt){
    if (stmt.getList() != null){
      ExpressionAlgo expOffset = new ExpressionAlgo(stmt.getList().getIdName().getIndex().toString(),"value");
      sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("ECX", "record"), expOffset, null));
      return (new ExpressionAlgo(stmt.getId().getOffset().toString(), "array"));
    }else{
      if (heap.search(className+methodName, stmt.getId().toString()) || className.equals("main"))
        return stmt.getId().accept(this);
      else{
        ExpressionAlgo expOffset = new ExpressionAlgo(stmt.getId().getIndex().toString(),"value");
        sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("ECX", "record"), expOffset, null));
        return (new ExpressionAlgo("", "instance"));
      }
    }
  }

  public ExpressionAlgo visit(LocationStmt stmt){
    if (stmt.getList() != null){
      ExpressionAlgo expOffset = new ExpressionAlgo(stmt.getList().getIdName().getIndex().toString(),"value");
      sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("ECX", "record"), expOffset, null));
      return (new ExpressionAlgo(stmt.getId().getOffset().toString(), "array"));
    }else{
      if (heap.search(className+methodName, stmt.getId().toString()) || className.equals("main"))
        return stmt.getId().accept(this);
      else{
        ExpressionAlgo expOffset = new ExpressionAlgo(stmt.getId().getIndex().toString(),"value");
        sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("ECX", "record"), expOffset, null));
        return (new ExpressionAlgo("", "instance"));
      }
    }
  }

  public ExpressionAlgo visit(MethodCallStmt stmt){
    if(stmt.getNavigation() != null)
      methodName = stmt.getNavigation().getIdName().toString();
    else
      methodName = stmt.getIdName().toString();

    LinkedList<Expression> param_list = new LinkedList<Expression>();
    for (Expression param : stmt.getExpressions()) {
      param_list.addFirst(param);
    }
    int i = 0;

    save_record(stmt.getAttrNum());
    ExpressionAlgo aux = null;
    for (Expression param : param_list) {
      ExpressionAlgo t0 = param.accept(this);

      switch (i) {
        case 0 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RDI","record"), t0, null));
                  break;
        case 1 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RSI","record"), t0, null));
                  break;
        case 2 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RDX","record"), t0, null));
                  break;
        case 3 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RCX","record"), t0, null));
                  break;
        case 4 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("R8","record"), t0, null));
                  break;
        case 5 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("R9","record"), t0, null));
                  break;
        default : sentence_list.add(new Sentence("PUSHQ", t0, null, null));
                  aux = t0;
                  break;
      }
      i++;
    }

    if (i>5 && i%2!=0)
      sentence_list.add(new Sentence("PUSHQ", aux, null, null));
    ExpressionAlgo name;
    if (stmt.getNavigation() != null){
      sentence_list.add(new Sentence("LEAQ", new ExpressionAlgo("R10","record"), new ExpressionAlgo(stmt.getIdName().getOffset().toString(), "offset"), null));
      name = new ExpressionAlgo("_InitMethod"+stmt.getNavigation().getIdName().toString(),"label");
    }
    else{
      if (stmt.isExtern())
        name = new ExpressionAlgo("_"+stmt.getIdName().toString(),"label");
      else
        name = new ExpressionAlgo("_InitMethod"+stmt.getIdName().toString(),"label");
    }
    ExpressionAlgo result = new ExpressionAlgo("result","value");
    sentence_list.add(new Sentence("CALL", name, result, null));
    load_record(stmt.getAttrNum());
    if(stmt.getExpressions().size() > 6){
      Integer size = (stmt.getExpressions().size() - 6)*4;
      sentence_list.add(new Sentence("SUBQ", new ExpressionAlgo("RSP","record"), new ExpressionAlgo(size.toString(),"value"), null));
    }
    sentence_list.add(new Sentence("", null, null, null));
    return new ExpressionAlgo("CARLO","record");
  }

  public ExpressionAlgo visit(MethodCallExpr stmt){
    if(stmt.getNavigation() != null)
      methodName = stmt.getNavigation().getIdName().toString();
    else
      methodName = stmt.getIdName().toString();

    LinkedList<Expression> param_list = new LinkedList<Expression>();
    for (Expression param : stmt.getExpressions()) {
      param_list.addFirst(param);
    }
    int i = 0;
    save_record(stmt.getAttrNum());
    for (Expression param : param_list) {
      ExpressionAlgo t0 = param.accept(this);

      switch (i) {
        case 0 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RDI","record"), t0, null));
                  break;
        case 1 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RSI","record"), t0, null));
                  break;
        case 2 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RDX","record"), t0, null));
                  break;
        case 3 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RCX","record"), t0, null));
                  break;
        case 4 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("R8","record"), t0, null));
                  break;
        case 5 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("R9","record"), t0, null));
                  break;
        default : sentence_list.add(new Sentence("PUSHQ", t0, null, null));
                  break;
      }
      i++;
    }

    ExpressionAlgo name;
     if (stmt.getNavigation() != null){
      sentence_list.add(new Sentence("LEAQ", new ExpressionAlgo("R10","record"), new ExpressionAlgo(stmt.getIdName().getOffset().toString(), "offset"), null));
      name = new ExpressionAlgo("_InitMethod"+stmt.getNavigation().getIdName().toString(),"label");
    }
    else
      if (stmt.isExtern())
        name = new ExpressionAlgo("_"+stmt.getIdName().toString(),"label");
      else
        name = new ExpressionAlgo("_InitMethod"+stmt.getIdName().toString(),"label");

    sentence_list.add(new Sentence("CALL", name, null, null));
    load_record(stmt.getAttrNum());
    if(stmt.getExpressions().size() > 6){
      Integer size = (stmt.getExpressions().size() - 6)*4;
      sentence_list.add(new Sentence("SUBQ", new ExpressionAlgo("RSP","record"), new ExpressionAlgo(size.toString(),"value"), null));
    }
    sentence_list.add(new Sentence("", null, null, null));
    return new ExpressionAlgo("RAX","record");
  }

  public ExpressionAlgo visit(MethodDecl stmt){
    methodName = stmt.getIdName().toString();
    Integer offset = search(methodName+stmt.getIdName().toString())*(-4)*16;

    if ((Math.abs(getOffset()) % 16) != 0)
      offset = -getOffset()+8;

    sentence_list.add(new Sentence("PUSHQ", new ExpressionAlgo("RBP","record"), null, null));
    sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RBP","record"), new ExpressionAlgo("RSP","record"), null));

    Integer new_offset = 2048;
    sentence_list.add(new Sentence("SUBQ", new ExpressionAlgo("RSP","record"), new ExpressionAlgo(new_offset.toString(),"value"), null));

    stmt.getBody().accept(this);
    sentence_list.add(new Sentence("LEAVE", new ExpressionAlgo("null",""), null, null));
    sentence_list.add(new Sentence("RET", new ExpressionAlgo("null",""), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(Minus stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right;
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    if (stmt.getRight() != null){
      right = stmt.getRight().accept(this);
      sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RAX", "record"), left, null));
      sentence_list.add(new Sentence("SUBQ", new ExpressionAlgo("RAX", "record"), right, null));
      sentence_list.add(new Sentence("MOVQ", t0, new ExpressionAlgo("RAX", "record"), null));
    }
    else{
      sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RAX", "record"), left, null));
      sentence_list.add(new Sentence("IMULQ", new ExpressionAlgo("RAX", "record"), new ExpressionAlgo("-1","value"), null));
      sentence_list.add(new Sentence("MOVQ", t0, new ExpressionAlgo("RAX", "record"), null));
    }
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(Navigation stmt){
    return null;
  }

  public ExpressionAlgo visit(Not stmt){
    ExpressionAlgo left = stmt.getExpr().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    notcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_notCondition"+notcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_notEnd"+notcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_notCondition"+notcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_notEnd"+notcc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(NotEqualTo stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    if (left.getType().equals("record") && left.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      left.setValue(left.getValue().replace("R","E"));
    }
    if (right.getType().equals("record") && right.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      right.setValue(right.getValue().replace("R","E"));
    }
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    neqcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("JNE", new ExpressionAlgo("_resultNotEqual"+neqcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endNotEqual"+neqcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultNotEqual"+neqcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endNotEqual"+neqcc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(Or stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    orcc++;
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultOr"+orcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), right, null));
    sentence_list.add(new Sentence("JE", new ExpressionAlgo("_resultOr"+orcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("0","value"), null));
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_endOr"+orcc,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_resultOr"+orcc,"label"), null, null));
    sentence_list.add(new Sentence("MOVL", t0, new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_endOr"+orcc,"label"), null, null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(Param stmt){
    ExpressionAlgo t0 = new ExpressionAlgo("8","offset");
    return t0;
  }

  public ExpressionAlgo visit(Percentage stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");

    if (left.getType().equals("record") && left.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      left.setValue(left.getValue().replace("R","E"));
    }
    if (right.getType().equals("record") && right.getValue().charAt(0)==('R')){
      System.out.println("Lo REMPLACE");
      right.setValue(right.getValue().replace("R","E"));
    }
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), left, null));
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EBX", "record"), right, null));
    sentence_list.add(new Sentence("CLTD", null, null, null));
    sentence_list.add(new Sentence("IDIVL", new ExpressionAlgo("EBX", "record"), null, null));
    sentence_list.add(new Sentence("MOVQ", t0, new ExpressionAlgo("RDX", "record"), null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(Plus stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    nextOffset();
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RAX", "record"), left, null));
    sentence_list.add(new Sentence("ADDQ", new ExpressionAlgo("RAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVQ", t0, new ExpressionAlgo("RAX", "record"), null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(Program stmt){
    for (ClassDecl clas : stmt.getClassList()) {
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_InitClass"+clas.getIdName().toString(),"label"), null, null));
      clas.accept(this);
      sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_EndClass"+clas.getIdName().toString(),"label"), null, null));
    }
    sentence_list.add(new Sentence("", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(ReturnStmt stmt){
    Sentence result;
    if (stmt.getExpression() == null)
     result = new Sentence("ReturnStmt", null, null, null);
    else{
      ExpressionAlgo return_value = stmt.getExpression().accept(this);
      result = new Sentence("MOVQ", new ExpressionAlgo("RAX","record"), return_value, null);
    }
    sentence_list.add(result);
    return null;
  }

  public ExpressionAlgo visit(ReturnExpr stmt){
    ExpressionAlgo return_value = stmt.getExpression().accept(this);
    sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RAX","record"), return_value, null));
    sentence_list.add(new Sentence("", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(SubAssignment stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RAX", "record"), left, null));
    sentence_list.add(new Sentence("SUBQ", new ExpressionAlgo("RAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVQ", left, new ExpressionAlgo("RAX", "record"), null));
    return left;
  }

  public ExpressionAlgo visit(Times stmt){
    ExpressionAlgo left = stmt.getLeft().accept(this);
    ExpressionAlgo right = stmt.getRight().accept(this);
    ExpressionAlgo t0 = new ExpressionAlgo(nextOffset().toString(),"offset");
    sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo("RAX", "record"), left, null));
    sentence_list.add(new Sentence("IMULQ", new ExpressionAlgo("RAX", "record"), right, null));
    sentence_list.add(new Sentence("MOVQ", t0, new ExpressionAlgo("RAX", "record"), null));
    sentence_list.add(new Sentence("", null, null, null));
    return t0;
  }

  public ExpressionAlgo visit(Type stmt){
    return null;
  }

  public ExpressionAlgo visit(WhileStmt stmt){
    whilecc++;
    int while_end = whilecc;
    label_stack.push("_BeginWhile"+whilecc);
    label_stack.push("_EndWhile"+whilecc);
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_BeginWhile"+while_end,"label"), null, null));
    ExpressionAlgo cond = stmt.getCondition().accept(this);
    sentence_list.add(new Sentence("MOVL", new ExpressionAlgo("EAX", "record"), new ExpressionAlgo("1","value"), null));
    sentence_list.add(new Sentence("CMPL", new ExpressionAlgo("EAX", "record"), cond, null));
    sentence_list.add(new Sentence("JNE", new ExpressionAlgo("_EndWhile"+while_end,"label"), null, null));
    stmt.getStatement().accept(this);
    sentence_list.add(new Sentence("JMP", new ExpressionAlgo("_BeginWhile"+while_end,"label"), null, null));
    sentence_list.add(new Sentence("LABEL", new ExpressionAlgo("_EndWhile"+while_end,"label"), null, null));
    label_stack.pop();
    label_stack.pop();
    sentence_list.add(new Sentence("", null, null, null));
    return null;
  }

  public ExpressionAlgo visit(Instance stmt){
    return null;
  }


  public String getOffset(Object o){

    if(o instanceof LocationExpr){
      LocationExpr lo = (LocationExpr)o;
      return lo.getId().getOffset().toString();
    }

    if(o instanceof LocationStmt){
      LocationStmt lo = (LocationStmt)o;
      return lo.getId().getOffset().toString();
    }

    return "";
  }

  public void save_record(Integer records){
    int i = 0;
    if (records > 6)
      records = 6;

    while(i < records){
      switch (i) {
        case 0 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo(nextOffset().toString(), "offset"), new ExpressionAlgo("RDI","record"), null));
                 restore_values.add(new Pair<String, Integer>("RDI", getOffset()));
                  break;
        case 1 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo(nextOffset().toString(), "offset"), new ExpressionAlgo("RSI","record"), null));
                 restore_values.add(new Pair<String, Integer>("RSI", getOffset()));
                  break;
        case 2 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo(nextOffset().toString(), "offset"), new ExpressionAlgo("RDX","record"), null));
                 restore_values.add(new Pair<String, Integer>("RDX", getOffset()));
                  break;
        case 3 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo(nextOffset().toString(), "offset"), new ExpressionAlgo("RCX","record"), null));
                 restore_values.add(new Pair<String, Integer>("RCX", getOffset()));
                  break;
        case 4 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo(nextOffset().toString(), "offset"), new ExpressionAlgo("R8","record"), null));
                 restore_values.add(new Pair<String, Integer>("R8", getOffset()));
                  break;
        case 5 : sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo(nextOffset().toString(), "offset"), new ExpressionAlgo("R9","record"), null));
                 restore_values.add(new Pair<String, Integer>("R9", getOffset()));
                  break;
        }
      i++;
    }

    sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo(nextOffset().toString(), "offset"), new ExpressionAlgo("R10","record"), null));
    restore_values.add(new Pair<String, Integer>("R10", getOffset()));
  }

  public void load_record(Integer records){
    int i = 0;
    if (records > 6)
      records = 6;

    while(i <= records){
      Pair<String,Integer> pair = restore_values.removeLast();
      sentence_list.add(new Sentence("MOVQ", new ExpressionAlgo(pair.getFst(), "record"), new ExpressionAlgo(pair.getSnd().toString(), "offset"), null));
      i++;
    }
  }

}