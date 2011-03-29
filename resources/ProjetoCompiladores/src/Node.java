import java.util.ArrayList;
import java.util.Iterator;


public abstract class Node {

	protected int line;
	protected int column;
	
	public String code;
	
	public Node(int left, int right) {
		this.line = left;
		this.column = right;
	}
	
	public abstract void analyze();
	
	public abstract void write();
}
