/*
 * Free to use and modify
 */
package compil.inv.semantica;

/**
 *
 * @author nicholas
 */
public class Caminho {
    
    public String caminhoCompleto;

    public Caminho(String id, Caminho c) {
        if( c != null )
            this.caminhoCompleto = id + ":" + c.caminhoCompleto;
        else
            this.caminhoCompleto = id;
    }
    
    
    
}
