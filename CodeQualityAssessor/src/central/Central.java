package central;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excel.Comparador;
import metrics.CYCLO_method;
import metrics.Loc_Class;
import metrics.Loc_Method;
import metrics.Metrics;
import metrics.NOM_Class;
import metrics.Resultado;
import metrics.WMC_Class;
import rules.Rule;

/**
 * @author Grupo 52
 * 
 * Writes the excel, applies the thresholds given by the Rules in the array of
 * Resultado and prepares the booResults arrays for the following comparisons
 */
public class Central {

	private File srcPath;
	private String excelFileDir = "";
	private File excelFile;
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
	private int tipoComparacao;
	private ArrayList<Resultado> all = new ArrayList<>();
	private ArrayList<BoolResultado> boolResultClass = new ArrayList<>();
	private ArrayList<BoolResultado> boolResultMethod = new ArrayList<>();
	private ArrayList<Rule> rules = new ArrayList<>();
	private ArrayList<File> files;
	private Metrics metric;
	private Comparador comparador;

	/**
	 * Writes the excel, applies the thresholds given by the Rules in the array of
	 * Resultado and prepares the booResults arrays for the following comparisons
	 * (false positive etc)
	 * 
	 * @throws IOException in case the is a problem importing or exporting the files
	 * @param rules          the array with the rules
	 * @param srcPath        the File
	 * @param tipoComparacao the comparation to be made
	 * @param files          the array with the files
	 */
	public Central(ArrayList<Rule> rules, File srcPath, int tipoComparacao, ArrayList<File> files) throws IOException {
		this.rules = rules;
		this.srcPath = srcPath;
		this.files = files;
		excelFile = new File(excelFileDir.concat("\\".concat(srcPath.getName().concat("_metrics.xlsx"))));
		this.tipoComparacao = tipoComparacao;
	}

	/**
	 * initiates the main Central flow and calls all methods
	 * 
	 * @throws IOException in case the is a problem importing or exporting the files
	 */
	public void ini() throws IOException {
		excelFile = new File(excelFileDir.concat("\\".concat(srcPath.getName().concat("_metrics.xlsx"))));
//		File[] v = extracts();
		XSSFWorkbook workBook = new XSSFWorkbook();
		Sheet sheet = workBook.createSheet(excelFile.getName().replaceFirst("[.][^.]+$", ""));
		for (int i = 0; i < files.size(); i++) {

			metric = new Metrics(files.get(i).getAbsolutePath());

			locMethod = new Loc_Method(metric);
			cycloMethod = new CYCLO_method(metric);
			locClass = new Loc_Class(metric);
			nomClass = new NOM_Class(metric);
			wmcClass = new WMC_Class(metric);
			writeExcel(sheet, workBook);
			fuelAllandBoolResults();
		}
		putMethodID();
		chooseRules(rules);
		numberOfSomething();
		numberOfMethods = boolResultMethod.size();
		OutputStream fileOut = new FileOutputStream(excelFile);
		workBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		System.out.println("\n***Exporta��o para Excel conclu�da!***\n");
		System.out.println("number of methods = " + numberOfMethods);
		System.out.println("number of packages = " + numberOfPackages);
		System.out.println("number of classes = " + numberOfClasses);
		System.out.println("number of lines = " + numberOfLines);
		comparador = new Comparador(boolResultMethod, boolResultClass, tipoComparacao);

	}

	/**
	 * Selects the rule choosen and calculates it's thresholds
	 * 
	 * @throws FileNotFoundException in case the file imported isn't found by the
	 *                               program
	 * @param rules zero, one or two rules given by the user and created in class
	 *              GUI
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

	/**
	 * Fills the array of the results with data like one of the metrics arrays (in
	 * this case "cyclomethod") the data copied to "all" are the same in all
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

			boolResultClass.add(new BoolResultado(i, cycloMethod.getResultados().get(i).getPackage(),
					cycloMethod.getResultados().get(i).getClasses(),
					cycloMethod.getResultados().get(i).getMethodNames(), false));
			boolResultMethod.add(new BoolResultado(i, cycloMethod.getResultados().get(i).getPackage(),
					cycloMethod.getResultados().get(i).getClasses(),
					cycloMethod.getResultados().get(i).getMethodNames(), false));
		}
	}

	/**
	 * enumerates all array with a MethodID enumerates the BoolResultado arrays with
	 * MethodID to appear in the GUI
	 */
	public void putMethodID() {
		for (int i = 0; i < all.size(); i++) {
			all.get(i).setMethodID(i + 1);
			boolResultClass.get(i).setId(i + 1);
			boolResultMethod.get(i).setId(i + 1);
		}
	}

