
public class OperationNode extends OclExpressionNode {

	protected boolean collectionOperation;
	
	public OperationNode(int left, int right) {
		super(left, right);
	}

	@Override
	public void analyze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void write() {
		// TODO Auto-generated method stub

	}
	
	public boolean isCollectionOperation() {
		return collectionOperation;
	}

}
