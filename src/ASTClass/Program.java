package ASTClass;

import java.util.ArrayList;
import java.util.List;
import Visitor.ASTVisitor;

public class Program extends AST {

  private List<ClassDecl> class_list;

  public Program(Integer a_line, Integer a_column){
    class_list = new ArrayList<ClassDecl>();
    line = a_line;
    column = a_column;
  }

  public Program(ClassDecl a_class, Integer a_line, Integer a_column){
    class_list = new ArrayList<ClassDecl>();
    class_list.add(a_class);
    line = a_line;
    column = a_column;
  }

  public List<ClassDecl> getClassList(){
    return (class_list == null) ? new ArrayList<ClassDecl>() : class_list;
  }

  public void setClassList(ArrayList<ClassDecl> cl){
    class_list = cl;
  }

  public void addClass(ClassDecl cl){
    class_list.add(cl);
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}