package rules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * Para testar o que o utilizador nos devolve na gui;
 * Vai escolher um nome para uma regra, ou utilizar as 2 pré-definidas;
 * Pode editar as pré-definidas, ou criar uma de raiz (com métricas só de class ou só de method);
 * 
 * Vamos receber uma String nome - quer seja nova ou pre definida
 * 		conjunto de métricas e limites e operadores
 *
 *Na gui mostrar o conjunto de ficheiros que ja existem de historico. Se o user selecionar criar novo e der um nome já existente, devia apaprecer
 *uma mensagem de confirmação "deseja mesmo reescrever esta regra ja existente?"
 *
 */

public class GuiOutput {

//	verificação para os metodos seres todos de class ou todos de method é feita na GUI

	public enum operators {
		OR, AND
	};

	public enum comparators {
		BIGGER, SMALLER, EQUALS
	};
//	pode ser atributo da gui e depois chamamos para estas classes

	private String ruleName;
//	se for Long_method ou God_class temos de verificar se efetuou edições na regra

	private ArrayList<String> metricName = new ArrayList<>();
	private ArrayList<Integer> limit = new ArrayList<>();
	private ArrayList<operators> oper = new ArrayList<>();
	private ArrayList<comparators> comp = new ArrayList<>();

	private final String folderPath = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ES_Rules_History\\";

	// Date format (day/month/year hour:min:seg a)
	private String string_date = "01/04/2021 13:00:00";
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public GuiOutput() {
//		deleteOlderFiles(folderPath, string_date);
		getRecentFiles(folderPath, string_date);
		
	}

	public ArrayList<Integer> getLimit() {
		return limit;
	}

	public ArrayList<String> getMetricName() {
		return metricName;
	}

	public ArrayList<operators> getOper() {
		return oper;
	}

	public String getRuleName() {
		return ruleName;
	}

	public ArrayList<comparators> getComp() {
		return comp;
	}

	public void writeFile(Rule rule) {
		try {
			FileOutputStream f = new FileOutputStream(new File(
					"C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ES_Rules_History\\" + rule.getRuleName()));

			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(rule);

			o.close();
			f.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Rule readFile(String name) {
		Rule rule = null;
		try {
			FileInputStream f = new FileInputStream(
					"C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ES_Rules_History\\" + name);

			ObjectInputStream o = new ObjectInputStream(f);

			// Write objects to file
			rule = (Rule) o.readObject();

			o.close();
			f.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rule;
	}

	public void deleteOlderFiles(String path, String date) {
		File folder = new File(path);
		File[] folderFiles = folder.listFiles();
		boolean a = false;
		Date d;
		try {
			d = dateFormat.parse(date);

			for (int i = 0; i < folderFiles.length; i++) {
				if (folderFiles[i].isFile()) {
					File file = folderFiles[i];
					long diff = file.lastModified();

					if (diff < d.getTime()) {
						file.delete();
						a = true;
						System.out.println("Ficheiro " + file.getName() + " apagado!\n");
					}
				}
			}
			if (a == false)
				System.out.println("Não foi encontrado nenhum ficheiro antes de " + d + " para ser apagado!\n");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getRecentFiles(String path, String date) {
		File folder = new File(path);
		File[] folderFiles = folder.listFiles();
		boolean a = false;
		Date d;
		try {
			System.out.println("---HISTÓRICO DE REGRAS---");
			d = dateFormat.parse(date);

			for (int i = 0; i < folderFiles.length; i++) {
				if (folderFiles[i].isFile()) {
					File file = folderFiles[i];
					long diff = file.lastModified();

					if (diff > d.getTime()) {
						a = true;
						System.out.println("	Ficheiro existente no histórico: " + file.getName());
					}
				}
			}
			if (a == false)
				System.out.println("Não foi encontrado nenhum ficheiro mais recente do que " + d + "\n");
			System.out.println("---FIM DO HISTÓRICO---\n");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String ruleName = "Regra2";
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparators> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operators> oper = new ArrayList<>();
		GuiOutput gui = new GuiOutput();

		// Para testar, deixar apenas 1 operacional.
//		NewRule r = new NewRule(ruleName, metricName, comp, limits, oper);
		//GodClass r = new GodClass(metricName, comp, limits, oper);
//		LongMethod r = new LongMethod(metricName, comp, limits, oper);
	//	gui.writeFile(r);
//		Rule g1 = gui.readFile("Regra1");
//		System.out.println("Nome da Regra guardada: " + g1.getRuleName());
//		for (int i = 0; i < metricName.size(); i++) {
//			System.out.println("Métrica: " + g1.getMetricName().get(i) + "   Comparador: " + g1.getComp().get(i)
//					+ "   Limite: " + g1.getLimits().get(i)); // FALTA O G1.GETCOMP.GET(J)
//
//		}
		
	}
}
