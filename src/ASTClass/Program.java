package src.ASTClass;

import java.util.ArrayList;
import java.util.List;

class Program{

  private List<ClassDecl> class_list;

  public Program(){
    class_list = new ArrayList<ClassDecl>();
  }

  public Program(ClassDecl a_class){
    class_list = new ArrayList<ClassDecl>();
    class_list.add(a_class);
  }

  public List<ClassDecl> getClassList(){
    return class_list;
  }

  public void setClassList(ArrayList<ClassDecl> cl){
    class_list = cl;
  }

  public void add(ClassDecl cl){
    class_list.add(cl);
  }
}