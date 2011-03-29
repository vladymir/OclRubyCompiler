
public class ConditionalExprNode extends OclExpressionNode {

	private OclExpressionNode lhs;
	private OclExpressionNode rhs;
	private String relop;
	
	public ConditionalExprNode(OclExpressionNode lhs, String relop, OclExpressionNode rhs, int left, int right) {
		super(left, right);
		this.lhs = lhs;
		this.rhs = rhs;
		this.relop = relop;
	}
	
	public void analyze() {
		this.lhs.analyze();
		this.rhs.analyze();
	}
	
	public void write() {
		System.out.print("(");
		lhs.write();
		System.out.print(relop);
		rhs.write();
		System.out.print(")");
	}

}
