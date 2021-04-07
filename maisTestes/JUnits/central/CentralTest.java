/**
 * 
 */
package central;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maisTestes.Excel;

/**
 * @author nmsid & henry
 *
 */
class CentralTest {
	static Central c;
	static File file;
	static Sheet sheet;
	static Excel excel;
	static XSSFWorkbook workBook;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		c= new Central();
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
	 * Test method for {@link central.Central#Central()}.
	 */
	@Test
	final void testCentral() {
		Assertions.assertNotNull(c);
	}

	/**
	 * Test method for {@link central.Central#writeExcel(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 * @throws IOException 
	 */
	@Test
	final void testWriteExcel() throws IOException {
		c.writeExcel(sheet, workBook);
		Assertions.assertNotNull(c);
	}

	/**
	 * Test method for {@link central.Central#writeClassExcel(int, org.apache.poi.ss.usermodel.Row, org.apache.poi.ss.usermodel.Cell)}.
	 */
	@Test
	final void testWriteClassExcel() {
		Row cabecalho= sheet.createRow(0);
		Assertions.assertNotNull(cabecalho);
	}

	/**
	 * Test method for {@link central.Central#cabecalho(org.apache.poi.ss.usermodel.Sheet, org.apache.poi.xssf.usermodel.XSSFWorkbook)}.
	 */
	@Test
	final void testCabecalho() {
		Row cabecalho2= sheet.createRow(0);
		Assertions.assertNotNull(cabecalho2);
	}

	/**
	 * Test method for {@link central.Central#main(java.lang.String[])}.
	 */
	@Test
	final void testMain() {
		fail("Not yet implemented"); // TODO
	}

}
