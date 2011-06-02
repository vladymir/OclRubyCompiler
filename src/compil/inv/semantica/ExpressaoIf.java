/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;


/**
 *
 * @author nicholas
 */
public class ExpressaoIf extends No {
    
    public Expressao exp1;
    public Expressao exp2;
    public Expressao exp3;

    public ExpressaoIf(Expressao exp1, Expressao exp2, Expressao exp3) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        gerarCodigo();
    }

    protected void gerarCodigo() {
        GeradorDeCodigo.getInstance().expressaoIf(this);
    }
    
}
