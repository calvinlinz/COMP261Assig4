import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;
import javax.swing.JFileChooser;

/**
 * The parser and interpreter. The top level parse function, a main method for
 * testing, and several utility methods are provided. You need to implement
 * parseProgram and all the rest of the parser.
 */
public class Parser {

	/**
	 * Top level parse method, called by the World
	 */
	static RobotProgramNode parseFile(File code) {
		Scanner scan = null;
		try {
			scan = new Scanner(code);
			// the only time tokens can be next to each other is
			// when one of them is one of (){},;
			scan.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");

			RobotProgramNode n = parseProgram(scan); // You need to implement this!!!

			scan.close();
			return n;
		} catch (FileNotFoundException e) {
			System.out.println("Robot program source file not found");
		} catch (ParserFailureException e) {
			System.out.println("Parser error:");
			System.out.println(e.getMessage());
			scan.close();
		}
		return null;
	}

	/** For testing the parser without requiring the world */

	public static void main(String[] args) {
		if (args.length > 0) {
			for (String arg : args) {
				File f = new File(arg);
				if (f.exists()) {
					System.out.println("Parsing '" + f + "'");
					RobotProgramNode prog = parseFile(f);
					System.out.println("Parsing completed ");
					if (prog != null) {
						System.out.println("================\nProgram:");
						System.out.println(prog);
					}
					System.out.println("=================");
				} else {
					System.out.println("Can't find file '" + f + "'");
				}
			}
		} else {
			while (true) {
				JFileChooser chooser = new JFileChooser(".");// System.getProperty("user.dir"));
				int res = chooser.showOpenDialog(null);
				if (res != JFileChooser.APPROVE_OPTION) {
					break;
				}
				RobotProgramNode prog = parseFile(chooser.getSelectedFile());
				System.out.println("Parsing completed");
				if (prog != null) {
					System.out.println("Program: \n" + prog);
				}
				System.out.println("=================");
			}
		}
		System.out.println("Done");
	}

	// Useful Patterns

	static Pattern NUMPAT = Pattern.compile("-?(0|[1-9][0-9]*)"); // ("-?(0|[1-9][0-9]*)");
	static Pattern OPENPAREN = Pattern.compile("\\(");
	static Pattern CLOSEPAREN = Pattern.compile("\\)");
	static Pattern OPENBRACE = Pattern.compile("\\{");
	static Pattern CLOSEBRACE = Pattern.compile("\\}");
	static Pattern RELOP = Pattern.compile("lt|gt|eq");
	static Pattern SEN = Pattern.compile("fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist");
	static Pattern COND = Pattern.compile("and|or");
	static Pattern OP = Pattern.compile("add|sub|mul|div");
	static Pattern COMMA = Pattern.compile(",");
	static Pattern SEMICOLON = Pattern.compile(";");

	static Operators buildOperation(Scanner s, String str) {
		Expression ex1 = findExpression(s);
		s.next();
		Expression ex2 = findExpression(s);
		return new Operators(str, ex1, ex2);
	}

	static Expression findExpression(Scanner s) {
		String str = s.next();
		if (OP.matcher(str).matches()) {
			s.next();
			Operators op = buildOperation(s, str);
			s.next();
			return new Expression(op);
		}

		if (NUMPAT.matcher(str).matches()) {
			return new Expression(Integer.parseInt(str));
		} else
			return new Expression(new Sen(str));

	}

	static Cond findCondition(Scanner s) {
		Relop relop = null;
		Cond condition = null;
		Expression ex1 = null;
		Expression ex2 = null;

		while (condition == null) {
			String next = s.next();
			Matcher rt = RELOP.matcher(next);
			if (rt.matches()) {
				relop = new Relop(next);
				s.next();
				ex1 = findExpression(s);
				s.next();
				ex2 = findExpression(s);
			} else if (COND.matcher(next).matches()) {
				Cond cond1 = findCondition(s);
				Cond cond2 = findCondition(s);
				condition = new Cond(new Relop(next), cond1, cond2);
			} else if (next.equals("not")) {
				Cond cond1 = findCondition(s);
				condition = new Cond(new Relop(next), cond1, null);
			}

			if (ex1 != null && ex2 != null && relop != null) {
				condition = new Cond(relop, ex1, ex2);
			}
			if (next.equals("}"))
				Parser.fail("failed to find condition", s);
		}
		return condition;
	}

