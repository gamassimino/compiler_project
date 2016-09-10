package ASTClass.ClassDecl;

class ClassDecl extends AST{
  private IdName id;
  private List<List<FieldDecl>> field_decl;
  private List<List<MethodDecl>> method_decl;
  
  public ClassDecl(IdName an_id){
    id = an_id;
  }
  
  public ClassDecl(IdName an_id, List<FieldDecl> an_field_decl){
    id = an_id;
    field_decl = an_field_decl;
  }
  
  public ClassDecl(IdName an_id, List<MethodDecl> an_method_decl){
    id = an_id;
    method_decl = an_method_decl;
  }
  
  public ClassDecl(IdName an_id, List<FieldDecl> an_field_decl, List<MethodDecl> an_method_decl){
    id = an_id;
    method_decl = an_method_decl;
    field_decl = an_field_decl;
  }
  
  public void setIdName(IdName an_id){
    id = an_id;    
  }

  public void setMethodDecl(List<MethodDecl> a_method_decl){
    method_decl = a_method_decl;
  }

  public void setFieldDecl(List<FieldDecl> a_field_decl){
    field_decl = a_field_decl;
  }

  public List<FieldDecl> getFieldDecl(){
    return field_decl;
  }

  public List<MethodDecl> getMethodDecl(){
    return method_decl;
  }

  public IdName getIdName(){
    return id;
  }
}