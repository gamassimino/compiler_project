package ASTClass;


public class ExpressionAlgo{
  private String value;
  private String type;

  public ExpressionAlgo(String a_value, String a_type){
    this.value = a_value;
    this.type = a_type;
  }

  public String getValue() {
    return this.value;
  }


  public void setValue(String a_value) {
    this.value = a_value;
  }


  public String getType() {
    return this.type;
  }


  public void setType(String a_type) {
    this.type = a_type;
  }
}
