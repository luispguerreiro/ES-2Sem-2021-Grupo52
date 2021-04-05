package maisTestes;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

<<<<<<< HEAD
import rules.GuiOutput;
import rules.Metrics;
import rules.GuiOutput.comparators;
import rules.Metrics.Loc_Method;
=======
import Metrics.CYCLO_method;
import Metrics.Loc_Class;
import Metrics.Loc_Method;
import Metrics.Metrics;
import Metrics.NOM_Class;
import Metrics.Resultado;
import Metrics.WMC_Class;
import maisTestes.GuiOutput.comparators;
>>>>>>> branch 'main' of https://github.com/luispguerreiro/ES-2Sem-2021-Grupo52

public class Threshold implements Serializable {
	
	
//	private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ConstantPoolGenerator.java";


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String metricName;
	private comparators o;
	private int limit;

	public Threshold(String metricName, comparators o, int limit) {
		this.metricName = metricName;
		this.o = o;
		this.limit = limit;
		System.out.println("Threshold: " + metricName + " " + o + " " + limit);
	}

<<<<<<< HEAD
	public int callMetric() throws FileNotFoundException  {
		Metrics m = new Metrics();
//		try {
		boolean a = true;
		if(a == true) {
=======
	public ArrayList<Resultado> callMetric() throws FileNotFoundException {
		Metrics m = new Metrics(FILE_PATH);
>>>>>>> branch 'main' of https://github.com/luispguerreiro/ES-2Sem-2021-Grupo52
		if (metricName.equals("LOC_class")) {
//				m.locClass();
			a=false;
				//System.out.println("nome classe:" + m.getS());
			return m.getLinhasClass();
//			return 10;
		} else if (metricName.equals("NOM_class")) {
//			NOM_Class n = new NOM_Class(c);
//			return Metrics.nom();
			return 20;
		} else if (metricName.equals("WMC_class")) {
//			return Metrics.wmc();
			return 10;
		} else if (metricName.equals("LOC_method")) {
<<<<<<< HEAD
			Loc_Method l = new Loc_Method();
			CompilationUnit cu = StaticJavaParser.parse(new File(m.getFilePath()));
			List<String> methodNamesLines = new ArrayList<>();
			l.visit(cu, methodNamesLines);
			
			//			return Metrics.locMethod();
			return 20;
=======
			Loc_Method d = new Loc_Method(new Metrics(FILE_PATH));
			return d.getResultados();
>>>>>>> branch 'main' of https://github.com/luispguerreiro/ES-2Sem-2021-Grupo52
		} else if (metricName.equals("CYCLO_method")) {
//			return Metrics.cyclo();
			return 10;
		}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}
		throw new IllegalArgumentException("Conflito ao identificar a métrica. \nTente novamente!");
	}
	
	public boolean result() throws FileNotFoundException {
		if(o == comparators.BIGGER) {
			return isBigger();
		}
		if(o == comparators.EQUALS) {
			return isEquals();
		}
		if(o == comparators.SMALLER) {
			return isSmaller();
		}
		throw new IllegalStateException();
	}

	public boolean isBigger() throws FileNotFoundException  {
		
		return callMetric() > limit;
	}

	public boolean isSmaller() throws FileNotFoundException {
		return callMetric() < limit;
	}

	public boolean isEquals() throws FileNotFoundException{
		return callMetric() == limit;
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

	public static void main(String[] args) throws FileNotFoundException {
		Threshold t =  new Threshold("NOM_class", comparators.SMALLER, 400);
		System.out.println(t.result());
	}

}
