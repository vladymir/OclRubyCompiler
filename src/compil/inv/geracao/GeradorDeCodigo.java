/*
 * Classe geradora de código do compilador OCL -> Ruby.
 * Disciplina de Compiladores - UFCG
 */

package compil.inv.geracao;

import compil.inv.semantica.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @version 0.1
 * @author Nicholas Alexander
 */

public class GeradorDeCodigo {
    
    private ArrayList<Invariante> listaInvariantes = new ArrayList<Invariante>();
    
    
    
    private GeradorDeCodigo() {
    }
    
    public static GeradorDeCodigo getInstance() {
        return GeradorDeCodigoHolder.INSTANCE;
    }
    
    public void concatenarSubExpressoesDe(Expressao e){
        if (e.exp2 == null && e.oprd == null )
            e.codigo = e.exp1.codigo;
        else if (e.exp2 == null)
            e.codigo = e.exp1.codigo + e.oprd.codigo;
        else if(e.oprd == null)
            e.codigo = e.exp1.codigo + e.exp2.codigo;
        else
            e.codigo = e.oprd.codigo + e.exp1.codigo + e.exp2.codigo;
        
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
    
    public void invariante(Invariante e){
        if (e.nome.equals("")) {
            String nomeAuto = "inv" + (this.listaInvariantes.size() +1);
            e.nome = nomeAuto;
        }
        e.codigo = "\tdef " + e.nome + "()\n" + checarInvariante(e);
        this.listaInvariantes.add(e);
    }
    
    public void classe(Classe c) {
        c.codigo = "class " + c.nome + "\n";
    }
    
    public void restricao(Restricao r) {
        r.codigo = r.classe.codigo;
        for (Iterator<Invariante> it = listaInvariantes.iterator(); it.hasNext();) {
            Invariante invariante = it.next();
            r.codigo += invariante.codigo;
        }
        System.out.println(r.codigo);
        
    }

    private String checarInvariante(Invariante e) {
        return "\t\treturn " + e.expressao.codigo;
    }
    
    private static class GeradorDeCodigoHolder {

        private static final GeradorDeCodigo INSTANCE = new GeradorDeCodigo();
    }
}
