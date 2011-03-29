public class ForAllOperationNode extends OperationNode {

	private OperationArgumentsNode o;
	private String col;
	
	public ForAllOperationNode(OperationArgumentsNode o, int left, int right) {
		super(left, right);
		this.o = o;
		this.collectionOperation = true;
	}

	public void analyze() {
		this.o.analyze();
	}

	@Override
	public void write() {
		Utils.writeIdent();
		System.out.println("for i in range(len(" + col + ")):");
		Utils.incrementIdent();
		o.write();
		Utils.decrementIdent();
	}
	
	public void setCollection(String col) {
		this.col = col;
	}
	
}
