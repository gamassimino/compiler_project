package src.ASTClass;

import java.util.ArrayList;
import java.util.List;
import src.ASTClass.Param;
import Visitor.ASTVisitor;

class ParamList{

  private Type type;
  private IdName id;
  private List<Param> listOfParam;

  public ParamList(){
    listOfParam = new ArrayList<Param>();
  }

  public ParamList(Type type, IdName id){
    Param param = new Param(type, id);
    listOfParam = new ArrayList<Param>();
    listOfParam.add(param);
  }
}