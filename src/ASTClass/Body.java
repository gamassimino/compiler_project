package ASTClass.Body;

class Body{
  private Block block;
  //EXTERN ?????
  
  public Body(Block bl){
    block = bl;
  }

  public Body(){
    block = new Block(); // ??? or nil ?
  }

  public Body(Extern ext){ // ????
    block = new Block(); // ???
    ext
  }

  public void setBlock(Block bl){
    block = bl;
  }

  public Body getBlock(){
    return block;
  }

  public void setBlock(Extern ext){
    extern = ext;
  }

  public Extern getExtern(){
    return extern;
  }
}