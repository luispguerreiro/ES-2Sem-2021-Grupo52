package rules;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import Metrics.Resultado;
import central.BoolResultado;
import rules.Rule.comparator;
import rules.Rule.operator;

public class Rule implements IRule, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum operator {
		XXX, OR, AND
	};

	public enum comparator {
		XXX, BIGGER, SMALLER, EQUALS, BIGGEREQUALS, SMALLEREQUALS
	};

	private ArrayList<Threshold> thresholds = new ArrayList<>();

	protected ArrayList<String> metricName = new ArrayList<>();
	protected ArrayList<comparator> comp = new ArrayList<>();
	protected ArrayList<Integer> limits = new ArrayList<>();
	protected ArrayList<operator> oper = new ArrayList<>();
	private String ruleName;
	private ArrayList<BoolResultado> ruleResults = new ArrayList<>();
	private int ruleType; // 0 if ruleType=god_class, 1 if ruleType=long_method

	public Rule(String ruleName, int ruleType, ArrayList<String> metricName, ArrayList<comparator> comp,
			ArrayList<Integer> limits, ArrayList<operator> oper) throws FileNotFoundException {
		this.ruleName = ruleName;
		this.metricName = metricName;
		this.comp = comp;
		this.limits = limits;
		this.oper = oper;
		this.ruleType = ruleType;

//		fuelArrays();
		check();

		createThresholds();
	}

	@Override
	public ArrayList<Threshold> getThresholds() {
		return thresholds;
	}

	@Override
	public void createThresholds() throws FileNotFoundException {
		for (int i = 0; i < metricName.size(); i++) {
			Threshold t = new Threshold(metricName.get(i), comp.get(i), limits.get(i));
			thresholds.add(t);
		}
	}

	public void calculateThresholds(ArrayList<Resultado> result, ArrayList<BoolResultado> boolresult)
			throws FileNotFoundException {
		for (int j = 0; j < result.size(); j++) {
			int pos = thresholds.get(0).positionToGet();
			if (thresholds.size() == 1) {
				boolresult.get(j).setVerificacao(logic1(thresholds.get(0), result.get(j).getAllInts()[pos]));
			} else if (thresholds.size() == 2) {
				int pos2 = thresholds.get(1).positionToGet();
				boolresult.get(j).setVerificacao(logic2(thresholds.get(0), thresholds.get(1),
						result.get(j).getAllInts()[pos], result.get(j).getAllInts()[pos2]));
			} else if (thresholds.size() == 3) {
				int pos2 = thresholds.get(1).positionToGet();
				int pos3 = thresholds.get(2).positionToGet();
				boolresult.get(j)
						.setVerificacao(logic3(thresholds.get(0), thresholds.get(1), thresholds.get(2),
								result.get(j).getAllInts()[pos], result.get(j).getAllInts()[pos2],
								result.get(j).getAllInts()[pos3]));
			}
		}
	}

	public boolean logic1(Threshold t, int valor) throws FileNotFoundException {
		return t.result(valor);
	}

	public boolean logic2(Threshold t, Threshold t1, int valor, int valor1) throws FileNotFoundException {
		if (oper.get(0).equals(operator.AND))
			return and(t.result(valor), t1.result(valor1));
		if (oper.get(0).equals(operator.OR))
			return or(t.result(valor), t1.result(valor1));
		throw new IllegalAccessError("Erro ao comparar thresholds\n");
	}

	public boolean logic3(Threshold t1, Threshold t2, Threshold t3, int valor1, int valor2, int valor3)
			throws FileNotFoundException {
		boolean aux = false;
		if (oper.get(0).equals(operator.AND))
			aux = and(t1.result(valor1), t2.result(valor2));
		else if (oper.get(0).equals(operator.OR))
			aux = or(t1.result(valor1), t2.result(valor2));
		if (oper.get(1).equals(operator.AND))
			return and(aux, t3.result(valor3));
		if (oper.get(1).equals(operator.OR))
			return or(aux, t3.result(valor3));
		throw new IllegalAccessError("Erro ao comparar thresholds\n");
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

	public int getRuleType() {
		return ruleType;
	}

	public ArrayList<comparator> getComp() {
		return comp;
	}

	public ArrayList<Integer> getLimits() {
		return limits;
	}

	public ArrayList<String> getMetricName() {
		return metricName;
	}

	public ArrayList<operator> getOper() {
		return oper;
	}

	@Override
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Override
	public void fuelArrays() {
		metricName.add("NOM_class");
		metricName.add("LOC_class");
		metricName.add("WMC_class");
		comp.add(comparator.BIGGER);
		comp.add(comparator.BIGGER);
		comp.add(comparator.SMALLER);
		limits.add(20);
		limits.add(30);
		limits.add(40);
		oper.add(operator.AND);
		oper.add(operator.OR);
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

	public static void main(String[] args) throws FileNotFoundException {
		String ruleName = "RegraNew";
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparator> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operator> oper = new ArrayList<>();
		metricName.add("NOM_class");
		metricName.add("LOC_class");
		metricName.add("WMC_class");
		comp.add(comparator.BIGGER);
		comp.add(comparator.BIGGER);
		comp.add(comparator.SMALLER);
		limits.add(20);
		limits.add(30);
		limits.add(40);
		oper.add(operator.OR);
		oper.add(operator.AND);

		Rule r = new Rule(ruleName, 0, metricName, comp, limits, oper);
		Threshold t = new Threshold("LOC_method", comparator.BIGGER, 10);
		Threshold t1 = new Threshold("CYCLO_method", comparator.BIGGER, 10);

		System.out.println(r.logic2(t, t1, 12, 12));

	}

}
