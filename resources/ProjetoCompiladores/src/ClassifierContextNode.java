public class ClassifierContextNode extends ContextNode {

	private String id1;
	private String id2;
	
	public ClassifierContextNode(String id1, String id2, int left, int right) {
		super(left, right);
		this.id1 = id1;
		this.id2 = id2;
	}
	
	public void analyze() {
		if (!AnalisadorSemantico.hasClass(id1)) {
			Utils.error(id1 + " was not declared", line, column);
		} else if (!AnalisadorSemantico.hasAttribute(id1, id2)) {
			Utils.error(id1 + " has no attribute called " + id2, line, column);
		}
	}
	
	public void write() {
		Utils.writeIdent();
		System.out.println("''' Context for " + id1 + ":" + id2 + "'''");
	}

}