	static void doLoop(Scanner s, Queue<RobotProgramNode> statements) {
		Queue<RobotProgramNode> block = new LinkedList();
		while (true) {
			STMT next = new STMT(s.next());
			if (next.toString().equals("}")) {
				break;
			}
			if(!next.isStatement() && !next.toString().equals("(") && !next.toString().equals(",") && !next.toString().equals(")") && !next.toString().equals("{"))Parser.fail("Not a Statement", s);
			
			if (next.checkIf()) {
				doIf(s, block);
			}
			if (next.checkWhile()) {
				doWhile(s, block);
			}
			if (next.checkLoop()) {
				doLoop(s, block);
			}
			if (next.checkAction()) {
				if(s.next().equals("(")){
					Expression exp = findExpression(s);
					ActNode act = new ActNode(next.toString(), exp);
					statements.add(act);
				}else{
					ActNode act = new ActNode(next.toString());
					statements.add(act);
					if(s.next().equals(";")){
						fail("missing ;", s);
				}
			}
			
		}
		if(block.isEmpty()) Parser.fail("Block is empty",s);
		statements.add(new Loop(block));
	}
}

	static void doWhile(Scanner s, Queue<RobotProgramNode> statements) {
		Queue<RobotProgramNode> block = new LinkedList();
		Cond condition = findCondition(s);

		while (s.hasNext()) {
			try {
				STMT next = new STMT(s.next());
				if (next.toString().equals("}")) {
					break;
				}
				if (!next.isStatement() && !next.toString().equals("(") && !next.toString().equals(",")
						&& !next.toString().equals(")") && !next.toString().equals("{"))
					Parser.fail("Not a Statement", s);
				if (next.checkIf()) {
					doIf(s, block);
				}
				if (next.checkWhile()) {
					doWhile(s, block);
				}
				if (next.checkLoop()) {
					doLoop(s, block);
				}
				if (next.checkAction()) {
					if (s.next().equals("(")) {
						Expression exp = findExpression(s);
						ActNode act = new ActNode(next.toString(), exp);
						statements.add(act);
					} else {
						ActNode act = new ActNode(next.toString());
						statements.add(act);
					}

				}
			} catch (NoSuchElementException e) {
				Parser.fail("failed to read", s);
			}
		}
		if (block.isEmpty())
			Parser.fail("Block is empty", s);
		WhileNode whileNode = new WhileNode(block);
		whileNode.setConditions(condition);
		statements.add(whileNode);

	}

	static void doIf(Scanner s, Queue<RobotProgramNode> statements) {
		Queue<RobotProgramNode> block = new LinkedList();
		Cond condition = findCondition(s);
		Queue<RobotProgramNode> elseBlock = new LinkedList();

		while (s.hasNext()) {
			try {
				STMT next = new STMT(s.next());
				if (next.toString().equals("}")) {
					break;
				}
				if (!next.isStatement() && !next.toString().equals("(") && !next.toString().equals(";")
						&& !next.toString().equals(",") && !next.toString().equals(")") && !next.toString().equals("{"))
					Parser.fail("Not a Statement", s);

				if (next.checkIf()) {
					doIf(s, block);
				}
				if (next.checkWhile()) {
					doWhile(s, block);
				}
				if (next.checkLoop()) {
					doLoop(s, block);
				}
				if (next.checkAction()) {
					if (s.next().equals("(")) {
						Expression exp = findExpression(s);
						ActNode act = new ActNode(next.toString(), exp);
						block.add(act);
					} else {
						ActNode act = new ActNode(next.toString());
						block.add(act);
					}

				}
			} catch (NoSuchElementException e) {
				Parser.fail("failed to read", s);
			}
		}

		if (s.next().equals("else")) {
			while (s.hasNext()) {
				try {
					STMT next = new STMT(s.next());
					if (next.toString().equals("}")) {
						break;
					}
					if (!next.isStatement() && !next.toString().equals("(") && !next.toString().equals(";")
							&& !next.toString().equals(",") && !next.toString().equals(")")
							&& !next.toString().equals("{"))
						Parser.fail("Not a Statement", s);

					if (next.checkIf()) {
						doIf(s, elseBlock);
					}
					if (next.checkWhile()) {
						doWhile(s, elseBlock);
					}
					if (next.checkLoop()) {
						doLoop(s, elseBlock);
					}
					if (next.checkAction()) {
						if (s.next().equals("(")) {
							Expression exp = findExpression(s);
							ActNode act = new ActNode(next.toString(), exp);
							elseBlock.add(act);
						} else {
							ActNode act = new ActNode(next.toString());
							elseBlock.add(act);
						}

					}
				} catch (NoSuchElementException e) {
					Parser.fail("failed to read", s);
				}
			}
		}
		if (block.isEmpty())
			Parser.fail("Block is empty", s);
		IfNode ifNode = new IfNode(block);
		ifNode.setConditions(condition);
		if (!elseBlock.isEmpty())
			ifNode.setElseBlock(elseBlock);
		statements.add(ifNode);
	}

