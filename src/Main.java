import java.io.FileNotFoundException;
import java.io.FileReader;

import compil.inv.parser.*;

public class Main {
	public static void main(String[] args) {
		try {
		OclCup cup = new OclCup(new OCL_Lexer(new FileReader("/home/vladymir/teste")));
		try {cup.parse(); 
		} catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("vlad");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
