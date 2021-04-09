 package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class NOM_Class {

//	private static final String FILE_PATH = "C:\\Users\\luisg\\Desktop\\SourceCodeParser.java";
	private int numOfMethods = 0;
	
	private int i = 1;
	
//	private ArrayList<Integer> empty = new ArrayList<>();
	private int[] empty = new int[5];
	
	ArrayList<Resultado> resultados = new ArrayList<>();

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
			resultados.add(new Resultado(i, pack + "/" + c.getNameAsString() , numOfMethods, empty));
			// System.out.println("Class " + c.getNameAsExpression() + " tem " +
			// numOfMethods + " métodos");

		}
	}

	/*
	 * public NOM_Class(ClassOrInterfaceDeclaration c) {
	 * List<ConstructorDeclaration> contructors = c.getConstructors();
	 * List<MethodDeclaration> methods = c.getMethods(); numOfMethods +=
	 * contructors.size() + methods.size(); System.out.println("Class " +
	 * c.getNameAsExpression() + " tem " + numOfMethods + " métodos");
	 * 
	 * // System.out.println("Métodos: " + m.getDeclarationAsString(false, false, //
	 * true)); }
	 */
	public ArrayList<Resultado> getResultados() {
		return resultados;
	}

	public static void main(String[] args) {
		try {
			NOM_Class a = new NOM_Class(new Metrics("C:\\Users\\r_f_g\\Desktop\\SourceCodeParser.java"));
			for (Resultado string : a.getResultados()) {
				System.out.println(string.getPath());
				System.out.println(string.getLinhas());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
