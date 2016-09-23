package ASTClass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Visitor.ASTVisitor;

public class ClassDecl extends AST{
  private IdName id;
  private List<List<FieldDecl>> field_decl;
  private List<MethodDecl> method_decl;

  public ClassDecl(IdName an_id, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    id = an_id;
    field_decl = null;
    method_decl = null;
  }

  public ClassDecl(IdName an_id){
    line = null;
    column = null;
    id = an_id;
    field_decl = null;
    method_decl = null;
  }

  public ClassDecl(IdName an_id, Object list, Boolean is_field, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    if (is_field) {
      id = an_id;
      field_decl = (List<List<FieldDecl>>)list;
      method_decl = null;
    }
    else{
      id = an_id;
      method_decl = (List<MethodDecl>)list;
      field_decl = null;
    }
  }

  public ClassDecl(IdName an_id, List<List<FieldDecl>> an_field_decl, List<MethodDecl> an_method_decl, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
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

  public void setFieldDecl(List<List<FieldDecl>> a_field_decl){
    field_decl = a_field_decl;
  }

  public List<List<FieldDecl>> getFieldDecl(){
    if(field_decl == null)
      return new ArrayList<List<FieldDecl>>();
    return field_decl;
  }

  public List<MethodDecl> getMethodDecl(){
    return (method_decl == null)? new ArrayList<MethodDecl>() : method_decl;
  }

  public IdName getIdName(){
    return id;
  }

  @Override
  public String toString() {
    return "class "+ id.toString();
  }

  public <T> T accept(ASTVisitor<T> v) {
    return v.visit(this);
  }
}