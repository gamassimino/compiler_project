package ASTClass.Program;
import java.util.LinkedList;

class Program{

  LinkedList<ClassAST> class_list;

  public Program(){
    class_list = new LinkedList<ClassAST>();
  }

  public Program(ClassAST class){
    class = new ClassAST();
    class_list = new LinkedList<ClassAST>();
    class_list.add(class);
  }

  public LinkedList<ClassAST> getClassList(){
    return class_list;
  }

  public void setClassList(LinkedList<ClassAST> cl){
    class_list = cl;
  }
}