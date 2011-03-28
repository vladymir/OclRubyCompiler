import java.io.FileReader;

import compil.inv.parser.OCL_Lexer;
import compil.inv.parser.OclCup;

;

public class Main {
	  public static void main(String argv[]) {
		  try {
			  OCL_Lexer scanner = new OCL_Lexer(new FileReader("/home/vladymir/teste"));
			  OclCup cup = new OclCup(scanner);
			  cup.debug_parse();
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
	  }
}
