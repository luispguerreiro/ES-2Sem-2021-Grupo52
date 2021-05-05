package excel;

/**
 * @author Grupo 52
 */

public class Linha {
	String pacote;
	String classe;
	String metodo;
	boolean is_God_Class;
	boolean is_Long_Method;

	/**
	 * Initializes a newly created Linha object
	 */
	public Linha() {
	}

	/**
	 * Sets the package name for the object
	 * 
	 * @param pacote string with the package name
	 */
	public void setPacote(String pacote) {
		this.pacote = pacote;
	}

	/**
	 * Sets the class name for the object
	 * 
	 * @param classe string with the class name
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * Sets the method name for the object
	 * 
	 * @param metodo string with the package name
	 */
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	/**
	 * Sets the result of the is_God_Class to true or false
	 * 
	 * @param is_God_Class the result of the Code Smell
	 */
	public void setIs_God_Class(boolean is_God_Class) {
		this.is_God_Class = is_God_Class;
	}

	/**
	 * Sets the result of the is_Long_Method to true or false
	 * 
	 * @param is_Long_Method the result of the Code Smell
	 */
	public void setIs_Long_Method(boolean is_Long_Method) {
		this.is_Long_Method = is_Long_Method;
	}

	/**
	 * Getter for the package name
	 * 
	 * @return the name of the package
	 */
	public String getPacote() {
		return pacote;
	}

	/**
	 * Getter for the class's name
	 * 
	 * @return the name of the class
	 */
	public String getClasse() {
		return classe;
	}

	/**
	 * Getter for the method's name
	 * 
	 * @return the name of the method
	 */
	public String getMetodo() {
		return metodo;
	}

	/**
	 * Getter for the result of the is_God_Class
	 * 
	 * @return a boolean with the result
	 */
	public boolean getis_God_Class() {
		return is_God_Class;
	}

	/**
	 * Getter for the result of the is_Long_Method
	 * 
	 * @return a boolean with the result
	 */
	public boolean getis_Long_Method() {
		return is_Long_Method;
	}

	/**
	 * Transforms the object in a String
	 * 
	 * @return the string itself
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return pacote + " " + classe + " " + metodo + " " + is_God_Class + " " + is_Long_Method;
	}
}
