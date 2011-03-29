
public class StringNode extends LiteralNode {

	private String valor;
	
	public StringNode(Object valor, int left, int right) {
		super(valor, left, right);
		this.valor = valor.toString();
	}

	@Override
	public void analyze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void write() {
		System.out.print("'" + valor + "'");
	}

}
