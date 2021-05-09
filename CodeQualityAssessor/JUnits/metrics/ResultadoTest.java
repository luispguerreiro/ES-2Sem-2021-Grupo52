package metrics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import metrics.CYCLO_method;
import metrics.Loc_Class;
import metrics.Loc_Method;
import metrics.NOM_Class;
import metrics.Resultado;
import metrics.WMC_Class;

class ResultadoTest {
	static String Path;
	static String Package;
	static String Classe;
	private static String FILE_PATH = "SourceCodeParser.java";
	private static Metrics m;
	private static Resultado rlc;
	private static Resultado rlm;
	private static Resultado rcyclo;
	private static Resultado rwmc;
	private static Resultado rnom;
	private static Resultado rTeste;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		m=new Metrics(FILE_PATH);
		Loc_Method l = new Loc_Method(m);
		Loc_Class lc= new Loc_Class(m);
		CYCLO_method cyclo = new CYCLO_method(m);
		WMC_Class wmc = new WMC_Class(m);
		NOM_Class nom = new NOM_Class(m);
		rTeste = new Resultado(0, FILE_PATH, 0, null);
		rlc= lc.getResultados().get(0);
		rlm=l.getResultados().get(0);
		rcyclo=cyclo.getResultados().get(0);
		rwmc=wmc.getResultados().get(0);
		rnom=nom.getResultados().get(0);
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
	final void testResultado() {
		Resultado resultado = new Resultado(0, FILE_PATH, 0, null);
		assertNotNull(resultado);
	}

	@Test
	final void testGetAllInts() {
	assertNotNull(rlc.getAllInts());	
	assertNotNull(rlm.getAllInts());	
	assertNotNull(rwmc.getAllInts());	
	assertNotNull(rcyclo.getAllInts());
	assertNotNull(rnom.getAllInts());	
	assertNull(rTeste.getAllInts());
	}


	@Test
	final void testSetMethodID() {
		int a = 2;
		rlc.setMethodID(a);
		assertEquals(a, rlc.getMethodID());
	}

	@Test
	final void testGetMethodID() {
		assertNotNull(rlm.getMethodID());
		assertEquals(rlm.getMethodID(), rcyclo.getMethodID());
		
	}

	@Test
	final void testGetLinhas() {
		assertNotNull(rlm.getLinhas());
		
	}

	@Test
	final void testGetPath() {
		assertNotNull(rlm.getPath());
		
		}

	@Test
	final void testGetPackage() {
		assertNotNull(rlm.getPackage());
	}

	@Test
	final void testGetClasses() {
		assertNotNull(rlm.getPackage());
	}

	@Test
	final void testGetMethodNames() {
		assertNotNull(rlm.getMethodNames());
	}

}
