package excel;

import java.util.ArrayList;

/**
 * @author Grupo 52
 */

public class Procura {
	Linha linha;

	/**
	 * Initializes a newly created Procura object
	 */
	public Procura() {
	}

	/**
	 * Searches for a method of a given class in an ArrayList with all rows from an
	 * Excel File
	 * 
	 * @param listaOriginal the array with all the rows from an Excel File
	 * @param classe        name of the class that contains the method
	 * @param metodo        name of the method to search for
	 * 
	 * @return row from the Excel File with all the information of a given method
	 */
	public Linha getProcura(ArrayList<Linha> listaOriginal, String classe, String metodo) {
		for (int i = 0; i < listaOriginal.size(); i++) {
			if (listaOriginal.get(i).getClasse().equals(classe) && listaOriginal.get(i).getMetodo().equals(metodo)) {
				linha = listaOriginal.get(i);
			}
		}

		return linha;
	}

	/**
	 * Getter for the object Linha
	 * 
	 * @return object Linha which contains all the information of a given method
	 */
	public Linha getLinha() {
		return linha;
	}
}
