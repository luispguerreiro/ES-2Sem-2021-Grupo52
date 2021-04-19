package Excel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import maisTestes.Comparador;
import maisTestes.Excel;
import maisTestes.Linha;
import central.BoolResultado;

class ComparadorTest {

	static Comparador c;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File("Code_Smells.xlsx");
		Excel excel = new Excel();
		excel.lerExcel(file);
		ArrayList<BoolResultado> boolMethod = new ArrayList<>();
		ArrayList<BoolResultado> boolClass = new ArrayList<>();
		ArrayList<Linha> linhas = new ArrayList<Linha>(excel.getList());
		c = new Comparador(boolMethod, boolClass, 1);
		c.methodComp(boolMethod, linhas);
		c.classComp(boolClass, linhas);

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
	void testComparador() throws FileNotFoundException, IOException {
		assertNotNull(c);
	}

	@Test
	void testMethodComp() throws FileNotFoundException, IOException {
		ArrayList<BoolResultado> boolMethod2 = new ArrayList<>();
		ArrayList<BoolResultado> boolClass2 = new ArrayList<>();
		Comparador c2 = new Comparador(boolMethod2, boolClass2, 1);
		assertNotNull(c.getMethodCheck());
		assertEquals(c.getMethodCheck(), c2.getMethodCheck());

	}

	@Test
	void testClassComp() {
		assertNotNull(c.getClassCheck());
	}

	@Test
	void testGetCountVP() {
		assertNotNull(c.getCountVP());
		assertEquals(0, c.getCountVP());
	}

	@Test
	void testGetCountFP() {
		assertNotNull(c.getCountFP());
		assertEquals(0, c.getCountFP());
	}

	@Test
	void testGetCountVN() {
		assertNotNull(c.getCountVN());
		assertEquals(0, c.getCountVN());
	}

	@Test
	void testGetCountFN() {
		assertNotNull(c.getCountFN());
		assertEquals(0, c.getCountFN());
	}

	@Test
	void testGetClassCheck() {
		ArrayList<String> nome = new ArrayList<>();
		assertNotNull(c.getClassCheck());
		assertEquals(nome, c.getClassCheck());
	}

	@Test
	void testGetMethodCheck() {
		ArrayList<String> nome1 = new ArrayList<>();
		assertNotNull(c.getMethodCheck());
		assertEquals(nome1, c.getMethodCheck());
	}

}
