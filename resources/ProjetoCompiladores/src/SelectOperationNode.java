
public class SelectOperationNode extends OperationNode {

	private Object o;
	private String col;
	
	public SelectOperationNode(Object o, int left, int right) {
		super(left, right);
		this.o = o;
	}
	
	public void write() {
		System.out.print("[i for i in range(len(" + col + ")) ");
		((Node)o).write();
		System.out.println(" ]");
	}
	
	public void setCollection(String col) {
		this.col = col;
	}

}
