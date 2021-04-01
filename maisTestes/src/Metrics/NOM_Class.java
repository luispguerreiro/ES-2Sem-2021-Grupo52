package maisTestes;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;

public class NOM_Class {

	private static final String FILE_PATH = "C:\\Users\\luisg\\Desktop\\SourceCodeParser.java";
	private int numOfMethods = 0;
	private ClassOrInterfaceDeclaration c;

	// NOM_class (# métodos p/ class) + WMC_Class (complexidade da classe) + CYCLO_method

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
