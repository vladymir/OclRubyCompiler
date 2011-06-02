/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;

/**
 *
 * @author nicholas
 */
public class Expressao extends No {
    public  No oprd ;
    public  No exp1 ;
    public  No exp2 ;

    public Expressao(Literal l) {
        this.exp1 = l;
        this.codigo = l.codigo;
        gerarCodigo();
    }
    
    public Expressao(Expressao exp1) {
        this.exp1 = exp1;
        gerarCodigo();
    }
    
    public Expressao(ExpressaoIf exp1) {
        this.exp1 = exp1;
        gerarCodigo();
    }
    
    public Expressao(Expressao exp1, Expressao exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        gerarCodigo();
    }
    
    public Expressao(Operador operador, Expressao exp1) {
        this.oprd = operador;
        this.exp1 = exp1;
        gerarCodigo();
    }
    
    public Expressao(Operador operador, Expressao exp1, Expressao exp2) {
        this.oprd = operador;
        this.exp1 = exp1;
        this.exp2 = exp2;
        gerarCodigo();
    }

    protected void gerarCodigo() {
        GeradorDeCodigo.getInstance().concatenarSubExpressoesDe(this);
    }
    
}
