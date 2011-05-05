package compil.xmi;

public class Literal {
	private String id;
	private String name;
	
	public Literal (String id, String name){
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString(){
		return "LiteralName: " + name + "           LiteralId: " + id;
	}
}	
