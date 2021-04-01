package maisTestes;

import java.util.ArrayList;

/*
 * Para testar o que o utilizador nos devolve na gui;
 * Vai escolher um nome para uma regra, ou utilizar as 2 pré-definidas;
 * Pode editar as pré-definidas, ou criar uma de raiz (com métricas só de class ou só de method);
 * 
 * Vamos receber uma String nome - quer seja nova ou pre definida
 * 		conjunto de métricas e limites e operadores
 */
public class GuiOutput {

//	verificação para os metodos seres todos de class ou todos de method é feita na GUI
	
	public enum operators {OR, AND};
	public enum comparators {BIGGER, SMALLER, EQUALS};
//	pode ser atributo da gui e depois chamamos para estas classes
	
	private String ruleName;
//	se for Long_method ou God_class temos de verificar se efetuou edições na regra
	
	private ArrayList<String> metricName = new ArrayList<>();
	
	private ArrayList<Integer> limit = new ArrayList<>();
	
	private ArrayList<operators> oper = new ArrayList<>();
	
	private ArrayList<comparators> comp = new ArrayList<>();
	
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
	
	
			
}
