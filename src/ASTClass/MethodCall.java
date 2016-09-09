package ASTClass.MethodCall;

class MethodCall extends Statement{
  private IdName id;
  private Navigation navigation;
  private List<Expression> expressions;

  public MethodCall(IdName an_id, Navigation a_navigation, ArrayList<Expression> an_expressions){
    id = an_id;
    navigation = a_navigation;
    expressions = an_expressions;
  }

  public MethodCall(IdName an_id, Navigation a_navigation){
    id = an_id;
    navigation = a_navigation;
  }

  public MethodCall(IdName an_id, ArrayList<Expression> an_expressions){
    id = an_id;
    navigation = a_navigation;
  }

  public void setIdName(IdName an_id){
    id = an_id;
  }

  public void setNavigation(Navigation a_navigation){
    navigation = a_navigation;
  }

  public void setExpressions(ArrayList<Expression> an_expressions){
    expressions = an_expressions;
  }

  public IdName getIdName(){
    return id;
  }

  public Navigation getNavigation(){
    return navigation;
  }

  public List<Expression> getExpressions(){
    return expressions;
  }
}