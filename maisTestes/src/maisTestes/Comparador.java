package maisTestes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import central.BoolResultado;

public class Comparador {
<<<<<<< HEAD
	
	public Comparador(ArrayList<BoolResultado> boolMethod, ArrayList<BoolResultado> boolClass) throws FileNotFoundException, IOException {
		File file= new File("C:\\Users\\Vasco\\Downloads\\Code_Smells.xlsx");
		Excel excel= new Excel();
=======

	public Comparador(ArrayList<BoolResultado> boolMethod, ArrayList<BoolResultado> boolClass)
			throws FileNotFoundException, IOException {
		File file = new File("C:\\Users\\henri\\Downloads\\Code_Smells.xlsx");
		Excel excel = new Excel();
>>>>>>> refs/remotes/origin/Rules&Thresholds
		excel.lerExcel(file);
<<<<<<< HEAD
		ArrayList<Linha> linhas= new ArrayList<Linha>(excel.getList());
		methodComp(boolMethod, linhas);
		classComp(boolClass, linhas);
		
	}
	public Comparador(ArrayList<BoolResultado> array) throws FileNotFoundException, IOException{
		getExcel("C:\\Users\\Vasco\\Downloads\\Code_Smells.xlsx");
//		File file= new File("C:\\Users\\Vasco\\Downloads\\Code_Smells.xlsx");
//		Excel excel= new Excel();
//		excel.lerExcel(file);
//		ArrayList<Linha> linhas= new ArrayList<Linha>(excel.getList());
		
	}
	public ArrayList<Linha> getExcel(String path) throws FileNotFoundException, IOException{
		File file= new File(path);
		Excel excel=new Excel();
		excel.lerExcel(file);
		ArrayList<Linha> linhas= new ArrayList<Linha>(excel.getList());
		return linhas;
	}
		
		
		
	public void methodComp(ArrayList<BoolResultado> boolMethod, ArrayList<Linha> linhas){
		for (int i=0; i< linhas.size(); i++) {
			for (int j=0; j<boolMethod.size();j++){
				if(linhas.get(i).getPacote().equals(boolMethod.get(j).getPackage()) && linhas.get(i).getClasse().equals(boolMethod.get(j).getClasses()) && linhas.get(i).getMetodo().equals(boolMethod.get(j).getMetodo())){
					if(linhas.get(i).getis_Long_Method() && boolMethod.get(j).getVerificacao()){
=======
		ArrayList<Linha> linhas = new ArrayList<Linha>(excel.getList());
//		System.out.println(linhas);

		for (int i = 0; i < linhas.size(); i++) {
			System.out.println(linhas.get(i).getMetodo());
			for (int j = 0; j < boolMethod.size(); j++) {
				if (linhas.get(i).getPacote().equals(boolMethod.get(j).getPackage())
						&& linhas.get(i).getClasse().equals(boolMethod.get(j).getClasses())
						&& linhas.get(i).getMetodo().equals(boolMethod.get(j).getMetodo())) {
					if (linhas.get(i).getis_Long_Method() && boolMethod.get(j).getVerificacao()) {
						System.out.println(boolMethod.get(j).getMetodo());
>>>>>>> refs/remotes/origin/Rules&Thresholds
						System.out.println("Verdadeiro Positivo");
					}
					if (!linhas.get(i).getis_Long_Method() && boolMethod.get(j).getVerificacao()) {
						System.out.println("---->"+linhas.get(i).getis_Long_Method());
						System.out.println("---->"+boolMethod.get(j).getVerificacao());
						System.out.println(boolMethod.get(j).getMetodo());
						
						
						System.out.println("Falso Positivo");
					}
					if (!linhas.get(i).getis_Long_Method() && !boolMethod.get(j).getVerificacao()) {
						System.out.println(boolMethod.get(j).getMetodo());

						System.out.println("Verdadeiro Negativo");
					}
					if (linhas.get(i).getis_Long_Method() && !boolMethod.get(j).getVerificacao()) {
						System.out.println(boolMethod.get(j).getMetodo());

						System.out.println("Falso Negativo");
					}

				}
			}
		}
<<<<<<< HEAD
	}
	public void classComp(ArrayList<BoolResultado> boolClass, ArrayList<Linha> linhas){
		for (int i=0; i< linhas.size(); i++) {
			for (int j=0; j<boolClass.size();j++){
				if(linhas.get(i).getPacote().equals(boolClass.get(j).getPackage()) && linhas.get(i).getClasse().equals(boolClass.get(j).getClasses()) && linhas.get(i).getMetodo().equals(boolClass.get(j).getMetodo())){
					if(linhas.get(i).getis_God_Class() && boolClass.get(j).getVerificacao()){
=======

//is_God_Class

		for (int i = 0; i < linhas.size(); i++) {
			System.out.println(boolClass.get(i).getMetodo());
			System.out.println(linhas.get(i).getMetodo());
			for (int j = 0; j < boolClass.size(); j++) {
				if (linhas.get(i).getPacote().equals(boolClass.get(j).getPackage())
						&& linhas.get(i).getClasse().equals(boolClass.get(j).getClasses())
						&& linhas.get(i).getMetodo().equals(boolClass.get(j).getMetodo())) {
					if (linhas.get(i).getis_God_Class() && boolClass.get(j).getVerificacao()) {
>>>>>>> refs/remotes/origin/Rules&Thresholds
						System.out.println("Verdadeiro Positivo");
					}
					if (!linhas.get(i).getis_God_Class() && boolClass.get(j).getVerificacao()) {
						System.out.println("Falso Positivo");
					}
					if (!linhas.get(i).getis_God_Class() && !boolClass.get(j).getVerificacao()) {
						System.out.println("Verdadeiro Negativo");
					}
					if (linhas.get(i).getis_God_Class() && !boolClass.get(j).getVerificacao()) {
						System.out.println("Falso Negativo");
					}
				}
			}
<<<<<<< HEAD
		}	
	}
		

=======
		}
	}
>>>>>>> refs/remotes/origin/Rules&Thresholds
}
