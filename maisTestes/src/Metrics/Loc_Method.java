package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;

public class Loc_Method {
	public ArrayList<Resultado> resultados = new ArrayList<>();
	private static final String FILE_PATH = "C:\\Users\\r_f_g\\Desktop\\SourceCodeParser.java";
	static int linhasfinal = 0;

	public Loc_Method(Metrics m) {
		CompilationUnit cu = m.getCu();
		String pack = cu.getPackageDeclaration().toString();
		ClassOrInterfaceDeclaration mainClass = m.getMainClass();
		List<CallableDeclaration> mainClassMet = mainClass.findAll(CallableDeclaration.class);
		List<String> Resultados = new ArrayList<>();
		List<TypeDeclaration> nodes = cu.findAll(TypeDeclaration.class);
		for (CallableDeclaration callableDeclaration : mainClassMet) {
			String mainClassName = mainClass.getNameAsString();
			List<Parameter> par = callableDeclaration.getParameters();
			String parameters = "";
			if (!par.isEmpty()) {
				for (Parameter p : par) {
					parameters += p.getType().toString();
					if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
						parameters += ",";
					}
				}
			}
			int inicio = callableDeclaration.getBegin().get().line;
			int fim = callableDeclaration.getEnd().get().line + 1;
			int linhasMethod = fim - inicio;
			resultados.add(new Resultado(
					pack + "/" + mainClassName + "/" + callableDeclaration.getNameAsString() + "(" + parameters + ")",
					linhasMethod, false));

		}

		List<ClassOrInterfaceDeclaration> nestedClasses = m.getNestedClasses();
		for (ClassOrInterfaceDeclaration nestClass : nestedClasses) {
			String NestClassNames = nestClass.getNameAsString();
			List<CallableDeclaration> contructorsNestClass = nestClass.findAll(CallableDeclaration.class);
			for (CallableDeclaration c : contructorsNestClass) {
				List<Parameter> par = c.getParameters();
				String parameters = "";
				if (!par.isEmpty()) {
					for (Parameter p : par) {
						parameters += p.getType().toString();
						if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
							parameters += ",";
						}

					}
				}
				int inicio = nestClass.getBegin().get().line;
				int fim = nestClass.getEnd().get().line + 1;
				int linhasMethod = fim - inicio;

				resultados.add(new Resultado(
						pack + "/" + NestClassNames + "/" + c.getNameAsString() + "(" + parameters + ")" + "/",
						linhasMethod, false));

			}
		}

	}

	public ArrayList<Resultado> getResultados() {
		return resultados;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Loc_Method a = new Loc_Method(new Metrics(FILE_PATH));
		System.out.println(a.getResultados().size());
		for (Resultado string : a.getResultados()) {
			// System.out.println(string.getPath());
			System.out.println(string.getLinhas());
			System.out.println(string.getMethodNames());
		}
	}
}
