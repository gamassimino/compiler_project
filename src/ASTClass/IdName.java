package ASTClass.IdName;

class IdName{
  private String id;
  private Integer size;

  public IdName(String an_id){
    id = an_id;
    size = null;
  }

  public IdName(String an_id, Integer a_size){
    id = an_id;
    size = a_size;
  }

  public void setIdName(String an_id){
    id = an_id;
  }

  public IdName getIdName(){
    return id;
  }

  public void setSize(Integer a_size){
    size = a_size;
  }

  public Integer getSize(){
    return size;
  }

  public String toString(){
    return id;
  }
}