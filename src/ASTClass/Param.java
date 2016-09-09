package ASTClass.Param;

class Param{

  private Type type;
  private IdName id;

  public Param(Type type, IdName id){
    this.type = type;
    this.id = id;
  }

  public void setType(Type type){
    this.type = type;
  }

  public Type getType(){
    return type;
  }

  public void setIdName(IdName id){
    this.id = id;
  }

  public IdName getIdName(){
    return id;
  }
}