import java.io.FileReader;

import compil.inv.parser.OCL_Lexer;
import compil.inv.parser.OCL_Parser;
import compil.xmi.XMLReader;


public class Main {
	  public static void main(String argv[]) {
		  try {
			  OCL_Lexer scanner = new OCL_Lexer(new FileReader(argv[0]));
			  OCL_Parser cup = new OCL_Parser(scanner);
			  XMLReader xmir = new XMLReader();
			  xmir.startParser();
			  cup.parse();
		  } catch(Exception e) {
			  System.err.println(e.getMessage());
		  }
	  }
}
