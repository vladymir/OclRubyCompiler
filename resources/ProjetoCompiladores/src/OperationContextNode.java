
public class OperationContextNode extends ContextNode {

	private PrePostOrBodyNode p;
	
	public OperationContextNode(Object pathOrName, PropertyCallNode p2, TypeNode t, int left,
			int right) {
		super(left, right);
	}
	
	
	public void analyze() {
		if (name != null) {
			p.setContext(name);
		}
		if (path != null) {
			path.analyze();
			p.setContext(path.getLastName());
		}
		p.analyze();
	}

}
