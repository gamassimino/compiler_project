package ASTClass;

// import Visitor.ASTVisitor;

public class Body{
  private Block block;

  public Body(Block bl){
    block = bl;
  }
  public Body(){
    block = null;
  }

  public void setBlock(Block bl){
    block = bl;
  }

  public Block getBlock(){
    return block;
  }

  // public <T> T accept(ASTVisitor<T> v) {
  //  return v.visit(this);
  // }
}