package ASTClass.Block;

class Block{
	private LinkedList<FieldDecl> field_decl;
	private LinkedList<Statement> statement;

	public Block(LinkedList<FieldDecl> list_fiel_decl){
		this.field_decl = list_fiel_decl ;
	}

	public Block(LinkedList<Statement> list_statement){
		this.statement = list_statement;
	}

	public Block(){
	}
	
}