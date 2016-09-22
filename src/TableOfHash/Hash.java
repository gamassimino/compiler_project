package TableOfHash;

import java.util.LinkedList;
import ASTClass.AST;
import ASTClass.FieldDecl;
import ASTClass.MethodDecl;
import ASTClass.ClassDecl;

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

  /**
  *
  * this are methods for search in the table the FieldDecl
  * where the Id is equal to item
  *
  **/

  // this method return true if the item is
  // allready in the table and bind the item
  // with the element on the table, modifying
  // ast structure, if the item isn't on the table
  // then return false
  public AST searchInTableFD(String item){
    for (int i = stack.size()-1; i >= 0; i--) {
      for (AST ast : stack.get(i)) {
        if(ast.getClass().toString().equals("class ASTClass.FieldDecl")){
          FieldDecl field = (FieldDecl)ast;
          if(field.getId().toString().equals(item))
            return ast;
        }
      }
    }
    return null;
  }


  // this method return true if the item is
  // allready in the table and bind the item
  // with the element on the table, modifying
  // ast structure, if the item isn't on the table
  // then return false
  public AST searchInLastLevelFD(String item){
    LinkedList<AST> listAst = stack.getLast();
    for (AST ast : listAst) {
      if(ast.getClass().toString().equals("class ASTClass.FieldDecl")){
        FieldDecl field = (FieldDecl)ast;
        if(field.getId().toString().equals(item))
          return ast;
      }
    }
    return null;
  }


  /**
  *
  * this are methods for search in the table the MethodDecl
  * where the Id is equal to item
  *
  **/

  // this method return true if the item is
  // allready in the table and bind the item
  // with the element on the table, modifying
  // ast structure, if the item isn't on the table
  // then return false
  public AST searchInTableMD(String item){
    for (int i = stack.size()-1; i >= 0; i--) {
      for (AST ast : stack.get(i)) {
        if(ast.getClass().toString().equals("class ASTClass.MethodDecl")){
          MethodDecl method = (MethodDecl)ast;
          if(method.getIdName().toString().equals(item))
            return ast;
        }
      }
    }
    return null;
  }

  // this method return true if the item is
  // allready in the table and bind the item
  // with the element on the table, modifying
  // ast structure, if the item isn't on the table
  // then return false
  public AST searchInLastLevelMD(String item){
    LinkedList<AST> listAst = stack.getLast();
    for (AST ast : listAst) {
      if(ast.getClass().toString().equals("class ASTClass.MethodDecl")){
        MethodDecl method = (MethodDecl)ast;
        if(method.getIdName().toString().equals(item))
          return ast;
      }
    }
    return null;
  }


  /**
  *
  * this are methods for search in the table the ClassDecl
  * where the Id is equal to item
  *
  **/

  // this method return true if the item is
  // allready in the table and bind the item
  // with the element on the table, modifying
  // ast structure, if the item isn't on the table
  // then return false
  public AST searchInLastLevelCD(String item){
    LinkedList<AST> listAst = stack.getLast();
    for (AST ast : listAst) {
      if(ast.getClass().toString().equals("class ASTClass.ClassDecl")){
        ClassDecl class_decl = (ClassDecl)ast;
        if(class_decl.getIdName().toString().equals(item))
          return ast;
      }
    }
    return null;
  }
}