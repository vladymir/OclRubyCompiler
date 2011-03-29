
public class PropertyCallNode extends Node {

	private PathNode path;
	
	public PropertyCallNode(PathNode path, int left, int right) {
		super(left, right);
		this.path = path;
	}

	@Override
	public void analyze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void write() {
		path.write();
	}

}
