package rules;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import Metrics.Resultado;
import central.BoolResultado;
import rules.GuiOutput.comparators;
import rules.GuiOutput.operators;

	public class Rule implements IRule, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Threshold> thresholds = new ArrayList<>();

	protected ArrayList<String> metricName = new ArrayList<>();
	protected ArrayList<comparators> comp = new ArrayList<>();
	protected ArrayList<Integer> limits = new ArrayList<>();
	protected ArrayList<operators> oper = new ArrayList<>();
	private String ruleName;
	private ArrayList<BoolResultado> ruleResults = new ArrayList<>();
	private int ruleType; // 0 if ruleType=god_class, 1 if ruleType=long_method

	public Rule(String ruleName, int ruleType, ArrayList<String> metricName, ArrayList<comparators> comp,
			ArrayList<Integer> limits, ArrayList<operators> oper) throws FileNotFoundException {
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
		System.out.println(result.get(2).getAllInts()[4]);
		for (int j = 0; j < result.size(); j++) {
			int pos = thresholds.get(0).positionToGet();
			if (thresholds.size() == 1) {
				boolresult.get(j).setVerificacao(logic1(thresholds.get(0), result.get(j).getAllInts()[pos]));
			}
			else if (thresholds.size() == 2) {
				int pos2 = thresholds.get(1).positionToGet();
				boolresult.get(j).setVerificacao(logic2(thresholds.get(0), thresholds.get(1),
						result.get(j).getAllInts()[pos],
						result.get(j).getAllInts()[pos2]));
			} 
			else if (thresholds.size() == 3) {
				int pos2 = thresholds.get(1).positionToGet();
				int pos3 = thresholds.get(2).positionToGet();
				boolresult.get(j).setVerificacao(logic3(thresholds.get(0), thresholds.get(1), thresholds.get(2),
						result.get(j).getAllInts()[pos], result.get(j).getAllInts()[pos2], result.get(j).getAllInts()[pos3]));
			}
		}
		System.out.println("Resultados booleanos: " + boolresult.get(20).getVerificacao() + " size " + boolresult.size());
	}

	@Override
	public boolean logic1(Threshold t, int valor) throws FileNotFoundException {
		return t.result(valor);
	}

	@Override
	public boolean logic2(Threshold t, Threshold t1, int valor, int valor1) throws FileNotFoundException {
		if (oper.get(0).equals(operators.AND))
			return and(t.result(valor), t1.result(valor1));
		if (oper.get(0).equals(operators.OR))
			return or(t.result(valor), t1.result(valor1));
		throw new IllegalAccessError("Erro ao comparar thresholds\n");
	}



	public boolean logic3 (Threshold t1, Threshold t2, Threshold t3, int valor1, int valor2, int valor3) throws FileNotFoundException{
		boolean aux= false;
		if (oper.get(0).equals(operators.AND))
			aux= and(t1.result(valor1), t2.result(valor2));
		else if (oper.get(0).equals(operators.OR))
			aux= or(t1.result(valor1), t2.result(valor2));
		if (oper.get(1).equals(operators.AND)) 
			return and(aux, t3.result(valor3));
		if (oper.get(1).equals(operators.OR)) 
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

	@Override
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

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
