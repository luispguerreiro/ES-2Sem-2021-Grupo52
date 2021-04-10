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
public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file= new File("C:\\Users\\Vasco\\Downloads\\Code_Smells.xlsx");
		Excel excel= new Excel();
		excel.lerExcel(file);
		ArrayList<Linha> linhas= new ArrayList<Linha>(excel.getList());
		System.out.println(linhas);
//		BoolResultado br= new BoolResultado("C:\\Users\\Vasco\\Downloads\\Code_Smells.xlsx", );
//		for (int i=0; i< linhas.size(); i++) {
//			if(linhas.get(i).getis_Long_Method()){
//				System.out.println("Verdadeiro Positivo");
//			}
//			if(!linhas.get(i).getis_Long_Method() && method.aplicarRegra()){
//				System.out.println("Falso Positivo");
//			}
//			if(!linhas.get(i).getis_Long_Method() && !method.aplicarRegra()){
//				System.out.println("Verdadeiro Negativo");
//			}
//			if(linhas.get(i).getis_Long_Method() && !method.aplicarRegra()){
//				System.out.println("Falso Negativo");
//			}
//		
////is_God_Class
//			if(linhas.get(i).getis_God_Class() && class.aplicarRegra()){
//				System.out.println("Verdadeiro Positivo");
//			}
//			if(!linhas.get(i).getis_God_Class() && class.aplicarRegra()){
//				System.out.println("Falso Positivo");
//			}
//			if(!linhas.get(i).getis_God_Class() && !class.aplicarRegra()){
//				System.out.println("Verdadeiro Negativo");
//			}
//			if(linhas.get(i).getis_God_Class() && !class.aplicarRegra()){
//				System.out.println("Falso Negativo");
//			}
//		
//		
//		}
			
		

	}

}