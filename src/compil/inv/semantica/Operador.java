/*
 * Free to use and modify
 */
package compil.inv.semantica;

/**
 *
 * @author nicholas
 */
public class Operador extends No {

    public static final int DIF = 0;
    public static final int MENORIGUAL = 1;
    public static final int MAIORIGUAL = 2;
    public static final int MENORQ = 3;
    public static final int MAIORQ = 4;
    public static final int IGUAL = 5;
    public static final int XOR = 6;
    public static final int OR = 7;
    public static final int AND = 8;
    public static final int NOT = 9;
    public static final int MENOS = 10;
    public static final int DIVIDIDO = 11;
    public static final int VEZES = 12;
    public static final int MAIS = 13;
    public static final int IMPLIES = 14;
    public static final int MENOSU = 15;
    
    public int tipo;
    
    public Operador(int tipo) {
        this.tipo = tipo;
    }
    
    
    
}
