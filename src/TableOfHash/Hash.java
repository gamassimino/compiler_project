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

  // this method return true if the item is
  // allready in the table and bind the item
  // with the element on the table, modifying
  // ast structure, if the item isn't on the table
  // then return false
  public AST searchInTable(String item){
    for (LinkedList<AST> listAst : stack) {
      for (AST ast : listAst) {
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
  public AST searchInTableT(String item, Integer level){
    LinkedList<AST> listAst = stack.get(level -1);
    for (AST ast : listAst) {
      if(ast.toString() == item)
        return ast;
    }
    return null;
  }
}