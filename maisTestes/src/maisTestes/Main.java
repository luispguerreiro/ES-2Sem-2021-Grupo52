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
public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file= new File("C:\\Users\\Vasco\\Downloads\\Code_Smells.xlsx");
		Excel excel= new Excel();
		excel.lerExcel(file);
		ArrayList<Linha> linhas= new ArrayList<Linha>(excel.getList());
		System.out.println(linhas);
		ArrayList<Rule> rules= new ArrayList<Rule>();
		rules.get(0).fuelArrays();
		Central c= new Central(rules);
		c.getBoolMethod();
		
//		BoolResultado br= new BoolResultado("C:\\Users\\Vasco\\Downloads\\Code_Smells.xlsx", );
		for (int i=0; i< linhas.size(); i++) {
			for (int j=0; j<c.getBoolMethod().size();j++){
				if(linhas.get(i).getPacote().equals(c.getBoolMethod().get(j).getMetodo())){
				if(linhas.get(i).getMetodo().equals(c.getBoolMethod().get(j).getMetodo())){
					if(linhas.get(i).getis_Long_Method() && c.getBoolMethod().get(j).getMetodo()){
						System.out.println("Verdadeiro Positivo");
					}
					if(!linhas.get(i).getis_Long_Method() && c.getBoolMethod().get(j).getMethod()){
						System.out.println("Falso Positivo");
					}
					if(!linhas.get(i).getis_Long_Method() && !c.getBoolMethod().get(j).getMethod()){
						System.out.println("Verdadeiro Negativo");
					}
					if(linhas.get(i).getis_Long_Method() && !method.aplicarRegra()){
						System.out.println("Falso Negativo");
					}
		
//is_God_Class
			if(linhas.get(i).getis_God_Class() && class.aplicarRegra()){
				System.out.println("Verdadeiro Positivo");
			}
			if(!linhas.get(i).getis_God_Class() && class.aplicarRegra()){
				System.out.println("Falso Positivo");
			}
			if(!linhas.get(i).getis_God_Class() && !class.aplicarRegra()){
				System.out.println("Verdadeiro Negativo");
			}
			if(linhas.get(i).getis_God_Class() && !class.aplicarRegra()){
				System.out.println("Falso Negativo");
			}
			
		
		
		}
			
		

	}
	}

}