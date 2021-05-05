package rules;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import central.BoolResultado;
import metrics.Resultado;
import rules.Rule.comparator;

/**
 * Allows user to create thresholds to compare the metric with the limit desired
 */
public class Threshold implements Serializable {

	/**
	 * @author Grupo 52
	 */

	private static String SRC_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10\\src\\com\\jasml\\classes\\ConstantPoolGenerator.java";

	private static final long serialVersionUID = 1L;
	/**
	 * the metric Name chosen by the user to be calculated
	 */
	private String metricName;
	/**
	 * the comparator chosen by the user to be calculated
	 */
	private comparator o;
	/**
	 * the limit defined by the user to be calculated
	 */
	private int limit;

	/** the array in which the results of the calculated thresholds will be added */
	private ArrayList<Resultado> resultados = new ArrayList<>();

	/**
	 * Allows user to create thresholds to compare the metric with the limit desired
	 * 
	 * @param metricName the metric Name chosen by the user to be calculated
	 * @param o          the comparator chosen by the user to be calculated
	 * @param limit      the limit defined by the user to be calculated
	 * @throws FileNotFoundException if the metric can't be calculated due to the
	 *                               file not being found
	 */
	public Threshold(String metricName, comparator o, int limit) throws FileNotFoundException {
		this.metricName = metricName;
		this.o = o;
		this.limit = limit;
		System.out.println("Threshold: " + metricName + " " + o + " " + limit);
	}

	/**
	 * Compares if the metric result is bigger then the limit the user desires
	 * 
	 * @param z the integer that represents the number of methods of classes in the
	 *          user's project
	 * @return true if the number is bigger then the limit
	 * @throws FileNotFoundException if the metric can't be calculated due to the
	 *                               file not being found
	 */
	public boolean isBigger(int z) throws FileNotFoundException {

		return z > limit;
	}

	/**
	 * Compares if the metric result is smaller then the limit the user desires
	 * 
	 * @param z the integer that represents the number of methods of classes in the
	 *          user's project
	 * @return true if the number is smaller then the limit
	 * @throws FileNotFoundException if the metric can't be calculated due to the
	 *                               file not being found
	 */
	public boolean isSmaller(int z) throws FileNotFoundException {
		return z < limit;
	}

	/**
	 * Compares if the metric result is equals to the limit the user desires
	 * 
	 * @param z the integer that represents the number of methods of classes in the
	 *          user's project
	 * @return true if the number is equals to the limit
	 * @throws FileNotFoundException if the metric can't be calculated due to the
	 *                               file not being found
	 */
	public boolean isEquals(int z) throws FileNotFoundException {
		return z == limit;
	}

	/**
	 * Compares if the metric result is bigger or equals to the limit the user
	 * desires
	 * 
	 * @param z the integer that represents the number of methods of classes in the
	 *          user's project
	 * @return true if the number is bigger or equals to the limit
	 * @throws FileNotFoundException if the metric can't be calculated due to the
	 *                               file not being found
	 */
	public boolean isBiggerEquals(int z) throws FileNotFoundException {
		return z >= limit;
	}

	/**
	 * Compares if the metric result is smaller or equals to the limit the user
	 * desires
	 * 
	 * @param z the integer that represents the number of methods of classes in the
	 *          user's project
	 * @return true if the number is smaller or equals to the limit
	 * @throws FileNotFoundException if the metric can't be calculated due to the
	 *                               file not being found
	 */
	public boolean isSmallerEquals(int z) throws FileNotFoundException {
		return z <= limit;
	}

	/**
	 * Uses the comparator defined by the user to calculate the result of the
	 * threshold
	 * 
	 * @param z the integer that represents the number of methods of classes in the
	 *          user's project
	 * @return the boolean result of the threshold based of the comparator used
	 * @throws FileNotFoundException if the metric can't be calculated due to the
	 *                               file not being found
	 */
	public boolean result(int z) throws FileNotFoundException {
		if (o == comparator.BIGGER) {
			return isBigger(z);
		}
		if (o == comparator.EQUALS) {
			return isEquals(z);
		}
		if (o == comparator.SMALLER) {
			return isSmaller(z);
		}
		if (o == comparator.SMALLEREQUALS) {
			return isSmallerEquals(z);
		}
		if (o == comparator.BIGGEREQUALS) {
			return isBiggerEquals(z);
		}

		throw new IllegalStateException();
	}

	/**
	 * Sets a new arrayList of BoolResults and gets the metric type
	 * 
	 * @param r the Arraylist of BoolResults
	 * 
	 */
	public void setMetricBoolean(ArrayList<BoolResultado> r) {
		int position = positionToGet();
	}

	/**
	 * Gets the metric type chosen and transforms to a integer
	 * 
	 * @return the metric type in integer
	 */
	public int positionToGet() {
		if (metricName.equals("NOM_class"))
			return 0;
		else if (metricName.equals("LOC_class"))
			return 1;
		else if (metricName.equals("WMC_class"))
			return 2;
		else if (metricName.equals("LOC_method"))
			return 3;
		else if (metricName.equals("CYCLO_method"))
			return 4;
		throw new IllegalArgumentException("metric name não encontrado\n");
	}

	/**
	 * Getter for the limit defined by the user
	 * 
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Getter for the comparator defined by the user
	 * 
	 * @return the comparator used
	 */
	public comparator getComparator() {
		return o;
	}

	/**
	 * Getter for the metricName defined by the user
	 * 
	 * @return the string with the metric name
	 */
	public String getMetricName() {
		return metricName;
	}

	/**
	 * Getter for the Array with the results of the calculated thresholds
	 * 
	 * @return the array with the results
	 */
	public ArrayList<Resultado> getResultados() {
		return resultados;
	}

	/**
	 * Sets a new metric name to be calculated in the threshold
	 * 
	 * @param string the metric name defined by the user
	 */
	public void setMetricName(String string) {
		this.metricName = string;
	}

	/**
	 * Sets a new limit to be calculated in the threshold
	 * 
	 * @param limit the limit defined by the user
	 */
	public void setLimit(int limit) {
		this.limit = limit;

	}

	/**
	 * Sets a new comparator to calculate the threshold
	 * 
	 * @param o the comparator defined by the user
	 */
	public void setComparator(comparator o) {
		this.o = o;
	}
}