package ASTClass;

import Visitor.ASTVisitor;

public class BoolLiteral extends Literal {
  private String rawValue;
  private Boolean value;
  private String intValue;

  /*
   * Constructor for int literal that takes a string as an input
   * @param: String Boolean
   */
  public BoolLiteral(String val){
    rawValue = val; // Will convert to int value in semantic check
    if (val.equals("true")){
      value = true;
      intValue = "1";
    }
    else{
      value = false;
      intValue = "0";
    }
  }

  @Override
  public Type getType() {
    return new Type("bool");
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

  public String getIntValue() {
    return intValue;
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

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}
