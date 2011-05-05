package compil.xmi;

import java.util.Vector;


public class Classe {
	private String nome;
	private String id;
	private String type;
	
	private Vector<Atributo> myAtt = new Vector<Atributo>();
	private Vector<Operacao> myOpp = new Vector<Operacao>();
	
	
	public Classe(String nome, String id, String type){
		this.nome = nome;
		this.id = id;
		this.type = type;
	}
	
	public void addAtributo(Atributo one){
		myAtt.add(one);
	}
	
	public void addOperation (Operacao one){
		myOpp.add(one);
	}
	
	public boolean hasOperation(Operacao one){
		for (Operacao op : myOpp){
		}
		return false;
	}
	
	public String getId() {
		return id;
	}
	
	public Vector<Atributo> getMyAtt() {
		return myAtt;
	}
	
	public Vector<Operacao> getMyOpp() {
		return myOpp;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getType() {
		return type;
	}
	
	public String iterateOverAssociation (String aString){
		for (Atributo at : myAtt){
			if (at.getAssociacao().equalsIgnoreCase(aString)){
				return at.getNome();
			}
		}
		return "";
	}
	
	public String toString(){
		String saida = "Nome da classe: " + nome + "\n";
		for (int i = 0; i < myAtt.size(); i++){
			saida += myAtt.get(i).toString();
		}
		return saida + "\n";
	}
}
