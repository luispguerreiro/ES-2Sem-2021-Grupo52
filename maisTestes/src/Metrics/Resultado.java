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
	public String getPackage() {
		String[] aux;
		aux = path.trim().split("/");
		String [] aux2=aux[0].trim().split(";");
		String pack =  aux2[0].substring(9);
		String[] packf = pack.split(" "); 
		return packf[1];
	}
	public String getClasses() {
		
		String[] aux;
		aux = path.trim().split("/");
		
		
		return aux[1];
	}
	
	public String getMethodNames() {
		String[] aux;
		
		aux = path.trim().split("/");	
		return aux[2];

	}
	
	public static void main(String[] args) {
		
	}
}
