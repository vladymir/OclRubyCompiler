/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;

/**
 *
 * @author nicholas
 */
public class Parametros extends No {

    public Declarador dec;
    public Expressao exp;

    public Parametros() {
        this.gerarCodigo();
    }

    public Parametros(Expressao exp) {
        this.exp = exp;
        this.gerarCodigo();
    }
    
    public Parametros(Declarador dec, Expressao exp) {
        this.dec = dec;
        this.exp = exp;
        this.gerarCodigo();
    }
    
    protected void gerarCodigo() {
        if (dec != null)
            dec.gerarCodigo();
        GeradorDeCodigo.getInstance().parametros(this);
        
    }
    
    
    
}
