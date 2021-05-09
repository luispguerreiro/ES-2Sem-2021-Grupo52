package metrics;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchEntry.Type;

/**
 * 
 * @author Grupo 52 

 * Calculates the Cyclomatic Complexity of the methods of the Metrics object imported
 * 
 */
public class CYCLO_method {

	private List<SwitchEntry> sw;
	private int cyclo = 1;
	private ArrayList<Resultado> resultados = new ArrayList<>();
	private String pack;
	private int i = 1;
	private ClassOrInterfaceDeclaration mainClass;
	@SuppressWarnings("rawtypes")
	private List<CallableDeclaration> mainClassMet;
	private List<ClassOrInterfaceDeclaration> nestedClasses;
	private String mainClassName;
	private Metrics m;
//	private ArrayList<Integer> empty = new ArrayList<>();
	private int[] empty = new int[5];

	/**
	 * Calculates the Cyclomatic Complexity of all the methods on the chosen Metrics
	 * object and adds the result to an array of Resultados with the Name of the
	 * package and the class.
	 * 
	 * @param m the object Metrics chosen
	 */

	public CYCLO_method(Metrics m) {
		this.m = m;
		mainClass = m.getMainClass();
		pack = m.getCu().getPackageDeclaration().toString();
		mainClassMet = mainClass.findAll(CallableDeclaration.class);
		mainClassName = mainClass.getNameAsString();
		nestedClasses = m.getNestedClasses();
		runMainClass();
		runNestedClasses();
	}

	/**
	 * Counts the cyclomatic complexity of all the statements in the array.
	 * 
	 * @param statements an array of statements
	 * 
	 */
	private void countStatements(List<Statement> statements) {
		for (Statement s : statements) {
			if (s.isContinueStmt() || s.isDoStmt() || s.isForEachStmt() || s.isForStmt() || s.isIfStmt()
					|| s.isWhileStmt()) {
				cyclo++;
			}
			if (s.isSwitchStmt()) {
				sw = s.findAll(SwitchEntry.class);
				for (SwitchEntry sws : sw) {
					if (sws.getType().equals(Type.STATEMENT_GROUP) && sws.getLabels().isNonEmpty()) {
						cyclo++;
					}
				}
			}
		}
	}
	
	private void runMainClass() {
		for (CallableDeclaration<?> callableDeclaration : mainClassMet) {
			List<Statement> statements = callableDeclaration.findAll(Statement.class);
			List<BinaryExpr> binExpressions = callableDeclaration.findAll(BinaryExpr.class);
			if (!statements.isEmpty() || !binExpressions.isEmpty()) {
				countStatements(statements);
				countBinaryExpressions(binExpressions);
			}
			List<Parameter> par = callableDeclaration.getParameters();
			String parameters = "";
			if (!par.isEmpty()) {
				for (Parameter p : par) {
					parameters += p.getType().toString();
					if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
						parameters += ",";
					}
				}
			}
			boolean a = false;
			for (ClassOrInterfaceDeclaration nestClass : m.getNestedClasses()) {
				for (CallableDeclaration<?> ctest : nestClass.findAll(CallableDeclaration.class)) {
					if (ctest.getName() == callableDeclaration.getName()) {
						a = true;
					}
				}
			}
			if (a == false) {
				resultados.add(new Resultado(i, pack + "/" + mainClassName + "/" + callableDeclaration.getNameAsString()
						+ "(" + parameters + ")", cyclo, empty));
				cyclo = 1;
			}
		}
	}
	
	private void runNestedClasses() {
		for (ClassOrInterfaceDeclaration nestClass : nestedClasses) {
			String NestClassNames = nestClass.getNameAsString();
			@SuppressWarnings("rawtypes")
			List<CallableDeclaration> contructorsNestClass = nestClass.findAll(CallableDeclaration.class);
			for (CallableDeclaration<?> c : contructorsNestClass) {
				List<Statement> statements = c.findAll(Statement.class);
				List<BinaryExpr> binExpressions = c.findAll(BinaryExpr.class);
				if (!statements.isEmpty() || !binExpressions.isEmpty()) {
					countStatements(statements);
					countBinaryExpressions(binExpressions);
				}
				List<Parameter> par = c.getParameters();
				String parameters = "";
				if (!par.isEmpty()) {
					for (Parameter p : par) {
						parameters += p.getType().toString();
						if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
							parameters += ",";
						}
					}
				}
				resultados.add(new Resultado(i, pack + "/" + mainClassName + "." + NestClassNames + "/"
						+ c.getNameAsString() + "(" + parameters + ")" + "/", cyclo, empty));
				cyclo = 1;
			}
		}
	}

	/**
	 * Counts the cyclomatic complexity of all binary expressions in the array
	 * 
	 * @param binExpression an array of Binary Expressions
	 */
	private void countBinaryExpressions(List<BinaryExpr> binExpressions) {
		for (BinaryExpr e : binExpressions) {
			if (e.getOperator().equals(Operator.AND) || e.getOperator().equals(Operator.OR)) {
				cyclo++;
			}
		}
	}
	
	/**
	 * Getter for the array with the results
	 * 
	 * @return the array with the results
	 */
	public ArrayList<Resultado> getResultados() {
		return resultados;
	}


}