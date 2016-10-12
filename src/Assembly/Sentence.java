package Assembly;

import ASTClass.*;
import java_cup.runtime.*;

public class Sentence{

  String operation;
  ExpressionAlgo operator_one;
  ExpressionAlgo operator_two;
  ExpressionAlgo result;

  public Sentence (String operation, ExpressionAlgo operator_one, ExpressionAlgo operator_two, ExpressionAlgo result){
    this.operation = operation;
    this.operator_one = operator_one;
    this.operator_two = operator_two;
    this.result = result;
  }

  public String getOperation(){
    return operation;
  }

  public ExpressionAlgo getOperatorOne(){
    return operator_one;
  }

  public ExpressionAlgo getOperatorTwo(){
    return operator_two;
  }

  public ExpressionAlgo getResult(){
    return result;
  }
}
