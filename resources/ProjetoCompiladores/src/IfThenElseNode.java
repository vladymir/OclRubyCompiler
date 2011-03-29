
public class IfThenElseNode extends OclExpressionNode {

	OclExpressionNode ifExpr;
	OclExpressionNode thenExpr;
	OclExpressionNode elseExpr;
	
	public IfThenElseNode(OclExpressionNode ifExpr, OclExpressionNode thenExpr, OclExpressionNode elseExpr, int left, int right) {
		super(left, right);
		this.ifExpr = ifExpr;
		this.thenExpr = thenExpr;
		this.elseExpr = elseExpr;
	}
	
	public void analyze() {
		ifExpr.analyze();
		thenExpr.analyze();
		elseExpr.analyze();
	}
	
	public void write() {
		System.out.print("if ");
		ifExpr.write();
		System.out.println(":");
		Utils.incrementIdent();
		Utils.writeIdent();
		thenExpr.write();
		Utils.decrementIdent();
		System.out.println();
		Utils.writeIdent();
		System.out.println("else:");
		Utils.incrementIdent();
		Utils.writeIdent();
		elseExpr.write();
		System.out.println();
		Utils.decrementIdent();
	}

}
