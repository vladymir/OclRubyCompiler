public class AddExprNode extends OclExpressionNode {

	private OclExpressionNode lhs;
	private OclExpressionNode rhs;
	private String op;

	public AddExprNode(OclExpressionNode lhs, String op, OclExpressionNode rhs,
			int left, int right) {
		super(left, right);
		this.lhs = lhs;
		this.rhs = rhs;
		this.op = op;
	}

	public void analyze() {
		this.lhs.analyze();
		this.rhs.analyze();
	}
	
	public void write() {
		System.out.print("(");
		lhs.write();
		System.out.print(op);
		rhs.write();
		System.out.print(")");
	}

}
