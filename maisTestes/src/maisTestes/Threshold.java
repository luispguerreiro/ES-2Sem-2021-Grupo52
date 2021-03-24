package maisTestes;

//import maisTestes.GuiOutput.comparators;
//para importar os enums de outras classes

public class Threshold {
	public enum comparator {
		BIGGER, SMALLER, EQUALS
	}

	private String metricName;
	private comparator o;
	private int limit;

	public Threshold(String metricName, comparator o, int limit) {
		this.metricName = metricName;
		this.o = o;
		this.limit = limit;
		System.out.println("Threshold: " + metricName + " " + o + " " + limit);
	}

	public int callMetric() {
		if (metricName.equals("LOC_class")) {
//			return Metrics.loc();
			return 10;
		} else if (metricName.equals("NOM_class")) {
//			return Metrics.nom();
			return 20;
		} else if (metricName.equals("WMC_class")) {
//			return Metrics.wmc();
			return 10;
		} else if (metricName.equals("LOC_method")) {
//			return Metrics.locMethod();
			return 20;
		} else if (metricName.equals("CYCLO_method")) {
//			return Metrics.cyclo();
			return 10;
		}
		throw new IllegalArgumentException("Conflito ao identificar a métrica. \nTente novamente!");
	}
	
	public boolean result() {
		if(o == comparator.BIGGER) {
			return isBigger();
		}
		if(o == comparator.EQUALS) {
			return isEquals();
		}
		if(o == comparator.SMALLER) {
			return isSmaller();
		}
		throw new IllegalStateException();
	}

	public boolean isBigger() {
		return callMetric() > limit;
	}

	public boolean isSmaller() {
		return callMetric() < limit;
	}

	public boolean isEquals() {
		return callMetric() == limit;
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

	public static void main(String[] args) {
		Threshold t =  new Threshold("LOC_class", comparator.SMALLER, 20);
		System.out.println(t.result());
	}

}
