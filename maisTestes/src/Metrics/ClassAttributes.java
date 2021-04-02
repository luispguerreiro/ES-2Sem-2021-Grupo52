package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;

public class ClassAttributes {

	private ClassOrInterfaceDeclaration clazz;
//	private String clazz;
	private List<ConstructorDeclaration> constructors;
	private List<MethodDeclaration> methods;
//	private List<String> constructorsName = new ArrayList<>();
	private List<String> methodsName = new ArrayList<>();
	private static final String FILE_PATH = "C:\\Users\\luisg\\Desktop\\SourceCodeParser.java";

	public ClassAttributes(ClassOrInterfaceDeclaration clazz) {
		this.clazz = clazz;
		listConstructors(clazz);
		listMethods(clazz);
	}

	private void listConstructors(ClassOrInterfaceDeclaration c) {
		this.constructors = c.getConstructors();
		if (!constructors.isEmpty()) {
			for (ConstructorDeclaration cd : constructors) {
				List<Parameter> par = cd.getParameters();
				String parameters = "";
				if (!par.isEmpty()) {
					for (Parameter p : par) {
//					System.out.println("Nome do parametro: " + p.getType().toString());
						parameters += p.getType().toString();
						if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
							parameters += ",";
						}
					}
				}
				String constructorWithParameters = c.getNameAsString() + "(" + parameters + ")";
				this.methodsName.add(constructorWithParameters);
//				System.out.println("Métodos: " + constructorWithParameters);
			}
		}
	}

	private void listMethods(ClassOrInterfaceDeclaration c) {
		this.methods = c.getMethods();
		if (!methods.isEmpty()) {
			for (MethodDeclaration m : methods) {
				List<Parameter> par = m.getParameters();
				String parameters = "";
				if (!par.isEmpty()) {
					for (Parameter p : par) {
//					System.out.println("Nome do parametro: " + p.getType().toString());
						parameters += p.getType().toString();
						if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
							parameters += ",";
						}
					}
				}
				String methodsWithParameters = m.getNameAsString() + "(" + parameters + ")";
				this.methodsName.add(methodsWithParameters);
//				System.out.println("Métodos: " + methodsWithParameters);
			}
		}
	}

	public List<String> getMethodsName() {
		return methodsName;
	}
	
	public ClassOrInterfaceDeclaration getClazz() {
		return clazz;
	}

	public static void main(String[] args) throws FileNotFoundException {
//		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
//		cu.findAll(ClassOrInterfaceDeclaration.class).forEach(c -> new CodeAttributes(c));
	}

}
