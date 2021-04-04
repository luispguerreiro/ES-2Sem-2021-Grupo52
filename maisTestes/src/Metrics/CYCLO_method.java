package Metrics;

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
	List<CallableDeclaration> contructors;
	private String pack;

	public CYCLO_method(Metrics m) {
		ClassOrInterfaceDeclaration mainClass = m.getMainClass();
		 pack = m.getCu().getPackageDeclaration().toString();
		List<ClassOrInterfaceDeclaration> nestedClasses = m.getNestedClasses();
		this.contructors = mainClass.findAll(CallableDeclaration.class);
		for (ClassOrInterfaceDeclaration nestClass : nestedClasses) {

			List<CallableDeclaration> contructorsNestClass = nestClass.findAll(CallableDeclaration.class);
			for (CallableDeclaration C : contructorsNestClass) {
				contructors.add(C);
			}
		}
	}

	public void Resolve() {
		for (CallableDeclaration callableDeclaration : contructors) {
			
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
				
			
			resultados.add(new Resultado(pack + "/"+callableDeclaration.findCompilationUnit()+callableDeclaration.getNameAsString()+"("+parameters+")" , cyclo,false));
			//resultados.add(callableDeclaration.getNameAsString());
			//resultados.add(Integer.toString(cyclo));

			//System.out.println("M�todo " + callableDeclaration.getNameAsString() + " tem complexidade " + cyclo);
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
	
	public ArrayList<Resultado> getResultados() {
		return resultados;
	}
	public static void main(String[] args) throws Exception {

		// CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

		CYCLO_method a =  new CYCLO_method(new Metrics(FILE_PATH));
		a.Resolve();
		for (Resultado string : a.getResultados()) {
			
			System.out.println(string.getPath());
			System.out.println(string.getLinhas());
		}
		
		
		;
	}

}