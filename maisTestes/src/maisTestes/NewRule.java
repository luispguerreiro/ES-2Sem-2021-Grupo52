package maisTestes;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import maisTestes.GuiOutput.comparators;
import maisTestes.GuiOutput.operators;

public class NewRule extends Rule {

	public NewRule(String ruleName, ArrayList<String> metricName, ArrayList<comparators> comp, ArrayList<Integer> limits,
			ArrayList<operators> oper) throws FileNotFoundException {
		super(ruleName, metricName, comp, limits, oper);
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		String ruleName= "Nome da regra Aqui";
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparators> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operators> oper = new ArrayList<>();

		NewRule r = new NewRule(ruleName, metricName, comp, limits, oper);
		System.out.println(ruleName);
	}

}