	/**
	 * Writes to excel all the necessary information call cabecalho (Sheet sheet,
	 * XSSFWorkbook workBook) to fill the excel header
	 * 
	 * @throws IOException error creating the excel
	 * @param sheet    Excel sheet created when excel opens
	 * @param workBook Excel book
	 */
	public void writeExcel(Sheet sheet, XSSFWorkbook workBook) throws IOException {
		sheet.setDefaultColumnWidth(20);
		cabecalho(sheet, workBook);
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
			numberOfLines += locMethod.getResultados().get(i).getLinhas();
		}
	}

	/**
	 * Fills excel header with the strings below (String[] c) and bold format
	 * 
	 * @param sheet    Excel sheet created when excel opens
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
		font.setBold(true);
		style.setFont(font);
		for (String s : c) {
			Cell cell = cabecalho.createCell(cellCount++);
			cell.setCellValue(s);
			cell.setCellStyle(style);
		}
	}

	/**
	 * Extracts the number of Classes and Packages from the boolResult Arrays to be
	 * written in the Excel file
	 */
	public void numberOfSomething() {
		ArrayList<String> aux = new ArrayList<>();
		ArrayList<String> aux2 = new ArrayList<>();
		for (int i = 0; i < boolResultMethod.size(); i++) {
			if (!aux.contains(boolResultMethod.get(i).getClasses())) {
				aux.add(boolResultMethod.get(i).getClasses());
			}
			if (!aux2.contains(boolResultMethod.get(i).getPackage())) {
				aux2.add(boolResultMethod.get(i).getPackage());
			}
		}
		numberOfClasses = aux.size();
		numberOfPackages = aux2.size();
	}

	/**
	 * Returns the scrPath of the File
	 * 
	 * @return the srcPath of the file
	 */
	public File getSourcePath() {
		return srcPath;
	}

	/**
	 * Sets a new Excel File Directory
	 * 
	 * @param excelFileDir Directory to save excel file
	 */
	public void setExcelFileDir(String excelFileDir) {
		this.excelFileDir = excelFileDir;

	}

	/**
	 * Sets a new Source Path for the Java Project File
	 * 
	 * @param sRC_PATH Java Project file
	 */
	public void setSRC_PATH(File sRC_PATH) {
		srcPath = sRC_PATH;
	}

	/**
	 * Returns the Excel File
	 * 
	 * @return the Excel file
	 */
	public File getExcelFile() {
		return excelFile;
	}

	/**
	 * Returns the Excel File Directory
	 * 
	 * @return the Excel file directory
	 */
	public String getExcelFileDir() {
		return excelFileDir;
	}

	/**
	 * Returns the Number of Classes in the Java Project File
	 * 
	 * @return the Number of Classes in the Java Project File
	 */
	public int getNumberOfClasses() {
		return numberOfClasses;
	}

	/**
	 * Returns the Number of Lines in the Java Project File
	 * 
	 * @return the Number of Lines in the Java Project File
	 */
	public int getNumberOfLines() {
		return numberOfLines;
	}

	/**
	 * Returns the Number of Methods in the Java Project File
	 * 
	 * @return the Number of Methods in the Java Project File
	 */
	public int getNumberOfMethods() {
		return numberOfMethods;
	}

	/**
	 * Returns the Number of Packages in the Java Project File
	 * 
	 * @return the Number of Packages in the Java Project File
	 */
	public int getNumberOfPackages() {
		return numberOfPackages;
	}

	/**
	 * Getter for the Comparator use in the Rule to calculate the thresholds
	 * 
	 * @return the Comparator use in the Rule to calculate the thresholds
	 */
	public Comparador getComparador() {
		return comparador;
	}

	/**
	 * Getter for an array with the Results of the Class metrics
	 * 
	 * @return the array with the Results of the Class metrics
	 */
	public ArrayList<BoolResultado> getBoolClass() {
		return boolResultClass;
	}

	/**
	 * Getter for the array with the Results of the Methods metrics
	 * 
	 * @return an array with the Results of the Methods metrics
	 */
	public ArrayList<BoolResultado> getBoolMethod() {
		return boolResultMethod;
	}

	/**
	 * Sets an array with the Results of the metrics
	 * 
	 * @param all an array with the Results of the all the metrics
	 */
	public void setAll(ArrayList<Resultado> all) {
		this.all = all;
	}
		

	/**
	 * Getter for the array with the Results of the metrics
	 * 
	 * @return an array with the Results of the metrics
	 */
	public ArrayList<Resultado> getAll() {
		return all;

	}

	/**
	 * Sets an array with the Boolean Results of the Methods metrics
	 * 
	 * @param boolMethod an array with the Boolean Results of the Methods metrics
	 */
	public void setBoolMethod(ArrayList<BoolResultado> boolMethod) {
		this.boolResultMethod = boolMethod;
	}

	/**
	 * Sets a new Comparator
	 * 
	 * @param d the value of the Comparador
	 */
	public void setComparador(Comparador d) {
		this.comparador = d;

	}

	/**
	 * Sets an array with the Boolean Results of the Class metrics
	 * 
	 * @param boolClass an array with the Boolean Results of the Class metrics
	 */
	public void setBoolClass(ArrayList<BoolResultado> boolClass) {
		this.boolResultClass = boolClass;

	}

	/**
	 * Sets the metrics to be evaluated
	 * 
	 * @param cyclo CYCLO_method
	 * @param loc   LOC_method
	 * @param locC  LOC_Class
	 * @param nom   NOM_Class
	 * @param wmc   WMC_Class
	 */
	public void setMetric(CYCLO_method cyclo, Loc_Method loc, Loc_Class locC, NOM_Class nom, WMC_Class wmc) {
		this.cycloMethod = cyclo;
		this.locMethod = loc;
		this.locClass = locC;
		this.nomClass = nom;
		this.wmcClass = wmc;
	}

	/**
	 * Sets a new File for the Excel file
	 * 
	 * @param file File
	 */
	public void setExcelFile(File file) {
		this.excelFile = file;
	}

}
