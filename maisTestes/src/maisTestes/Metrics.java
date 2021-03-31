package maisTestes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Metrics {
	// private static final String SRC_PATH =
	// "C:\\Users\\r_f_g\\eclipse-workspace\\Es Teste\\src";
	static int linhasClass;
	int linhasMethod;
	private static final String FILE_PATH = "C:\\Users\\r_f_g\\eclipse-workspace\\JparsecTeste\\Snippet.java";
	private static final String SRC_PATH = "C:\\Users\\r_f_g\\eclipse-workspace\\JparsecTeste";
	private static CompilationUnit cu = null;
	List<String> collector = new ArrayList<>();

	String s = new String();

	public static void main(String[] args) throws ParseException, IOException {
		Metrics m = new Metrics();
		// CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		// List<String> methodNamesLines = new ArrayList<>();
		// List<String> ClassLines = new ArrayList<>();
		m.locClass(FILE_PATH);
		m.locMethod(FILE_PATH);

	}
	

	public void locClass(String FilePath) throws FileNotFoundException {

		CompilationUnit cu2 = StaticJavaParser.parse(new File(FilePath));

		List<TypeDeclaration> nodes = cu2.findAll(TypeDeclaration.class);

		for (TypeDeclaration n : nodes) {

			if (n.isClassOrInterfaceDeclaration()) {
				int inicio = n.getBegin().get().line;
				int fim = n.getEnd().get().line;
				linhasClass = fim - inicio;
				s = n.getNameAsString();
				System.out.println("O nome da classe é: " + n.getNameAsString());
				System.out.println("o numero de linhas da classe é: " + linhasClass);
			}
		}
	}

	public void locMethod(String FilePath) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(new File(FilePath));
		List<String> methodNamesLines = new ArrayList<>();
		new Loc_Method().visit(cu, methodNamesLines);
		List<TypeDeclaration> nodes = cu.findAll(TypeDeclaration.class);
		// for (int i = 0; i < nodes.size(); i++) {
		// TypeDeclaration n = nodes.get(i);
		for (TypeDeclaration n : nodes) {
			for (int i = 0; i < n.getMembers().size(); i++) {
				if (n.getMember(i).isConstructorDeclaration()) {
					int inicio = n.getMember(i).getBegin().get().line;
					int fim = n.getMember(i).getEnd().get().line;
					int linhasMethod = fim - inicio;
					methodNamesLines.add(n.getNameAsString());
					methodNamesLines.add(Integer.toString(linhasMethod));
					System.out.println("construtor:" + n.getName());
					System.out.println(linhasMethod);
				}

			}
		}

	}

	public static int getLinhasClass() {
		return linhasClass;
	}

	public String getS() {
		return s;
	}

	public static String getFilePath() {
		return FILE_PATH;
	}

	public static class Loc_Method extends VoidVisitorAdapter<List<String>> {

		@Override
		public void visit(MethodDeclaration m, List<String> collector) {
			super.visit(m, collector);
			List<TypeDeclaration> nodes = m.findAll(TypeDeclaration.class);

			int inicio = m.getBegin().get().line;
			int fim = m.getEnd().get().line;
			int linhasMethod = fim - inicio;
			System.out.println("Method " + m.getNameAsString());
			System.out.println("- " + Integer.toString(linhasMethod));
			collector.add(m.getNameAsString());
			collector.add(Integer.toString(linhasMethod));

		}

	}

}