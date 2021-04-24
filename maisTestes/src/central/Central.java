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

	private File srcPath;

	private String excelFileDir = ""; // vai estar em branco e é
										// necessário fazer
										// setExcelFileDir
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

	public Central(ArrayList<Rule> rules, File srcPath, int tipoComparacao, ArrayList<File> files) throws IOException {
		this.rules = rules;
		this.srcPath = srcPath;
		this.files = files;
		excelFile = new File(excelFileDir.concat("\\".concat(srcPath.getName().concat("_metrics.xlsx"))));
		this.tipoComparacao = tipoComparacao;
	}

	/*
	 * initiates the main Central flow call all methods
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
			// numberOfClasses += wmcClass.getResultados().size();
		}
		putMethodID();
		chooseRules(rules);
		numberOfSomething();
		numberOfMethods = boolResultMethod.size();

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
		comparador = new Comparador(boolResultMethod, boolResultClass, tipoComparacao);

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

			boolResultClass.add(new BoolResultado(i, cycloMethod.getResultados().get(i).getPackage(),
					cycloMethod.getResultados().get(i).getClasses(),
					cycloMethod.getResultados().get(i).getMethodNames(), false));
			boolResultMethod.add(new BoolResultado(i, cycloMethod.getResultados().get(i).getPackage(),
					cycloMethod.getResultados().get(i).getClasses(),
					cycloMethod.getResultados().get(i).getMethodNames(), false));
		}
	}

	/*
	 * enumerates all array with a MethodID
	 */
	public void putMethodID() {
		for (int i = 0; i < all.size(); i++) {
			all.get(i).setMethodID(i + 1);
			boolResultClass.get(i).setId(i+1);
			boolResultMethod.get(i).setId(i+1);
		}
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

			numberOfLines += locMethod.getResultados().get(i).getLinhas();
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

	public void numberOfSomething() {
		int k = 0;
		ArrayList<String> aux = new ArrayList();
		ArrayList<String> aux2 = new ArrayList();
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

	public File getSourcePath() {
		return srcPath;
	}

	/*
	 * @param excelFileDir Directory to save excel file
	 */
	public void setExcelFileDir(String excelFileDir) {
		this.excelFileDir = excelFileDir;
//		excelFile = new File(excelFileDir.concat("\\".concat(srcPath.getName().concat("_metrics.xlsx"))));

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

	public Comparador getComparador() {
		return comparador;
	}

	public ArrayList<BoolResultado> getBoolClass() {
		return boolResultClass;
	}

	public ArrayList<BoolResultado> getBoolMethod() {
		return boolResultMethod;
	}

	public void setAll(ArrayList<Resultado> all) {
		this.all= all;
		
	}

	public ArrayList<Resultado> getAll() {
		return all;
	}

	public void setBoolMethod(ArrayList<BoolResultado> boolMethod) {
		this.boolResultMethod=  boolMethod;
		
	}

	public void setComparador(Comparador d) {
		this.comparador= d;
		
	}


	


}
