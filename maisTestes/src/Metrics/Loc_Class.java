package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

public class Loc_Class {
	private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\SourceCodeParser.java";
	private int linhasClass;
	private int linhasClassMain;
	private int i = 1;
	private String s = new String();
	ArrayList<Resultado> resultados = new ArrayList<>();
	
//	private ArrayList<Integer> empty = new ArrayList<>();
	private int[] empty = new int[5];

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
			resultados.add(new Resultado(i, pack + "/" + NestClassNames, linhasClass, empty));

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
		 * inicio; s = n.getNameAsString(); // System.out.println("O nome da classe é: "
		 * + n.getNameAsString()); //
		 * System.out.println("o numero de linhas da classe é: " + linhasClass);
		 * //resultados.add(s); //resultados.add(Integer.toString(linhasClass));
		 * resultados.add(new Resultado(pack + "/" + s , linhasClass,false)); //
		 * System.out.println(statements.size() + binExpressions.size()); //
		 * System.out.println(statements.size()); //
		 * System.out.println(binExpressions.size()); } }
		 */
	}

	public ArrayList<Resultado> getResultados() {
		return resultados;
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		
		Loc_Class a = new Loc_Class(new Metrics(FILE_PATH));
		for (Resultado string : a.getResultados()) {
			// System.out.println(string.getPath());
			System.out.println(string.getLinhas());
			System.out.println(string.getClasses());
			System.out.println(string.getMethodID());
		}
	}
}
