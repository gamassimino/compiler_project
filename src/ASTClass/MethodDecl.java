package src.ASTClass;

import Visitor.ASTVisitor;

class MethodDecl{
  private IdName id;
  private Param param;
  private Body body;
  private Type type;

  public MethodDecl(Type a_type, IdName an_id, Param a_param, Body a_body){
    type = a_type;
    id = an_id;
    param = a_param;
    body = a_body;
  }

  public MethodDecl(Type a_type, IdName an_id, Body a_body){
    type = a_type;
    id = an_id;
    body = a_body;
    param = null;
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

  public Type getType(){
    return type;
  } 

  public IdName getIdName(){
    return id;
  } 

  public Param getParam(){
    return param;
  } 

  public Body getBody(){
    return body;
  }

  public String toString(){
    String rtn;
    rtn = type.toString()+" "+ id.toString() +" (" +param.toString()+") " + body.toString();
    return rtn;
  }

}