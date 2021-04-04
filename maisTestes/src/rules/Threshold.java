package rules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import rules.GuiOutput.comparators;
import rules.Metrics.Loc_Method;

public class Threshold implements Serializable {

	private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ConstantPoolGenerator.java";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String metricName;
	private comparators o;
	private int limit;
	
	private ArrayList<Integer> array = new ArrayList<>();
	private ArrayList<Boolean> boolArray = new ArrayList<>();

	public Threshold(String metricName, comparators o, int limit) {
		this.metricName = metricName;
		this.o = o;
		this.limit = limit;
		System.out.println("Threshold: " + metricName + " " + o + " " + limit);
	}

	public ArrayList<Integer> callMetric() throws FileNotFoundException {
		Metrics m = new Metrics();
//		ArrayList<Integer> array = new ArrayList<>();
//		try {
		boolean a = true;
		if (a == true) {
			if (metricName.equals("LOC_class")) {
				m.locClass(FILE_PATH);
				a = false;
				System.out.println("nome classe:" + m.getS());
				array.add(m.getLinhasClass());
				return array;
//			return 10;
			} else if (metricName.equals("NOM_class")) {
//			NOM_Class n = new NOM_Class(c);
//			return Metrics.nom();
				array.add(20);
				return array;
			} else if (metricName.equals("WMC_class")) {
//			return Metrics.wmc();
				array.add(10);
				return array;
			} else if (metricName.equals("LOC_method")) {
				Loc_Method l = new Loc_Method();
				CompilationUnit cu = StaticJavaParser.parse(new File(m.getFilePath()));
				List<String> methodNamesLines = new ArrayList<>();
				l.visit(cu, methodNamesLines);
				for (int i = 1; i < l.getCollector().size(); i+=2) {
					array.add(Integer.parseInt(l.getCollector().get(i)));
						System.out.println("     " + l.getCollector().get(i));
				}
//				array.add(20);
				return array;
			} else if (metricName.equals("CYCLO_method")) {
//			return Metrics.cyclo();
				array.add(10);
				return array;
			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}
		throw new IllegalArgumentException("Conflito ao identificar a métrica. \nTente novamente!");
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

	public ArrayList<Boolean> result() throws FileNotFoundException {
		callMetric();
		for (int t=0; t<array.size(); t++) {
			System.out.println(" array_size==   " + array.size());
			if (o == comparators.BIGGER) {
				boolArray.add(isBigger(array.get(t)));
			}
			if (o == comparators.EQUALS) {
				boolArray.add(isEquals(array.get(t)));
			}
			if (o == comparators.SMALLER) {
				boolArray.add(isSmaller(array.get(t)));
			}

		}
		return boolArray;
	}

	public int getLimit() {
		return limit;
	}

	public comparators getComparator() {
		return o;
	}

	public String getMetricName() {
		return metricName;
	}
	
	public ArrayList<Boolean> getBoolArray() {
		return boolArray;
	}
	
	public ArrayList<Integer> getArray() {
		return array;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Threshold t = new Threshold("LOC_method", comparators.SMALLER, 10);
		
		System.out.println(t.result());
	}

}
