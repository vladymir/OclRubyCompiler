
public class AttrContextNode extends ContextNode {

	private Object type;
	private InitNode init;
	
	public AttrContextNode(Object pathOrName, Object type, InitNode init, int left, int right) {
		super(left, right);
		this.type = type;
		this.init = init;
	}
	
	public void analyze() {
		if (name != null) {
			init.setContext(name);
		}
		if (path != null) {
			path.analyze();
			init.setContext(path.getLastName());
		}
		init.analyze();
	}

}
