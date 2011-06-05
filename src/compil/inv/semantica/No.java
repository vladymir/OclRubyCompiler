package compil.inv.semantica;

public abstract class No {
		public String tipo = "";
		public String tipoRequerido;
        public String codigo = "";       
        
        
    	public String getTipo() {
    		return tipo;
    	}
    	
    	public void setTipo(String tipo) {
    		if (tipo != "") {
    			this.tipo = tipo;
    		}

    	}
    	
    	public String getTipoRequerido() {
    		return tipoRequerido;
    	}

    	public void setTipoRequerido(String tipoRequerido) {
    		if (tipoRequerido != null) {
    			this.tipoRequerido = tipoRequerido;
    		}

    	}
        
        protected abstract void gerarCodigo();
        
        public void checkTipo() throws SemanticException { 
        	
    		if ( !this.getTipo().equals(this.getTipoRequerido()))
    			throw new SemanticException(
    					"Semantic Exception: tipos incompativeis. Esperava: " + this.getTipoRequerido() + " encontrado "
    							+ this.getTipo() );    		
    			
    	}	
}