package central;

public class BoolResultado {
	/**
	 * @author Grupo 52
	 * 
	 */

	private String classes;
	private String metodo;
	private String pack;
	private boolean verificacao;
	private int id;

	/**
	 * Object with the results of the metrics in boolean
	 * 
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
	 * @param the result of the metric calculated
	 */
	public void setVerificacao(boolean verificacao) {
		this.verificacao = verificacao;
	}

	/**
	 * Getter for the metric Id
	 * 
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
	 */
	public String getPackage() {
		return pack;
	}

	/**
	 * Getter for the Class the metric calculated
	 * 
	 */
	public String getClasses() {
		return classes;
	}

	/**
	 * Getter for the Method the metric calculated
	 * 
	 */
	public String getMetodo() {
		return metodo;
	}

	/**
	 * Getter for the Result the metric calculated
	 * 
	 */
	public boolean getVerificacao() {
		return verificacao;
	}

}