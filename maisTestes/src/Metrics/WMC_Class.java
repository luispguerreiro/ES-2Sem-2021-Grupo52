package Metrics;

import java.io.FileNotFoundException;
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

public class WMC_Class {

	private static final String FILE_PATH = "C:\\Users\\r_f_g\\Desktop\\SourceCodeParser.java";
	private List<SwitchEntry> sw;
	private int cyclo = 1;
	private ArrayList<Resultado> resultados = new ArrayList<>();
	private List<ClassOrInterfaceDeclaration> contructors = new ArrayList<>();
	private String pack = "";

	public WMC_Class(Metrics m) {

		ClassOrInterfaceDeclaration mainClass = m.getMainClass();
		List<ClassOrInterfaceDeclaration> nestedClasses = m.getNestedClasses();
		pack = m.getCu().getPackageDeclaration().toString();
		List<CallableDeclaration> mainClassMet = mainClass.findAll(CallableDeclaration.class);

		String mainClassName = mainClass.getNameAsString();
		List<Statement> statementsmain = mainClass.findAll(Statement.class);
		List<BinaryExpr> binExpressionsmain = mainClass.findAll(BinaryExpr.class);
		if (!statementsmain.isEmpty() || !binExpressionsmain.isEmpty()) {
			countStatements(statementsmain);
			countBinaryExpressions(binExpressionsmain);
		}
		int a = mainClassMet.size();

		resultados.add(new Resultado(pack + "/" + mainClassName + "/" + mainClass.getNameAsString(), cyclo+a, false));
		cyclo = 0;

		for (ClassOrInterfaceDeclaration nestClass : nestedClasses) {
			
				List<Statement> statements = nestClass.findAll(Statement.class);
				List<BinaryExpr> binExpressions = nestClass.findAll(BinaryExpr.class);
				if (!statements.isEmpty() || !binExpressions.isEmpty()) {
					countStatements(statements);
					countBinaryExpressions(binExpressions);
				}
				List<CallableDeclaration> nestedClassMet = nestClass.findAll(CallableDeclaration.class);
				
				int b = nestedClassMet.size();

				resultados.add(new Resultado(pack + "/" + nestClass.getNameAsString(), cyclo+b, false));

				// System.out.println("M�todo " + " tem complexidade " + cyclo);
				cyclo = 0;

			

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

	public ArrayList<Resultado> getResultados() {
		return resultados;
	}

	public static void main(String[] args) throws FileNotFoundException {
		WMC_Class a = new WMC_Class(new Metrics(FILE_PATH));
		
		for (Resultado string : a.getResultados()) {
			// System.out.println(string.getPath());
			System.out.println(string.getClasses());
			System.out.println(string.getLinhas());
		}

	}

}