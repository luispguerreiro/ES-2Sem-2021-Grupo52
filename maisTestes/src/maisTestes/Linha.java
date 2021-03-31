package maisTestes;

public class Linha {
	String pacote;
	String classe;
	String metodo;
	boolean is_God_Class;
	boolean is_Long_Method;

	public void setPacote(String pacote) {
		this.pacote = pacote;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public void setIs_God_Class(boolean is_God_Class) {
		this.is_God_Class = is_God_Class;
	}

	public void setIs_Long_Method(boolean is_Long_Method) {
		this.is_Long_Method = is_Long_Method;
	}

	public String getPacote() {
		return pacote;
	}

	public String getClasse() {
		return classe;
	}

	public String getMetodo() {
		return metodo;
	}

	public boolean getis_God_Class() {
		return is_God_Class;
	}

	public boolean getis_Long_Method() {
		return is_Long_Method;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return pacote + " " + classe + " " + metodo + " " + is_God_Class + " " + is_Long_Method;
	}

}
