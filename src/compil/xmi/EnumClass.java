package compil.xmi;

import java.util.Vector;


public class EnumClass {
	private String name;
	private String id;
	private String type;
	
	Vector<Literal> myLiteralVector = new Vector<Literal>();
	
	public EnumClass(String name, String id, String type){
		this.name = name;
		this.id = id;
		this.type = type;
	}
	
	public void addLiteral(Literal one){
		myLiteralVector.add(one);
	}
	
	public String toString(){
		return "EnumClass name: " + name + "       EnumClass id: " + id + "         Enumclass type: " + type
		 + "\n" + vetorToString();
		
	}
	
	public String vetorToString(){
		String saida = "";
		for (int i = 0; i < myLiteralVector.size(); i++){
			saida += "Literal" + i + ": " + myLiteralVector.get(i) + "\n";
			
		}
		return saida;
	}
}
