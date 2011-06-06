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
    public  Operador oprd ;
    public  No exp1 ;
    public  Expressao exp2 ;

    public Expressao(Propriedade p) {
        this.exp1 = p;
        this.gerarCodigo();
    }
    
    public Expressao(Propriedade p, Expressao e) {
        this.exp1 = p;
        this.exp2 = e;
        this.codigo = ".";
        this.gerarCodigo();
    }
    
    public Expressao(OperacaoCol p, Expressao e) {
        this.exp1 = p;
        this.exp2 = e;
        this.codigo = ".";
        this.gerarCodigo();
    }
    
    public Expressao(Literal l) {
        this.exp1 = l;
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
    
    public void setTipoNodo(){
		if(exp2 == null){
			setTipo(exp1.getTipo());
		} else {
			setTipo(exp2.getTipo());
			
		}
	}
    
}
