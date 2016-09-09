package ASTClass.Body;

class Body{
  private Block block;
  //EXTERN ?????
  
  public Body(Block bl){
    block = bl;
  }

  // public Body(Extern ext){ // ????
  //   block = new Block(); // ???
  //   // ext
  // }

  public void setBlock(Block bl){
    block = bl;
  }

  public Block getBlock(){
    return block;
  }

  // public void setBlock(Extern ext){
  //   extern = ext;
  // }

  // public Extern getExtern(){
  //   return extern;
  // }
}