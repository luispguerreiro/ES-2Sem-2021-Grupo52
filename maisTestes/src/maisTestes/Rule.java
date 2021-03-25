package maisTestes;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.naming.LimitExceededException;

import maisTestes.GuiOutput.comparators;
import maisTestes.GuiOutput.operators;

public class Rule {

	// NUM_METRICS vai ser atributo da class metrics
	private final int NUM_METRICS;

	private ArrayList<Threshold> thresholds = new ArrayList<>();

	private ArrayList<String> metricName = new ArrayList<>();
	private ArrayList<comparators> comp = new ArrayList<>();
	private ArrayList<Integer> limits = new ArrayList<>();
	private ArrayList<operators> oper = new ArrayList<>();

	private ArrayList<Boolean> threshResults = new ArrayList<>();

	private String rule;

	public Rule(ArrayList<String> metricName, ArrayList<comparators> comp, ArrayList<Integer> limits,
			ArrayList<operators> oper) throws FileNotFoundException {
		this.metricName = metricName;
		this.comp = comp;
		this.limits = limits;
		this.oper = oper;

		fuelArrays();

		NUM_METRICS = metricName.size();

		createThresholds();
		System.out.println("---->Resultado da Logica:" + logic());
	}

	public ArrayList<Threshold> getThresholds() {
		return thresholds;
	}

	public void createThresholds() throws FileNotFoundException {
		for (int i = 0; i < NUM_METRICS; i++) {
			Threshold t = new Threshold(metricName.get(i), comp.get(i), limits.get(i));
			thresholds.add(t);
			threshResults.add(t.result());
			System.out.println("-->Resultado de metrica Threashold: " + t.result());
		}
	}

	public boolean logic() {
		while(!(threshResults.size()==1)) {
			if (oper.get(0).equals(operators.AND)) {
				System.out.println(and(threshResults.get(0), threshResults.get(1)));
				threshResults.set(0, and(threshResults.get(0), threshResults.get(1)));
				threshResults.remove(1);
				oper.remove(0);
			} else {
				System.out.println(or(threshResults.get(0), threshResults.get(1)));
				threshResults.set(0, or(threshResults.get(0), threshResults.get(1)));
				threshResults.remove(1);
				oper.remove(0);
			}
		}
		return threshResults.get(0);
	}

	public boolean and(boolean one, boolean two) {
		if (one == true) {
			if (two == true)
				return true;
		}
		return false;
	}

	public boolean or(boolean one, boolean two) {
		if (one == false) {
			if (two == false)
				return false;
		}
		return true;
	}

	/**
	 * public boolean isLong_Method(Rule r) { if (r.getThresholds().size() != 2 ||
	 * r.getOperators().get(0).equals(logicOperator.OR)) return false; if
	 * (!thresholds.get(0).equals(thresholds.get(1))) { if
	 * (thresholds.get(0).getComparator().equals(Threshold.comparator.BIGGER) &&
	 * thresholds.get(1).getComparator().equals(Threshold.comparator.BIGGER)) { if
	 * ((thresholds.get(0).getMetricName().equals("LOC_method") ||
	 * thresholds.get(0).getMetricName().equals("CYCLO_Method")) &&
	 * (thresholds.get(1).getMetricName().equals("LOC_method") ||
	 * thresholds.get(1).getMetricName().equals("CYCLO_Method"))) { return true; } }
	 * } return false; }
	 * 
	 * public boolean isGod_Class(Rule r) { if (r.getThresholds().size() != 2 ||
	 * r.getOperators().get(0).equals(logicOperator.AND)) return false; if
	 * (!thresholds.get(0).equals(thresholds.get(1))) { if
	 * (thresholds.get(0).getComparator().equals(Threshold.comparator.BIGGER) &&
	 * thresholds.get(1).getComparator().equals(Threshold.comparator.BIGGER)) { if
	 * ((thresholds.get(0).getMetricName().equals("WMC_class") ||
	 * thresholds.get(0).getMetricName().equals("NOM_class")) &&
	 * (thresholds.get(1).getMetricName().equals("WMC_class") ||
	 * thresholds.get(1).getMetricName().equals("NOM_class"))) { return true; } } }
	 * 
	 * return false; }
	 * 
	 **/

//		StringBuilder st= new StringBuilder();
//		if (metrics.size() > 1) {
//			for (int i = 0, j = 0; j < metrics.size() / 2; j++, i += 2) {
////				System.out.println(metrics.get(i) + operators.get(j) + metrics.get(i + 1));
//				st.append(metrics.get(i));
//				st.append(" ");
//				st.append(operators.get(j).toString());
//				st.append(" ");
//				st.append(metrics.get(i + 1));
//				st.append(" ");
//				st.append(operators.get(j+1).toString());
//				st.append(" ");
//
//
//			}
//			System.out.println(st);
//		}
//	}

	public void fuelArrays() {
		metricName.add("NOM_class");
		metricName.add("LOC_class");
		metricName.add("WMC_class");
		comp.add(comparators.BIGGER);
		comp.add(comparators.BIGGER);
		comp.add(comparators.SMALLER);
		limits.add(20);
		limits.add(30);
		limits.add(40);
		oper.add(operators.AND);
		oper.add(operators.OR);
	}

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparators> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operators> oper = new ArrayList<>();

		Rule r = new Rule(metricName, comp, limits, oper);

	}

}
