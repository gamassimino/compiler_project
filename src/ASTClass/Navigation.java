package ASTClass;

import Visitor.ASTVisitor;

public class Navigation{
  private Navigation navigation;
  private IdName id;
  private Expression expr;
  private Integer line;
  private Integer column;

  /**
  * the attribute expression is only the navigation is a method
  */

  public Navigation(Navigation a_navigation, IdName an_id, Integer a_line, Integer a_column){
    navigation = a_navigation;
    id = an_id;
    expr = null;
    line = a_line;
    column = a_column;
  }

  public Navigation(Navigation a_navigation, Expression an_expr, Integer a_line, Integer a_column){
    navigation = a_navigation;
    expr = an_expr;
    id = null;
    line = a_line;
    column = a_column;
  }

  public Navigation(IdName an_id, Integer a_line, Integer a_column){
    navigation = null;
    id = an_id;
    line = a_line;
    column = a_column;
  }

  public void setNavigation(Navigation a_navigation){
    navigation = a_navigation;
  }
  public void setIdName(IdName an_id){
    id = an_id;
  }

  public IdName getIdName(){
    return id;
  }

  public Integer getLine(){
    return line;
  }

  public Integer getColumn(){
    return column;
  }

  public Navigation getNavigation(){
    return navigation;
  }

  public Expression getExpression(){
    return expr;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}