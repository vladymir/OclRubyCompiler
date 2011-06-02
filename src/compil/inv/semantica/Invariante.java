/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;

/**
 *
 * @author nicholas
 */
public class Invariante extends No {
    public Expressao expressao;
    public String nome;

    public Invariante(Expressao expressao) {
        this.expressao = expressao;
        this.nome = "";
        gerarCodigo();
    }

    public Invariante(Expressao expressao, String nome) {
        this.expressao = expressao;
        this.nome = nome;
        gerarCodigo();
    }

    protected void gerarCodigo() {
        GeradorDeCodigo.getInstance().invariante(this);
    }
    
    
}
