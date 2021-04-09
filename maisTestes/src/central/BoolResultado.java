package central;

public class BoolResultado {
	
	private String path;
	private boolean verificacao;
	
	public BoolResultado(String path, boolean verificacao){
		this.path=path;
		this.verificacao=verificacao;
	}
	
	public void setVerificacao(boolean verificacao) {
		this.verificacao = verificacao;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean getVerificacao(){
		return verificacao;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
