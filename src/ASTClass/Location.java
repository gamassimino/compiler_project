package ASTClass.Location;

public abstract class Location extends Expression {
	protected IdName id;
	
	public void setId(IdName id){
		this.id = id;
	}
	
	public IdName getId(){
		return id;
	}
}
