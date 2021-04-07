package luis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchEntry.Type;

public class Metrics {

	private String classPackage, className;
	private ClassOrInterfaceDeclaration mainClass;
	private List<ClassOrInterfaceDeclaration> nestedClasses;
	private List<Metrics> classMetrics = new ArrayList<>();
	private List<CallableDeclaration<?>> methods = new ArrayList<>();
	private List<Integer> LOC_Method_Results = new ArrayList<>();
	private List<Integer> CYCLO_Method_Results = new ArrayList<>();
	private List<String> methodsName = new ArrayList<>();
	private int numOfMethods = 0;
	private int CYCLO_Class = 0;
	private int LOC_Class;

	public Metrics(String FILE_PATH) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(FILE_PATH));
		classPackage = cu.getPackageDeclaration().get().getNameAsString();
//		System.out.println(classPackage);

		classMetrics.add(this);
		listClasses(cu);
		NOM_Class(mainClass);
		
		for (CallableDeclaration<?> method : methods) {
			LOC_Method(method);
			CYCLO_Method(method);
		}
		
		LOC_Class(mainClass);
		WMC_Class(CYCLO_Method_Results);

		if (!nestedClasses.isEmpty()) {
			for (ClassOrInterfaceDeclaration nested : nestedClasses) {
				classMetrics.add(new Metrics(nested, className));
			}
		}
	}

	private Metrics(ClassOrInterfaceDeclaration nestedClass, String mainClassName) {
		classPackage = nestedClass.findCompilationUnit().get().getPackageDeclaration().get().getNameAsString();
		className = mainClassName + "." + nestedClass.getNameAsString();
		NOM_Class(nestedClass);
		for (CallableDeclaration<?> method : methods) {
			LOC_Method(method);
			CYCLO_Method(method);
		}
		LOC_Class(nestedClass);
		WMC_Class(CYCLO_Method_Results);
	}

	private void listClasses(CompilationUnit cu) {
		nestedClasses = cu.findAll(ClassOrInterfaceDeclaration.class);
		if (!nestedClasses.isEmpty() && nestedClasses.size() >= 2 && !nestedClasses.getClass().isInterface()) {
			mainClass = nestedClasses.get(0);
			nestedClasses.remove(mainClass);
		} else {
			mainClass = nestedClasses.get(0);
		}
		className = mainClass.getNameAsString();
	}

	private void NOM_Class(ClassOrInterfaceDeclaration c) {
//		List<ConstructorDeclaration> contructors = c.getConstructors();
//		List<MethodDeclaration> methods = c.getMethods().;
		c.getConstructors().forEach(cd -> methods.add(cd));
		c.getMethods().forEach(m -> methods.add(m));
		listMethods(methods);
		numOfMethods += methods.size();
//		System.out.println("Class " + className + " tem " + numOfMethods + " métodos");
	}

	private void listMethods(List<CallableDeclaration<?>> methods) {
		if (!methods.isEmpty()) {
			for (CallableDeclaration<?> m : methods) {
				List<Parameter> par = m.getParameters();
				String parameters = "";
				if (!par.isEmpty()) {
					for (Parameter p : par) {
						parameters += p.getType().toString();
						if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
							parameters += ",";
						}
					}
				}
				String methodsWithParameters = m.getNameAsString() + "(" + parameters + ")";
				methodsName.add(methodsWithParameters);
			}
		}
	}

	private void LOC_Method(CallableDeclaration<?> method) {
		int begin = method.getBegin().get().line;
		int end = method.getEnd().get().line + 1;
		int methodLines = end - begin;
//		System.out.println("Método " + method.getName() + " tem " + methodLines + " linhas");
		LOC_Method_Results.add(methodLines);
	}

	private void LOC_Class(ClassOrInterfaceDeclaration c) {
		int begin = c.getBegin().get().line;
		int end = c.getEnd().get().line;
		LOC_Class = end - begin;
//		System.out.println("Linhas da class " + LOC_Class);
	}

	private void WMC_Class(List<Integer> CYCLO_Results) {
		
		for (Integer i : CYCLO_Results) {
			CYCLO_Class += i;
		}
//		System.out.println("Complexidade Class " + className + " = " + CYCLO_Class);
	}

	private void CYCLO_Method(CallableDeclaration<?> method) {
		int cyclo = 1;
		List<Statement> statements = method.findAll(Statement.class);
		List<BinaryExpr> binExpressions = method.findAll(BinaryExpr.class);
		if (!statements.isEmpty()) {
			cyclo += countStatements(statements);
		}
		if (!binExpressions.isEmpty()) {
			cyclo += countBinaryExpressions(binExpressions);
		}
		CYCLO_Method_Results.add(cyclo);
//		System.out.println("Complexidade método " + method.getName() + " = " + cyclo);
	}

	private int countStatements(List<Statement> statements) {
		List<SwitchEntry> sw;
		int count = 0;
		for (Statement s : statements) {
			if (s.isForEachStmt() || s.isForStmt() || s.isIfStmt() || s.isWhileStmt()) {
				count++;
			}
			if (s.isSwitchStmt()) {
				sw = s.findAll(SwitchEntry.class);
				for (SwitchEntry sws : sw) {
					if (sws.getType().equals(Type.STATEMENT_GROUP) && sws.getLabels().isNonEmpty()) {
						count++;
					}
				}
			}
		}
		return count;
	}

	private int countBinaryExpressions(List<BinaryExpr> binExpressions) {
		int count = 0;
		for (BinaryExpr e : binExpressions) {
			if (e.getOperator().equals(Operator.AND) || e.getOperator().equals(Operator.OR)) {
				count++;
			}
		}
		return count;
	}

	public String getClassPackage() {
		return classPackage;
	}

	public String getClassName() {
		return className;
	}

	public List<Metrics> getClassMetrics() {
		return classMetrics;
	}

	public List<CallableDeclaration<?>> getMethods() {
		return methods;
	}

	public List<Integer> getLOC_Method_Results() {
		return LOC_Method_Results;
	}

	public List<Integer> getCYCLO_Method_Results() {
		return CYCLO_Method_Results;
	}

	public List<String> getMethodsName() {
		return methodsName;
	}

	public int getNumOfMethods() {
		return numOfMethods;
	}

	public int getCYCLO_Class() {
		return CYCLO_Class;
	}

	public int getLOC_Class() {
		return LOC_Class;
	}
	
}
