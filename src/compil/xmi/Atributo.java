package compil.xmi;

public class Atributo {
	private String nome;
	private String id;
	private String associacao;
	private String tipo;
	
	public Atributo (String nome, String id, String associacao, String tipo){
		this.nome = nome;
		this.id = id;
		this.associacao = associacao;
		this.tipo = tipo;
	}
	
	public String getAssociacao() {
		return associacao;
	}
	
	public String getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return "Atributo nome: " + nome + "\n";
	}
}
