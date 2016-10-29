package TableOfHash;

import java.util.LinkedList;
import java.util.List;
import ASTClass.FieldDecl;
import ASTClass.IdName;
import ASTClass.Pair;

public class Heap{
  private List<Pair<String, LinkedList<String>>> heap;

  public Heap(){
    heap = new LinkedList<Pair<String, LinkedList<String>>>();
  }

  public void insert(String name, LinkedList<String> listId){
    heap.add(new Pair<String, LinkedList<String>>(name, listId));
  }

  public Boolean search(String name, String id){
    for (Pair<String, LinkedList<String>> pair : heap) {
      if(pair.getFst().equals(name))
        for (String attribute : pair.getSnd()) {
          if (attribute.equals(id))
            return true;
        }      
    }
    return false;
  }

}