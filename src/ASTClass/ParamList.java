package ASTClass.ParamList;
import java.util.LinkedList;

class ParamList{

  private Type type;
  private IdName id;
  private List<Param> listOfParam;

  public ParamList(){
    listOfParam = new LinkedList<Param>();
  }

  public ParamList(Type type, IdName id){
    param = new Param(type, id);
    listOfParam = new LinkedList<Param>();
    listOfParam.add(param);
  }
}