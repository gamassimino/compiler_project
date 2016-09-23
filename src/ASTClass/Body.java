package ASTClass;

import Visitor.ASTVisitor;

public class Body{
  private Block block;
  private Integer line;
  private Integer column;

  public Body(Block bl, Integer a_line, Integer a_column){
    block = bl;
    line = a_line;
    column = a_column;
  }

  public Body(Integer a_line, Integer a_column){
    block = null;
    line = a_line;
    column = a_column;
  }

  public Body(){
    block = null;
    line = null;
    column = null;
  }

  public void setBlock(Block bl){
    block = bl;
  }

  public Block getBlock(){
    return block;
  }

  public Integer getLine(){
    return line;
  }

  public Integer getColumn(){
    return column;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}