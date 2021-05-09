package metrics;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.Parameter;
/**
 * Calculates the number of lines of each method on the Metrics object imported
 * 
 */
public class Loc_Method {
	/**
	 * @author Grupo52
	 * 
	 */
	public ArrayList<Resultado> resultados = new ArrayList<>();
	static int linhasfinal = 0;
	private int[] empty = new int[5];
	private int i = 1;
	private CompilationUnit cu;
	private String pack;
	private ClassOrInterfaceDeclaration mainClass;
	private String mainClassName;
	@SuppressWarnings("rawtypes")
	private List<CallableDeclaration> mainClassMet;
	private Metrics m;
	private List<ClassOrInterfaceDeclaration> nestedClasses;

	/**
	 * Calculates the number of lines of a method on the Metrics object with comments and empty spaces
	 * and adds the result to an array of Resultados with the Name of the package, the class and method.
	 * 
	 * @param m the object Metrics chosen
	 */

	public Loc_Method(Metrics m) {
		this.m = m;
		cu = m.getCu();
		pack = cu.getPackageDeclaration().toString();
		mainClass = m.getMainClass();
		mainClassMet = mainClass.findAll(CallableDeclaration.class);
		mainClassName = mainClass.getNameAsString();
		nestedClasses = m.getNestedClasses();
		runMainClass();
		runNestedClasses();
	}

	private void runMainClass() {
		for (CallableDeclaration<?> callableDeclaration : mainClassMet) {
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
				int inicio = callableDeclaration.getBegin().get().line;
				int fim = callableDeclaration.getEnd().get().line + 1;
				int linhasMethod = fim - inicio;
				resultados.add(new Resultado(i, pack + "/" + mainClassName + "/" + callableDeclaration.getNameAsString()
						+ "(" + parameters + ")", linhasMethod, empty));
			}
		}
	}

	private void runNestedClasses() {
		for (ClassOrInterfaceDeclaration nestClass : nestedClasses) {
			String NestClassNames = nestClass.getNameAsString();
			@SuppressWarnings("rawtypes")
			List<CallableDeclaration> contructorsNestClass = nestClass.findAll(CallableDeclaration.class);
			for (CallableDeclaration<?> c : contructorsNestClass) {
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
				int inicio = c.getBegin().get().line;
				int fim = c.getEnd().get().line + 1;
				int linhasMethod = fim - inicio;
				resultados.add(new Resultado(i, pack + "/" + mainClassName + "." + NestClassNames + "/"
						+ c.getNameAsString() + "(" + parameters + ")" + "/", linhasMethod, empty));
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