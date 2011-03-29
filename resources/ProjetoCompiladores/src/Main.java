import java.io.*;

import java_cup.runtime.Symbol;

public class Main {

	public static void main(String argv[]) {
		/* Start the parser */
		try {
			OclCup p = new OclCup(new OclLexer(new FileReader(argv[0])));
			Symbol s = p.parse();
			Object result = s.value;
			AnalisadorSemantico as = AnalisadorSemantico.getInstance();
			as.startParsing();
			System.out.println("Output:");
			System.out.println();
			((Node) result).analyze();
			if (Utils.getErrors() == 0) {
				((Node) result).write();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
