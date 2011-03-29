
import java.util.Vector;

import parser.Classe;
import parser.XMLReader;

public class AnalisadorSemantico {
	private static AnalisadorSemantico instance;
	private static XMLReader xmlReader;

	private AnalisadorSemantico(XMLReader xml) {
		this.xmlReader = xml;
	}

	public static AnalisadorSemantico getInstance() {
		if (instance == null) {
			instance = new AnalisadorSemantico(new XMLReader());
		}
		return instance;
	}

	public void startParsing() {
		xmlReader.startParser();
	}

	public static Vector<Classe> getClasses() {
		return xmlReader.getMyClasses();
	}

	public static boolean hasClass(String classe) {
		for (int i = 0; i < getClasses().size(); i++) {
			if (getClasses().get(i).getNome().equals(classe)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasAttribute(String classe, String atributo) {
		int posicao = 0;
		for (int i = 0; i < getClasses().size(); i++) {
			if (getClasses().get(i).getNome().equals(classe)) {
				posicao = i;
			}
		}

		for (int i = 0; i < getClasses().get(posicao).getMyAtt().size(); i++) {
			if (getClasses().get(posicao).getMyAtt().get(i).getNome().equals(
					atributo)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCollection(String classe) {
		String collection = classe.concat("*");
		for (int i = 0; i < getClasses().size(); i++) {
			if (getClasses().get(i).getNome().equals(collection)) {
				return true;
			}
		}
		return false;
	}

	public static boolean relationAtribute(String atributoRelacao,
			String atributo) {
		int posicaoClasse = -1;
		for (int i = 0; i < getClasses().size(); i++) {
			if (getClasses().get(i).getNome().trim().equalsIgnoreCase(
					atributoRelacao.substring(0, atributoRelacao.length() - 1)
							.trim())) {
				posicaoClasse = i;
			}
		}

		if (posicaoClasse != -1) {
			for (int j = 0; j < getClasses().get(posicaoClasse).getMyAtt()
					.size(); j++) {
				if (getClasses().get(posicaoClasse).getMyAtt().get(j).getNome()
						.equals(atributo)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		XMLReader xml = new XMLReader();
		AnalisadorSemantico anal = new AnalisadorSemantico(xml);
		anal.startParsing();
		System.out.println(getClasses());
	}
}
