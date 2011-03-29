public class PrePostOrBodyNode extends ContextBodyNode {

	private String name;
	private OclExpressionNode oclExpression;
	private String context;

	public PrePostOrBodyNode(String name, OclExpressionNode oclExpression,
			int left, int right) {
		super(left, right);
		this.name = name;
		this.oclExpression = oclExpression;
	}

	public void analyze() {
		oclExpression.analyze();
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public void write() {
		oclExpression.analyze();
	}

}
