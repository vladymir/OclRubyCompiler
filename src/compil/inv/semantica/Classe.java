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
    public String apelido;

    public Classe(String nome, String apelido) {
        this.nome = nome;
        this.apelido = apelido;
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
