/*
 * Classe geradora de código do compilador OCL -> Ruby.
 * Disciplina de Compiladores - UFCG
 */

package compil.inv.geracao;

import compil.inv.semantica.*;

/**
 * @version 0.1
 * @author Nicholas Alexander
 */

public class GeradorDeCodigo {
    
    
    
    private GeradorDeCodigo() {
    }
    
    public static GeradorDeCodigo getInstance() {
        return GeradorDeCodigoHolder.INSTANCE;
    }
    
   
    public void operacaoUnaria(Operador o, Literal l){
        
    }

    public void operacao(Operador o, Operacao op1, Operacao op2){
        
    }
    
    public void oprd(Operador oprd) {
        switch(oprd.tipo) {
            case Operador.AND:
                oprd.codigo = " && ";
                break;
            case Operador.OR:
                oprd.codigo = " || ";
                break;
            case Operador.NOT:
                oprd.codigo = " !";
                break;
            case Operador.MENOSU:
                oprd.codigo = " -";
                break;
            case Operador.XOR:
                break;
            case Operador.IGUAL:
                oprd.codigo = " == ";
                break;
            case Operador.DIF:
                oprd.codigo = " != ";
                break;
            case Operador.MAIS:
                oprd.codigo = " + ";
                break;
            case Operador.MENOS:
                oprd.codigo = " - ";
                break;
            case Operador.VEZES:
                oprd.codigo = " * ";
                break;
            case Operador.DIVIDIDO:
                oprd.codigo += " / ";
                break;
            case Operador.MAIORQ:
                oprd.codigo = " > ";
                break;
            case Operador.MENORQ:
                oprd.codigo = " < ";
                break;
            case Operador.MAIORIGUAL:
                oprd.codigo = " >= ";
                break;
            case Operador.MENORIGUAL:
                oprd.codigo = " <= ";
                break;
            case Operador.IMPLIES:
                break;
        }
    }
    
    private static class GeradorDeCodigoHolder {

        private static final GeradorDeCodigo INSTANCE = new GeradorDeCodigo();
    }
}
