package central;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Metrics.CYCLO_method;
import Metrics.Loc_Class;
import Metrics.Loc_Method;
import Metrics.Metrics;
import Metrics.NOM_Class;
import Metrics.Resultado;
import Metrics.WMC_Class;
import maisTestes.Comparador;
import rules.Rule;
import rules.Rule.comparator;
import rules.Rule.operator;

/*
 * Central is where we write the excel, apply the thresholds given by the Rules in ArrayList<Rule>
 * and prepare the booResults arrays for the following comparations (false positive etc)
 */
public class Central {

	private File srcPath = new File("C:\\Users\\henri\\Downloads\\jasml_0.10");

	private String excelFileDir = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho"; // vai estar em branco e é
																						// necessário fazer
																						// setExcelFileDir
	private File excelFile;
	private File historyFile = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");

	private CYCLO_method cycloMethod;
	private Loc_Method locMethod;
	private Loc_Class locClass;
	private NOM_Class nomClass;
	private WMC_Class wmcClass;
	
	private int separador = 0;
	private int numberOfPackages = 0;
	private int numberOfClasses = 0;
	private int numberOfMethods = 0;
	private int numberOfLines = 0;

	ArrayList<Resultado> all = new ArrayList<>();
	ArrayList<BoolResultado> boolResultClass = new ArrayList<>();
	ArrayList<BoolResultado> boolResultMethod = new ArrayList<>();
	ArrayList<Rule> rules = new ArrayList<>();

	private Metrics metric;

	public Central(ArrayList<Rule> rules, File srcPath) throws IOException {
		this.rules = rules;
		this.srcPath = srcPath;
		excelFile = new File(excelFileDir.concat("\\".concat(srcPath.getName().concat("_metrics.xlsx"))));
	}

	/*
	 * initiates the main Central flow call all methods
	 */
	public void ini() throws IOException {
		File[] v = extracts();
		XSSFWorkbook workBook = new XSSFWorkbook();
		Sheet sheet = workBook.createSheet(excelFile.getName().replaceFirst("[.][^.]+$", ""));
		for (int i = 0; i < v.length; i++) {

			metric = new Metrics(v[i].getAbsolutePath());

			locMethod = new Loc_Method(metric);
			cycloMethod = new CYCLO_method(metric);
			locClass = new Loc_Class(metric);
			nomClass = new NOM_Class(metric);
			wmcClass = new WMC_Class(metric);

			writeExcel(sheet, workBook);

			fuelAllandBoolResults();
			//numberOfClasses += wmcClass.getResultados().size();
		}
		putMethodID();
		chooseRules(rules);
		numberOfSomething();
		numberOfMethods=boolResultMethod.size();
		
		// sys();

		OutputStream fileOut = new FileOutputStream(excelFile);
		workBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		System.out.println("\n***Exporta��o para Excel conclu�da!***\n");

		
		System.out.println("number of methods = " + numberOfMethods);
		System.out.println("number of packages = " + numberOfPackages);
		System.out.println("number of classes = " + numberOfClasses);
		System.out.println("number of lines = " + numberOfLines);
//		Comparador c = new Comparador(boolResultMethod, boolResultClass);;

	}

	public void sys() {
		for (int i = 0; i < all.size(); i++) {
			System.out.println("ID  " + all.get(i).getMethodID());
			System.out.println("CCC Path: " + boolResultClass.get(i).getClasses());
			System.out.println("CCC Path: " + boolResultClass.get(i).getMetodo());
			System.out.println("CCC Boolean:  " + boolResultClass.get(i).getVerificacao());
			System.out.println("MMM Path: " + boolResultMethod.get(i).getClasses());
			System.out.println("MMM Path: " + boolResultMethod.get(i).getMetodo());
			System.out.println("MMM Boolean:  " + boolResultMethod.get(i).getVerificacao());
			for (int j = 0; j < all.get(i).getAllInts().length; j++) {
				System.out.println("INTS--  " + all.get(i).getAllInts()[j]);
			}
		}
	}

	/*
	 * Invoke calculateThresholds(ArrayList<Resultado> result,
	 * ArrayList<BoolResultado> boolresult) for each position of ArrayList<Rule> and
	 * considering the ruleType (0=godClass and 1=LongMethod)
	 * 
	 * @param rules zero, one or two rules given by the user and created in class
	 * GUI
	 */
	public void chooseRules(ArrayList<Rule> rules) throws FileNotFoundException {
		if (rules.isEmpty())
			return;
		for (int i = 0; i < rules.size(); i++) {
			if (rules.get(i).getRuleType() == 0)
				rules.get(i).calculateThresholds(all, boolResultClass);
			if (rules.get(i).getRuleType() == 1)
				rules.get(i).calculateThresholds(all, boolResultMethod);
		}
	}

