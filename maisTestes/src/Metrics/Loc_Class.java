package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;

public class Loc_Class {
	private static final String FILE_PATH = "C:\\\\Users\\\\r_f_g\\\\Desktop\\\\SourceCodeParser.java";
	private int linhasClass;

	private String s = new String();
	ArrayList<String> resultados = new ArrayList<>();

	public Loc_Class(Metrics m) {

		CompilationUnit cu2 = m.getCu();

		List<TypeDeclaration> nodes = cu2.findAll(TypeDeclaration.class);
		// System.out.println(nodes.size());
		for (TypeDeclaration n : nodes) {
			// System.out.println();
			// System.out.println(nodes.size());
			if (n.isClassOrInterfaceDeclaration()) {
				// System.out.println();
				int inicio = n.getBegin().get().line;
				int fim = n.getEnd().get().line;
				linhasClass = fim - inicio;
				s = n.getNameAsString();
				// System.out.println("O nome da classe �: " + n.getNameAsString());
				// System.out.println("o numero de linhas da classe �: " + linhasClass);
				resultados.add(s);
				resultados.add(Integer.toString(linhasClass));
				// System.out.println(statements.size() + binExpressions.size());
				// System.out.println(statements.size());
				// System.out.println(binExpressions.size());
			}
		}
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