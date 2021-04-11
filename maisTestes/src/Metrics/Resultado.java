package Metrics;

import java.util.ArrayList;

public class Resultado {
	private String path;
	private int linhas;
	private int methodID;
	private ArrayList<Integer> allInts = new ArrayList<>();
	private int[] intis = new int[5];
	/*
	 * Posição do allInts e respetiva metrica: 0->nomClass 1->loc_class 2->wmcClass
	 * 3->locMethod 4->cycloMethod
	 */

	public Resultado(int methodID, String path, int linhas, int[] intis) {
		this.path = path;
		this.linhas = linhas;
		this.methodID = methodID;
		this.allInts = allInts;
		this.intis = intis;
	}



	public int[] getAllInts() {
		return intis;
	}

	public void setMethodID(int methodID) {
		this.methodID = methodID;
	}

	public int getMethodID() {
		return methodID;
	}

	public int getLinhas() {
		return linhas;
	}

	public String getPath() {
		return path;
	}

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

	public String getClasses() {

		String[] aux;
		aux = path.trim().split("/");

		return aux[1];
	}

	public String getMethodNames() {
		String[] aux;

		aux = path.trim().split("/");
		return aux[2];

	}

	public static void main(String[] args) {

	}
}
