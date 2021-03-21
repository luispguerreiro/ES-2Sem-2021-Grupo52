package maisTestes;

public class Threshold {
	public enum comparator {BIGGER, SMALLER, EQUALS}

	private String metricName; 
	private int metricValue;
	private comparator o;
	private int limit;
	
	
	
	public Threshold(String metricName, int metricValue, comparator o, int limit) {
		this.metricName=metricName;
		this.metricValue=metricValue;
		this.o=o;
		this.limit = limit;
		System.out.println("Threshold: " + metricValue + " " + o + " " + limit);
	}
	
	
	public boolean isBigger(Threshold t) {
		return metricValue > limit;
	}
	
	public boolean isSmaller(Threshold t) {
		return metricValue < limit;
	}
	
	public boolean isEquals(Threshold t) {
		return metricValue == limit;
	}
	
	public void comparator(Threshold t){
//		if(t.getComparator().equals(null))
//			throw new IllegalArgumentException("Escolha um dos comparadores");
		if(t.getComparator().equals(comparator.BIGGER))
			System.out.println(isBigger(t));
		if(t.getComparator().equals(comparator.SMALLER))
			System.out.println(isSmaller(t));
		if(t.getComparator().equals(comparator.EQUALS))
			System.out.println(isEquals(t));
	}
	
	
	public int getMetric() {
		return metricValue;
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
		Threshold t = new Threshold("LOC_Methos", 30 , comparator.BIGGER, 50);
		t.comparator(t);
	
	}

}
