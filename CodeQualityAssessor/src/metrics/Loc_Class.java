package metrics;

import java.util.ArrayList;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

/**
 * Calculates the number of lines of each class on the Metrics object imported
 * 
 */
public class Loc_Class {
	/**
	 * @author Grupo52
	 * 
	 */
	private int linhasClass;
	private int linhasClassMain;
	private int i = 1;
	ArrayList<Resultado> resultados = new ArrayList<>();
	private int[] empty = new int[5];

	/**
	 * Calculates the number of lines of all the class with comments on the Metrics
	 * object and empty spaces and adds the result to an array of Resultados with
	 * the Name of the package and the class.
	 * 
	 * @param m the object Metrics chosen
	 */

	public Loc_Class(Metrics m) {
		CompilationUnit cu2 = m.getCu();
		String pack = cu2.getPackageDeclaration().toString();
		ClassOrInterfaceDeclaration mainClass = m.getMainClass();
		String mainClassName = mainClass.getNameAsString();
		int iniciomain = mainClass.getBegin().get().line;
		int fimmain = mainClass.getEnd().get().line;
		linhasClassMain = fimmain - iniciomain;
		resultados.add(new Resultado(i, pack + "/" + mainClassName, linhasClassMain, empty));

		for (ClassOrInterfaceDeclaration nestClass : m.getNestedClasses()) {
			String NestClassNames = nestClass.getNameAsString();
			int inicio = nestClass.getBegin().get().line;
			int fim = nestClass.getEnd().get().line;
			linhasClass = fim - inicio;
			linhasClassMain = linhasClassMain - linhasClass;
			resultados.add(new Resultado(i, pack + "/" + mainClassName + "." + NestClassNames, linhasClass, empty));

		}
		resultados.remove(0);
		resultados.add(0, new Resultado(i, pack + "/" + mainClassName, linhasClassMain, empty));
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