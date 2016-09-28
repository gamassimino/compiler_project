package ASTClass;


public class ExpressionAlgo{
  protected Expression expr;
  protected Type type;
  protected Integer line;
  protected Integer column;
  protected String name;

  public ExpressionAlgo(String name){
    this.name = name;
    this.type = new Type("Label");
  }

  public ExpressionAlgo(Expression expr, Type type){
    this.type = type;
    this.expr = expr;
  }

  public ExpressionAlgo(Expression expr){
    this.expr = expr;
    this.type = expr.getType();
  }

  public ExpressionAlgo(Type type){
    this.type = type;
  }
  public Integer getLine() {
    return line;
  }

  public void setLine(Integer ln) {
    line = ln;
  }

  public Integer getColumn() {
    return column;
  }

  public void setColumn(Integer cn) {
    column = cn;
  }

  public Expression getExpression(){
    return this.expr;
  }

  public Type getType() {
    return this.type;
  }

  public void setType(Type t) {
    this.type = t;
  }

  public boolean supportOp(){
    return true;
  }

}
