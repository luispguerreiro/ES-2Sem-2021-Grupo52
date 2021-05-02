package Excel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maisTestes.Linha;

/**
 * @author Grupo 52
 */

class LinhaTest {
	static Linha linha;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		linha = new Linha();
		linha.setPacote("pacote");
		linha.setClasse("classe");
		linha.setMetodo("metodo");
		linha.setIs_God_Class(true);
		linha.setIs_Long_Method(true);
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
	void testSetPacote() {
		Linha linha = new Linha();
		linha.setPacote("pacote");

		assertNotNull(linha.getPacote());
		assertEquals("pacote", linha.getPacote());
	}

	@Test
	void testSetClasse() {
		assertNotNull(linha.getClasse());
		assertEquals("classe", linha.getClasse());
	}

	@Test
	void testSetMetodo() {
		assertNotNull(linha.getMetodo());
		assertEquals("metodo", linha.getMetodo());
	}

	@Test
	void testSetIs_God_Class() {
		assertNotNull(linha.getis_God_Class());
		assertEquals(true, linha.getis_God_Class());
		assertTrue(linha.getis_God_Class());
	}

	@Test
	void testSetIs_Long_Method() {
		assertNotNull(linha.getis_Long_Method());
		assertEquals(true, linha.getis_Long_Method());
		assertTrue(linha.getis_Long_Method());
	}

	@Test
	void testGetPacote() {
		assertNotNull(linha.getPacote());
		assertEquals("pacote", linha.getPacote());
	}

	@Test
	void testGetClasse() {
		assertNotNull(linha.getClasse());
		assertEquals("classe", linha.getClasse());
	}

	@Test
	void testGetMetodo() {
		assertNotNull(linha.getMetodo());
		assertEquals("metodo", linha.getMetodo());
	}

	@Test
	void testGetis_God_Class() {
		assertNotNull(linha.getis_God_Class());
		assertTrue(linha.getis_God_Class());
	}

	@Test
	void testGetis_Long_Method() {
		assertNotNull(linha.getis_Long_Method());
		assertTrue(linha.getis_Long_Method());
	}

	@Test
	void testToString() {
		String string = linha.toString();

		assertEquals(string, linha.toString());
		assertTrue(string.matches("pacote(.*)"));
		assertTrue(string.contains("pacote classe metodo"));
	}
	
}
