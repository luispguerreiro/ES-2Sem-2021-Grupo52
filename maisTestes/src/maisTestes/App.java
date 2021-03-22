package maisTestes;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class App {
	// private static final String SRC_PATH =
	// "C:\\Users\\r_f_g\\eclipse-workspace\\Es Teste\\src";
	private static final String FILE_PATH = "C:\\Users\\r_f_g\\eclipse-workspace\\JparsecTeste\\Snippet.java";
	private static final String SRC_PATH = "C:\\Users\\r_f_g\\eclipse-workspace\\JparsecTeste";
	private static CompilationUnit cu = null;

	public static void main(String[] args) throws ParseException, IOException {
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		List<String> methodNamesLines = new ArrayList<>();
		List<String> ClassLines = new ArrayList<>();
		new Loc_Method().visit(cu, methodNamesLines);
		new Loc_Class().visit(cu, ClassLines);
		

	}
	
	
	private static class Loc_Method extends VoidVisitorAdapter<List<String>> {
		@Override
		public void visit(MethodDeclaration m, List<String> collector) {
			super.visit(m, collector);
			int inicio = m.getBegin().get().line;
			int fim = m.getEnd().get().line;
			int linhas = fim - inicio;
			System.out.println(m.getNameAsString());
			System.out.println(Integer.toString(linhas));
			collector.add(m.getNameAsString());
			collector.add(Integer.toString(linhas));

		}
		
	}
	
	private static class Loc_Class extends VoidVisitorAdapter<List<String>> {
		@Override
		public void visit(AnnotationDeclaration n, List<String> arg) {
			super.visit(n, arg);
		}
	}
	
}