package maisTestes;

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

	public Rule(ArrayList<String> metricName, ArrayList<comparators> comp, ArrayList<Integer> limits,
			ArrayList<operators> oper) {
		this.metricName = metricName;
		this.comp = comp;
		this.limits = limits;
		this.oper = oper;
		
		fuelArrays();
		
		NUM_METRICS = metricName.size();

		createThresholds();
	}

	public ArrayList<Threshold> getThresholds() {
		return thresholds;
	}

	public void createThresholds() {
		for (int i = 0; i < NUM_METRICS; i++) {
			thresholds.add(new Threshold(metricName.get(i), comp.get(i), limits.get(i)));
			System.out.println("Threshold: " + metricName.get(i) + " " + comp.get(i) + " " + limits.get(i));
		}
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
		metricName.add("LOC_class");
		metricName.add("NOM_class");
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

	public static void main(String[] args) {
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparators> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operators> oper = new ArrayList<>();
		
//		ArrayList<Threshold> thresholds = new ArrayList<>();


		Rule r = new Rule(metricName, comp, limits, oper);
		
	}

}
