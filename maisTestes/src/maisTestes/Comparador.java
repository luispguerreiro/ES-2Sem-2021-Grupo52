package maisTestes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import central.BoolResultado;

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
//						System.out.println("Verdadeiro Positivo");
						countVP++;
					}
					if (!linhas.get(i).getis_Long_Method() && boolMethod.get(j).getVerificacao()) {
						methodCheck.add("Falso Positivo");
//						System.out.println("Falso Positivo");
						countFP++;
					}
					if (!linhas.get(i).getis_Long_Method() && !boolMethod.get(j).getVerificacao()) {
						methodCheck.add("Verdadeiro Negativo");
//						System.out.println("Verdadeiro Negativo");
						countVN++;
					}
					if (linhas.get(i).getis_Long_Method() && !boolMethod.get(j).getVerificacao()) {
						methodCheck.add("Falso Negativo");
//						System.out.println("Falso Negativo");
						countFN++;
					}
				}
			}
			if (p == 0)
				methodCheck.add("erro");
		}
	}

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
//						System.out.println("Verdadeiro Positivo");
						countVP++;
					}
					if (!linhas.get(i).getis_God_Class() && boolClass.get(j).getVerificacao()) {
						classCheck.add("Falso Positivo");
//						System.out.println("Falso Positivo");
						countFP++;
					}
					if (!linhas.get(i).getis_God_Class() && !boolClass.get(j).getVerificacao()) {
						classCheck.add("Verdadeiro Negativo");
//						System.out.println("Verdadeiro Negativo");
						countVN++;
					}
					if (linhas.get(i).getis_God_Class() && !boolClass.get(j).getVerificacao()) {
						classCheck.add("Falso Negativo");
//						System.out.println("Falso Negativo");
						countFN++;
					}

				}
			}
			if (p == 0)
				classCheck.add("erro");
		}
	}

	public int getCountVP() {
		return countVP;
	}

	public int getCountFP() {
		return countFP;
	}

	public int getCountVN() {
		return countVN;
	}

	public int getCountFN() {
		return countFN;
	}

	public ArrayList<String> getClassCheck() {
		return classCheck;
	}

	public ArrayList<String> getMethodCheck() {
		return methodCheck;
	}

}