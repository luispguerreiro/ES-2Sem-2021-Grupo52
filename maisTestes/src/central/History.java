package central;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import rules.Rule;

public class History {
//	private final String folderPath = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ES_Rules_History\\";
	private String folderPathToSave;
	private String folderPathToRead;
	private String ruleName;
	private String string_date = "01/04/2021 13:00:00";
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public History() {
//		deleteOlderFiles(folderPath, string_date);
//		getRecentFiles(folderPath, string_date);
	}

	public void setFolderPathToSave(String folderPath) {
		this.folderPathToSave = folderPath;
	}
	
	public void setFolderPathToRead(String folderPathToRead) {
		this.folderPathToRead = folderPathToRead;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/*
	 * file name will always be rules' rule name first position
	 */
	public void writeFile(ArrayList<Rule> rules) {
		try {
//			FileOutputStream f = new FileOutputStream(new File(folderPath + "\\" + rules.get(0).getRuleName()));
			FileOutputStream f = new FileOutputStream(new File(folderPathToSave + "\\" + ruleName));

			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(rules);

			o.close();
			f.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Rule> readFile(String name) {
		ArrayList<Rule> rules = null;
		try {
//			FileInputStream f = new FileInputStream(folderPathToRead + "\\" + name);
			FileInputStream f = new FileInputStream(name);

			ObjectInputStream o = new ObjectInputStream(f);

			rules = (ArrayList<Rule>) o.readObject();

			o.close();
			f.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rules;
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

	public static void main(String[] args) {
		History h = new History();
		h.setFolderPathToRead("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ES_Rules_History");
		h.setRuleName("OII");
		ArrayList<Rule> r = new ArrayList<>();
		r = h.readFile("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ES_Rules_History\\RegraNew");

		for(int i=0; i<r.size();i++)
		System.out.println(r.get(i).getRuleType() + " " + r.get(i).getMetricName().get(i));
	}
}
