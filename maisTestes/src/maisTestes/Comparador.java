package maisTestes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import central.BoolResultado;
import central.Central;
import rules.Rule;

public class Comparador {
	
	
	private int countVP=0;
	private int countVN=0;
	private int countFP=0;
	private int countFN=0;
	
	
	public Comparador(ArrayList<BoolResultado> boolMethod, ArrayList<BoolResultado> boolClass, int tipo) throws FileNotFoundException, IOException {
		File file= new File("C:\\Users\\Vasco\\Downloads\\Code_Smells.xlsx");
		Excel excel= new Excel();
		excel.lerExcel(file);
		ArrayList<Linha> linhas= new ArrayList<Linha>(excel.getList());
		if(tipo==1){
			methodComp(boolMethod, linhas);
			classComp(boolClass, linhas);
		}
		if(tipo==2){
			classComp(boolClass, linhas);
		}
		if(tipo==3){
			methodComp(boolMethod, linhas);
		}
		
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
						System.out.println("Verdadeiro Positivo");
						countVP++;
					}
					if(!linhas.get(i).getis_Long_Method() && boolMethod.get(j).getVerificacao()){
						System.out.println("Falso Positivo");
						countFP++;
					}
					if(!linhas.get(i).getis_Long_Method() && !boolMethod.get(j).getVerificacao()){
						System.out.println("Verdadeiro Negativo");
						countVN++;
					}
					if(linhas.get(i).getis_Long_Method() && !boolMethod.get(j).getVerificacao()){
						System.out.println("Falso Negativo");
						countFN++;
					}
					
				}
			}
		}
	}
	public void classComp(ArrayList<BoolResultado> boolClass, ArrayList<Linha> linhas){
		for (int i=0; i< linhas.size(); i++) {
			for (int j=0; j<boolClass.size();j++){
				if(linhas.get(i).getPacote().equals(boolClass.get(j).getPackage()) && linhas.get(i).getClasse().equals(boolClass.get(j).getClasses()) && linhas.get(i).getMetodo().equals(boolClass.get(j).getMetodo())){
					if(linhas.get(i).getis_God_Class() && boolClass.get(j).getVerificacao()){
						System.out.println("Verdadeiro Positivo");
						countVP++;
					}
					if(!linhas.get(i).getis_God_Class() && boolClass.get(j).getVerificacao()){
						System.out.println("Falso Positivo");
						countFP++;
					}
					if(!linhas.get(i).getis_God_Class() && !boolClass.get(j).getVerificacao()){
						System.out.println("Verdadeiro Negativo");
						countVN++;
					}
					if(linhas.get(i).getis_God_Class() && !boolClass.get(j).getVerificacao()){
						System.out.println("Falso Negativo");
						countFN++;
					}
					
				}
			}
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

}

