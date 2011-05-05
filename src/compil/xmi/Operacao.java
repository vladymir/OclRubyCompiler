package compil.xmi;

public class Operacao {
	public String id = "";
	public String name = "";
	public String type = "";
	public String retur = "";
	public String direction = "";
	
	//Para ownedParameter
	public String idParam = "";
	public String nameParam = "";
	public String typeParam = "";
	public String directionParam = "";
	
	//Para returnResult
	public String returId = "";
	public String returType = "";
	public String returDirec = "";
	
	public Operacao(String name, String id){
		this.name = name;
		this.id = id;
	}
	
	public Operacao(String name, String id, String type, String idParam, String nameParam, String typeParam, String directionParam){
		this.name = name;
		this.id = id;
		this.type = type;
		this.idParam = idParam;
		this.nameParam = nameParam;
		this.typeParam = typeParam;
		this.directionParam = directionParam;
	}
	
	public Operacao(String name, String id, String type, String idParam, String nameParam, String directionParam){
		this.name = name;
		this.id = id;
		this.type = type;
		this.idParam = idParam;
		this.nameParam = nameParam;
		this.directionParam = directionParam;
	}
	
	public Operacao(String id, String name, String type){
		this.name = name;
		this.id = id;
		this.type = type;
	}
	
	public Operacao (String id, String name, String type, String returId, String returType, String returDirec, int hola){
		this.id = id;
		this.name = name;
		this.type = type;
		this.returId = returId;
		this.returType = returType;
		this.returDirec = returDirec;
	}
	
	public Operacao (String id, String name, String type, String returId, String returDirec){
		this.id = id;
		this.name = name;
		this.type = type;
		this.returId = returId;
		this.returDirec = returDirec;
	}
	
}
