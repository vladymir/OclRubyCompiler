public class PathNode extends Node {

	private Object path;
	private Object id;

	public PathNode(Object path, Object id, int left, int right) {
		super(left, right);
		this.path = path;
		this.id = id;
	}

	public void analyze() {
		if (this.path instanceof PathNode) {
			((PathNode) this.path).analyze();
		}
		if (this.id instanceof PathNode) {
			((PathNode) this.id).analyze();
		}
		if (this.id == null && this.path instanceof String) {
			if (!AnalisadorSemantico.hasClass((String) this.path)) {
				Utils.error((String) this.path + " was not declared!", line,
						column);
			}
		}
		if (this.path instanceof String && this.id instanceof String) {
			if (!AnalisadorSemantico.hasAttribute((String) path, (String) id)) {
				Utils.error((String) path + " has no attribute called "
						+ (String) id, line, column);
			}
		}
	}

	public String getLastName() {
		if (id != null && id instanceof String) {
			return (String)id;
		} else if (id != null && id instanceof PathNode) {
			return ((PathNode) id).getLastName();
		}
		return "";
	}

	@Override
	public void write() {
		if (path == null) {
			System.out.print(id);
		}
	}

}
