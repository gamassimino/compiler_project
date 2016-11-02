package ASTClass;

import Visitor.ASTVisitor;

public abstract class AST {
  protected Integer line;
  protected Integer column;
  protected Integer offset;
  protected Integer index;
  
  public Integer getLine() {
    return line;
  }
  
  public void setLine(Integer ln) {
    line = ln;
  }
  
  public Integer getColumn() {
    return column;
  }

  
  public void setOffset(Integer off) {
    offset = off;
  }
  
  public Integer getOffset() {
    return offset;
  }
  
  public void setIndex(Integer a_index) {
    index = a_index;
  }
  
  public Integer getIndex() {
    return index;
  }
  
  public void setColumn(Integer cn) {
    column = cn;
  }
  
  public abstract <T> T accept(ASTVisitor<T> v);
}
