package rules;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import rules.GuiOutput.comparators;
import rules.GuiOutput.operators;

public class LongMethod extends Rule {
	
	private static final String locMethod = "LOC_method";
	private static final String cycloMethod = "CYCLO_method";
	private static final String ruleName= "Long_method";
	

	public LongMethod(ArrayList<String> metricName ,ArrayList<comparators> comp, ArrayList<Integer> limits,
			ArrayList<operators> oper) throws FileNotFoundException {
		
		super(ruleName, metricName, comp, limits, oper);
	}
	
	
	
	@Override
	public void fuelArrays() {
		metricName.add(locMethod);
		metricName.add(cycloMethod);
		comp.add(comparators.BIGGER);
		comp.add(comparators.BIGGER);
		limits.add(10);
		limits.add(10);
		oper.add(operators.AND);
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparators> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operators> oper = new ArrayList<>();
		
		LongMethod l = new LongMethod(metricName, comp, limits, oper);
		System.out.println(ruleName);
	
	}

}
