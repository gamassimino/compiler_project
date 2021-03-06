package ASTClass;

import Visitor.ASTVisitor;

public class IdName{
  private String id;
  private String record;
  private Expression size;
  private Type type;
  private Integer line;
  private Integer column;
  private Integer offset;
  private Integer index;

  public IdName(String an_id){
    id = an_id;
    size = null;
    type = null;
  }

  public IdName(String an_id, Integer a_line, Integer a_column){
    id = an_id;
    size = null;
    type = null;
    record = null;
    offset = null;
    line = a_line;
    column = a_column;
  }

  public IdName(String an_id, Expression a_size){
    id = an_id;
    size = a_size;
    type= null;
    offset = null;
  }

  public IdName(String an_id, Expression a_size, Integer a_line, Integer a_column){
    id = an_id;
    size = a_size;
    type= null;
    line = a_line;
    column = a_column;
    record = null;
    offset = null;
  }

  public IdName(String an_id, Expression a_size, Type a_type){
    id = an_id;
    size = a_size;
    type= a_type;
    record = null;
    offset = null;
  }

  public void setOffset(Integer off) {
    offset = off;
  }
  
  public Integer getOffset() {
    return offset;
  }

  public void setIdName(String an_id){
    id = an_id;
  }

  public IdName getIdName(){
    return new IdName(id);
  }

  public void setType(Type a_type){
    type = a_type;
  }

  public Type getType(){
    return type;
  }

  public Integer getLine(){
    return line;
  }

  public Integer getColumn(){
    return column;
  }

  public void setIndex(Integer a_index) {
    index = a_index;
  }
  
  public Integer getIndex() {
    return index;
  }
  
  public void setRecord(String a_record) {
    record = a_record;
  }
  
  public String getRecord() {
    return record;
  }

  public void setSize(Expression a_size){
    size = a_size;
  }

  public Expression getSize(){
    return size;
  }

  public String toString(){
    return id;
  }

  public <T> T accept(ASTVisitor<T> v) {
   return v.visit(this);
  }
}