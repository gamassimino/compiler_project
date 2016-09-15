package ASTClass;

// import Visitor.ASTVisitor;
import java.util.ArrayList;
import java.util.List;

public class Block extends Statement {
  private List<Statement> statements;
  private List<FieldDecl> fields_decl;

  public Block(ArrayList<FieldDecl> f, ArrayList<Statement> s){
    fields_decl = f;
    statements = s;
  }
  public Block(Object list, Boolean is_statement){
    if (is_statement) {
      statements = (List<Statement>)list;
      fields_decl = null;
    }else{
      fields_decl = (List<FieldDecl>)list;
      statements = null;
    }
  }

  public Block() {
    fields_decl = null;
    statements = null;
  }

  public void addStatement(Statement s) {
    this.statements.add(s);
  }

  public List<Statement> getStatements() {
    return this.statements;
  }

  public void addFieldDecl(FieldDecl f) {
    this.fields_decl.add(f);
  }

  public List<FieldDecl> getFieldDecl() {
    return this.fields_decl;
  }

  @Override
  public String toString() {
    String rtn = "";

    for (Statement s: statements) {
      rtn += s.toString() + '\n';
    }

    for (FieldDecl f: fields_decl) {
      rtn += f.toString() + '\n';
    }

    if (rtn.length() > 0) return rtn.substring(0, rtn.length() - 1); // remove last new line char

    return rtn;
  }

  // public <T> T accept(ASTVisitor<T> v) {
  //   return v.visit(this);
  // }

}
