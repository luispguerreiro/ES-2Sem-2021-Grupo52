package maisTestes;

import java.util.ArrayList;

public class Procura {
	Linha linha;

	public Linha getProcura(ArrayList<Linha> listaOriginal, String classe, String metodo) {
		for (int i = 0; i < listaOriginal.size(); i++) {
			if (listaOriginal.get(i).getClasse().equals(classe) && listaOriginal.get(i).getMetodo().equals(metodo)) {
				linha = listaOriginal.get(i);
			}
		}

		return linha;
	}

	public Linha getLinha() {
		return linha;
	}
}
