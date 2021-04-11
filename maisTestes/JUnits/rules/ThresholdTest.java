package rules;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Metrics.Resultado;
import central.BoolResultado;
import rules.Rule.comparator;

/**
 * @author nmsid henri
 *
 */
class ThresholdTest {
	static String FILE_PATH;
	static long serialVersionUID;
	static String metricName;
	static comparator o;
	static int limit;
	static ArrayList<Resultado> resultados;
	static Threshold t;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		serialVersionUID = 1L;
		metricName = "NOM_class";
		o = comparator.BIGGER;
		limit = 20;
		FILE_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10\\src\\com\\jasml\\classes\\ConstantPoolGenerator.java";
		resultados = new ArrayList<>();
		t = new Threshold(metricName, o, limit);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link rules.Threshold#Threshold(java.lang.String, rules.GuiOutput.comparators, int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testThreshold() throws FileNotFoundException {
		Assertions.assertNotNull(t);
	}

	/**
	 * Test method for {@link rules.Threshold#isBigger(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testIsBigger() throws FileNotFoundException {
		int smaller = 10;
		Assertions.assertFalse(t.isBigger(smaller));
		Assertions.assertTrue(t.isBigger(40));
	}

	/**
	 * Test method for {@link rules.Threshold#isSmaller(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testIsSmaller() throws FileNotFoundException {
		int bigger = 10;
		Assertions.assertTrue(t.isSmaller(bigger));
		Assertions.assertFalse(t.isSmaller(40));
	}

	/**
	 * Test method for {@link rules.Threshold#isEquals(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testIsEquals() throws FileNotFoundException {
		int equals = 20;
		Assertions.assertTrue(t.isEquals(equals));
		Assertions.assertFalse(t.isEquals(19));
	}

	/**
	 * Test method for {@link rules.Threshold#result(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testResult() throws FileNotFoundException {
		int limit= 20;
		t.setLimit(limit);
		t.setComparator(comparator.BIGGER);
		Assertions.assertTrue(t.result(40));
		Assertions.assertFalse(t.result(19));
		Assertions.assertFalse(t.result(20));
		t.setComparator(comparator.SMALLER);
		Assertions.assertTrue(t.result(10));
		Assertions.assertFalse(t.result(20));
		Assertions.assertFalse(t.result(21));
		t.setComparator(comparator.EQUALS);
		Assertions.assertTrue(t.result(20));
		Assertions.assertFalse(t.result(19));
		Assertions.assertFalse(t.result(21));

	}
	
	/**
	 * Test method for {@link rules.Threshold#result(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testSetLimit()  {
		int limit= 20;
		t.setLimit(limit);
		Assertions.assertEquals(limit, t.getLimit());
	}
	/**
	 * Test method for
	 * {@link rules.Threshold#setMetricBoolean(java.util.ArrayList)}.
	 */
	@Test
	final void testSetMetricBoolean() {
		ArrayList<BoolResultado> r= new ArrayList<>();
		Assertions.assertNotNull(r);
	}

	/**
	 * Test method for {@link rules.Threshold#positionToGet()}.
	 * @throws IllegalArgumentException
	 */
	@Test
	final void testPositionToGet() {
//		Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
//	        throw new IllegalArgumentException("error message");

		Assertions.assertEquals(t.positionToGet(), 0);
		t.setMetricName("LOC_class");
		Assertions.assertEquals(t.positionToGet(), 1);
		t.setMetricName("WMC_class");
		Assertions.assertEquals(t.positionToGet(), 2);
		t.setMetricName("LOC_method");
		Assertions.assertEquals(t.positionToGet(), 3);
		t.setMetricName("CYCLO_method");
		Assertions.assertEquals(t.positionToGet(), 4);

	}

	/**
	 * 
	 * Test method for {@link rules.Threshold#getLimit()}.
	 */
	@Test
	final void testGetLimit() {
		Assertions.assertEquals(t.getLimit(), limit);
	}

	/**
	 * Test method for {@link rules.Threshold#getComparator()}.
	 */
	@Test
	final void testGetComparator() {
		Assertions.assertEquals(t.getComparator(), o);
	}

	/**
	 * Test method for {@link rules.Threshold#getMetricName()}.
	 */
	@Test
	final void testGetMetricName() {
		Assertions.assertEquals(t.getMetricName(), "NOM_class");
		
	}

	/**
	 * Test method for {@link rules.Threshold#setMetricName()}.
	 */
	@Test
	final void testsetMetricName() {
		t.setMetricName("NOM_class");
		Assertions.assertEquals(t.getMetricName(), "NOM_class");
	}

	/**
	 * Test method for {@link rules.Threshold#getResultados()}.
	 */
	@Test
	final void testGetResultados() {
		Assertions.assertNotNull(resultados);
		Assertions.assertEquals(t.getResultados(), resultados);
	}

	/**
	 * Test method for {@link rules.Threshold#main(java.lang.String[])}.
	 */
	@Test
	final void testMain() {
		fail("Not yet implemented"); // TODO
	}

}