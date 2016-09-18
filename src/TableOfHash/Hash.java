package TableOfHash;

import java.util.LinkedList;
import ASTClass.AST;

public class Hash{
  public final LinkedList<LinkedList<AST>> stack;

  public Hash(){
    stack = new LinkedList<LinkedList<AST>>();
  }

  public LinkedList<AST> currentLevel(){
    return stack.get(stack.size()-1);
  }

  public void destroyLevel(){
    stack.remove();
  }

  public void createLevel(){
    stack.add(new LinkedList<AST>());
  }

  public void insertInLevel(AST item){
    stack.get(stack.size()-1).add(item);
  }

  public void searchInLevel(AST item){

  }

}