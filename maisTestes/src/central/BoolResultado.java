package central;

/**
 * @author Grupo 52
 * 
 */
public class BoolResultado {
	

	private String classes;
	private String metodo;
	private String pack;
	private boolean verificacao;
	private int id;

	/**
	 * Object with the results of the metrics in boolean
	 * 
	 * @param id the id to be displayed
	 * @param pack the package of the method analyzed
	 * @param classes the class of the method analyzed
	 * @param metodo the method analyzed
	 * @param verificacao the boolean result of the method analyzed
	 */
	public BoolResultado(int id, String pack, String classes, String metodo, boolean verificacao) {
		this.id = id;
		this.classes = classes;
		this.metodo = metodo;
		this.pack = pack;
		this.verificacao = verificacao;
	}

	/**
	 * Set's the result of the metric to true or false
	 * 
	 * @param verificacao the result of the metric calculated
	 */
	public void setVerificacao(boolean verificacao) {
		this.verificacao = verificacao;
	}

	/**
	 * Getter for the metric Id
	 * 
	 * @return the id to be displayed in the GUI
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set's Id to be displayed in the GUI
	 * 
	 * @param id the id of the metric Result
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for the Package the metric calculated
	 * 
	 * @return a string with the Package
	 */
	public String getPackage() {
		return pack;
	}

	/**
	 * Getter for the Class the metric calculated
	 * 
	 * @return a string with the Classes
	 */
	public String getClasses() {
		return classes;
	}

	/**
	 * Getter for the Method the metric calculated
	 * 
	 * @return a string with the Methods
	 */
	public String getMetodo() {
		return metodo;
	}

	/**
	 * Getter for the Result the metric calculated
	 * 
	 * @return a boolean with the result
	 */
	public boolean getVerificacao() {
		return verificacao;
	}

}