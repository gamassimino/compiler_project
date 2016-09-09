package ASTClass.ClassDecl;

class ClassDecl{
  private IdName id;
  private FieldDecl field_decl;
  private MethodDecl method_decl;
  
  public ClassDecl(IdName an_id){
    id = an_id;
  }
  
  public ClassDecl(IdName an_id, FieldDecl an_field_decl){
    id = an_id;
    field_decl = an_field_decl;
  }
  
  public ClassDecl(IdName an_id, MethodDecl an_method_decl){
    id = an_id;
    method_decl = an_method_decl;
  }
  
  public ClassDecl(IdName an_id, FieldDecl an_field_decl, MethodDecl an_method_decl){
    id = an_id;
    method_decl = an_method_decl;
    field_decl = an_field_decl;
  }
  
  public void setIdName(IdName an_id){
    id = an_id;    
  }

  public void setMethodDecl(MethodDecl a_method_decl){
    method_decl = a_method_decl;
  }

  public void setFieldDecl(FieldDecl a_field_decl){
    field_decl = a_field_decl;
  }

  public getFieldDecl(){
    return field_decl;
  }

  public getMethodDecl(){
    return method_decl;
  }

  public getIdName(){
    return id ;
  }
}