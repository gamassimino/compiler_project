package ASTClass;

import Visitor.ASTVisitor;

public class IntLiteral extends Literal {
  private String rawValue;
  private Integer value;

  /*
   * Constructor for int literal that takes a string as an input
   * @param: String integer
   */
  public IntLiteral(String val){
    rawValue = val; // Will convert to int value in semantic check
    value = new Integer(val);
  }

  @Override
  public Type getType() {
    return new Type("integer");
  }

  public String getStringValue() {
    return rawValue;
  }

  public void setStringValue(String stringValue) {
    this.rawValue = stringValue;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(int value) {
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

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}
