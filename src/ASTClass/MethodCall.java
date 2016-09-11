package src.ASTClass;

import java.util.ArrayList;
import java.util.List;

class MethodCall extends Statement{
  private IdName id;
  private Navigation navigation;
  private List<Expression> expression;

  public MethodCall(IdName an_id, Navigation a_navigation, ArrayList<Expression> an_expression){
    id = an_id;
    navigation = a_navigation;
    expression = an_expression;
  }

  public MethodCall(IdName an_id, Navigation a_navigation){
    id = an_id;
    navigation = a_navigation;
  }

  public MethodCall(IdName an_id, ArrayList<Expression> an_expression){
    id = an_id;
    expression = an_expression;
  }

  public void setIdName(IdName an_id){
    id = an_id;
  }

  public void setNavigation(Navigation a_navigation){
    navigation = a_navigation;
  }

  public void setExpressions(ArrayList<Expression> an_expression){
    expression = an_expression;
  }

  public IdName getIdName(){
    return id;
  }

  public Navigation getNavigation(){
    return navigation;
  }

  public List<Expression> getExpressions(){
    return expression;
  }
}