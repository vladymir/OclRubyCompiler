public class InvariantNode extends ContextBodyNode {

	private String id;
	private OclExpressionNode oclExpression;
	private InvariantNode invariant;
	private String context;

	public InvariantNode(String id, OclExpressionNode oclExpression, int left,
			int right) {
		super(left, right);
		this.id = id;
		this.oclExpression = oclExpression;
	}

	public void analyze() {
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public void write() {
		Utils.writeIdent();
		System.out.println("def checaInv1(self):");
		Utils.incrementIdent();
		if (id != null) {
			Utils.writeIdent();
			System.out.print(id + " = ");
			oclExpression.write();
		} else {
			Utils.writeIdent();
			oclExpression.write();
		}
		Utils.decrementIdent();
		System.out.println();

	}

}
