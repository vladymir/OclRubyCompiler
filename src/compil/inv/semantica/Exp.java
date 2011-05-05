package compil.inv.semantica;


public class Exp {
	public String type;
	private String val;
	
	public Exp(String type) {
		this.type = type;
	}
	public Exp(String type, String val) {
		this.type = type;
		this.val = val;
	}
	
	public static Exp checkType(Exp e, String type){
		if (e.type == type)
			return new Exp(type);
		throw new RuntimeException("Semantic error. Type should be: " + type + ", but is: " + e.type); 
	
	}
	
	public static Exp checkType(Exp e1, Exp e2, String type) {
		if (e1.type == type)
			if (e2.type == type)
				return new Exp(type);
			else
				throw new RuntimeException("Semantic error. Type should be: " + type + ", but is: " + e2.type); 
		throw new RuntimeException("Semantic error. Type should be: " + type + ", but is: " + e1.type);
	}
	
	public static Exp checkSameType(Exp e1, Exp e2, String type){
		if (e1.type == e2.type || (e1 == null || e2 == null))
			return new Exp(type);
		throw new RuntimeException("Semantic error. Operands type should be equal");
	}
	
	public static Exp ifExpType(Exp e1, Exp e2, Exp e3) {
		if (e1.type == "Boolean")
			if (e1.val == "True")
				return new Exp(e2.type);
		return new Exp(e3.type);
				
	}
}