//package Metrics;
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//
//public class CallMetrics {
//
//	private Metrics m;
//	private String metricName;
//
//	public CallMetrics(String filePath, String metricName) throws FileNotFoundException {
//		m = new Metrics(filePath);
//		this.metricName = metricName;
//		init();
//	}
//	//
//	public ArrayList<Resultado> init() { // vai ser ArrayList<Resultado>
//		if (metricName.equals("LOC_class")) {
//			Loc_Class a = new Loc_Class(m);
//			System.out.println(a.getResultados());
//			System.out.println("nome classe:" + m.getS());
//			return a.getResultados();
//		} else if (metricName.equals("NOM_class")) {
//			NOM_Class b = new NOM_Class(m);
//			return b.getResultados();
//		} else if (metricName.equals("WMC_class")) {
//			WMC_Class c = new WMC_Class(m);
//			return c.getResultados();
//		} else if (metricName.equals("LOC_method")) {
//			Loc_Method d = new Loc_Method(m);
//			d.getResultados();
//			return d.getResultados();
//		} else if (metricName.equals("CYCLO_method")) {
//			CYCLO_method e = new CYCLO_method(m);
//			return e.getResultados();
//		}
//		throw new IllegalArgumentException("Conflito ao identificar a métrica. \nTente novamente!");
//	}
//
//	public static void main(String[] args) {
//
//	}
//
//}
