package TableOfHash;

import java.util.LinkedList;
import ASTClass.AST;
import ASTClass.FieldDecl;

public class Hash{
  private LinkedList<LinkedList<AST>> stack;

  public Hash(){
    stack = new LinkedList<LinkedList<AST>>();
  }

  public LinkedList<AST> currentLevel(){
    return stack.getLast();
  }

  public void destroyLevel(){
    stack.removeLast();
  }

  public void createLevel(){
    stack.add(new LinkedList<AST>());
  }

  public void insertInLevel(AST item){
    stack.getLast().add(item);
  }

  // this method return true if the item is
  // allready in the table and bind the item
  // with the element on the table, modifying
  // ast structure, if the item isn't on the table
  // then return false
  public AST searchInTable(String item){
    for (int i = stack.size()-1; i >= 0; i--) {
      for (AST ast : stack.get(i)) {
        FieldDecl field = (FieldDecl)ast;
        if(field.getId().toString().equals(item))
          return ast;
      }
    }
    return null;
  }

  // this method return true if the item is
  // allready in the table and bind the item
  // with the element on the table, modifying
  // ast structure, if the item isn't on the table
  // then return false
  public AST searchInLastLevel(String item){
    LinkedList<AST> listAst = stack.getLast();
    for (AST ast : listAst) {
      FieldDecl field = (FieldDecl)ast;
      if(field.getId().toString().equals(item))
        return ast;
    }
    return null;
  }
}