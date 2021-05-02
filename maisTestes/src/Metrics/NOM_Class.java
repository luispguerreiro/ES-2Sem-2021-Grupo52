 package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
/**
 * Calculates the number of the methods of the Metrics object imported
 * 
 */
public class NOM_Class {
	/**
	 * @author Grupo52
	 * 
	 */
	private static final String FILE_PATH = "C:\\Users\\r_f_g\\Desktop\\SourceCodeParser.java";
	private int numOfMethods = 0;
	
	private int i = 1;
	
//	private ArrayList<Integer> empty = new ArrayList<>();
	private int[] empty = new int[5];
	
	ArrayList<Resultado> resultados = new ArrayList<>();

	
	/**
	 * Calculates the number of methods of all the classes with comments on the Metrics object and empty spaces
	 * and adds the result to an array of Resultados with the Name of the package and the class.
	 * 
	 * @param m the object Metrics chosen
	 */

	
	public NOM_Class(Metrics m) {
		String pack = m.getCu().getPackageDeclaration().toString();
		ClassOrInterfaceDeclaration mainClass = m.getMainClass();
		numOfMethods += mainClass.getConstructors().size() + mainClass.getMethods().size();
		//resultados.add(mainClass.getNameAsString());
	//	resultados.add(Integer.toString(numOfMethods));
		resultados.add(new Resultado(i, pack + "/" + mainClass.getNameAsString() , numOfMethods, empty));
		// System.out.println(mainClass.getNameAsString());
		// System.out.println(numOfMethods);
		numOfMethods = 0;
		List<ClassOrInterfaceDeclaration> clazz = m.getNestedClasses();
		for (ClassOrInterfaceDeclaration c : clazz) {
			List<ConstructorDeclaration> contructors = c.getConstructors();
			List<MethodDeclaration> methods = c.getMethods();
			numOfMethods += contructors.size() + methods.size();
			//resultados.add(c.getNameAsString());
		//	resultados.add(Integer.toString(numOfMethods));
			resultados.add(new Resultado(i, pack + "/" + mainClass.getNameAsString() +"."+ c.getNameAsString() , numOfMethods, empty));
			// System.out.println("Class " + c.getNameAsExpression() + " tem " +
			// numOfMethods + " métodos");

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
