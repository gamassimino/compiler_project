package ASTClass.MethodDecl;

class MethodDecl{
  private IdName id;
  private Param param;
  private Body body;
  private Type type;

  public MethodDecl(Type a_type, IdName an_id, Param a_param, Body a_body){
    Type type = a_type;
    IdName id = an_id;
    Param param = a_param;
    Body body = a_body;
  }

  public MethodDecl(Type a_type, IdName an_id, Param a_param){
    Type type = a_type;
    IdName id = an_id;
    Param param = a_param;
  }

  public void setType(Type a_type){
    type = a_type;
  } 

  public void setIdName(IdName an_id){
    id = an_id;
  } 

  public void setParam(Param a_param){
    param = a_param;
  } 

  public void setBody(Body a_body){
    body = a_body;
  }

  public void getType(){
    return type;
  } 

  public void getIdName(){
    return id;
  } 

  public void getParam(){
    return param;
  } 

  public void getBody(){
    return body;
  }

}