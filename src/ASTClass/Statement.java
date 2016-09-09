package ASTClass.Statement;

public abstract class Statement extends AST {

  private Location location;
  private Expression expression;
  private MethodCall method_call;
  private Statement statement;
  private IdName id;
  private Block block;

  public Statement(){
  }

  public Statement(Location loc, Sym symbol, Expression expr){
    Expression expr = new Expression(loc,symbol,expr);
  }

  public Statement(MethodCall m){
    MethodCall method = new MethodCall(m);
  }

  public Statement(Expression expr, Block if_block, Block else_block){
    IfStmt if_stm;
    if (else_block == null)
       if_stm = new IfStmt(e, if_block);
    else
      if_stm = new IfStmt(e, if_block, else_block);
  }

  public Statement(){
    //for constructor is missing
  }

  public Statement(){
    //while constructor is missing
  }

  public Statement(Sym symbol){
    switch(symbol) {
      case RETURN:
        ReturnStmt returnStmt = new ReturnStmt();
      case BREAK:
        BreakStmt breakStmt = new BreakStmt();
        //break constructor is missing
      case CONTINUE:
        ContinueStmt continueStmt = new ContinueStmt();
        //continue constructor is missing
    }
  }

  public Statement(Block block){
    //consultar por el constructor de block
  }

  public void setLocation(Location loc){
    location = loc;
  }

  public Location getLocation(){
    return location;
  }

  public void setExpression(Expression expr){
    expression = expr;
  }

  public Expression getExpression(){
    return expression;
  }

  public void setMethodCall(MethodCall method){
    method_call = method;
  }

  public MethodCall getMethodCall(){
    return method_call;
  }

  public void setStatement(Statement stat){
    statement = stat;
  }

  public Statement getStatement(){
    return statement;
  }

  public void setIdName(IdName idParam){
    id = idParam;
  }

  public IdName getIdName(){
    return id;
  }

  public void setBolck(Block blockParam){
    block = blockParam;
  }

  public Block getBlock(){
    return block;
  }
}