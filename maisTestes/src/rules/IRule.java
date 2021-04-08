package rules;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IRule {
	ArrayList<Threshold> getThresholds();

	void createThresholds() throws FileNotFoundException;

	boolean logic();

	boolean and(boolean one, boolean two);

	boolean or(boolean one, boolean two);

	String getRuleName();

	void setRuleName(String ruleName);

	void fuelArrays();

	void check();

	void setLimits(ArrayList<Integer> limits);
}
