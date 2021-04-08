package rules;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Metrics.Resultado;
import rules.GuiOutput.comparators;
import rules.GuiOutput.operators;

public class GodClass extends Rule {

	private static final String locClass = "LOC_class";
	private static final String nomClass = "NOM_class";
	private static final String wmcClass = "WMC_class";
	private ArrayList<Resultado> results = new ArrayList<>();
	private static final int ruleType = 0;

	
	public GodClass(String ruleName, ArrayList<String> metricName, ArrayList<comparators> comp, ArrayList<Integer> limits,
			ArrayList<operators> oper) throws FileNotFoundException {
		super(ruleName, ruleType, metricName, comp, limits, oper);
	}


	@Override
	public void fuelArrays() {
		metricName.add(locClass);
		metricName.add(nomClass);
//		metricName.add(wmcClass);
		comp.add(comparators.BIGGER);
//		comp.add(comparators.BIGGER);
		comp.add(comparators.BIGGER);
		// ver limite da loc_class
		limits.add(1);
		limits.add(10);
//		limits.add(50);
//		oper.add(operators.OR);
		oper.add(operators.OR);
		//oper.add(operators.OR);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparators> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operators> oper = new ArrayList<>();
		String ruleName = "godClass_V1.0";
		
		GodClass g = new GodClass(ruleName, metricName, comp, limits, oper);
	}


	@Override
	public boolean logic() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean logic(int valor) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
