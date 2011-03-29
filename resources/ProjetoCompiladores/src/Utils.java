import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

	private static final HashMap<String, ArrayList<String>> validOperands = new HashMap<String, ArrayList<String>>();
	private static int errors = 0;
	private static int ident = 0;

	public static boolean isValidOperand(String type1, String type2) {
		if (validOperands.isEmpty()) {
			fillOperands();
		}
		if (!validOperands.get(type1).contains(type2))
			return true;
		return false;
	}

	public static String coerce(String type1, String type2) {
		if (type1.equals(type2))
			return type1;
		if (type1.equals("Real")) {
			if (type2.equals("Integer") || type2.equals("Boolean"))
				return "Real";
		}
		if (type1.equals("Integer") && type2.equals("Boolean"))
			return "Integer";
		if (type1.equals("Boolean") && type2.equals("Integer"))
			return "Integer";
		return null;
	}

	private static void fillOperands() {
		ArrayList<String> integers = new ArrayList<String>();
		integers.add("Integer");
		integers.add("Real");
		integers.add("Boolean");
		validOperands.put("Integer", integers);
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("String");
		validOperands.put("String", strings);
		ArrayList<String> booleans = new ArrayList<String>();
		booleans.add("Boolean");
		booleans.add("Integer");
		booleans.add("Real");
		validOperands.put("Boolean", booleans);
	}

	public static void error(String message, int line, int column) {
		System.err.print(message);
		System.err.print(" : ");
		System.err.print('(');
		System.err.print(line);
		System.err.print(" , ");
		System.err.print(column);
		System.err.println(')');
		errors++;
	}

	public static int getErrors() {
		return errors;
	}
	
	public static int getIdent() {
		return ident;
	}
	
	public static void incrementIdent() {
		ident += 4;
	}
	
	public static void decrementIdent() {
		ident -= 4;
	}
	
	public static void writeIdent() {
		for (int i = 0; i < ident; i++) System.out.print(" ");
	}

}
