package central;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import rules.Rule;

/**
 * @author Nazif Sidique & Henrique Marques
 *
 */
public class History {
	private String folderPathToSave;
	private String folderPathToRead;
	private String ruleName;

	public History() {
	}

	public void setFolderPathToSave(String folderPath) {
		this.folderPathToSave = folderPath;
	}
	
	public String getFolderPathToSave() {
		return folderPathToSave;
	}
	
	public void setFolderPathToRead(String folderPathToRead) {
		this.folderPathToRead = folderPathToRead;
	}
	
	public String getFolderPathToRead() {
		return folderPathToRead;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleName() {
		return ruleName;
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

	
}
