public class OclExpressionNode extends Node {

	private Object expr;
	private String context;

	public OclExpressionNode(int left, int right) {
		super(left, right);
	}

	public void analyze() {
		if (context == null) {
			System.err.println("opa");
		}
		if (expr instanceof String) {
			if (!AnalisadorSemantico.hasAttribute(context, (String) expr)) {
				Utils.error(context + " has no attribute called "
						+ expr.toString() + "!", line, column);
			}
		}
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public void write() {
	}
}
