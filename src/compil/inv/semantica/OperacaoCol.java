/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;

/**
 *
 * @author nicholas
 */
public class OperacaoCol extends No {

    public OprdCol operacao;
    public Parametros p;

    public OperacaoCol(OprdCol operacao, Parametros p) {
        this.operacao = operacao;
        this.p = p;
        this.gerarCodigo();
    }
    
    protected void gerarCodigo() {
        GeradorDeCodigo.getInstance().operacaoCol(this);
        
        
    }
    
    
}
