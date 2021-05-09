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
 * @author Grupo 52
 */
public class History {
	private String folderPathToSave;
	private String folderPathToRead;
	private String ruleName;

	/**
	 * Allows user to create a file with rules previously selected
	 */

	public History() {
	}

	/**
	 * Allows user to choose a folder to Save the Rules
	 * 
	 * @param folderPath a string with the intended path to save the file
	 */
	public void setFolderPathToSave(String folderPath) {
		this.folderPathToSave = folderPath;
	}

	/**
	 * Getter for the Folder's Path to Save the Rules
	 * 
	 * @return a string with the intended path to save the file
	 */
	public String getFolderPathToSave() {
		return folderPathToSave;
	}

	/**
	 * Set's the Folder's Path to Read the Rules
	 * 
	 * @param folderPathToRead the folder Path to Read the rules saved by the user
	 */
	public void setFolderPathToRead(String folderPathToRead) {
		this.folderPathToRead = folderPathToRead;
	}

	/**
	 * Getter for the Folder's Path to read the Rules
	 * 
	 * @return a string with the user's folder's path
	 */
	public String getFolderPathToRead() {
		return folderPathToRead;
	}

	/**
	 * Set's the Name for the file to be saved
	 * 
	 * @param ruleName the name the user wants to save the file with
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * Getter for the rule folder's name
	 * 
	 * @return the name of the folder
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * file name will always be rules' rule name first position
	 * 
	 * @param rules the array with the rules the user wants to save
	 */
	public void writeFile(ArrayList<Rule> rules) {
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(new File(folderPathToSave + "\\" + ruleName + ".jrule")));
			o.writeObject(rules);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * reads the user's file with the rules
	 * 
	 * @param name the user's File name
	 * @return ArrayList of rules with all the rules in the user's file
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Rule> readFile(String name) {
		ArrayList<Rule> rules = null;
		try {
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(name));
			rules = (ArrayList<Rule>) o.readObject();
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rules;
	}
}