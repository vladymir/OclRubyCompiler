/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;


/**
 *
 * @author nicholas
 */
public class Restricao extends No {

    public Classe classe;

    public Restricao(Classe c) {
        this.classe = c;
        gerarCodigo();
    }

    protected void gerarCodigo() {
        GeradorDeCodigo.getInstance().restricao(this);
    }
    
}
