
public class InitNode extends Node {

	private OclExpressionNode oclExpression;
	private InitNode init;
	private String context;
	
	public InitNode(OclExpressionNode oclExpression, InitNode init, int left, int right) {
		super(left, right);
		this.oclExpression = oclExpression;
		this.init = init;
	}
	
	public void analyze() {
		oclExpression.setContext(context);
		oclExpression.analyze();
		if (init != null) {
			init.setContext(context);
			init.analyze();
		}
	}
	
	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}

}
