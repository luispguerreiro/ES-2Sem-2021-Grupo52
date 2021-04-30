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

/**
 *The metric to be calculated
 * 
 */
public class Metrics {
	private static String FILE_PATH = "C:\\\\Users\\\\r_f_g\\\\Desktop\\\\SourceCodeParser.java";
	private static final String SRC_PATH = "C:\\Users\\r_f_g\\eclipse-workspace\\JparsecTeste";
	private static CompilationUnit cu;
	private List<ClassOrInterfaceDeclaration> clazz;
	private ClassOrInterfaceDeclaration mainClass;
	private List<ClassOrInterfaceDeclaration> nestedClasses;
/** 
 * Class Constructor that turns the filePath into a file and separates the classes into a list
 * @param FILE_PATH the path of the file to make a Metrics object out of.
 * @throws FileNotFoundException in case the file imported isn't found
 * */
	
	public Metrics(String FILE_PATH) throws FileNotFoundException {
		this.FILE_PATH = FILE_PATH;
		this.cu = StaticJavaParser.parse(new File(FILE_PATH));
		this.clazz = cu.findAll(ClassOrInterfaceDeclaration.class);
		listClasses(cu);
	}

	/** Returns the main ClassOrInterfaceDeclaration of the file 
	 * @return the main class*/
	public ClassOrInterfaceDeclaration getMainClass() {
		return mainClass;
	}
	
	/** Returns a list of nested ClassOrInterfaceDeclaration of the file 
	 * @return the list of nested classes*/
	public List<ClassOrInterfaceDeclaration> getNestedClasses() {
		return nestedClasses;
	}
	/** Returns the CompilationUnit of the file 
	 * @return the CompilationUnit*/
	public CompilationUnit getCu() {
		return cu;
	}
	/** Makes a list of the classes that are in the CompilationUnit
	 * @param cu the CompilationUnit
	 */
	private void listClasses(CompilationUnit cu) {
		List<TypeDeclaration<?>> types = cu.getTypes();
		nestedClasses = cu.findAll(ClassOrInterfaceDeclaration.class);
		if (!types.isEmpty() && !types.getClass().isInterface()) {
			for (TypeDeclaration<?> t : types) {

//				System.out.println("Types: " + t.getNameAsString());
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
//				System.out.println("Nested class: " + mainClass.getNameAsString() + "." + nc.getName());
//				new NOM_Class(nested);
			}
		} else {
			nestedClasses.clear();
		}
	}
	/** Getter for the list of classes, clazz
	 * @return list of classOrInterfaceDeclaration of the metric*/
	public List<ClassOrInterfaceDeclaration> getClazz() {
		return clazz;
	}



	/** Getter for the FILE_PATH
	 * @return the path of the file*/
	public static String getFilePath() {
		return FILE_PATH;
	}

}