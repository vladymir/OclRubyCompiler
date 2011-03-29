import java.util.ArrayList;
import java.util.Iterator;

public class ContextNode extends Node {

	public static int numContexts = 1;
	protected PathNode path;
	protected String name;
	private ArrayList<ContextNode> contexts = new ArrayList<ContextNode>();
	
	public ContextNode(int left, int right) {
		super(left, right);
	}
	
	public void analyze() {
		
	}

	@Override
	public void write() {
	}

}
