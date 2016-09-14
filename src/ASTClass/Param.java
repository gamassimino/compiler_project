package src.ASTClass;

import java.util.ArrayList;
import java.util.List;
import Visitor.ASTVisitor;

class Param{

  private List<Pair<Type, IdName>> list;

  public Param(List<Pair<Type, IdName>> a_list){
    this.list = a_list;
  }

  public Param(Type a_type, IdName an_id){
    list = new ArrayList<Pair<Type, IdName>>();
    list.add(new Pair<Type, IdName>(a_type, an_id));
  }

  public void addParam(Type a_type, IdName an_id){
    list.add(new Pair<Type, IdName>(a_type, an_id));
  }

  public Pair<Type, IdName> getParam(){
    return list.get(list.size());
  }

  public Pair<Type, IdName> removeParam(){
    return list.remove(list.size());
  }

  @Override
  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}