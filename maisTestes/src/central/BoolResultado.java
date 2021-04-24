package central;

public class BoolResultado {
	
	private String classes;
	private String metodo;
	private String pack;
	private boolean verificacao;
	private int id;
	
	public BoolResultado(int id, String pack, String classes, String metodo, boolean verificacao){
		this.id=id;
		this.classes=classes;
		this.metodo=metodo;
		this.pack=pack;
		this.verificacao=verificacao;
	}
	
	public void setVerificacao(boolean verificacao) {
		this.verificacao = verificacao;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPackage() {
		return pack;
	}
	
	public String getClasses() {
		return classes;
	}
	
	public String getMetodo() {
		return metodo;
	}
	
	public boolean getVerificacao(){
		return verificacao;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