	/*
	 * Fills the ArrayList<Resultado> All with data like one of the metrics arrays
	 * (in this case "cyclomethod") the data copied to "all" are the same in all
	 * vectors, then fill in "all" the array with 5 integers in which each position
	 * corresponds to a metric
	 * 
	 * Fills boolResults arrays with the same information that is equals in all
	 * metrics arrays and then initializes the arrays' boolean with "false".
	 */
	public void fuelAllandBoolResults() {
		int k = 0;
		for (int i = 0; i < cycloMethod.getResultados().size(); i++) {

			if (!(cycloMethod.getResultados().get(i).getClasses()
					.equals(cycloMethod.getResultados().get(k).getClasses()))
					&& k < nomClass.getResultados().size() - 1) {
				k++;
			}
			
			int LinhasNomC = (nomClass.getResultados().get(k).getLinhas());
			int LinhasLocC = (locClass.getResultados().get(k).getLinhas());
			int LinhasWMC = (wmcClass.getResultados().get(k).getLinhas());
			int LinhasMethod = (locMethod.getResultados().get(i).getLinhas());
			int LinhasCyclo = (cycloMethod.getResultados().get(i).getLinhas());

			int[] vetorResultado = { LinhasNomC, LinhasLocC, LinhasWMC, LinhasMethod, LinhasCyclo };

			all.add(new Resultado(i, cycloMethod.getResultados().get(i).getPath(),
					cycloMethod.getResultados().get(i).getLinhas(), vetorResultado));

			boolResultClass.add(new BoolResultado(cycloMethod.getResultados().get(i).getPackage(),
					cycloMethod.getResultados().get(i).getClasses(),
					cycloMethod.getResultados().get(i).getMethodNames(), false));
			boolResultMethod.add(new BoolResultado(cycloMethod.getResultados().get(i).getPackage(),
					cycloMethod.getResultados().get(i).getClasses(),
					cycloMethod.getResultados().get(i).getMethodNames(), false));
		}
	}

	/*
	 * enumerates all array with a MethodID
	 */
	public void putMethodID() {
		for (int i = 0; i < all.size(); i++)
			all.get(i).setMethodID(i + 1);
	}

	/*
	 * write to excel all the necessary information call cabecalho (Sheet sheet,
	 * XSSFWorkbook workBook) to fill the excel header
	 * 
	 * @param sheet Excel sheet created when excel opens
	 * 
	 * @param workBook Excel book
	 */
	public void writeExcel(Sheet sheet, XSSFWorkbook workBook) throws IOException {
		sheet.setDefaultColumnWidth(20);
		cabecalho(sheet, workBook);
		int rowCount = 1;
		int k = 0;

		for (int i = 0; i < cycloMethod.getResultados().size(); i++) {
			Row row = sheet.createRow(++separador);
			int colCount = 0;

			Cell methodID = row.createCell(0);
			Cell pack = row.createCell(++colCount);
			Cell classes = row.createCell(++colCount);
			Cell methods = row.createCell(++colCount);
			Cell nomC = row.createCell(++colCount);
			Cell locC = row.createCell(++colCount);
			Cell wmcC = row.createCell(++colCount);
			Cell locM = row.createCell(++colCount);
			Cell cycloM = row.createCell(++colCount);

			methodID.setCellValue(separador);
			pack.setCellValue(locMethod.getResultados().get(i).getPackage());
			classes.setCellValue(locMethod.getResultados().get(i).getClasses());
			methods.setCellValue(locMethod.getResultados().get(i).getMethodNames());

			if (!(cycloMethod.getResultados().get(i).getClasses()
					.equals(cycloMethod.getResultados().get(k).getClasses()))
					&& k < nomClass.getResultados().size() - 1) {
				k++;
			}

			nomC.setCellValue(nomClass.getResultados().get(k).getLinhas());
			locC.setCellValue(locClass.getResultados().get(k).getLinhas());
			wmcC.setCellValue(wmcClass.getResultados().get(k).getLinhas());

			locM.setCellValue(locMethod.getResultados().get(i).getLinhas());
			cycloM.setCellValue(cycloMethod.getResultados().get(i).getLinhas());

			rowCount++;
			
			numberOfLines+=locMethod.getResultados().get(i).getLinhas();
		}
	}

	/*
	 * Fills excel header with the strings below (String[] c) and bold format
	 * 
	 * @param sheet Excel sheet created when excel opens
	 * 
	 * @param workBook Excel book
	 */
	public void cabecalho(Sheet sheet, XSSFWorkbook workBook) {
		String[] c = { "MethodID", "Package", "Class", "Method", "NOM_class", "LOC_class", "WMC_class", "LOC_method",
				"CYCLO_method" };
		Row cabecalho = sheet.createRow(0);
		int cellCount = 0;
		XSSFCellStyle style = workBook.createCellStyle();
		XSSFFont font = workBook.createFont();
		font.setFontHeightInPoints((short) 15);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setBold(true);
		style.setFont(font);
		for (String s : c) {
			Cell cell = cabecalho.createCell(cellCount++);
			cell.setCellValue(s);
			cell.setCellStyle(style);
		}
	}

