package Metrics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

public class Main {

	private static final String FILE_PATH = "C:\\Users\\luisg\\Desktop\\SourceCodeParser.java";
	private ClassOrInterfaceDeclaration mainClass;
	private List<ClassOrInterfaceDeclaration> nestedClasses;
	
	public Main(CompilationUnit cu) {
		listClasses(cu);
		ClassAttributes mainClassAttributes = new ClassAttributes(mainClass);
		for (String mainClassMethods : mainClassAttributes.getMethodsName()) {
			System.out.println(mainClassMethods);
		}
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
		}
		else {
			nestedClasses.clear();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(FILE_PATH));		
		Main m = new Main(cu);
	}

}
