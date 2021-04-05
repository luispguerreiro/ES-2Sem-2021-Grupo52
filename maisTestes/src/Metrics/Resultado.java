package Metrics;

public class Resultado {
	private String path;
	private int linhas;
	private boolean verificacao;
	

	public Resultado(String path, int linhas, boolean verificacao) {
	this.path=path;
	this.linhas=linhas;
	this.verificacao=verificacao;
		
	}
	
	public void setTrue() {
		verificacao= true;

	}
	
	public boolean getVerificacao(){
		return verificacao;
		
	}
	
	public int getLinhas() {
		return linhas;
	}
	
	public String getPath() {
		return path;
	}
	
	
}