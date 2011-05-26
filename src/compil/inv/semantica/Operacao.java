/*
 * Free to use and modify
 */
package compil.inv.semantica;

/**
 *
 * @author nicholas
 */
public class Operacao extends No {
    private No oprd ;
    private No op1;
    private No op2 ;

    public Operacao(No valor) {
        op1 = valor;
        codigo = valor.codigo;
    }
    
    public Operacao(No operador, No operando1, No operando2) {
        this.oprd = operador;
        this.op1 = operando1;
        this.op2 = operando2;
    }
    
}
