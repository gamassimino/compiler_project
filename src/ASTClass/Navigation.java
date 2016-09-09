package ASTClass.Navigation;

class Navigation{
  private Navigation navigation;
  private IdName id;

  public Navigation(Navigation a_navigation, IdName an_id){
    navigation = a_navigation;
    id = an_id
  }

  public Navigation(IdName an_id){
    navigation = null;
    id = an_id
  }

  public void setNavigation(Navigation a_navigation){
    navigation = a_navigation;
  }
  public void setIdName(IdName an_id){
    id = an_id;
  }

  public void getIdName(){
    return id;
  }

  public void getNavigation(){
    return navigation;
  }
}