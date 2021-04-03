package Metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Metrics {

	static int linhasClass;
	int linhasMethod;
	private static String FILE_PATH = "C:\\\\Users\\\\r_f_g\\\\Desktop\\\\SourceCodeParser.java";
	private static final String SRC_PATH = "C:\\Users\\r_f_g\\eclipse-workspace\\JparsecTeste";
	private static CompilationUnit cu;
	private List<ClassOrInterfaceDeclaration> clazz;
	private ClassOrInterfaceDeclaration mainClass;
	private List<ClassOrInterfaceDeclaration> nestedClasses;
	private String s = new String();

	public Metrics(String FILE_PATH) throws FileNotFoundException {
		this.FILE_PATH = FILE_PATH;
		this.cu = StaticJavaParser.parse(new File(FILE_PATH));
		this.clazz = cu.findAll(ClassOrInterfaceDeclaration.class);
		listClasses(cu);
	}

	public ClassOrInterfaceDeclaration getMainClass() {
		return mainClass;
	}

	public List<ClassOrInterfaceDeclaration> getNestedClasses() {
		return nestedClasses;
	}

	public static CompilationUnit getCu() {
		return cu;
	}

	private void listClasses(CompilationUnit cu) {
		List<TypeDeclaration<?>> types = cu.getTypes();
		nestedClasses = cu.findAll(ClassOrInterfaceDeclaration.class);
		if (!types.isEmpty() && !types.getClass().isInterface()) {
			for (TypeDeclaration<?> t : types) {

				System.out.println("Types: " + t.getNameAsString());
				if (t.isClassOrInterfaceDeclaration()) {
					mainClass = (ClassOrInterfaceDeclaration) t;
//					System.out.println("Main Class: " + t.getNameAsString());
//					new NOM_Class(mainClass);
				}
			}
		}
		if (!nestedClasses.isEmpty() && nestedClasses.size() >= 2 && !nestedClasses.getClass().isInterface()) {
			nestedClasses.remove(mainClass);
			for (ClassOrInterfaceDeclaration nc : nestedClasses) {
				System.out.println("Nested class: " + mainClass.getNameAsString() + "." + nc.getName());
//				new NOM_Class(nested);
			}
		} else {
			nestedClasses.clear();
		}
	}

	public List<ClassOrInterfaceDeclaration> getClazz() {
		return clazz;
	}

	public static void main(String[] args) throws ParseException, IOException {
		Metrics m = new Metrics(FILE_PATH);
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

}