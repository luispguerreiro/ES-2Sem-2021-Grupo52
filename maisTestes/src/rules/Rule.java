package rules;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Metrics.Resultado;
import central.BoolResultado;

public class Rule implements Serializable {

	/**
	 * @author Grupo 52
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * User can only select OR and AND as operators for the Rules
	 */
	public enum operator {
		Select, OR, AND
	};

	/**
	 * User can only select the options bellow as comparators for the Rules "Select"
	 * is used in GUI class as default
	 */
	public enum comparator {
		Select, BIGGER, SMALLER, EQUALS, BIGGEREQUALS, SMALLEREQUALS
	};

	private ArrayList<Threshold> thresholds = new ArrayList<>();
	/** the array with thresholds */

	protected ArrayList<String> metricName = new ArrayList<>();
	/** the array with the metric names */
	protected ArrayList<comparator> comp = new ArrayList<>();
	/** the array with the comparators */
	protected ArrayList<Integer> limits = new ArrayList<>();
	/** the array with the limits */
	protected ArrayList<operator> oper = new ArrayList<>();
	/** the array with the operators */
	private String ruleName;
	/** the rule Name */
	private ArrayList<BoolResultado> ruleResults = new ArrayList<>();
	/** the array with the boolean results */
	private int ruleType;

	/** type of rule created (0 if ruleType=god_class, 1 if ruleType=long_method) */

	/**
	 * Allows user to create a rule with multiple thresholds
	 * 
	 * @throws FileNotFoundException if the file imported isn't found
	 */

	public Rule(String ruleName, int ruleType, ArrayList<String> metricName, ArrayList<comparator> comp,
			ArrayList<Integer> limits, ArrayList<operator> oper) throws FileNotFoundException {
		this.ruleName = ruleName;
		this.metricName = metricName;
		this.comp = comp;
		this.limits = limits;
		this.oper = oper;
		this.ruleType = ruleType;

		check();

		createThresholds();
	}

	/**
	 * Getter for the array of the Thresholds
	 * 
	 * @return arrayList with all the Thresholds of the rule
	 */
	public ArrayList<Threshold> getThresholds() {
		return thresholds;
	}

	/**
	 * creates the Thresholds and adds them to the Rule
	 * 
	 * @throws FileNotFoundException if the file imported isn't found
	 */
	public void createThresholds() throws FileNotFoundException {
		for (int i = 0; i < metricName.size(); i++) {
			Threshold t = new Threshold(metricName.get(i), comp.get(i), limits.get(i));
			thresholds.add(t);
		}
	}

	/**
	 * Gets the results of the calculated Thresholds and inserts the result in a
	 * boolResult array
	 * 
	 * @param result     the array with the results of all the calculated thresholds
	 *                   of the Rule
	 * @param boolresult the array with the boolean result of the calculated
	 *                   thresholds of the Rule
	 * @throws FileNotFoundException if the file imported isn't found
	 */
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

	/**
	 * calculates the logic of a threshold
	 * 
	 * @param t     the threshold to be calculated
	 * @param valor the limit of the respective threshold
	 * @return the boolean result of the calculated threshold
	 * @throws FileNotFoundException if the file imported isn't found
	 */
	public boolean logic1(Threshold t, int valor) throws FileNotFoundException {
		return t.result(valor);
	}

	/**
	 * calculates the logic between two thresholds
	 * 
	 * @param t      the thresholds to be calculated
	 * @param t1     the thresholds to be calculated
	 * @param valor  the limits of the respective thresholds
	 * @param valor1 the limits of the respective thresholds
	 * @return the boolean logic between the thresholds using the operator selected
	 * @throws FileNotFoundException if the file imported isn't found
	 */
	public boolean logic2(Threshold t, Threshold t1, int valor, int valor1) throws FileNotFoundException {
		if (oper.get(0).equals(operator.AND))
			return and(t.result(valor), t1.result(valor1));
		if (oper.get(0).equals(operator.OR))
			return or(t.result(valor), t1.result(valor1));
		throw new IllegalAccessError("Erro ao comparar thresholds\n");
	}

	/**
	 * calculates the logic between three thresholds
	 * 
	 * @param t1     the thresholds to be calculated
	 * @param t2     the thresholds to be calculated
	 * @param t3     the thresholds to be calculated
	 * @param valor1 the limits of the respective thresholds
	 * @param valor2 the limits of the respective thresholds
	 * @param valor3 the limits of the respective thresholds
	 * @return the boolean logic between the thresholds using the operators selected
	 * @throws FileNotFoundException if the file imported isn't found
	 */
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

	/**
	 * calculates the thresholds if the operator choosen is AND
	 * 
	 * @param one the result of the first threshold calculated
	 * @param two the result of the second threshold calculated
	 * @return the result of a AND logic between two booleans
	 */
	public boolean and(boolean one, boolean two) {
		if (one == true) {
			if (two == true)
				return true;
		}
		return false;
	}

	/**
	 * calculates the thresholds if the operator choosen is OR
	 * 
	 * @param one the result of the first threshold calculated
	 * @param two the result of the second threshold calculated
	 * @return the result of a OR logic between two booleans
	 */
	public boolean or(boolean one, boolean two) {
		if (one == false) {
			if (two == false)
				return false;
		}
		return true;
	}

	/**
	 * Checks if the Rule is created correctly, with all arrays in the desired size
	 * to be calculated
	 * 
	 * @throws IllegalArgumentException if the user tries to create a rule with unwanted sizes of the arrays
	 */
	public void check() {
		if (metricName.size() == comp.size() && comp.size() == limits.size() && (limits.size() == oper.size() + 1)) {
			System.out.println("Vetores criados corretamente!");
		} else {
			JOptionPane.showMessageDialog(null, "Não selecionou uma pasta de projeto!");
			metricName.clear();
			comp.clear();
			limits.clear();
			oper.clear();
			throw new IllegalArgumentException("Não pode continuar! \nverificar tamanho dos vetores!");
		}
	}

	/**
	 * Getter for the Rule Name
	 * 
	 * @return the String with the rule name
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * Getter for the RuleType
	 * 
	 * @return the integer with the type of Rule created
	 */
	public int getRuleType() {
		return ruleType;
	}

	/**
	 * Getter for the array of the Comparators
	 * 
	 * @return arrayList with all the comparators of the rule
	 */
	public ArrayList<comparator> getComp() {
		return comp;
	}

	/**
	 * Getter for the array of the limits of the Thresholds
	 * 
	 * @return arrayList with all the limits of the Thresholds of the rule
	 */
	public ArrayList<Integer> getLimits() {
		return limits;
	}

	/**
	 * Getter for the array of the metric names
	 * 
	 * @return arrayList with all metric names in the rule
	 */
	public ArrayList<String> getMetricName() {
		return metricName;
	}

	/**
	 * Getter for the array of the Operators
	 * 
	 * @return arrayList with all the operators of the rule
	 */
	public ArrayList<operator> getOper() {
		return oper;
	}

	/**
	 * Set's a name for the rule created
	 * 
	 * @param ruleName a String chosen by the user to name the Rule created
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * Set's a array of limits for the rule created
	 * 
	 * @param limits gets the array of limits chosen by the user to calculate the
	 *               thresholds of the Rule created
	 */
	public void setLimits(ArrayList<Integer> limits) {
		this.limits = limits;
	}

	/**
	 * Set's a array of thresholds for the rule created
	 * 
	 * @param thresholds gets the array of thresholds created by the user to insert
	 *                   in the Rule created
	 */
	public void setThresholds(ArrayList<Threshold> thresholds) {
		this.thresholds = thresholds;

	}

}