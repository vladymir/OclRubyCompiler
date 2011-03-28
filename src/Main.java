import java.io.FileReader;

import compil.inv.parser.OCL_Lexer;
import compil.inv.parser.OclCup;

;

public class Main {
	public static void main(String[] args) {

		try {
			OclCup cup = new OclCup(new OCL_Lexer(new FileReader(
					"/home/vladymir/teste")));
			cup.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
