/**
 * 
 */
package central;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maisTestes.Excel;
import rules.GuiOutput.comparators;
import rules.GuiOutput.operators;
import rules.Rule;

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
		static ArrayList<comparators> comp= new ArrayList<>();
		static ArrayList<Integer> limits= new ArrayList<>();
		static ArrayList<operators> oper= new ArrayList<>();
		static String SRC_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10";
		static File file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		
		/**
		 * @throws java.lang.Exception
		 */
		@BeforeAll
		static void setUpBeforeClass() throws Exception {
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
			file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx");
		
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
		
//		assertEquals(rules, c.getAll());
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
	 */
	@Test
	final void testChooseRules() {
		fail("Not yet implemented"); // TODO
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
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#writeExcel(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 */
	@Test
	final void testWriteExcel() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#cabecalho(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 */
	@Test
	final void testCabecalho() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#extracted()}.
	 */
	@Test
	final void testExtracted() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#listFiles(java.nio.file.Path)}.
	 */
	@Test
	final void testListFiles() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#pathsToFiles(java.util.List)}.
	 */
	@Test
	final void testPathsToFiles() {
		fail("Not yet implemented"); // TODO
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
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link central.Central#main(java.lang.String[])}.
	 */
	@Test
	final void testMain() {
		fail("Not yet implemented"); // TODO
	}

}
