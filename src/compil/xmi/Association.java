package compil.xmi;

public class Association {
	private String id;
	private String idAssociationBegin;
	private String idAssociationEnd;
	private String idAssociationChild;
	private String typeAssociationChild;
	private String assAssociationChild;
	
	public Association(String id, String idAssB, String idAssE, String idAssociationChild, String typeAssociationChild, String assAssociationChild){
		this.id = id;
		this.idAssociationBegin = idAssB;
		this.idAssociationEnd = idAssE;
		this.idAssociationChild = idAssociationChild;
		this.typeAssociationChild = typeAssociationChild;
		this.assAssociationChild = assAssociationChild;
	}
	
	public String getIdAssociationBegin() {
		return idAssociationBegin;
	}
	
	public String getIdAssociationEnd() {
		return idAssociationEnd;
	}
	public String getId() {
		return id;
	}

	public String toString(){
		if (idAssociationChild.equals(""))
			return "ID: " + id  + "    ID Ass Begin: " + idAssociationBegin
				+ "      ID Ass End: " + idAssociationEnd;
		return "ID: " + id  + "    ID Ass Begin: " + idAssociationBegin
			+ "      ID Ass End: " + idAssociationEnd + "     ID child: " + idAssociationChild + "      Type association child: " + typeAssociationChild+
			"          Association child: " + assAssociationChild; 
	}
}
