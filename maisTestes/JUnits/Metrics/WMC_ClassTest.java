package Metrics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WMC_ClassTest {
	private static String FILE_PATH = "SourceCodeParser.java";
	static Metrics m;
	private static WMC_Class wmc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		m = new Metrics(FILE_PATH);
		wmc = new WMC_Class(m);
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
	final void testWMC_Class() {
		WMC_Class wmcClass = new WMC_Class(m);
		assertNotNull(wmcClass);
	}


	@Test
	final void testGetResultados() {
		assertNotNull(wmc.getResultados());
		assertEquals(wmc.getResultados().size(),3);
	}

}
