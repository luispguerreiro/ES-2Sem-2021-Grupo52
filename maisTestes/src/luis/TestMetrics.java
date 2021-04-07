package luis;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TestMetrics {

	private List<Metrics> metrics = new ArrayList<>();

	public TestMetrics(String Pa) throws FileNotFoundException {
		metrics.addAll(new Metrics("C:\\Users\\luisg\\Desktop\\SourceCodeParser.java").getClassMetrics());
		for (Metrics m : metrics) {
			for (int i = 0; i < m.getNumOfMethods(); i++) {
				System.out.println("Package: " + m.getClassPackage() + " Class: " + m.getClassName() + " tem NOM_Class = " + m.getNumOfMethods()
						+ ", WMC_Class = " + m.getCYCLO_Class() + ", LOC_Class = " + m.getLOC_Class() + " || Metodo: "
						+ m.getMethodsName().get(i) +
						" CYCLO_Method = " + m.getCYCLO_Method_Results().get(i)
						+ ", LOC_Method = " + m.getLOC_Method_Results().get(i));
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		new TestMetrics();
	}

}
