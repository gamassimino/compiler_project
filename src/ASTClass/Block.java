package ASTClass;

import Visitor.ASTVisitor;
import java.util.ArrayList;
import java.util.List;

public class Block extends Statement {
  private List<Statement> statements;
  private List<List<FieldDecl>> fields_decl;

  public Block(List<List<FieldDecl>> f, List<Statement> s ,Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    fields_decl = f;
    statements = s;
  }
  public Block(Object list, Boolean is_statement, Integer a_line, Integer a_column){
    line = a_line;
    column = a_column;
    if (is_statement) {
      statements = (List<Statement>)list;
      fields_decl = null;
    }else{
      fields_decl = (List<List<FieldDecl>>)list;
      statements = null;
    }
  }

  public Block(Integer a_line, Integer a_column) {
    line = a_line;
    column = a_column;
    fields_decl = null;
    statements = null;
  }

  public Block() {
    line = null;
    column = null;
    fields_decl = null;
    statements = null;
  }

  public void addStatement(Statement s) {
    this.statements.add(s);
  }

  public List<Statement> getStatements() {
    return (statements == null) ? new ArrayList<Statement>() : statements;
  }

  public void addFieldDecl(List<FieldDecl> f) {
    this.fields_decl.add(f);
  }

  public List<List<FieldDecl>> getFieldDecl() {
    return (fields_decl == null) ? new ArrayList<List<FieldDecl>>() : fields_decl;
  }

  public String className(){
    return "Block";
  }

  @Override
  public String toString() {
    String rtn = "";

    for (Statement s: statements) {
      rtn += s.toString() + '\n';
    }

    for (List<FieldDecl> f: fields_decl) {
      for (FieldDecl field: f) {
        rtn += field.toString() + '\n';
      }
    }

    if (rtn.length() > 0) return rtn.substring(0, rtn.length() - 1); // remove last new line char

    return rtn;
  }

  public <T> T accept(ASTVisitor<T> v) {
    return v.visit(this);
  }

}
