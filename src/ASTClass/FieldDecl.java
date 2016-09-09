package ASTClass.FieldDecl;

class FieldDecl{
  private Type type;
  private List<IdName> list_id;

  public FieldDecl(Type a_type, ArrayList<IdName> a_list_id){
    type = a_type;
    list_id = a_list_id;
  }

  public void setListId(ArrayList<IdName> a_list_id){
    list_id = a_list_id;
  }
  
  public void setType(Type a_type){
    type = a_type;
  }
  
  public Type getType(){
    return type;
  }

  public List<IdName> getListId(){
    return list_id;
  }
 
}