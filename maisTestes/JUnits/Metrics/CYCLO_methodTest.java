package Metrics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CYCLO_methodTest {
	private static String FILE_PATH = "C:\\Users\\r_f_g\\Desktop\\SourceCodeParser.java";
	static Metrics m;
	private static CYCLO_method cyclo;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		m = new Metrics(FILE_PATH);
		 cyclo = new CYCLO_method(m);
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
	final void testCYCLO_method() {
		CYCLO_method teste = new CYCLO_method(m);
		assertNotNull(teste);
	}

	@Test
	final void testGetResultados() {
		assertNotNull(cyclo.getResultados());
		assertEquals(cyclo.getResultados().size(), 32);
	}

}
