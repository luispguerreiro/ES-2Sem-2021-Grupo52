package excel;

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

import central.BoolResultado;
import excel.Comparador;
import excel.Excel;
import excel.Linha;

/**
 * @author Grupo 52
 */

class ComparadorTest {

	static Comparador c;
	static ArrayList<BoolResultado> boolMethod;
	static ArrayList<BoolResultado> boolClass;
	static ArrayList<Linha> linhas;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File("Code_Smells.xlsx");
		Excel excel = new Excel();
		excel.lerExcel(file);
		boolMethod = new ArrayList<>();
		boolMethod.add(new BoolResultado(1, "testpackage", "SimpleClass", "SimpleClass()", true));
		boolMethod.add(new BoolResultado(4, "com.jasml.classes", "Attribute", "Attribute(byte,int)", true));
		boolClass = new ArrayList<>();
		boolClass.add(new BoolResultado(1, "testpackage", "SimpleClass", "SimpleClass()", true));
		boolClass.add(new BoolResultado(4, "com.jasml.classes", "Attribute", "Attribute(byte,int)", true));
		linhas = new ArrayList<Linha>(excel.getList());
		c = new Comparador(boolMethod, boolClass, 1);
		c.methodComp(boolMethod, linhas);
		c.classComp(boolClass, linhas);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		c = new Comparador(boolMethod, boolClass, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testComparador() throws FileNotFoundException, IOException {
		assertNotNull(c);

		c = new Comparador(boolMethod, boolClass, 2);
		assertNotNull(c);

		c = new Comparador(boolMethod, boolClass, 3);
		assertNotNull(c);
	}

	@Test
	void testMethodComp() throws FileNotFoundException, IOException {
		ArrayList<BoolResultado> boolMethod2 = new ArrayList<>();
		ArrayList<BoolResultado> boolClass2 = new ArrayList<>();
		boolMethod2.add(new BoolResultado(1, "testpackage", "SimpleClass", "SimpleClass()", true));
		boolMethod2.add(new BoolResultado(4, "com.jasml.classes", "Attribute", "Attribute(byte,int)", true));
		Comparador c2 = new Comparador(boolMethod2, boolClass2, 3);
		c2.methodComp(boolMethod2, linhas);

		assertNotNull(c2.getMethodCheck());
		assertEquals(c.getMethodCheck(), c2.getMethodCheck());
		assertFalse(linhas.isEmpty());
	}

	@Test
	void testClassComp() throws FileNotFoundException, IOException {
		ArrayList<BoolResultado> boolMethod2 = new ArrayList<>();
		ArrayList<BoolResultado> boolClass2 = new ArrayList<>();
		boolClass2.add(new BoolResultado(1, "testpackage", "SimpleClass", "SimpleClass()", true));
		boolClass2.add(new BoolResultado(4, "com.jasml.classes", "Attribute", "Attribute(byte,int)", true));
		Comparador c3 = new Comparador(boolMethod2, boolClass2, 2);
		c3.classComp(boolClass2, linhas);

		assertNotNull(c3);
		assertEquals(c.getClassCheck(), c3.getClassCheck());
		assertFalse(linhas.isEmpty());
	}

	@Test
	void testGetCountVP() {
		assertNotNull(c.getCountVP());
		assertEquals(0, c.getCountVP());
	}

	@Test
	void testGetCountFP() {
		assertNotNull(c.getCountFP());
		assertEquals(2, c.getCountFP());
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
		nome.add("erro");
		nome.add("Falso Positivo");
		c.classComp(boolClass, linhas);

		assertNotNull(c.getClassCheck());
//		assertEquals(nome, c.getClassCheck());
	}

	@Test
	void testGetMethodCheck() {
		ArrayList<String> nome1 = new ArrayList<>();
		nome1.add("erro");
		nome1.add("Falso Positivo");
		c.methodComp(boolMethod, linhas);

		assertNotNull(c.getMethodCheck());
//		assertEquals(nome1, c.getMethodCheck());
	}
}
