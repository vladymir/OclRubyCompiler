/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;

/**
 *
 * @author nicholas
 */
public class Classe extends No {
    
    public String nome;
    public String pacote;

    public Classe(String nome, String pacote) {
        this.nome = nome;
        this.pacote = pacote;
        this.gerarCodigo();
    }

    public Classe(String nome) {
        this.nome = nome;
        this.gerarCodigo();
    }

    protected void gerarCodigo() {
        GeradorDeCodigo.getInstance().classe(this);
    }
    
    
    
}
