
public class RelationalExprNode extends OclExpressionNode {

	OclExpressionNode lhs;
	OclExpressionNode rhs;
	String op;
	
	public RelationalExprNode(OclExpressionNode lhs, String op, OclExpressionNode rhs, int left, int right) {
		super(left, right);
		this.lhs = lhs;
		this.rhs = rhs;
		this.op = op;
	}
	
	public void write() {
		System.out.print("(");
		lhs.write();
		System.out.print(op);
		rhs.write();
		System.out.print(")");
	}

}
