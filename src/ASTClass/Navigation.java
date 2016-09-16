package ASTClass;

import Visitor.ASTVisitor;

public class Navigation{
  private Navigation navigation;
  private IdName id;
  private Expression expr;

  public Navigation(Navigation a_navigation, IdName an_id){
    navigation = a_navigation;
    id = an_id;
    expr = null;
  }

  public Navigation(Navigation a_navigation, Expression an_expr){
    navigation = a_navigation;
    expr = an_expr;
    id = null;
  }

  public Navigation(IdName an_id){
    navigation = null;
    id = an_id;
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

  public Navigation getNavigation(){
    return navigation;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}