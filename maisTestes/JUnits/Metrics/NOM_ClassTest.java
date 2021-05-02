package Metrics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NOM_ClassTest {
	private static String FILE_PATH = "SourceCodeParser.java";
	static Metrics m;
	private static NOM_Class nomClass;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		m = new Metrics(FILE_PATH);
		nomClass = new NOM_Class(m);
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
	final void testNOM_Class() {
		NOM_Class nom = new NOM_Class(m);
		assertNotNull(nom);
	}

	@Test
	final void testGetResultados() {
		assertNotNull(nomClass.getResultados());
		assertEquals(nomClass.getResultados().size(),3);
	}

}