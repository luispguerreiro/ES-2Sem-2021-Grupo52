package Metrics;

import java.util.ArrayList;
/**
 *@author Grupo52  
 * 
 */
public class Resultado {
	
	/**
	 *Joins the results of the thresholds of the rule
	 * 
	 */
	private String path;
	private int linhas;
	private int methodID;
	private ArrayList<Integer> allInts = new ArrayList<>();
	private int[] intis = new int[5];
	/*
	 * Posição do allInts e respetiva metrica: 0->nomClass 1->loc_class 2->wmcClass
	 * 3->locMethod 4->cycloMethod
	 */
	
	/** 
	 * Class Constructor to store the metrics results.
	 * @param methodID the id of the object result.
	 * @param path the String with all the info of the result, including package, class and method if it has.
	 * @param linhas the result of the metric.
	 * @param intis an array used to identify what metric is used to get that result.
	 * */
	public Resultado(int methodID, String path, int linhas, int[] intis) {
		this.path = path;
		this.linhas = linhas;
		this.methodID = methodID;
		this.allInts = allInts;
		this.intis = intis;
	}


	/**
	 * Getter for the array that identifies what metric was used
	 * 
	 * @return the array with the results
	 */
	
	public int[] getAllInts() {
		return intis;
	}
	/**
	 * setter for the methodID
	 * 
	 * 
	 */
	public void setMethodID(int methodID) {
		this.methodID = methodID;
	}
	/**
	 * Getter for the methodID
	 * 
	 * @return the id of object result
	 */
	public int getMethodID() {
		return methodID;
	}
	/**
	 * Getter for the result
	 * 
	 * @return the result of the object
	 */
	public int getLinhas() {
		return linhas;
	}

	/**
	 * Getter for the path
	 * 
	 * @return the whole string with package, class and method
	 */
	public String getPath() {
		return path;
	}
	/**
	 * Changes the path String to get only the package
	 * 
	 * @return the string with the package name
	 */
	public String getPackage() {
		String[] aux;
		aux = path.trim().split("/");
		String[] aux2 = aux[0].trim().split(";");
		String pack = aux2[0].substring(9);
		String[] packf = pack.split(" ");
		if (packf[0].equals("empty"))
			return " ";
		return packf[1];
//		return pack;
	}
	/**
	 * Changes the path String to get only the class
	 * 
	 * @return the string with the class name
	 */
	public String getClasses() {

		String[] aux;
		aux = path.trim().split("/");

		return aux[1];
	}
	/**
	 * Changes the path String to get only the method name
	 * 
	 * @return the string with the method name
	 */
	public String getMethodNames() {
		String[] aux;

		aux = path.trim().split("/");
		return aux[2];

	}


}
