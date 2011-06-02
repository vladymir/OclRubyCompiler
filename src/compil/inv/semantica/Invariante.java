/*
 * Free to use and modify
 */
package compil.inv.semantica;

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
    }

    public Invariante(Expressao expressao, String nome) {
        this.expressao = expressao;
        this.nome = nome;
    }
    
    
}
