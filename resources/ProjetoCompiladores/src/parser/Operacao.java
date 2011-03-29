package parser;

public class Operacao {
	private String id = "";
	private String name = "";
	private String type = "";
	private String retur = "";
	private String direction = "";
	
	//Para ownedParameter
	private String idParam = "";
	private String nameParam = "";
	private String typeParam = "";
	private String directionParam = "";
	
	//Para returnResult
	private String returId = "";
	private String returType = "";
	private String returDirec = "";
	
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
