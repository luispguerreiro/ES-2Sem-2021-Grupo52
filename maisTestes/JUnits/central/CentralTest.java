/**
 * 
 */
package central;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Metrics.CYCLO_method;
import Metrics.Loc_Class;
import Metrics.Loc_Method;
import Metrics.Metrics;
import Metrics.NOM_Class;
import Metrics.Resultado;
import Metrics.WMC_Class;
import maisTestes.Comparador;
import maisTestes.Excel;
//import rules.GuiOutput.comparators;
//import rules.GuiOutput.operators;
import rules.Rule;
import rules.Rule.comparator;
import rules.Rule.operator;

/**
 * @author nmsid
 *
 */
class CentralTest {
	static Central c;
	static Sheet sheet;
	static Excel excel;
	static ArrayList<Rule> rules = new ArrayList<>();
	static ArrayList<String> metricName = new ArrayList<>();
	static ArrayList<comparator> comp = new ArrayList<>();
	static ArrayList<Integer> limits = new ArrayList<>();
	static ArrayList<operator> oper = new ArrayList<>();
	static ArrayList<BoolResultado> boolMethod = new ArrayList<>();
	static ArrayList<BoolResultado> boolClass = new ArrayList<>();
	static ArrayList<Resultado> all = new ArrayList<>();
	static ArrayList<File> files = new ArrayList<>();
	static String excelFileDir ="";
	static String SRC_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10";
	static File SRC_path = new File("C:\\Users\\nmsid\\Downloads\\jasml_0.10");
	static String PATH2 = "C:\\Users\\henri\\Downloads\\jasml_0.10\\src\\com\\jasml\\classes\\ConstantPoolItem.java";
	static File file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_0.10");
	static int separador;
	static Metrics metric;
	static CYCLO_method cyclo;
	static Loc_Method locMethod;
	static Loc_Class locClass;
	static NOM_Class nomClass;
	static WMC_Class wmcClass;
	static int numberOfPackages = 0;
	static int numberOfClasses = 0;
	static int numberOfMethods = 0;
	static int numberOfLines = 0;
	static int tipoComparacao;
	static XSSFWorkbook workBook = new XSSFWorkbook();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		separador = 0;
		metricName.add("NOM_class");
		metricName.add("LOC_class");
		comp.add(comparator.BIGGER);
		comp.add(comparator.BIGGER);
		limits.add(13);
		limits.add(20);
		oper.add(operator.AND);
		rules.add(new Rule("LONG_method", 1, metricName, comp, limits, oper));
		tipoComparacao = 1;
		metric = new Metrics(PATH2);
		cyclo = new CYCLO_method(metric);
		files.add(new File(PATH2));
		c = new Central(rules, file, tipoComparacao, files);
		c.setExcelFileDir("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho");
//		file = new File("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link central.Central#Central(java.util.ArrayList, java.io.File, int)}.
	 * 
	 */
	@Test
	final void testCentral() {
		Assertions.assertNotNull(c);

	}

	/**
	 * Test method for {@link central.Central#ini()}.
	 * 
	 * @throws IOException
	 */
	@Test
	final void testIni() throws IOException {
		c.ini();
		Assertions.assertEquals(numberOfMethods, boolMethod.size());
	}


