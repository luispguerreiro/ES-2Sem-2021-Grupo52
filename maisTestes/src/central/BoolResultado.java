package central;

public class BoolResultado {
	
	private String classes;
	private String metodo;
	private boolean verificacao;
	
	public BoolResultado(String classes, String metodo, boolean verificacao){
		this.classes=classes;
		this.metodo=metodo;
		this.verificacao=verificacao;
	}
	
	public void setVerificacao(boolean verificacao) {
		this.verificacao = verificacao;
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
