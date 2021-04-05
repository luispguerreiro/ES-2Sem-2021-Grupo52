package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchEntry.Type;

public class WMC_Class {

	private static final String FILE_PATH = "C:\\Users\\r_f_g\\Desktop\\SourceCodeParser.java";
	private List<SwitchEntry> sw;
	private int cyclo = 1;
	private ArrayList<String> resultados = new ArrayList<>();
	List<ClassOrInterfaceDeclaration> contructors = new ArrayList<>();

	public WMC_Class(Metrics m) {
		ClassOrInterfaceDeclaration mainClass = m.getMainClass();
		List<ClassOrInterfaceDeclaration> nestedClasses = m.getNestedClasses();
		contructors.add(mainClass);
		for (ClassOrInterfaceDeclaration nestClass : nestedClasses) {
			contructors.add(nestClass);

		}

	}

	public void Resolve() {
		for (ClassOrInterfaceDeclaration callableDeclaration : contructors) {
			List<Statement> statements = callableDeclaration.findAll(Statement.class);
			List<BinaryExpr> binExpressions = callableDeclaration.findAll(BinaryExpr.class);
			if (!statements.isEmpty() || !binExpressions.isEmpty()) {
				countStatements(statements);
				countBinaryExpressions(binExpressions);
			}

			resultados.add(callableDeclaration.getNameAsString());
			resultados.add(Integer.toString(cyclo));
			// System.out.println("Método " + " tem complexidade " + cyclo);
			cyclo = 1;

		}

	}

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

	private void countBinaryExpressions(List<BinaryExpr> binExpressions) {
		for (BinaryExpr e : binExpressions) {
			if (e.getOperator().equals(Operator.AND) || e.getOperator().equals(Operator.OR)) {
				cyclo++;
			}
		}
	}

	public int getCyclo() {
		return cyclo;
	}

	/*
	 * public void Resolve() { for (CallableDeclaration callableDeclaration :
	 * contructorsNestClass) { List<Statement> statements =
	 * callableDeclaration.findAll(Statement.class); List<BinaryExpr> binExpressions
	 * = callableDeclaration.findAll(BinaryExpr.class); if (!statements.isEmpty() ||
	 * !binExpressions.isEmpty()) { countStatements(statements);
	 * countBinaryExpressions(binExpressions); }
	 * resultados.add(callableDeclaration.getNameAsString());
	 * resultados.add(Integer.toString(cyclo));
	 * 
	 * System.out.println("Método " + callableDeclaration.getNameAsString() +
	 * " tem complexidade " + cyclo);
	 * 
	 * }
	 * 
	 * }
	 */
	public ArrayList<String> getResultados() {
		return resultados;
	}

	public static void main(String[] args) throws FileNotFoundException {
		WMC_Class a = new WMC_Class(new Metrics(FILE_PATH));
		a.Resolve();
		for (String string : a.getResultados()) {
			System.out.println(string);
		}

	}

}