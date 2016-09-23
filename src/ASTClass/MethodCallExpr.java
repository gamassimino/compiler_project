package ASTClass;

import java.util.ArrayList;
import java.util.List;
import Visitor.ASTVisitor;

public class MethodCallExpr extends Expression{
  private IdName id;
  private Navigation navigation;
  private List<Expression> expression;

  public MethodCallExpr(IdName an_id, Navigation a_navigation, ArrayList<Expression> an_expression){
    id = an_id;
    navigation = a_navigation;
    expression = an_expression;
  }

  public MethodCallExpr(IdName an_id, Navigation a_navigation){
    id = an_id;
    navigation = a_navigation;
    expression = null;
  }

  public MethodCallExpr(IdName an_id, ArrayList<Expression> an_expression){
    id = an_id;
    expression = an_expression;
    navigation = null;
  }

  public MethodCallExpr(IdName an_id){
    id = an_id;
    expression = null;
    navigation = null;
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

  public Type getType(){
    return id.getType();
  }

  public List<Expression> getExpressions(){
    return (expression == null) ? new ArrayList<Expression>() : expression;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}