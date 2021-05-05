package Excel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excel.Excel;
import excel.Procura;

/**
 * @author Grupo 52
 */

class ProcuraTest {
	static Procura p;
	static Excel excel;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		p = new Procura();
		excel = new Excel();

		excel.lerExcel(new File("Code_Smells.xlsx"));
		p.getProcura(excel.getList(), "GrammerException", "GrammerException(int,String)");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetProcura() throws FileNotFoundException, IOException {
		assertNotNull(p.getLinha());
	}

	@Test
	void testGetLinha() {
		Procura p1 = new Procura();
		p1.getProcura(excel.getList(), "GrammerException", "GrammerException(int,String)");
		
		assertNotNull(p1.getLinha());
		assertEquals(p.getLinha(), p1.getLinha());
	}
}
