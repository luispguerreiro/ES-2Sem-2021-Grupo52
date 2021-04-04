package rules;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.naming.LimitExceededException;

import rules.GuiOutput.comparators;
import rules.GuiOutput.operators;

public abstract class Rule implements IRule, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// NUM_METRICS vai ser atributo da class metrics
	private final int NUM_METRICS;

	private ArrayList<Threshold> thresholds = new ArrayList<>();

	protected ArrayList<String> metricName = new ArrayList<>();
	protected ArrayList<comparators> comp = new ArrayList<>();
	protected ArrayList<Integer> limits = new ArrayList<>();
	protected ArrayList<operators> oper = new ArrayList<>();
	private String ruleName;
	private ArrayList<Boolean> threshResults = new ArrayList<>();

	public Rule(String ruleName, ArrayList<String> metricName, ArrayList<comparators> comp, ArrayList<Integer> limits,
			ArrayList<operators> oper) throws FileNotFoundException {
		this.ruleName = ruleName;
		this.metricName = metricName;
		this.comp = comp;
		this.limits = limits;
		this.oper = oper;

		fuelArrays();
		check();

		NUM_METRICS = metricName.size();

		createThresholds();
		System.out.println("---->Resultado da Logica:" + logic());
	}

	@Override
	public ArrayList<Threshold> getThresholds() {
		return thresholds;
	}

	@Override
	public void createThresholds() throws FileNotFoundException {
		for (int i = 0; i < NUM_METRICS; i++) {
			Threshold t = new Threshold(metricName.get(i), comp.get(i), limits.get(i));
			thresholds.add(t);
//			t.result();
////			threshResults.add(t.getBoolArray().);
//			threshResults = t.getBoolArray();
//			System.out.println("-->Resultado de metrica Threshold: " + t.result() );
			calculateThresholds(t);
			logic();
		}
	}
	
	public void calculateThresholds(Threshold t) throws FileNotFoundException{
//		threshResults.addAll(t.result());
		threshResults.addAll(t.result());
		for (int i = 0; i < threshResults.size(); i++) {
			System.out.println("-->Resultado de metrica Threshold: " + threshResults.get(i));
		}
	}

	@Override
	public boolean logic() {
		System.out.println("______" + threshResults);
		System.out.println("Logic() size " + threshResults.size() + "\n oper: " + oper.get(0));
//		while (!(threshResults.size() == 1)) {
			while (threshResults.size() > 1) {
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

	@Override
	public boolean and(boolean one, boolean two) {
		if (one == true) {
			if (two == true)
				return true;
		}
		return false;
	}

	@Override
	public boolean or(boolean one, boolean two) {
		if (one == false) {
			if (two == false)
				return false;
		}
		return true;
	}

	@Override
	public String getRuleName() {
		return ruleName;
	}
	
	public ArrayList<comparators> getComp() {
		return comp;
	}
	
	public ArrayList<Integer> getLimits() {
		return limits;
	}
	
	public ArrayList<String> getMetricName() {
		return metricName;
	}
	
	public ArrayList<operators> getOper() {
		return oper;
	}
	
	public ArrayList<Boolean> getThreshResults() {
		return threshResults;
	}

	@Override
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
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

	@Override
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
//		for (int i = 0, j = 0; i <= metricName.size() && j < metricName.size() - 1; i++, j++) {
//			System.out.println("HELLOOOOO" + oper.get(0) + oper.get(1));
//			System.out.println(
//					"HELOOOOO" + metricName.get(i) + " " + comp.get(i) + " " + limits.get(i) + " " + oper.get(j));
//			System.out.print(oper.get(i));
//		}

	}

	@Override
	public void check() {
		if (metricName.size() == comp.size() && comp.size() == limits.size() && (limits.size() == oper.size() + 1)) {
			System.out.println("Vetores criados corretamente!");
		} else
			throw new IllegalArgumentException("Não pode continuar! \nverificar tamanho dos vetores!");
	}

	@Override
	public void setLimits(ArrayList<Integer> limits) {
		this.limits = limits;
	}

}
