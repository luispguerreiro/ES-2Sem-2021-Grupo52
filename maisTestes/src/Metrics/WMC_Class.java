package Metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class WMC_Class {

	private static final String FILE_PATH = "C:\\Users\\luisg\\Desktop\\SourceCodeParser.java";
//	private static final String FILE_PATH = "C:\\Users\\luisg\\Desktop\\ParsingException.java";
	
	private int cycloClass = 0;
	
	public WMC_Class(ClassOrInterfaceDeclaration c) {
		List<ConstructorDeclaration> contructors = c.findAll(ConstructorDeclaration.class);
		List<MethodDeclaration> methods = c.findAll(MethodDeclaration.class);

		for (ConstructorDeclaration cd : contructors) {
//			new CYCLO_method(cd);
			cycloClass += new CYCLO_method(cd).getCyclo();
		}
		
		for (MethodDeclaration m : methods) {
//			new CYCLO_method(m);
			cycloClass += new CYCLO_method(m).getCyclo();
		}
//		cycloClass -= (contructors.size() + methods.size());
		System.out.println("Class: " + c.getNameAsString() + " tem complexidade " + cycloClass);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		List<ClassOrInterfaceDeclaration> classes = cu.findAll(ClassOrInterfaceDeclaration.class);

		for (ClassOrInterfaceDeclaration cs : classes) {
			new WMC_Class(cs);
		}
	}
	
}