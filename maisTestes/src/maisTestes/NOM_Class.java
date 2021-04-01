package maisTestes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;

public class NOM_Class {

	private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\SourceCodeParser.java";
	private int numOfMethods = 0;
	private ClassOrInterfaceDeclaration c;

	private ClassOrInterfaceDeclaration mainClass;
	private List<ClassOrInterfaceDeclaration> nestedClasses;

	// NOM_class (# métodos p/ class) + WMC_Class (complexidade da classe) +
	// CYCLO_method

	public NOM_Class(ClassOrInterfaceDeclaration c) {
		this.c = c;
		List<ConstructorDeclaration> contructors = c.getConstructors();
		List<MethodDeclaration> methods = c.getMethods();
		numOfMethods += contructors.size() + methods.size();
		System.out.println("Class " + c.getNameAsExpression() + " tem " + numOfMethods + " métodos");

		if (!contructors.isEmpty() || !methods.isEmpty()) {
			for (ConstructorDeclaration cs : contructors) {
				List<Parameter> par = cs.getParameters();
				String parameters = "";
				if (!par.isEmpty()) {
					for (Parameter p : par) {
//						System.out.println("Nome do parametro: " + p.getType().toString());
						parameters += p.getType().toString();
						if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
							parameters += ",";
						}
					}
				}
				System.out.println("Métodos: " + cs.getNameAsString() + "(" + parameters + ")");
			}
			for (MethodDeclaration m : methods) {
				List<Parameter> par = m.getParameters();
				String parameters = "";
				if (!par.isEmpty()) {
					for (Parameter p : par) {
//						System.out.println("Nome do parametro: " + p.getType().toString());
						parameters += p.getType().toString();
						if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
							parameters += ",";
						}
					}
				}
				System.out.println("Métodos: " + m.getNameAsString() + "(" + parameters + ")");
			}

		}

		// System.out.println("Métodos: " + m.getDeclarationAsString(false, false,
		// true));
	}

	public int getNumOfMethods() {
		return numOfMethods;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ClassOrInterfaceDeclaration mainClass;
		CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(FILE_PATH));
		List<TypeDeclaration<?>> types = cu.getTypes();
		if (!types.isEmpty()) {
			for (TypeDeclaration<?> t : types) {
				if (t.isClassOrInterfaceDeclaration()) {
					mainClass = (ClassOrInterfaceDeclaration) t;
					System.out.println("Main Class: " + t.getNameAsString());
					new NOM_Class(mainClass);
				}
			}
		}
	}
}
