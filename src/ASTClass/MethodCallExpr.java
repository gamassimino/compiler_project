package ASTClass;

import java.util.ArrayList;
import java.util.List;
import Visitor.ASTVisitor;

public class MethodCallExpr extends Expression{
  private IdName id;
  private Navigation navigation;
  private List<Expression> expression;
  private Boolean extern;
  private Integer attr_number;

  public MethodCallExpr(IdName an_id, Navigation a_navigation, ArrayList<Expression> an_expression, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    id = an_id;
    navigation = a_navigation;
    expression = an_expression;
  }

  public MethodCallExpr(IdName an_id, Navigation a_navigation, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    id = an_id;
    navigation = a_navigation;
    expression = null;
  }

  public MethodCallExpr(IdName an_id, ArrayList<Expression> an_expression, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    id = an_id;
    expression = an_expression;
    navigation = null;
  }

  public MethodCallExpr(IdName an_id, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
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
    if (navigation == null)
      return id.getType();
    else
      return navigation.getIdName().getType();
  }

  public void setExtern(Boolean extern){
    this.extern = extern;
  }

  public Boolean isExtern(){
    return extern;
  }

  public void setAttrNum(Integer attr){
    this.attr_number = attr;
  }
  
  public Integer getAttrNum(){
    return attr_number;
  }

  public List<Expression> getExpressions(){
    return (expression == null) ? new ArrayList<Expression>() : expression;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}