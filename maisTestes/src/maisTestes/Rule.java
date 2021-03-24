package maisTestes;

import java.util.ArrayList;

public class Rule {

	public enum logicOperator {
		AND, OR
	}

	// NUM_METRICS vai ser atributo da class metrics
	private final static int NUM_METRICS = 5;

	// Este arrayList vai ser de "metrics"
	private ArrayList<Threshold> thresholds = new ArrayList<>();
	public ArrayList<logicOperator> operators = new ArrayList<>();

	public Rule(ArrayList<Threshold> thresholds, ArrayList<logicOperator> operators) {
		this.thresholds = thresholds;
		this.operators = operators;
		fuelArrays();
		if (!(thresholds.size() == operators.size() + 1))
			throw new IllegalArgumentException("Regra mal criada");

	}

	public ArrayList<Threshold> getThresholds() {
		return thresholds;
	}

	public ArrayList<logicOperator> getOperators() {
		return operators;
	}

//	public boolean compareThresholds(Rule r) {
//		r.getThresholds();
//		
//		if(r.getOperators())
//		
//	}

	public boolean isLong_Method(Rule r) {
		if (r.getThresholds().size() != 2 || r.getOperators().get(0).equals(logicOperator.OR))
			return false;
		if (!thresholds.get(0).equals(thresholds.get(1))) {
			if (thresholds.get(0).getComparator().equals(Threshold.comparator.BIGGER)
					&& thresholds.get(1).getComparator().equals(Threshold.comparator.BIGGER)) {
				if ((thresholds.get(0).getMetricName().equals("LOC_method")
						|| thresholds.get(0).getMetricName().equals("CYCLO_Method"))
						&& (thresholds.get(1).getMetricName().equals("LOC_method")
								|| thresholds.get(1).getMetricName().equals("CYCLO_Method"))) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isGod_Class(Rule r) {
		if (r.getThresholds().size() != 2 || r.getOperators().get(0).equals(logicOperator.AND))
			return false;
		if (!thresholds.get(0).equals(thresholds.get(1))) {
			if (thresholds.get(0).getComparator().equals(Threshold.comparator.BIGGER)
					&& thresholds.get(1).getComparator().equals(Threshold.comparator.BIGGER)) {
				if ((thresholds.get(0).getMetricName().equals("WMC_class")
						|| thresholds.get(0).getMetricName().equals("NOM_class"))
						&& (thresholds.get(1).getMetricName().equals("WMC_class")
								|| thresholds.get(1).getMetricName().equals("NOM_class"))) {
					return true;
				}
			}
		}

		return false;
	}

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
//		Threshold t = new Threshold("LOC_method", 100, Threshold.comparator.BIGGER, 50);
//		Threshold t1 = new Threshold("WMC_class", 50, Threshold.comparator.BIGGER, 50);
//		Threshold t2 = new Threshold("NOM_class", 30, Threshold.comparator.BIGGER, 50);
//		getThresholds().add(t);
//		getThresholds().add(t1);
//		getThresholds().add(t2);
//		operators.add(logicOperator.AND);
		operators.add(logicOperator.OR);
//		operators.add(logicOperator.AND);
//		operators.add(logicOperator.OR);
	}

	public static void main(String[] args) {
		ArrayList<Threshold> thresholds = new ArrayList<>();
		ArrayList<logicOperator> operators = new ArrayList<>();
		Rule r = new Rule(thresholds, operators);
		System.out.println(r.isGod_Class(r));
	}

}
