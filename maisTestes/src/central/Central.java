package central;

import java.io.FileNotFoundException;

import Metrics.CYCLO_method;
import Metrics.Loc_Class;
import Metrics.Loc_Method;
import Metrics.Metrics;
import Metrics.NOM_Class;
import Metrics.WMC_Class;

public class Central {

	
	private String SRC_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\miniJasml";
	private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\ConstantPoolGenerator.java";
	private Loc_Method locMethod;
	private CYCLO_method cycloMethod;
	private Loc_Class locClass;
	private NOM_Class nomClass;
	private WMC_Class wmcClass;
	
	private Metrics metric;
	
	public Central() throws FileNotFoundException{
		metric = new Metrics(FILE_PATH);
		locMethod = new Loc_Method(metric);
		cycloMethod = new CYCLO_method(metric);
		locClass = new Loc_Class(metric);
		nomClass = new NOM_Class(metric);
		wmcClass = new WMC_Class(metric);
		
	}
	
	public void sys(){
		System.out.println(locMethod.getResultados().get(0).getPath());
	}		
	
	public static void main(String[] args) throws FileNotFoundException {
		Central c = new Central();
		c.sys();
	}

}