	/**
	 * See assignment handout for the grammar.
	 */
	static RobotProgramNode parseProgram(Scanner s) {
		// THE PARSER GOES HERE
		Queue<RobotProgramNode> statements = new LinkedList();
		while (s.hasNext()) {
			try {
				STMT next = new STMT(s.next());
				if (next.toString().equals("}")) {
					break;
				}
				if (!next.isStatement() && !next.toString().equals("(") && !next.toString().equals(";")
						&& !next.toString().equals(",") && !next.toString().equals(")") && !next.toString().equals("{"))
					Parser.fail("Not a Statement", s);

				if (next.checkIf()) {
					doIf(s, statements);
				}
				if (next.checkWhile()) {
					doWhile(s, statements);
				}
				if (next.checkLoop()) {
					doLoop(s, statements);
				}
				if (next.checkAction()) {
					if (s.next().equals("(")) {
						Expression exp = findExpression(s);
						ActNode act = new ActNode(next.toString(), exp);
						statements.add(act);
					} else {
						ActNode act = new ActNode(next.toString());
						statements.add(act);
					}

				}
			} catch (NoSuchElementException e) {
				Parser.fail("failed to read", s);
			}
		}
		Program prog = new Program(statements);
		return prog;
	}

	// utility methods for the parser

	/**
	 * Report a failure in the parser.
	 */
	static void fail(String message, Scanner s) {
		String msg = message + "\n   @ ...";
		for (int i = 0; i < 5 && s.hasNext(); i++) {
			msg += " " + s.next();
		}
		throw new ParserFailureException(msg + "...");
	}

	/**
	 * Requires that the next token matches a pattern if it matches, it consumes
	 * and returns the token, if not, it throws an exception with an error
	 * message
	 */
	static String require(String p, String message, Scanner s) {
		if (s.hasNext(p)) {
			return s.next();
		}
		fail(message, s);
		return null;
	}

	static String require(Pattern p, String message, Scanner s) {
		if (s.hasNext(p)) {
			return s.next();
		}
		fail(message, s);
		return null;
	}

	/**
	 * Requires that the next token matches a pattern (which should only match a
	 * number) if it matches, it consumes and returns the token as an integer if
	 * not, it throws an exception with an error message
	 */
	static int requireInt(String p, String message, Scanner s) {
		if (s.hasNext(p) && s.hasNextInt()) {
			return s.nextInt();
		}
		fail(message, s);
		return -1;
	}

	static int requireInt(Pattern p, String message, Scanner s) {
		if (s.hasNext(p) && s.hasNextInt()) {
			return s.nextInt();
		}
		fail(message, s);
		return -1;
	}

	/**
	 * Checks whether the next token in the scanner matches the specified
	 * pattern, if so, consumes the token and return true. Otherwise returns
	 * false without consuming anything.
	 */
	static boolean checkFor(String p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

	static boolean checkFor(Pattern p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

}

// You could add the node classes here, as long as they are not declared public
// (or private)
