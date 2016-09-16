package ASTClass;

// import Visitor.ASTVisitor;

public class BoolLiteral extends Literal {
  private String rawValue;
  private Boolean value;

  /*
   * Constructor for int literal that takes a string as an input
   * @param: String Boolean
   */
  public BoolLiteral(String val){
    rawValue = val; // Will convert to int value in semantic check
    value = null;
  }

  @Override
  public Type getType() {
    return new Type("boolean");
  }

  public String getStringValue() {
    return rawValue;
  }

  public void setStringValue(String stringValue) {
    this.rawValue = stringValue;
  }

  public Boolean getValue() {
    return value;
  }

  public void setValue(Boolean value) {
    this.value = value;
  }

  public String getRawValue() {
    return rawValue;
  }

  public void setRawValue(String rawValue) {
    this.rawValue = rawValue;
  }

  @Override
  public String toString() {
    return rawValue;
  }

  // public <T> T accept(ASTVisitor<T> v) {
  //  return v.visit(this);
  // }
}
