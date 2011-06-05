/*
 * Free to use and modify
 */
package compil.inv.semantica;

import compil.inv.geracao.GeradorDeCodigo;
import java.util.ArrayList;

/**
 *
 * @author nicholas
 */
public class Declarador extends No {
    public ArrayList<String> listaDeclaradores = new ArrayList<String>();

    public Declarador(String declarador, Declarador decAux) {
        this.listaDeclaradores.add(declarador);
        if (decAux != null) {
            for (String dec : decAux.listaDeclaradores) {
                this.listaDeclaradores.add(dec);
            }
        }
    }

    protected void gerarCodigo() {
        GeradorDeCodigo.getInstance().declaradores(this);
    }
    
    
    
    
}
