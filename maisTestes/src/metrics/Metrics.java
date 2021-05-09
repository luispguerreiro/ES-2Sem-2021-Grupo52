package metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

/**
 * The metric to be calculated
 * 
 */
public class Metrics {

	/**
	 * @author Grupo52
	 * 
	 */
	private String FILE_PATH;
	private CompilationUnit cu;
	private List<ClassOrInterfaceDeclaration> clazz;
	private ClassOrInterfaceDeclaration mainClass;
	private List<ClassOrInterfaceDeclaration> nestedClasses;

	/**
	 * Class Constructor that turns the filePath into a file and separates the
	 * classes into a list
	 * 
	 * @param FILE_PATH the path of the file to make a Metrics object out of.
	 * @throws FileNotFoundException in case the file imported isn't found
	 */
	public Metrics(String FILE_PATH) throws FileNotFoundException {
		this.FILE_PATH = FILE_PATH;
		this.cu = StaticJavaParser.parse(new File(FILE_PATH));
		this.clazz = cu.findAll(ClassOrInterfaceDeclaration.class);
		listClasses(cu);
	}

	/**
	 * Returns the main ClassOrInterfaceDeclaration of the file
	 * 
	 * @return the main class
	 */
	public ClassOrInterfaceDeclaration getMainClass() {
		return mainClass;
	}

	/**
	 * Returns a list of nested ClassOrInterfaceDeclaration of the file
	 * 
	 * @return the list of nested classes
	 */
	public List<ClassOrInterfaceDeclaration> getNestedClasses() {
		return nestedClasses;
	}

	/**
	 * Returns the CompilationUnit of the file
	 * 
	 * @return the CompilationUnit
	 */
	public CompilationUnit getCu() {
		return cu;
	}

	/**
	 * Makes a list of the classes that are in the CompilationUnit
	 * 
	 * @param cu the CompilationUnit
	 */
	private void listClasses(CompilationUnit cu) {
		List<TypeDeclaration<?>> types = cu.getTypes();
		nestedClasses = cu.findAll(ClassOrInterfaceDeclaration.class);
		if (!types.isEmpty() && !types.getClass().isInterface()) {
			for (TypeDeclaration<?> t : types) {
				if (t.isClassOrInterfaceDeclaration()) {
					mainClass = (ClassOrInterfaceDeclaration) t;
				}
			}
		}
		if (!nestedClasses.isEmpty() && nestedClasses.size() >= 2 && !nestedClasses.getClass().isInterface()) {
			nestedClasses.remove(mainClass);
		} else {
			nestedClasses.clear();
		}
	}

	/**
	 * Getter for the list of classes, clazz
	 * 
	 * @return list of classOrInterfaceDeclaration of the metric
	 */
	public List<ClassOrInterfaceDeclaration> getClazz() {
		return clazz;
	}

	/**
	 * Getter for the FILE_PATH
	 * 
	 * @return the path of the file
	 */
	public String getFilePath() {
		return FILE_PATH;
	}

}