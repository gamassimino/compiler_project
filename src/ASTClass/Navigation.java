package src.ASTClass;

class Navigation{
  private Navigation navigation;
  private IdName id;

  public Navigation(Navigation a_navigation, IdName an_id){
    navigation = a_navigation;
    id = an_id;
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

  @Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}