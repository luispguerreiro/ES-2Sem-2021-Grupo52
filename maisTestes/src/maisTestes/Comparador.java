package maisTestes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import central.BoolResultado;

/**
 * @author Grupo 52
 */

public class Comparador {

	private int countVP = 0;
	private int countVN = 0;
	private int countFP = 0;
	private int countFN = 0;
	private ArrayList<String> methodCheck;
	private ArrayList<String> classCheck;

	private File file = new File("Code_Smells.xlsx");

	public Comparador(ArrayList<BoolResultado> boolMethod, ArrayList<BoolResultado> boolClass, int tipo)
			throws FileNotFoundException, IOException {
		Excel excel = new Excel();
		excel.lerExcel(file);
		ArrayList<Linha> linhas = new ArrayList<Linha>(excel.getList());
		if (tipo == 1) {
			methodComp(boolMethod, linhas);
			classComp(boolClass, linhas);
		}
		if (tipo == 2) {
			classComp(boolClass, linhas);
		}
		if (tipo == 3) {
			methodComp(boolMethod, linhas);
		}

	}

//	public ArrayList<Linha> getExcel(String path) throws FileNotFoundException, IOException {
//		File file = new File(path);
//		Excel excel = new Excel();
//		excel.lerExcel(file);
//		ArrayList<Linha> linhas = new ArrayList<Linha>(excel.getList());
//		return linhas;
//	}

	/**
	 * Determines the quality of detection of code smells by comparing the results
	 * of the Excel file with the results of code smells using the thresholds chosen
	 * by the user (regarding methods)
	 * 
	 * @param boolMethod the array list with all the results of code smells using
	 *                   the thresholds chosen by the user (regarding methods)
	 * @param linhas     the array list with all the rows from an Excel File
	 * 
	 */

	public void methodComp(ArrayList<BoolResultado> boolMethod, ArrayList<Linha> linhas) {
		methodCheck = new ArrayList<>();
		for (int j = 0; j < boolMethod.size(); j++) {
			int p = 0;
			System.out.println(boolMethod.get(j).getVerificacao());
			for (int i = 0; i < linhas.size(); i++) {
				if (linhas.get(i).getPacote().equals(boolMethod.get(j).getPackage())
						&& linhas.get(i).getClasse().equals(boolMethod.get(j).getClasses())
						&& linhas.get(i).getMetodo().equals(boolMethod.get(j).getMetodo())) {
					p = 1;
					if (linhas.get(i).getis_Long_Method() && boolMethod.get(j).getVerificacao()) {
						methodCheck.add("Verdadeiro Positivo");
						countVP++;
					}
					if (!linhas.get(i).getis_Long_Method() && boolMethod.get(j).getVerificacao()) {
						methodCheck.add("Falso Positivo");
						countFP++;
					}
					if (!linhas.get(i).getis_Long_Method() && !boolMethod.get(j).getVerificacao()) {
						methodCheck.add("Verdadeiro Negativo");
						countVN++;
					}
					if (linhas.get(i).getis_Long_Method() && !boolMethod.get(j).getVerificacao()) {
						methodCheck.add("Falso Negativo");
						countFN++;
					}
				}
			}
			if (p == 0)
				methodCheck.add("erro");
		}
	}

	/**
	 * Determines the quality of detection of code smells by comparing the results
	 * of the Excel file with the results of code smells using the thresholds chosen
	 * by the user (regarding classes)
	 * 
	 * @param boolClass the array list with all the results of code smells using the
	 *                  thresholds chosen by the user (regarding classes)
	 * @param linhas    the array list with all the rows from an Excel File
	 * 
	 */

	public void classComp(ArrayList<BoolResultado> boolClass, ArrayList<Linha> linhas) {
		classCheck = new ArrayList<>();
		for (int j = 0; j < boolClass.size(); j++) {
			int p = 0;
			for (int i = 0; i < linhas.size(); i++) {
				if (linhas.get(i).getPacote().equals(boolClass.get(j).getPackage())
						&& linhas.get(i).getClasse().equals(boolClass.get(j).getClasses())
						&& linhas.get(i).getMetodo().equals(boolClass.get(j).getMetodo())) {
					p = 1;
					if (linhas.get(i).getis_God_Class() && boolClass.get(j).getVerificacao()) {
						classCheck.add("Verdadeiro Positivo");
						countVP++;
					}
					if (!linhas.get(i).getis_God_Class() && boolClass.get(j).getVerificacao()) {
						classCheck.add("Falso Positivo");
						countFP++;
					}
					if (!linhas.get(i).getis_God_Class() && !boolClass.get(j).getVerificacao()) {
						classCheck.add("Verdadeiro Negativo");
						countVN++;
					}
					if (linhas.get(i).getis_God_Class() && !boolClass.get(j).getVerificacao()) {
						classCheck.add("Falso Negativo");
						countFN++;
					}
				}
			}
			if (p == 0)
				classCheck.add("erro");
		}
	}

	/**
	 * Getter for the number of VP
	 * 
	 * @return the number of VP
	 */
	public int getCountVP() {
		return countVP;
	}

	/**
	 * Getter for the number of FP
	 * 
	 * @return the number of FP
	 */
	public int getCountFP() {
		return countFP;
	}

	/**
	 * Getter for the number of VN
	 * 
	 * @return the number of VN
	 */
	public int getCountVN() {
		return countVN;
	}

	/**
	 * Getter for the number of FN
	 * 
	 * @return the number of FN
	 */
	public int getCountFN() {
		return countFN;
	}

	/**
	 * Getter for the Array List of results obtained from the quality of the
	 * detection of code smells regarding classes
	 * 
	 * @return the list itself
	 */

	public ArrayList<String> getClassCheck() {
		return classCheck;
	}

	/**
	 * getter for the Array List of results obtained from the quality of the
	 * detection of code smells regarding methods
	 * 
	 * @return the list itself
	 */

	public ArrayList<String> getMethodCheck() {
		return methodCheck;
	}
}