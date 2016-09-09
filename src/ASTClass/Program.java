package ASTClass.Program;
import java.util.LinkedList;

class Program{

  LinkedList<ClassDecl> class_list;

  public Program(){
    class_list = new LinkedList<ClassDecl>();
  }

  public Program(ClassDecl class){
    class = new ClassDecl();
    class_list = new LinkedList<ClassDecl>();
    class_list.add(class);
  }

  public LinkedList<ClassDecl> getClassList(){
    return class_list;
  }

  public void setClassList(LinkedList<ClassDecl> cl){
    class_list = cl;
  }
}