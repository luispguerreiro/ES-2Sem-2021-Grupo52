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
	private static final String ruleName = "God_class";
	private ArrayList<Resultado> results = new ArrayList<>();

	
	public GodClass(ArrayList<String> metricName, ArrayList<comparators> comp, ArrayList<Integer> limits,
			ArrayList<operators> oper) throws FileNotFoundException {
		super(ruleName, metricName, comp, limits, oper);
	}


	@Override
	public void fuelArrays() {
		metricName.add(locClass);
		metricName.add(nomClass);
		metricName.add(wmcClass);
		comp.add(comparators.BIGGER);
		comp.add(comparators.BIGGER);
		comp.add(comparators.BIGGER);
		// ver limite da loc_class
		limits.add(300);
		limits.add(10);
		limits.add(50);
		oper.add(operators.OR);
		oper.add(operators.OR);
		//oper.add(operators.OR);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparators> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operators> oper = new ArrayList<>();
		
		GodClass g = new GodClass(metricName, comp, limits, oper);
	}

}
