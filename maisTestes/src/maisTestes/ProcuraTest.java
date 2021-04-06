package maisTestes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProcuraTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	void testGetProcura() throws FileNotFoundException {
		Procura p = new Procura();
		Excel excel = new Excel();

		excel.lerExcel(new File("C:\\Users\\joao_\\Downloads\\Code_Smells.xlsx"));
		p.getProcura(excel.getList(), "GrammerException");

		assertNotNull(p.getLista());
		assertEquals(4, p.getLista().size());
		assertFalse(p.getLista().isEmpty());
	}

	@Test
	void testGetLista() throws FileNotFoundException {
		Procura p = new Procura();
		Excel excel = new Excel();

		excel.lerExcel(new File("C:\\Users\\joao_\\Downloads\\Code_Smells.xlsx"));
		p.getProcura(excel.getList(), "GrammerException");

		assertNotNull(p.getLista());
		assertFalse(p.getLista().isEmpty());
	}

}
