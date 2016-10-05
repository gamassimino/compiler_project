package ASTClass;

import Visitor.ASTVisitor;
import java.util.List;
import java.util.ArrayList;

public class Instance extends AST{

  private String name;
  private Type type;
  private List<List<FieldDecl>> attributes;
  private List<MethodDecl> methods;

  public Instance(String name, Type type, List<List<FieldDecl>> attributes, List<MethodDecl> methods){
    this.name = name;
    this.type = type;
    this.attributes = attributes;
    this.methods = methods;
  }

  public String getName (){
    return name;
  }

  public Type getType (){
    return type;
  }

  public List<List<FieldDecl>> getFieldDecl (){
    return (attributes == null) ? new ArrayList<List<FieldDecl>>() : attributes;
  }

  public List<MethodDecl> getMethodDecl (){
    return (methods == null) ? new ArrayList<MethodDecl>() : methods;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}