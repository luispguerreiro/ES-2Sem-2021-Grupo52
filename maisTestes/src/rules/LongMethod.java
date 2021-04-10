package rules;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import rules.GuiOutput.comparators;
import rules.GuiOutput.operators;

public class LongMethod extends Rule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String locMethod = "LOC_method";
	private static final String cycloMethod = "CYCLO_method";
	private static final int ruleType=1;
	

	public LongMethod(String ruleName, ArrayList<String> metricName ,ArrayList<comparators> comp, ArrayList<Integer> limits,
			ArrayList<operators> oper) throws FileNotFoundException {
		
		super(ruleName, ruleType, metricName, comp, limits, oper);
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
		String ruleName = "regra10";
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparators> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operators> oper = new ArrayList<>();
		
		LongMethod l = new LongMethod(ruleName, metricName, comp, limits, oper);
		System.out.println(ruleType);
	
	}




}
