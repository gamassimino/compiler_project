package Assembly;

import ASTClass.*;
import java_cup.runtime.*;

public class Sentence{

  String operation;
  LocationExpr operator_one;
  LocationExpr operator_two;
  LocationExpr result;

  public Sentence (String operation, LocationExpr operator_one, LocationExpr operator_two, LocationExpr result){
    this.operation = operation;
    this.operator_one = operator_one;
    this.operator_two = operator_two;
    this.result = result;
  }

  public LocationExpr getOperatorOne(){
    return operator_one;
  }

  public LocationExpr getOperatorTwo(){
    return operator_two;
  }

  public LocationExpr getResult(){
    return result;
  }
}