	/**
	 * Test method for {@link central.Central#chooseRules(java.util.ArrayList)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testChooseRules() throws FileNotFoundException {
		ArrayList<Rule> rulesEmpty = new ArrayList<>();
		ArrayList<Rule> rules1 = new ArrayList<>();
		rules1.add(new Rule("LONG_method", 1, metricName, comp, limits, oper));
		ArrayList<Rule> rules2 = new ArrayList<>();
		rules2.add(new Rule("NOM_class", 0, metricName, comp, limits, oper));
		rules2.add(new Rule("LOC_class", 0, metricName, comp, limits, oper));
		Assertions.assertTrue(rulesEmpty.isEmpty());
		Assertions.assertFalse(!rulesEmpty.isEmpty());
		Assertions.assertNotNull(rulesEmpty);
		Assertions.assertFalse(rules1.isEmpty());
		Assertions.assertEquals(rules1.size(), 1);
		Assertions.assertNotNull(rules1);
		Assertions.assertFalse(rules2.isEmpty());
		Assertions.assertEquals(rules2.size(), 2);
		Assertions.assertNotNull(rules2);

	}

	/**
	 * Test method for {@link central.Central#fuelAllandBoolResults()}.
	 */
	@Test
	final void testFuelAllandBoolResults() {
		locMethod = new Loc_Method(metric);
		locClass = new Loc_Class(metric);
		nomClass = new NOM_Class(metric);
		wmcClass = new WMC_Class(metric);
		cyclo= new CYCLO_method(metric);
		c.setMetric(cyclo, locMethod, locClass, nomClass, wmcClass);
		c.fuelAllandBoolResults();
		Assertions.assertNotNull(boolClass);
	}

	/**
	 * Test method for {@link central.Central#putMethodID()}.
	 */
	@Test
	final void testPutMethodID() {
		int[] ints = new int[5];
		all.add(new Resultado(1, SRC_PATH, 30, ints));
		all.add(new Resultado(1, SRC_PATH, 30, ints));
		c.setAll(all);
		boolMethod.add(new BoolResultado(5, "String1 ", "String2 ", "String3 ", false));
		boolMethod.add(new BoolResultado(5, "String1 ", "String2 ", "String3 ", false));
		boolClass.add(new BoolResultado(5, "String1 ", "String2 ", "String3 ", false));
		boolClass.add(new BoolResultado(5, "String1 ", "String2 ", "String3 ", false));
		c.setBoolMethod(boolMethod);
		c.setBoolClass(boolClass);
		c.putMethodID();
//		for (int i = 0; i < all.size(); i++){
//			all.get(i).setMethodID(i + 1);
//		}
		Assertions.assertEquals(1, c.getAll().get(0).getMethodID());
		Assertions.assertEquals(2, c.getAll().get(1).getMethodID());
		Assertions.assertEquals(1, c.getBoolMethod().get(0).getId());
		Assertions.assertEquals(2, c.getBoolClass().get(1).getId());
	}

	/**
	 * Test method for
	 * {@link central.Central#writeExcel(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 * 
	 * @throws IOException
	 */
	@Test
	final void testWriteExcel() throws IOException {
		File file3 = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		Sheet sheet = workBook.createSheet(file3.getName().replaceFirst("[.][^.]+$", ""));
		Row row = sheet.createRow(++separador);
		Assertions.assertNotNull(row);
		Assertions.assertNotNull(workBook);
		Assertions.assertNotNull(sheet);
		c.writeExcel(sheet, workBook);
		Assertions.assertNotNull(file3);
	}

	/**
	 * Test method for
	 * {@link central.Central#cabecalho(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 */
	@Test
	final void testCabecalho() {
		XSSFWorkbook workBook = new XSSFWorkbook();
		String[] string = { "MethodID", "Package", "Class", "Method", "NOM_class", "LOC_class", "WMC_class",
				"LOC_method", "CYCLO_method" };
		int cellCount = 0;
		Sheet sheet = workBook.createSheet(file.getName().replaceFirst("[.][^.]+$", ""));
		Row cabecalho = sheet.createRow(0);
		Cell cell = cabecalho.createCell(cellCount++);
		Assertions.assertNotNull(cabecalho);
		Assertions.assertNotNull(cell);
		Assertions.assertEquals(9, string.length);
	}


	/**
	 * Test method for {@link central.Central#numberOfSomething()}.
	 */
	@Test
	final void testNumberOfSomething() {

		boolMethod.add(new BoolResultado(5, "String1 ", "String2 ", "String3 ", false));
		c.setBoolMethod(boolMethod);
		c.numberOfSomething();
		Assertions.assertEquals(boolMethod, c.getBoolMethod());
	}

	/**
	 * Test method for {@link central.Central#getSourcePath()}.
	 */
	@Test
	final void testGetSourcePath() {
		File file4 = new File("C:\\Users\\nmsid\\Downloads\\jasml_0.10");
		c.setSRC_PATH(file4);
		assertEquals(file4, c.getSourcePath());
	}