	/*
	 * Extracts all .java files from a given directory
	 * 
	 * @return File[] with all .java files.
	 */
	public File[] extracts() throws IOException {

		ArrayList<File> lista = new ArrayList<File>();
		File[] javaFiles = new File[0];
		if (srcPath.isDirectory()) {
			Path path = Paths.get(srcPath.getAbsolutePath());
			List<Path> paths = listFiles(path);
			List<File> files = pathsToFiles(paths);
			for (int i = 0; i < paths.size(); i++) {
				if (files.get(i).isFile() && files.get(i).getPath().endsWith(".java")) {
					lista.add(files.get(i));
				}
			}
			javaFiles = new File[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				javaFiles[i] = lista.get(i);
			}
		}
		return javaFiles;
	}

	/*
	 * auxiliar method from "extracts()".
	 * 
	 * @return all files in a List<Path>
	 */
	public List<Path> listFiles(Path path) throws IOException {
		List<Path> result;
		try (Stream<Path> walk = Files.walk(path)) {
			result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
		}
		return result;
	}

	/*
	 * auxiliar method from "extracts()".
	 * 
	 * @return all Path to File
	 */
	public List<File> pathsToFiles(List<Path> path) {
		List<File> files = new ArrayList<File>();
		for (int i = 0; i < path.size(); i++) {
			files.add(path.get(i).toFile());
		}
		return files;
	}
	
	public void numberOfSomething() {
		int k = 0;
		ArrayList<String> aux = new ArrayList();
		ArrayList<String> aux2 = new ArrayList();
		for(int i=0; i<boolResultMethod.size(); i++) {
			if (!aux.contains(boolResultMethod.get(i).getClasses())) {
				aux.add(boolResultMethod.get(i).getClasses());
			}
			if(!aux2.contains(boolResultMethod.get(i).getPackage())) {
				aux2.add(boolResultMethod.get(i).getPackage());
			}
		}
		numberOfClasses=aux.size();
		numberOfPackages=aux2.size();
	}

	public File getSourcePath() {
		return srcPath;
	}

	/*
	 * @param excelFileDir Directory to save excel file
	 */
	public void setExcelFileDir(String excelFileDir) {
		this.excelFileDir = excelFileDir;
	}

	/*
	 * @param sRC_PATH Java Project file
	 */
	public void setSRC_PATH(File sRC_PATH) {
		srcPath = sRC_PATH;
	}
	
	public File getExcelFile() {
		return excelFile;
	}

	public String getExcelFileDir() {
		return excelFileDir;
	}
	
	public int getNumberOfClasses() {
		return numberOfClasses;
	}
	 
	public int getNumberOfLines() {
		return numberOfLines;
	}
	
	public int getNumberOfMethods() {
		return numberOfMethods;
	}
	
	public int getNumberOfPackages() {
		return numberOfPackages;
	}

//	public ArrayList<Resultado> getAll() {
//		return all;
//	}

	public ArrayList<BoolResultado> getBoolClass() {
		return boolResultClass;
	}

	public ArrayList<BoolResultado> getBoolMethod() {
		return boolResultMethod;
	}

	public static ArrayList<Rule> testMain() throws FileNotFoundException {
		String ruleName = "RegraNew";
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparator> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operator> oper = new ArrayList<>();
		metricName.add("NOM_class");
		metricName.add("LOC_class");
		metricName.add("WMC_class");
		comp.add(comparator.BIGGER);
		comp.add(comparator.BIGGER);
		comp.add(comparator.SMALLER);
		limits.add(20);
		limits.add(30);
		limits.add(40);
		oper.add(operator.AND);
		oper.add(operator.OR);

		Rule r = new Rule(ruleName, 0, metricName, comp, limits, oper);

		String ruleName1 = "Regra3";
		ArrayList<String> metricName1 = new ArrayList<>();
		ArrayList<comparator> comp1 = new ArrayList<>();
		ArrayList<Integer> limits1 = new ArrayList<>();
		ArrayList<operator> oper1 = new ArrayList<>();
		metricName1.add("LOC_method");
		metricName1.add("CYCLO_method");
		comp1.add(comparator.BIGGER);
		comp1.add(comparator.SMALLER);
		limits1.add(20);
		limits1.add(40);
		oper1.add(operator.AND);

		Rule r1 = new Rule(ruleName1, 1, metricName1, comp1, limits1, oper1);

		ArrayList<Rule> rules = new ArrayList();
		rules.add(r);
		rules.add(r1);
		return rules;

	}

	public static void main(String[] args) throws IOException {
		ArrayList<Rule> rules = testMain();
		File srcPath = new File("C:\\Users\\henri\\Downloads\\jasml_0.10");
		Central c = new Central(rules, srcPath);
//		c.ini();
//
//		History hist = new History();
//		hist.writeFile(rules);
//		ArrayList<Rule> r = hist.readFile(rules.get(0).getRuleName());

	}
}
