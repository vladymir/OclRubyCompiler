
public class UnaryExprNode extends OclExpressionNode {

	private OclExpressionNode expr;
	private String op;
	
	public UnaryExprNode(String op, OclExpressionNode expr, int left, int right) {
		super(left, right);
		this.expr = expr;
		this.op = op;
	}
	
	public void analyze() {
		this.expr.analyze();
	}
	
	public void write() {
		System.out.print(op + " ");
		expr.write();
	}

}
