package maisTestes;
import java.util.ArrayList;

public class Procura {
	ArrayList<Linha> lista;

	public ArrayList<Linha> getProcura(ArrayList<Linha> listaOriginal, String classe) {
		this.lista = new ArrayList<Linha>();
		for (int i = 0; i < listaOriginal.size(); i++) {
			if (listaOriginal.get(i).getClasse().equals(classe)) {
				lista.add(listaOriginal.get(i));
			}
		}

		return lista;
	}
	
	public ArrayList<Linha> getLista() {
		return lista;
	}
}
