
public class LiteralNode extends OclExpressionNode {

	private Object valor;
	
	public LiteralNode(Object valor, int left, int right) {
		super(left, right);
		this.valor = valor;
	}
	
//	public String getValor() {
//		return valor.toString();
//	}
	
	public void write() {
		if (valor instanceof Node) {
			((Node) valor).write();
		} else {
			System.out.print(valor);
		}
	}

}
