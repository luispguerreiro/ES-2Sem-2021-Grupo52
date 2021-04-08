package Metrics;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class NOM_Class {

//	private static final String FILE_PATH = "C:\\Users\\luisg\\Desktop\\SourceCodeParser.java";
	private int numOfMethods = 0;

	public NOM_Class(ClassOrInterfaceDeclaration c) {
		List<ConstructorDeclaration> contructors = c.getConstructors();
		List<MethodDeclaration> methods = c.getMethods();
		numOfMethods += contructors.size() + methods.size();
		System.out.println("Class " + c.getNameAsExpression() + " tem " + numOfMethods + " métodos");

		// System.out.println("Métodos: " + m.getDeclarationAsString(false, false, true));
	}
	
	
	
	

//	public static void main(String[] args) {
//		try {
//			new NOM_Class();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

}
