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
            e.codigo += e.exp1.codigo;
        else if (e.exp2 == null) {
            e.codigo += e.oprd.codigo + e.exp1.codigo;
        }
        else if(e.oprd == null) {
            if (e.exp2.oprd != null && e.exp2.oprd.tipo == Operador.IMPLIES){
                e.codigo = "if " + e.exp1.codigo + ": ";
                e.codigo += e.exp2.codigo + " end";
            } else
                e.codigo += e.exp1.codigo + e.exp2.codigo;
        }
        else
            e.codigo += e.exp1.codigo + e.oprd.codigo + e.exp2.codigo;
        
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
        }
    }
    
    public void invariante(Invariante e){
        if (e.nome.equals("")) {
            String nomeAuto = "inv" + (this.listaInvariantes.size() +1);
            e.nome = nomeAuto;
        }
        e.codigo = "\tdef " + e.nome + "()\n" + checarInvariante(e) + "\n";
        e.codigo += "\tend\n";
        this.listaInvariantes.add(e);
    }
    
    private String checarInvariante(Invariante e) {
        return "\t\treturn " + e.expressao.codigo;
    }
        
    public void classe(Classe c) {
        c.codigo = "class " + c.nome + "\n";
    }
    
    public void expressaoIf(ExpressaoIf eIf) {
        eIf.codigo = "\tif " + eIf.exp1 + "\n";
        eIf.codigo += "\t\t" + eIf.exp2 + "\n";
        eIf.codigo += "\telse\n" + eIf.exp3 + "\n"; 
    }
    
    public void restricao(Restricao r) {
        r.codigo = r.classe.codigo;
        r.codigo += "\tdef checkAllInv()\n";
        for (Iterator<Invariante> it = listaInvariantes.iterator(); it.hasNext();) {
            Invariante invariante = it.next();
            r.codigo += "\t\t" + invariante.nome + "()\n";
        }
        r.codigo += "\tend\n";
        for (Iterator<Invariante> it = listaInvariantes.iterator(); it.hasNext();) {
            Invariante invariante = it.next();
            r.codigo += invariante.codigo;
        }
        r.codigo += "end\n";
        listaInvariantes.clear();
    }


    public void oprdCol(OprdCol oprd) {
        switch(oprd.tipo) {
            case OprdCol.FORALL:
                oprd.codigo = "all?";
                break;
            case OprdCol.SIZE:
                oprd.codigo = "count";
                break;
            case OprdCol.INCLUDES:
                oprd.codigo = "include?";
                break;
            case OprdCol.SELECT:
                oprd.codigo = "select";
                break;
            default:
                oprd.codigo = "aImplementar";                    
        }
        
    }

    public void declaradores(Declarador dec) {
        dec.codigo = "| ";
        for (Iterator<String> it = dec.listaDeclaradores.iterator(); it.hasNext();) {
            String declarador = it.next();
            dec.codigo += declarador;
            if (it.hasNext())
                dec.codigo += ", ";
        }
        dec.codigo += " |";
    }

    public void parametros(Parametros par) {
        String blocoIni = "(";
        String blocoFim = ")";
        if (par.dec != null) {
            blocoIni = "{ ";
            blocoFim = " }";
            par.codigo += par.dec.codigo;
        }
        if (par.exp != null)
            par.codigo += par.exp.codigo;
        
        par.codigo = blocoIni + par.codigo + blocoFim;
    }

    public void operacaoCol(OperacaoCol oper) {
        oper.codigo = oper.operacao.codigo + oper.p.codigo;
    }

    public void chamadaPropriedade(Propriedade p) {
        p.codigo = p.caminho.caminhoCompleto;
        if (p.param != null)
            p.codigo += p.param.codigo;
    }

    private static class GeradorDeCodigoHolder {

        private static final GeradorDeCodigo INSTANCE = new GeradorDeCodigo();
    }
}
