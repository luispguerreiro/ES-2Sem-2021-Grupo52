package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

public class Loc_Class {
	private static final String FILE_PATH = "C:\\\\Users\\\\r_f_g\\\\Desktop\\\\SourceCodeParser.java";
	private int linhasClass;

	private String s = new String();
	ArrayList<String> resultados = new ArrayList<>();

	public Loc_Class(Metrics m) {
		ClassOrInterfaceDeclaration mainClass = m.getMainClass();
		List<ClassOrInterfaceDeclaration> nestedClasses = m.getNestedClasses();
		CompilationUnit cu2 = m.getCu();
		int inicioMain = cu2.getPackageDeclaration().get().getBegin().get().line;
		int fimMain = mainClass.getEnd().get().line;
		for (ClassOrInterfaceDeclaration n : nestedClasses) {
				// System.out.println();
				// System.out.println(nodes.size());
				
					int inicio = n.getBegin().get().line;
					int fim = n.getEnd().get().line;
					linhasClass = fim - inicio;
					s = n.getNameAsString();
					// System.out.println("O nome da classe é: " + n.getNameAsString());
					// System.out.println("o numero de linhas da classe é: " + linhasClass);
					resultados.add(s);
					resultados.add(Integer.toString(linhasClass));
					// System.out.println(statements.size() + binExpressions.size());
					// System.out.println(statements.size());
					// System.out.println(binExpressions.size());
				fimMain -= linhasClass;
		}
		resultados.add(mainClass.getNameAsString());
		resultados.add(Integer.toString(fimMain-inicioMain));
		
/*
		List<TypeDeclaration> nodes = cu2.findAll(TypeDeclaration.class);
		// System.out.println(nodes.size());
		for (TypeDeclaration n : nodes) {
			// System.out.println();
			// System.out.println(nodes.size());
			if (n.isClassOrInterfaceDeclaration()) {
				// System.out.println();
				n.asClassOrInterfaceDeclaration();
				int inicio = cu2.getPackageDeclaration().get().getBegin().get().line;
				System.out.println(inicio);
				//int inicio = n.getBegin().get().line;
				int fim = n.getEnd().get().line;
				linhasClass = fim - inicio;
				s = n.getNameAsString();
				// System.out.println("O nome da classe é: " + n.getNameAsString());
				// System.out.println("o numero de linhas da classe é: " + linhasClass);
				resultados.add(s);
				resultados.add(Integer.toString(linhasClass));
				// System.out.println(statements.size() + binExpressions.size());
				// System.out.println(statements.size());
				// System.out.println(binExpressions.size());
			}
		}*/
	}

	public ArrayList<String> getResultados() {
		return resultados;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Loc_Class a = new Loc_Class(new Metrics(FILE_PATH));
		for (String string : a.getResultados()) {
			System.out.println(string);

		}
	}
}
