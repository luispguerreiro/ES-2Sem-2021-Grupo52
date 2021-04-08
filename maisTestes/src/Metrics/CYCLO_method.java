package Metrics;

<<<<<<< HEAD
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

public class CYCLO_method {

	private static final String FILE_PATH = "C:\\Users\\r_f_g\\Desktop\\SourceCodeParser.java";
	private List<SwitchEntry> sw;
	private int cyclo = 1;
	private ArrayList<Resultado> resultados = new ArrayList<>();
	private String pack;

	public CYCLO_method(Metrics m) {
		ClassOrInterfaceDeclaration mainClass = m.getMainClass();
		pack = m.getCu().getPackageDeclaration().toString();
		List<CallableDeclaration> mainClassMet = mainClass.findAll(CallableDeclaration.class);
		for (CallableDeclaration callableDeclaration : mainClassMet) {
			String mainClassName = mainClass.getNameAsString();
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

			resultados.add(new Resultado(pack + "/" + mainClassName + "/" + callableDeclaration.getNameAsString() + "("
					+ parameters + ")" , cyclo, false));
			cyclo = 1;
		}

		List<ClassOrInterfaceDeclaration> nestedClasses = m.getNestedClasses();
		for (ClassOrInterfaceDeclaration nestClass : nestedClasses) {
			String NestClassNames = nestClass.getNameAsString();
			List<CallableDeclaration> contructorsNestClass = nestClass.findAll(CallableDeclaration.class);
			for (CallableDeclaration c : contructorsNestClass) {

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

				resultados.add(new Resultado(
						pack + "/" + NestClassNames + "/" + c.getNameAsString() + "(" + parameters + ")" + "/", cyclo,
						false));
				// resultados.add(callableDeclaration.getNameAsString());
				// resultados.add(Integer.toString(cyclo));

				// System.out.println("M�todo " + callableDeclaration.getNameAsString() + " tem
				// complexidade " + cyclo);
				cyclo = 1;
			}
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

	public static void main(String[] args) throws Exception {

		// CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

		CYCLO_method a = new CYCLO_method(new Metrics(FILE_PATH));
		// a.Resolve();
		System.out.println(a.getResultados().size());
		for (Resultado string : a.getResultados()) {
			// System.out.println(string.getPath());
			System.out.println(string.getMethodNames());
			System.out.println(string.getLinhas());
//System.out.println(string.getClasses());
		}

		;
	}

=======
import java.io.File;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchEntry.Type;

public class CYCLO_method {

	private static final String FILE_PATH = "C:\\Users\\luisg\\Desktop\\SourceCodeParser.java";
	private List<SwitchEntry> sw;
	private int cyclo = 1;

	public CYCLO_method(CallableDeclaration<?> methods) {
		List<Statement> statements = methods.findAll(Statement.class);
		List<BinaryExpr> binExpressions = methods.findAll(BinaryExpr.class);
		if(!statements.isEmpty() || !binExpressions.isEmpty()) {
			countStatements(statements);
			countBinaryExpressions(binExpressions);
		}
		System.out.println("M�todo " + methods.getNameAsString() + " tem complexidade " + cyclo);
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
	
	public static void main(String[] args) throws Exception {

		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		
		List<ConstructorDeclaration> contructors = cu.findAll(ConstructorDeclaration.class);
		List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);

		for (ConstructorDeclaration c : contructors) {
			new CYCLO_method(c);
		}
		
		for (MethodDeclaration m : methods) {
			new CYCLO_method(m);
		}
	}
	
>>>>>>> refs/remotes/origin/Luis
}