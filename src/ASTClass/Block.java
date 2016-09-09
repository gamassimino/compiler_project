package ASTClass.Block;

import java.util.ArrayList;
import java.util.List;

public class Block extends Statement {
  private List<Statement> statements;
  private List<FieldDecl> fields_decl;
  private int blockId;
  
  public Block(int bId) {
    fields_decl = new ArrayList<FieldDecl>();
    statements = new ArrayList<Statement>();
    blockId = bId;
  }
  
  public Block(int bId, List<Statement> s, List<FieldDecl> f) {
    blockId = bId;
    statements = s;
    fields_decl = f;
  }
  public Block(int bId, List<Statement> s) {
    blockId = bId;
    statements = s;
  }

  public Block(int bId, List<FieldDecl> f) {
    blockId = bId;
    fields_decl = f;
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
    
  public int getBlockId() {
    return blockId;
  }

  public void setBlockId(int blockId) {
    this.blockId = blockId;
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

  @Override
  public <T> T accept(ASTVisitor<T> v) {
    return v.visit(this);
  }
  
}
