package Metrics;

public class Resultado {
	private String path;
	private int linhas;
	private boolean verificacao;
	

	public Resultado(String path, int linhas, boolean verifacao) {
	this.path=path;
	this.linhas=linhas;
	this.verificacao=verifacao;
		
	}
	
	private void setTrue() {
		verificacao= true;

	}
	
	private boolean getVerificacao(){
		return verificacao;
		
	}
	
	public int getLinhas() {
		return linhas;
	}
	
	public String getPath() {
		return path;
	}
	
	
}
