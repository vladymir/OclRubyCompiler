/*
 * Free to use and modify
 */
package compil.inv.semantica;


import compil.inv.geracao.GeradorDeCodigo;

/**
 *
 * @author nicholas
 */
public class Literal extends No {

    public static final int STRING = 0;
    public static final int NUMERO = 1;
    public static final int BOOLEANO = 3;
    private static final int INTEIRO = 4;
    private static final int REAL = 5;
    
    public String valor;
    public int tipo;
    
    
    public Literal(String valor, int tipo) {
        this.valor = valor;
        this.gerarCodigo();
    }

    protected void gerarCodigo() {
        this.codigo = valor;
    }
    
    public void setTipoNodo(){
		if(valor.contains(".")){
			setTipo("REAL");
		} else {
			setTipo("INTEGER");
		}
	}
    
    
    
}
