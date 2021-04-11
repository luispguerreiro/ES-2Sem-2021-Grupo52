package Metrics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Loc_MethodTest {
	private static String FILE_PATH = "C:\\\\Users\\\\r_f_g\\\\Desktop\\\\Jasml\\\\src\\\\com\\\\jasml\\\\classes\\\\Attribute.java";
	static Metrics m;
	private static Loc_Method locMethod2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		m = new Metrics(FILE_PATH);
		 locMethod2 = new Loc_Method(m);
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
	final void testLoc_Method() {
		Loc_Method locMethod = new Loc_Method(m);
		assertNotNull(locMethod);
	}

	@Test
	final void testGetResultados() {
		assertNotNull(locMethod2.getResultados());
		assertEquals(locMethod2.getResultados().size(), 2);
	}

}
