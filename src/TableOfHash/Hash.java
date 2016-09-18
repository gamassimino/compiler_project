package TablOfHash;

import java.uti.LinkedList;

public class Hash{
  public final LinkedList<LinkedList<AST>> stack;

  public LinkedList<AST> currentLevel(){
    return stack.get(stack.size());
  }

  public void destroyLevel(){
    return stack.remove();
  }

  public void createLevel(){
    return stack.add(new LinkedList<AST>());
  }

  public void inserInLevel(AST item){
    return stack.get(stack.size()).add(item);
  }

}