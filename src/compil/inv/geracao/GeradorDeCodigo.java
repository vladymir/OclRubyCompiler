/*
 * Classe geradora de código do compilador OCL -> Ruby.
 * Disciplina de Compiladores - UFCG
 */
package compil.inv.geracao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import compil.inv.semantica.*;
import compil.xmi.Atributo;
import compil.xmi.Operacao;
import compil.xmi.XMLReader;
import java.io.BufferedWriter;
import java.io.File;

/**
 * @version 0.1
 * @author Nicholas Alexander
 */
public class GeradorDeCodigo {

    private ArrayList<Invariante> listaInvariantes = new ArrayList<Invariante>();
    private int ident = 0;

    private GeradorDeCodigo() {
    }

    public static GeradorDeCodigo getInstance() {
        return GeradorDeCodigoHolder.INSTANCE;
    }

    public void concatenarSubExpressoesDe(Expressao e) {
        if (e.exp2 == null && e.oprd == null) {
            e.codigo += e.exp1.codigo;
        } else if (e.exp2 == null) {
            e.codigo += e.oprd.codigo + e.exp1.codigo;
        } else if (e.oprd == null) {
            if (e.exp2.oprd != null && e.exp2.oprd.tipo == Operador.IMPLIES) {
                e.codigo = "if " + e.exp1.codigo + ": ";
                e.codigo += e.exp2.codigo + " end";
            } else {
                e.codigo += e.exp1.codigo + e.exp2.codigo;
            }
        } else {
            e.codigo += e.exp1.codigo + e.oprd.codigo + e.exp2.codigo;
        }

    }

    public void oprd(Operador oprd) {
        switch (oprd.tipo) {
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

    public void invariante(Invariante e) {
        if (e.nome.equals("")) {
            String nomeAuto = "inv" + (this.listaInvariantes.size() + 1);
            e.nome = nomeAuto;
        }
        e.codigo = identacao(true) + "def " + e.nome + "()\n" + checarInvariante(e) + "\n";
        e.codigo += identacao(false) + "end\n";
        this.decrementarIdent();
        this.listaInvariantes.add(e);
    }

    private String checarInvariante(Invariante e) {
        String ret = identacao(true) + "return " + e.expressao.codigo;
        this.decrementarIdent();
        return ret;
    }

    public void classe(Classe c) {
        compil.xmi.Classe classe = classeDoXML(c);
        if (classe != null) {
            c.codigo = "class " + c.nome + "\n";
            identacao(true);
            for (Atributo atributo : classe.getMyAtt()) {
                c.codigo += identacao(false) + "attr_accessor :" + atributo.getNome() + "\n";
            }
            c.codigo += identacao(false) + "def initialize\n";
            identacao(true);
            for (Atributo atributo : classe.getMyAtt()) {
                if (!atributo.getNome().equals("")) {
                    c.codigo += identacao(false) + "@" + atributo.getNome() + "\n";
                }
            }
            decrementarIdent();
            c.codigo += identacao(false) + "end\n";
            for (Operacao operacao : classe.getMyOpp()) {
                c.codigo += identacao(false) + "def " + operacao.name + "\n";
                c.codigo += identacao(false) + "end\n";
            }
            decrementarIdent();
        } else {
            throw new RuntimeException("Classe " + c.nome + " nao existe no modelo");
        }


    }

    public void expressaoIf(ExpressaoIf eIf) {
        eIf.codigo = "if " + eIf.exp1.codigo + ": ";
        eIf.codigo += eIf.exp2.codigo + " else " + eIf.exp3.codigo + " end\n";
    }

    public void restricao(Restricao r) {
        r.codigo = r.classe.codigo;
        r.codigo += identacao(true) + "def checkAllInv()\n";
        for (Iterator<Invariante> it = listaInvariantes.iterator(); it.hasNext();) {
            Invariante invariante = it.next();
            r.codigo += identacao(true) + invariante.nome + "()\n";
            this.decrementarIdent();

        }
        r.codigo += identacao(false) + "end\n";
        this.decrementarIdent();
        for (Iterator<Invariante> it = listaInvariantes.iterator(); it.hasNext();) {
            Invariante invariante = it.next();
            if (r.classe.apelido != null)
                invariante.codigo = invariante.codigo.replaceAll(" " + r.classe.apelido + ".", " self.");
            r.codigo += invariante.codigo;
        }

        r.codigo += "end\n";
        listaInvariantes.clear();
    }

    public void oprdCol(OprdCol oprd) {
        switch (oprd.tipo) {
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
            if (it.hasNext()) {
                dec.codigo += ", ";
            }
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
        if (par.exp != null) {
            par.codigo += par.exp.codigo;
        }

        par.codigo = blocoIni + par.codigo + blocoFim;
    }

    public void operacaoCol(OperacaoCol oper) {
        oper.codigo = oper.operacao.codigo;
        if (oper.p != null) {
            oper.codigo += oper.p.codigo;
        }
    }

    public void chamadaPropriedade(Propriedade p) {
        p.codigo = p.caminho.caminhoCompleto;
        if (p.param != null) {
            p.codigo += p.param.codigo;
        }
    }

    public void gerarArquivoRuby(Restricao r) {
        String nomeArq = r.classe.nome + ".fromOcl.rb";
        int i = 0;
        File f = new File(nomeArq);
        while (f.exists()) {
            nomeArq = r.classe.nome + ++i + ".fromOcl.rb";
            f = new File(nomeArq);
        }

        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(nomeArq));
            file.write(r.codigo);
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(GeradorDeCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String identacao(boolean incrementar) {
        String identacao = "";
        if (incrementar) {
            ident++;
        }
        for (int i = 0; i < ident; i++) {
            identacao += "\t";
        }
        return identacao;
    }

    private void decrementarIdent() {
        this.ident--;
    }

    private compil.xmi.Classe classeDoXML(Classe c) {
        for (compil.xmi.Classe classe : XMLReader.getMyClasses()) {
            if (classe.getNome().equals(c.nome)) {
                return classe;
            }
        }
        return null;
    }

    private static class GeradorDeCodigoHolder {

        private static final GeradorDeCodigo INSTANCE = new GeradorDeCodigo();
    }
}
