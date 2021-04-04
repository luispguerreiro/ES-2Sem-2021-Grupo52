package maisTestes;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Metrics.CYCLO_method;
import Metrics.Loc_Class;
import Metrics.Loc_Method;
import Metrics.Metrics;
import Metrics.NOM_Class;
import Metrics.Resultado;
import Metrics.WMC_Class;
import maisTestes.GuiOutput.comparators;

public class Threshold {
	private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ConstantPoolGenerator.java";

	public enum comparator {
		BIGGER, SMALLER, EQUALS
	}

//	private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ConstantPoolGenerator.java";

	private String metricName;
	private comparators o;
	private int limit;

	public Threshold(String metricName, comparators o, int limit) {
		this.metricName = metricName;
		this.o = o;
		this.limit = limit;
		System.out.println("Threshold: " + metricName + " " + o + " " + limit);
	}

	public ArrayList<Resultado> callMetric() throws FileNotFoundException {
		Metrics m = new Metrics(FILE_PATH);
		if (metricName.equals("LOC_class")) {
			Loc_Class a = new Loc_Class(m);
			System.out.println(a.getResultados());
			System.out.println("nome classe:" + m.getS());
			return a.getResultados();
//			return 10;
		} else if (metricName.equals("NOM_class")) {
			NOM_Class b = new NOM_Class(m);
			return b.getResultados();
		} else if (metricName.equals("WMC_class")) {
			WMC_Class c = new WMC_Class(m);
			return c.getResultados();
		} else if (metricName.equals("LOC_method")) {
			Loc_Method d = new Loc_Method(new Metrics(FILE_PATH));
			return d.getResultados();
		} else if (metricName.equals("CYCLO_method")) {
			CYCLO_method e = new CYCLO_method(m);
			e.Resolve();
			return e.getResultados();
		}
		throw new IllegalArgumentException("Conflito ao identificar a métrica. \nTente novamente!");
	}

	/*
	 * public boolean result() throws FileNotFoundException { if (o ==
	 * comparators.BIGGER) { return isBigger(); } if (o == comparators.EQUALS) {
	 * return isEquals(); } if (o == comparators.SMALLER) { return isSmaller(); }
	 * throw new IllegalStateException(); }
	 * 
	 * 
	 * public boolean isBigger() throws FileNotFoundException { return callMetric()
	 * > limit; }
	 * 
	 * public boolean isSmaller() throws FileNotFoundException { return callMetric()
	 * < limit; }
	 * 
	 * public boolean isEquals() throws FileNotFoundException { return callMetric()
	 * == limit; }
	 */
	public int getLimit() {
		return limit;
	}

	public comparators getComparator() {
		return o;
	}

	public String getMetricName() {
		return metricName;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Threshold t = new Threshold("LOC_method", comparators.SMALLER, 400);
		System.out.println();
	}

}
