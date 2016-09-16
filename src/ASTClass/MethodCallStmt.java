package ASTClass;

import java.util.ArrayList;
import java.util.List;
import Visitor.ASTVisitor;

public class MethodCallStmt extends Statement{
  private IdName id;
  private Navigation navigation;
  private List<Expression> expression;

  public MethodCallStmt(IdName an_id, Navigation a_navigation, ArrayList<Expression> an_expression){
    id = an_id;
    navigation = a_navigation;
    expression = an_expression;
  }

  public MethodCallStmt(IdName an_id, Navigation a_navigation){
    id = an_id;
    navigation = a_navigation;
    expression = null;
  }

  public MethodCallStmt(IdName an_id, ArrayList<Expression> an_expression){
    id = an_id;
    expression = an_expression;
    navigation= null;
  }
  public MethodCallStmt(IdName an_id){
    id = an_id;
    expression = null;
    navigation= null;
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
    return (expression == null) ? new ArrayList<Expression>() : expression;
  }

  // @Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}