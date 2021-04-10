package rules;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Metrics.Resultado;
import central.BoolResultado;

public interface IRule {
	ArrayList<Threshold> getThresholds();


	void createThresholds() throws FileNotFoundException;
	
	public void calculateThresholds(ArrayList<Resultado> result, ArrayList<BoolResultado> boolresult)
			throws FileNotFoundException;
	
	public boolean logic1(Threshold t, int valor) throws FileNotFoundException;
		
	public boolean logic2(Threshold t, Threshold t1, int valor, int valor1) throws FileNotFoundException;
	
	public boolean logic3(Threshold t, Threshold t1, Threshold t2, int valor1, int valor2, int valor3)throws FileNotFoundException;
	
	boolean and(boolean one, boolean two);

	boolean or(boolean one, boolean two);

	String getRuleName();
	
	public int getRuleType();

	void setRuleName(String ruleName);

	void fuelArrays();

	void check();

	void setLimits(ArrayList<Integer> limits);

}
