package rules;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Metrics.Resultado;
import central.BoolResultado;
import rules.Rule.comparator;
import rules.Rule.operator;

public interface IRule {
	public ArrayList<Threshold> getThresholds();

	public void createThresholds() throws FileNotFoundException;

	public void calculateThresholds(ArrayList<Resultado> result, ArrayList<BoolResultado> boolresult)
			throws FileNotFoundException;

	public boolean logic1(Threshold t, int valor) throws FileNotFoundException;

	public boolean logic2(Threshold t, Threshold t1, int valor, int valor1) throws FileNotFoundException;

	public boolean logic3(Threshold t, Threshold t1, Threshold t2, int valor1, int valor2, int valor3)
			throws FileNotFoundException;

	public boolean and(boolean one, boolean two);

	public boolean or(boolean one, boolean two);

	public String getRuleName();

	public int getRuleType();

	public ArrayList<comparator> getComp();

	public ArrayList<Integer> getLimits();

	public ArrayList<String> getMetricName();

	public ArrayList<operator> getOper();

	public void setRuleName(String ruleName);

	public void fuelArrays();

	public void check();

	public void setLimits(ArrayList<Integer> limits);

	public void setThresholds(ArrayList<Threshold> thresholds);

}
