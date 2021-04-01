package Metrics;

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
		System.out.println("Método " + methods.getNameAsString() + " tem complexidade " + cyclo);
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
	
}