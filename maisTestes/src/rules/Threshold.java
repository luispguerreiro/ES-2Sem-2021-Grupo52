package rules;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import Metrics.Resultado;
import central.BoolResultado;
import rules.Rule.comparator;

public class Threshold implements Serializable {

//	private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ConstantPoolGenerator.java";
	private static String SRC_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10\\src\\com\\jasml\\classes\\ConstantPoolGenerator.java";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String metricName;
	private comparator o;
	private int limit;

	private ArrayList<Resultado> resultados = new ArrayList<>(); // vai ser ArrayList<Resultado>

	public Threshold(String metricName, comparator o, int limit) throws FileNotFoundException {
		this.setMetricName(metricName);
		this.o = o;
		this.limit = limit;
		System.out.println("Threshold: " + metricName + " " + o + " " + limit);
	}

	public boolean isBigger(int z) throws FileNotFoundException {

		return z > limit;
	}

	public boolean isSmaller(int z) throws FileNotFoundException {
		return z < limit;
	}

	public boolean isEquals(int z) throws FileNotFoundException {
		return z == limit;
	}

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
		throw new IllegalStateException();
	}

	public void setMetricBoolean(ArrayList<BoolResultado> r) {
		int position = positionToGet();
	}

	public int positionToGet() {
		if (getMetricName().equals("NOM_class"))
			return 0;
		else if (getMetricName().equals("LOC_class"))
			return 1;
		else if (getMetricName().equals("WMC_class"))
			return 2;
		else if (getMetricName().equals("LOC_method"))
			return 3;
		else if (getMetricName().equals("CYCLO_method"))
			return 4;
		throw new IllegalArgumentException("metric name não encontrado\n");
	}

	public int getLimit() {
		return limit;
	}

	public comparator getComparator() {
		return o;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public void setComparator(comparator o) {
		this.o= o;
	}
	
	public void setLimit(int limit) {
		this.limit=limit;
		
	}

	public ArrayList<Resultado> getResultados() {
		return resultados;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Threshold t = new Threshold("LOC_method", comparator.SMALLER, 10);

	}

	

}
