package Metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Loc_Method {
	public ArrayList<Resultado> resultados = new ArrayList<>();
	private static final String FILE_PATH = "C:\\Users\\r_f_g\\Desktop\\SourceCodeParser.java";
	static int linhasfinal = 0;

	public Loc_Method(Metrics m) {
		CompilationUnit cu = m.getCu();
		String pack = cu.getPackageDeclaration().toString();

		List<String> Resultados = new ArrayList<>();
		List<TypeDeclaration> nodes = cu.findAll(TypeDeclaration.class);

		for (TypeDeclaration n : nodes) {
			ClassOrInterfaceDeclaration classe = (ClassOrInterfaceDeclaration) n;
			String className = classe.getNameAsString();
			List<ConstructorDeclaration> constructors = classe.getConstructors();
			List<MethodDeclaration> methods = classe.getMethods();
			for (ConstructorDeclaration cs : constructors) {
				int inicio = cs.getBegin().get().line;
				int fim = cs.getEnd().get().line + 1;
				int linhasMethod = fim - inicio;
				List<Parameter> par = cs.getParameters();
				String parameters = "";
				if (!par.isEmpty()) {
					for (Parameter p : par) {
						parameters += p.getType().toString();
						if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
							parameters += ",";
						}
					}
				} else {
				}
				
				resultados.add(new Resultado(pack + "/"+className + "/"+ cs.getNameAsString() + "(" + parameters + ")", linhasMethod,false));
			}
			for (MethodDeclaration md : methods) {
				
				int inicio = md.getBegin().get().line;
				int fim = md.getEnd().get().line + 1;
				int linhasMethod = fim - inicio;
				List<Parameter> par = md.getParameters();
				String parameters = "";
				if (md.getParameters().isEmpty()) {
					resultados.add(new Resultado(pack + "/"+className + "/"+ md.getNameAsString() + "(" + parameters + ")", linhasMethod,false));
					//resultados.add(md.getNameAsString() + "(" + parameters + ")");
					//resultados.add(Integer.toString(linhasMethod));
				}
				for (Parameter p : par) {
					parameters += p.getType().toString();
					if (par.size() > 1 && !par.get(par.size() - 1).equals(p)) {
						parameters += ",";
					}
					resultados.add(new Resultado(pack + "/"+className + "/"+ md.getNameAsString() + "(" + parameters + ")", linhasMethod,false));
					//resultados.add + "(" + parameters + ")");
					//resultados.add(Integer.toString(linhasMethod));
				}
			}
		}
	}

	public ArrayList<Resultado> getResultados() {
		return resultados;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Loc_Method a = new Loc_Method(new Metrics(FILE_PATH));

		for (Resultado string : a.getResultados()) {
			//System.out.println(string.getPath());
			//System.out.println(string.getLinhas());
			System.out.println(string.getMethodNames());
		}
	}
}
