/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;

/**
 *
 * @author nicholas
 */
public class Propriedade extends No {

    public Caminho caminho;
    public Parametros param;

    public Propriedade(Caminho caminho, Parametros param) {
        this.caminho = caminho;
        this.param = param;
        this.gerarCodigo();
    }
    
    
    protected void gerarCodigo() {
        GeradorDeCodigo.getInstance().chamadaPropriedade(this);
    }
    
}
