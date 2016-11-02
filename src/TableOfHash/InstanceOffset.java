package TableOfHash;

import java.util.LinkedList;
import ASTClass.Pair;

public class InstanceOffset{
  private LinkedList<Pair<String, Integer>> stack;

  public InstanceOffset(){
    stack = new LinkedList<Pair<String, Integer>>();
  }

  public Integer getOffset(String className){
    for (Pair<String, Integer> pair : stack) {
      if(pair.getFst().equals(className))
        return pair.getSnd();
    }
    return null;
  }

  public void insert(Pair<String, Integer> item){
    stack.add(item);
  }

}