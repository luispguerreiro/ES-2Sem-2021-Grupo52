/**
 * 
 */
package central;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

import Metrics.Resultado;
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
		static ArrayList<Rule> rules= new ArrayList<>();
		static ArrayList<String> metricName= new ArrayList<>();
		static ArrayList<comparator> comp= new ArrayList<>();
		static ArrayList<Integer> limits= new ArrayList<>();
<<<<<<< HEAD
		static ArrayList<operators> oper= new ArrayList<>();
<<<<<<< HEAD
<<<<<<< HEAD
//		static String SRC_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10";
//		static File file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		
		static String SRC_PATH = "C:\\Users\\henri\\Downloads\\jasml_0.10";

		static File file = new File("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		
=======
		static String SRC_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10";
		static File file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		static int separador;
>>>>>>> branch 'henry&nazif' of https://github.com/luispguerreiro/ES-2Sem-2021-Grupo52
		/**
		 * @throws java.lang.Exception
		 */
		@BeforeAll
		static void setUpBeforeClass() throws Exception {
			separador= 0;
			metricName.add("NOM_class");
			metricName.add("LOC_class");
			comp.add(comparators.BIGGER);
			comp.add(comparators.BIGGER);
			limits.add(13);
			limits.add(20);
			oper.add(operators.AND);
			rules.add(new Rule("LONG_method", 1, metricName, comp,
					 limits,  oper));
			c= new Central(rules);
//			file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
			file = new File("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		
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
	 * Test method for {@link central.Central#Central(java.util.ArrayList)}.
	 */
	@Test
	final void testCentral() {
		Assertions.assertNotNull(c);
	}

	/**
	 * Test method for {@link central.Central#sys()}.
	 */
	@Test
	final void testSys() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#chooseRules(java.util.ArrayList)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testChooseRules() throws FileNotFoundException {
		ArrayList<Rule> rulesEmpty= new ArrayList<>();
		ArrayList<Rule> rules1= new ArrayList<>();
		rules1.add(new Rule("LONG_method", 1, metricName, comp,
				 limits,  oper));
		ArrayList<Rule> rules2= new ArrayList<>();
		rules2.add(new Rule("NOM_class", 0, metricName, comp,
				 limits,  oper));
		rules2.add(new Rule("LOC_class", 0, metricName, comp,
				 limits,  oper));
			Assertions.assertTrue(rulesEmpty.isEmpty());
			Assertions.assertNotNull(rulesEmpty);
			Assertions.assertEquals(rules1.size(),1);
			Assertions.assertNotNull(rules1);
			Assertions.assertEquals(rules2.size(),2); 
			Assertions.assertNotNull(rules2);
			
		
	}

	/**
	 * Test method for {@link central.Central#fuelAll()}.
	 */
	@Test
	final void testFuelAll() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#putMethodID()}.
	 */
	@Test
	final void testPutMethodID() {
		ArrayList<Resultado> all = new ArrayList<>();
		int[] ints= new int[5];
		all.add(new Resultado(3, SRC_PATH, 30, ints));
		c.putMethodID();
		Assertions.assertEquals(3, all.get(0).getMethodID());
	}

	/**
	 * Test method for {@link central.Central#writeExcel(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 * @throws IOException 
	 */
	@Test
	final void testWriteExcel() throws IOException {
		File file3 = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook();
		Sheet sheet = workBook.createSheet(file3.getName().replaceFirst("[.][^.]+$", ""));
		Row row = sheet.createRow(++separador);
		Assertions.assertNotNull(row);
		Assertions.assertNotNull(workBook);
		Assertions.assertNotNull(sheet);
		c.writeExcel(sheet, workBook);	
		Assertions.assertNotNull(file3);
		
		}

	/**
	 * Test method for {@link central.Central#cabecalho(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 */
	@Test
	final void testCabecalho() {
		XSSFWorkbook workBook = new XSSFWorkbook();
		String[] string = { "MethodID", "Package", "Class", "Method", "NOM_class", "LOC_class", "WMC_class", "LOC_method",
		"CYCLO_method" };
		int cellCount= 0;
		Sheet sheet = workBook.createSheet(file.getName().replaceFirst("[.][^.]+$", ""));
		Row cabecalho = sheet.createRow(0);
		Cell cell = cabecalho.createCell(cellCount++);
		Assertions.assertNotNull(cabecalho);
		Assertions.assertNotNull(cell);
		Assertions.assertEquals(9, string.length);
		
	}

	/**
	 * Test method for {@link central.Central#extracted()}.
	 * @throws IOException 
	 */
	@Test
	final void testExtracted() throws IOException {
		File dir = new File(SRC_PATH);
		ArrayList<File> lista = new ArrayList<File>();
		Assertions.assertNotNull(dir);
		Assertions.assertNotNull(lista);
		File[] v = c.extracted();
		Assertions.assertNotNull(v);
		Assertions.assertEquals(44, v.length);
	}

	/**
	 * Test method for {@link central.Central#listFiles(java.nio.file.Path)}.
	 * @throws IOException 
	 */
	@Test
	final void testListFiles() throws IOException {
		List<Path> result= new ArrayList<>();
		List<File> files = new ArrayList<File>();
		File dir = new File(SRC_PATH);
		Path path = Paths.get(dir.getAbsolutePath());
		result=c.listFiles(path);
		Assertions.assertNotNull(files);
		Assertions.assertNotNull(result);
		Assertions.assertNotNull(dir);
		Assertions.assertNotNull(path);
		Assertions.assertEquals(result.size(), c.listFiles(path).size());
		
	}

	/**
	 * Test method for {@link central.Central#pathsToFiles(java.util.List)}.
	 */
	@Test
	final void testPathsToFiles() {
		List<Path> paths= new ArrayList<>();
		List<File> files = new ArrayList<File>();
		File dir = new File(SRC_PATH);
		Path path = Paths.get(dir.getAbsolutePath());
		paths.add(path);
		Assertions.assertNotNull(files);
		Assertions.assertNotNull(paths);
		Assertions.assertNotNull(dir);
		Assertions.assertNotNull(path);
		files= c.pathsToFiles(paths);
		Assertions.assertEquals(files.size(), paths.size());
		
	}

	/**
	 * Test method for {@link central.Central#getSourcePath()}.
	 */
	@Test
	final void testGetSourcePath() {
		assertEquals(SRC_PATH, c.getSourcePath());
		
	}

	/**
	 * Test method for {@link central.Central#getFile()}.
	 */
	@Test
	final void testGetFile() {
		assertEquals(file, c.getFile());
	}

	/**
	 * Test method for {@link central.Central#setSourcePath(java.lang.String)}.
	 */
	@Test
	final void testSetSourcePath() {
		 String SRC_PATH2 = "C:\\Users\\nmsid\\Downloads\\jasml_0.10";
		 c.setSourcePath(SRC_PATH2);
			assertEquals(SRC_PATH2, c.getSourcePath());
	}

	/**
	 * Test method for {@link central.Central#setFile(java.io.File)}.
	 */
	@Test
	final void testSetFile() {
		File file2 = new File("C:\\Users\\nmsid\\OneDrive\\Documentos\\jasml_metrics.xlsx");
				c.setFile(file2);
		assertEquals(file2, c.getFile());
	}

	/**
	 * Test method for {@link central.Central#getAll()}.
	 */
	@Test
	final void testGetAll() {
		ArrayList<Resultado> all = c.getAll();
		assertEquals(all, c.getAll());
	}

	/**
	 * Test method for {@link central.Central#main(java.lang.String[])}.
	 */
	@Test
	final void testMain() {
		fail("Not yet implemented"); // TODO
	}

}
=======
=======
		static ArrayList<operator> oper= new ArrayList<>();

//		static String SRC_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10";
//		static File file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		
//		static String SRC_PATH = "C:\\Users\\henri\\Downloads\\jasml_0.10";
//
//		static File file = new File("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		

>>>>>>> refs/heads/Junits
		static String SRC_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10";
		static File file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		static int separador;
		/**
		 * @throws java.lang.Exception
		 */
		@BeforeAll
		static void setUpBeforeClass() throws Exception {
			separador= 0;
			metricName.add("NOM_class");
			metricName.add("LOC_class");
			comp.add(comparator.BIGGER);
			comp.add(comparator.BIGGER);
			limits.add(13);
			limits.add(20);
			oper.add(operator.AND);
			rules.add(new Rule("LONG_method", 1, metricName, comp,
					 limits,  oper));
			c= new Central(rules);
//			file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
			file = new File("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		
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
	 * Test method for {@link central.Central#Central(java.util.ArrayList)}.
	 */
	@Test
	final void testCentral() {
		Assertions.assertNotNull(c);
	}

	/**
	 * a
	 * Test method for {@link central.Central#sys()}.
	 */
	@Test
	final void testSys() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#chooseRules(java.util.ArrayList)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testChooseRules() throws FileNotFoundException {
		ArrayList<Rule> rulesEmpty= new ArrayList<>();
		ArrayList<Rule> rules1= new ArrayList<>();
		rules1.add(new Rule("LONG_method", 1, metricName, comp,
				 limits,  oper));
		ArrayList<Rule> rules2= new ArrayList<>();
		rules2.add(new Rule("NOM_class", 0, metricName, comp,
				 limits,  oper));
		rules2.add(new Rule("LOC_class", 0, metricName, comp,
				 limits,  oper));
			Assertions.assertTrue(rulesEmpty.isEmpty());
			Assertions.assertNotNull(rulesEmpty);
			Assertions.assertEquals(rules1.size(),1);
			Assertions.assertNotNull(rules1);
			Assertions.assertEquals(rules2.size(),2); 
			Assertions.assertNotNull(rules2);
			
		
	}

	/**
	 * Test method for {@link central.Central#fuelAll()}.
	 */
	@Test
	final void testFuelAll() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#putMethodID()}.
	 */
	@Test
	final void testPutMethodID() {
		ArrayList<Resultado> all = new ArrayList<>();
		int[] ints= new int[5];
		all.add(new Resultado(3, SRC_PATH, 30, ints));
		c.putMethodID();
		Assertions.assertEquals(3, all.get(0).getMethodID());
	}

	/**
	 * Test method for {@link central.Central#writeExcel(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 * @throws IOException 
	 */
	@Test
	final void testWriteExcel() throws IOException {
		File file3 = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook();
		Sheet sheet = workBook.createSheet(file3.getName().replaceFirst("[.][^.]+$", ""));
		Row row = sheet.createRow(++separador);
		Assertions.assertNotNull(row);
		Assertions.assertNotNull(workBook);
		Assertions.assertNotNull(sheet);
		c.writeExcel(sheet, workBook);	
		Assertions.assertNotNull(file3);
		
		}

	/**
	 * Test method for {@link central.Central#cabecalho(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 */
	@Test
	final void testCabecalho() {
		XSSFWorkbook workBook = new XSSFWorkbook();
		String[] string = { "MethodID", "Package", "Class", "Method", "NOM_class", "LOC_class", "WMC_class", "LOC_method",
		"CYCLO_method" };
		int cellCount= 0;
		Sheet sheet = workBook.createSheet(file.getName().replaceFirst("[.][^.]+$", ""));
		Row cabecalho = sheet.createRow(0);
		Cell cell = cabecalho.createCell(cellCount++);
		Assertions.assertNotNull(cabecalho);
		Assertions.assertNotNull(cell);
		Assertions.assertEquals(9, string.length);
		
	}

	/**
	 * Test method for {@link central.Central#extracted()}.
	 * @throws IOException 
	 */
	@Test
	final void testExtracted() throws IOException {
		File dir = new File(SRC_PATH);
		ArrayList<File> lista = new ArrayList<File>();
		Assertions.assertNotNull(dir);
		Assertions.assertNotNull(lista);
		File[] v = c.extracted();
		Assertions.assertNotNull(v);
		Assertions.assertEquals(44, v.length);
	}

	/**
	 * Test method for {@link central.Central#listFiles(java.nio.file.Path)}.
	 * @throws IOException 
	 */
	@Test
	final void testListFiles() throws IOException {
		List<Path> result= new ArrayList<>();
		List<File> files = new ArrayList<File>();
		File dir = new File(SRC_PATH);
		Path path = Paths.get(dir.getAbsolutePath());
		result=c.listFiles(path);
		Assertions.assertNotNull(files);
		Assertions.assertNotNull(result);
		Assertions.assertNotNull(dir);
		Assertions.assertNotNull(path);
		Assertions.assertEquals(result.size(), c.listFiles(path).size());
		
	}

	/**
	 * Test method for {@link central.Central#pathsToFiles(java.util.List)}.
	 */
	@Test
	final void testPathsToFiles() {
		List<Path> paths= new ArrayList<>();
		List<File> files = new ArrayList<File>();
		File dir = new File(SRC_PATH);
		Path path = Paths.get(dir.getAbsolutePath());
		paths.add(path);
		Assertions.assertNotNull(files);
		Assertions.assertNotNull(paths);
		Assertions.assertNotNull(dir);
		Assertions.assertNotNull(path);
		files= c.pathsToFiles(paths);
		Assertions.assertEquals(files.size(), paths.size());
		
	}

	/**
	 * Test method for {@link central.Central#getSourcePath()}.
	 */
	@Test
	final void testGetSourcePath() {
		assertEquals(SRC_PATH, c.getSourcePath());
		
	}

	/**
	 * Test method for {@link central.Central#getFile()}.
	 */
	@Test
	final void testGetFile() {
		assertEquals(file, c.getFile());
	}

	/**
	 * Test method for {@link central.Central#setSourcePath(java.lang.String)}.
	 */
	@Test
	final void testSetSourcePath() {
		 String SRC_PATH2 = "C:\\Users\\nmsid\\Downloads\\jasml_0.10";
		 c.setSourcePath(SRC_PATH2);
			assertEquals(SRC_PATH2, c.getSourcePath());
	}

	/**
	 * Test method for {@link central.Central#setFile(java.io.File)}.
	 */
	@Test
	final void testSetFile() {
		File file2 = new File("C:\\Users\\nmsid\\OneDrive\\Documentos\\jasml_metrics.xlsx");
				c.setFile(file2);
		assertEquals(file2, c.getFile());
	}

	/**
	 * Test method for {@link central.Central#getAll()}.
	 */
	@Test
	final void testGetAll() {
		ArrayList<Resultado> all = c.getAll();
		assertEquals(all, c.getAll());
	}

	/**
	 * Test method for {@link central.Central#main(java.lang.String[])}.
	 */
	@Test
	final void testMain() {
		fail("Not yet implemented"); // TODO
	}

}
<<<<<<< HEAD
>>>>>>> refs/heads/main
=======
>>>>>>> refs/heads/Junits
