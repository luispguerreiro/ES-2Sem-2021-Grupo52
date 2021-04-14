package Metrics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Loc_ClassTest {
	private static String FILE_PATH = "C:\\Users\\r_f_g\\Desktop\\Jasml\\src\\com\\jasml\\classes\\Attribute.java";
	static Metrics m;
	private static Loc_Class locClass2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		m = new Metrics(FILE_PATH);
		 locClass2 = new Loc_Class(m);
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
	final void testLoc_Class() {
		Loc_Class locclass = new Loc_Class(m);
		assertNotNull(locclass);
	}

	@Test
	final void testGetResultados() {
		assertNotNull(locClass2.getResultados());
	}

}
