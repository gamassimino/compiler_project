package ASTClass.ParamList;
import java.util.LinkedList;

class ParamList{

  Type type;
  IdName id;
  LinkedList<Param> listOfParam;

  public ParamList(){
    listOfParam = new LinkedList<Param>();
  }

  public ParamList(Type type, IdName id){
    Param param = new Param(type,id);
    listOfParam = new LinkedList<Param>();
    listOfParam.add(param);
  }
}