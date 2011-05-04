import java.io.FileReader;

import compil.inv.parser.OCL_Lexer;
import compil.inv.parser.OclCup;


public class Main {
	  public static void main(String argv[]) {
		  try {
			  OCL_Lexer scanner = new OCL_Lexer(new FileReader(argv[0]));
			  OclCup cup = new OclCup(scanner);
			  cup.parse();
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
	  }
}
