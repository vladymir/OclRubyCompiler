/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;

/**
 *
 * @author nicholas
 */
public class OprdCol extends No {

    public static final int FORALL = 0;
    public static final int INCLUDES = 1;
    public static final int SELECT = 2;
    public static final int EXISTS = 3;
    public static final int EXCLUDES = 4;
    public static final int SIZE = 5;
    public static final int INCLUDESALL = 6;
    
    public int tipo;

    public OprdCol(int tipo) {
        this.tipo = tipo;
        this.gerarCodigo();
    }
    
    protected void gerarCodigo() {
        GeradorDeCodigo.getInstance().oprdCol(this);
    }
    
}
