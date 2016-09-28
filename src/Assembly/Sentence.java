package Assembly;

import ASTClass.*;
import java_cup.runtime.*;

public class Sentence{

  String operation;
  Expression operator_one;
  Expression operator_two;
  Expression result;

  public Sentence (String operation, Expression operator_one, Expression operator_two, Expression result){
    this.operation = operation;
    this.operator_one = operator_one;
    this.operator_two = operator_two;
    this.result = result;
  }

  public Expression getOperatorOne(){
    return operator_one;
  }

  public Expression getOperatorTwo(){
    return operator_two;
  }

  public Expression getResult(){
    return result;
  }

  public String toString(){
    return operation+" "+operator_one.getType().toString()+" "+operator_two.getType().toString();
  }
}
