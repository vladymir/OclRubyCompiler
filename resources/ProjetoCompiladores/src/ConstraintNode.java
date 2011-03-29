
public class ConstraintNode extends Node {

	private ContextNode decl;
	private ContextBodyNode body;
	private static int numContexts = 0;
	
	
	public ConstraintNode(ContextNode decl, ContextBodyNode body, int left, int right) {
		super(left, right);
		this.decl = decl;
		this.body = body;
		this.numContexts++;
	}

	@Override
	public void analyze() {
		this.decl.analyze();
		this.body.analyze();
	}

	@Override
	public void write() {
		Utils.writeIdent();
		System.out.println("class Teste");
		Utils.incrementIdent();
		decl.write();
		body.write();
		System.out.println();
		Utils.decrementIdent();
	}

	public void addConstraint(ConstraintNode cd) {
	}

}
