package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
/**
 * Calculates the number of lines of each class on the Metrics object imported
 * 
 */
public class Loc_Class {
	/**
	 * @author Grupo52
	 * 
	 */
	private static final String FILE_PATH = "C:\\\\Users\\\\r_f_g\\\\Desktop\\\\SourceCodeParser.java";
	private int linhasClass;
	private int linhasClassMain;
	private int i = 1;
	private String s = new String();
	ArrayList<Resultado> resultados = new ArrayList<>();
	
//	private ArrayList<Integer> empty = new ArrayList<>();
	private int[] empty = new int[5];

	
	/**
	 * Calculates the number of lines of all the class with comments on the Metrics object and empty spaces
	 * and adds the result to an array of Resultados with the Name of the package and the class.
	 * 
	 * @param m the object Metrics chosen
	 */

	public Loc_Class(Metrics m) {

	

		CompilationUnit cu2 = m.getCu();
		String pack = cu2.getPackageDeclaration().toString();
		ClassOrInterfaceDeclaration mainClass = m.getMainClass();
		List<TypeDeclaration> nodes = cu2.findAll(TypeDeclaration.class);
		List<CallableDeclaration> mainClassMet = mainClass.findAll(CallableDeclaration.class);

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
			resultados.add(new Resultado(i, pack + "/"+mainClassName+"." + NestClassNames, linhasClass, empty));

		}
		resultados.remove(0);
		resultados.add(0, new Resultado(i, pack + "/" + mainClassName, linhasClassMain, empty));

		/*
		 * 
		 * 
		 * // System.out.println(nodes.size()); for (TypeDeclaration n : nodes) { //
		 * System.out.println(); // System.out.println(nodes.size()); if
		 * (n.isClassOrInterfaceDeclaration()) { // System.out.println(); int inicio =
		 * n.getBegin().get().line; int fim = n.getEnd().get().line; linhasClass = fim -
		 * inicio; s = n.getNameAsString(); // System.out.println("O nome da classe �: "
		 * + n.getNameAsString()); //
		 * System.out.println("o numero de linhas da classe �: " + linhasClass);
		 * //resultados.add(s); //resultados.add(Integer.toString(linhasClass));
		 * resultados.add(new Resultado(pack + "/" + s , linhasClass,false)); //
		 * System.out.println(statements.size() + binExpressions.size()); //
		 * System.out.println(statements.size()); //
		 * System.out.println(binExpressions.size()); } }
		 */
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
