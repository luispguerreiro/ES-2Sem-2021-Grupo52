package Excel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maisTestes.Linha;

class LinhaTest {

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
	void testSetPacote() {
		Linha linha = new Linha();
		linha.setPacote("pacote");
		
		assertNotNull(linha.getPacote());
		assertEquals("pacote", linha.getPacote());
	}

	@Test
	void testSetClasse() {
		Linha linha = new Linha();
		linha.setClasse("classe");
		
		assertNotNull(linha.getClasse());
		assertEquals("classe", linha.getClasse());
	}

	@Test
	void testSetMetodo() {
		Linha linha = new Linha();
		linha.setMetodo("metodo");
		
		assertNotNull(linha.getMetodo());
		assertEquals("metodo", linha.getMetodo());
	}

	@Test
	void testSetIs_God_Class() {
		Linha linha = new Linha();
		linha.setIs_God_Class(true);
		
		assertNotNull(linha.getis_God_Class());
		assertEquals(true, linha.getis_God_Class());
		assertTrue(linha.getis_God_Class());
	}

	@Test
	void testSetIs_Long_Method() {
		Linha linha = new Linha();
		linha.setIs_Long_Method(true);
		
		assertNotNull(linha.getis_Long_Method());
		assertEquals(true, linha.getis_Long_Method());
		assertTrue(linha.getis_Long_Method());
	}

	@Test
	void testGetPacote() {
		Linha linha = new Linha();
		linha.setPacote("pacote");
		
		assertNotNull(linha.getPacote());
		assertEquals("pacote", linha.getPacote());
	}

	@Test
	void testGetClasse() {
		Linha linha = new Linha();
		linha.setClasse("classe");
		
		assertNotNull(linha.getClasse());
		assertEquals("classe", linha.getClasse());
	}

	@Test
	void testGetMetodo() {
		Linha linha = new Linha();
		linha.setMetodo("metodo");
		
		assertNotNull(linha.getMetodo());
		assertEquals("metodo", linha.getMetodo());
	}

	@Test
	void testGetis_God_Class() {
		Linha linha = new Linha();
		linha.setIs_God_Class(true);
		
		assertNotNull(linha.getis_God_Class());
		assertTrue(linha.getis_God_Class());
	}

	@Test
	void testGetis_Long_Method() {
		Linha linha = new Linha();
		linha.setIs_Long_Method(true);
		
		assertNotNull(linha.getis_Long_Method());
		assertTrue(linha.getis_Long_Method());
	}

	@Test
	void testToString() {
		Linha linha = new Linha();
		
		linha.setPacote("pacote");
		linha.setClasse("classe");
		linha.setMetodo("metodo");
		linha.setIs_God_Class(true);
		linha.setIs_Long_Method(true);
		
		String string = linha.toString();
		
		assertEquals(string, linha.toString());
		assertTrue(string.matches("pacote(.*)"));
		assertTrue(string.contains("pacote classe metodo"));
	}

}