	/**
	 * Test method for {@link central.Central#setExcelFileDir(java.lang.String)}.
	 */
	@Test
	final void testSetExcelFileDir() {
		String excelDir = "C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho";
		c.setExcelFileDir(excelDir);
		assertEquals(excelDir, c.getExcelFileDir());
	}

		/**
		 * Test method for {@link central.Central#getBoolClass()}.
		 */
		@Test
		final void testGetBoolClass() {
			boolClass = c.getBoolClass();
			assertEquals(boolClass, c.getBoolClass());
		}

		/**
		 * Test method for {@link central.Central#getBoolClass()}.
		 */
		@Test
		final void testGetBoolMethod() {
			boolMethod = c.getBoolMethod();
			assertEquals(boolMethod, c.getBoolMethod());
		}

	/**
	 * Test method for {@link central.Central#setSRC_PATH(java.io.File)}.
	 */
	@Test
	final void testSetSRC_PATH() {
		File file4 = new File("C:\\Users\\nmsid\\Downloads\\jasml_0.10");
		c.setSRC_PATH(file4);
		assertEquals(file4, c.getSourcePath());
	}

	/**
	 * Test method for {@link central.Central#getExcelFile()}.
	 */
	@Test
	final void testGetExcelFile() {
		File file3 = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		c.setExcelFile(file3);
		assertEquals(file3, c.getExcelFile());
	}

	/**
	 * Test method for {@link central.Central#getExcelFileDir()}.
	 */
	@Test
	final void testGetExcelFileDir() {
		String excelDir = "C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho";
		c.setExcelFileDir(excelDir);
		assertEquals(excelDir, c.getExcelFileDir());
	}

	/**
	 * Test method for {@link central.Central#getNumberOfClasses()}.
	 */
	@Test
	final void testGetNumberOfClasses() {
		int numberOfClasses = 1;
		assertEquals(numberOfClasses, c.getNumberOfClasses());
	}

	/**
	 * Test method for {@link central.Central#getNumberOfLines()}.
	 */
	@Test
	final void testGetNumberOfLines() {
		int numberOfLines = 8;
		assertEquals(numberOfLines, c.getNumberOfLines());
	}

	/**
	 * Test method for {@link central.Central#getNumberOfMethods()}.
	 */
	@Test
	final void testGetNumberOfMethods() {
		int numberOfMethods = 1;
		assertEquals(numberOfMethods, c.getNumberOfMethods());
	}

	/**
	 * Test method for {@link central.Central#getNumberOfPackages()}.
	 */
	@Test
	final void testGetNumberOfPackages() {
		int numberOfPackages = 1;
		assertEquals(numberOfPackages, c.getNumberOfPackages());
	}

	/**
	 * Test method for {@link central.Central#getComparador()}.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Test
	final void testGetComparador() throws FileNotFoundException, IOException {
		Comparador d = new Comparador(boolMethod, boolClass, tipoComparacao);
		c.setComparador(d);
		assertEquals(d, c.getComparador());
	}

	/**
	 * Test method for {@link central.Central#setComparador()}.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Test
	final void testSetComparador() throws FileNotFoundException, IOException {
		Comparador d = new Comparador(boolMethod, boolClass, tipoComparacao);
		c.setComparador(d);
		assertEquals(d, c.getComparador());
	}

	/**
	 * Test method for {@link central.Central#getAll()}.
	 *
	 */
	@Test
	final void testGetAll() {
		assertEquals(all, c.getAll());
	}

	/**
	 * Test method for {@link central.Central#getAll()}.
	 *
	 */
	@Test
	final void testSetAll() {
		c.setAll(all);
		assertEquals(all, c.getAll());
	}

	/**
	 * Test method for {@link central.Central#setBoolMethod()}.
	 */
	@Test
	final void testsetBoolMethod() {
		c.setBoolMethod(boolMethod);
		assertEquals(boolMethod, c.getBoolMethod());
	}



}
