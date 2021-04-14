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
import rules.Rule.comparator;
import rules.Threshold;

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
		o= comparator.BIGGER;
		limit= 20;
		FILE_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10\\src\\com\\jasml\\classes\\ConstantPoolGenerator.java";
		resultados = new ArrayList<>();
		t= new Threshold (metricName, o, limit);
		
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
	 * Test method for {@link rules.Threshold#Threshold(java.lang.String, rules.GuiOutput.comparators, int)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testThreshold() throws FileNotFoundException {
		
		Assertions.assertNotNull(t);
	}

	/**
	 * Test method for {@link rules.Threshold#isBigger(int)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testIsBigger() throws FileNotFoundException {
		int smaller= 10;
		int bigger= 30;
		Assertions.assertFalse(t.isBigger(smaller));
		Assertions.assertTrue(t.isBigger(bigger));
	}

	/**
	 * Test method for {@link rules.Threshold#isSmaller(int)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testIsSmaller() throws FileNotFoundException {
		int smaller= 10;
		int bigger= 30;
		Assertions.assertTrue(t.isSmaller(smaller));
		Assertions.assertFalse(t.isSmaller(bigger));
	}

	/**
	 * Test method for {@link rules.Threshold#isEquals(int)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testIsEquals() throws FileNotFoundException {
		int smaller= 10;
		int bigger= 30;
		int equals= 20;
		Assertions.assertFalse(t.isEquals(smaller));
		Assertions.assertFalse(t.isEquals(bigger));
		Assertions.assertTrue(t.isEquals(equals));
		
	}

	/**
	 * Test method for {@link rules.Threshold#result(int)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testResult() throws FileNotFoundException {
//		comparator bi= comparator.BIGGER;
		int smaller= 10;
		int bigger= 30;
		int equals= 20;
		t.setComparator(comparator.BIGGER);
		Assertions.assertFalse(t.result(smaller));
		Assertions.assertTrue(t.result(bigger));
//		comparator sm= comparator.SMALLER;
		t.setComparator(comparator.SMALLER);
		Assertions.assertFalse(t.result(bigger));
		Assertions.assertTrue(t.result(smaller));
//		comparator eq= comparator.EQUALS;
		t.setComparator(comparator.EQUALS);
		Assertions.assertFalse(t.result(smaller));
		Assertions.assertTrue(t.result(equals));
	}
	
	/**
	 * Test method for {@link rules.Threshold#setMetricBoolean(java.util.ArrayList)}.
	 */
	@Test
	final void testSetMetricBoolean() {
//		metricName= "NOM_class";
//		int position;
//		t.setMetricName(metricName);
//		t.positionToGet();
//		position= t.positionToGet();
//		Assertions.assertEquals(position, t.setMetricBoolean());	
		fail("Not yet implemented"); // TODO
		
	}

	/**
	 * Test method for {@link rules.Threshold#positionToGet()}.
	 * 	 * @throws IllegalArgumentException 
	 */
	@Test
	final void testPositionToGet() {
		metricName= "NOM_class";
		t.setMetricName(metricName);
		Assertions.assertEquals(0, t.positionToGet());
		t.setMetricName("LOC_class");
		Assertions.assertEquals(1, t.positionToGet());
		t.setMetricName("WMC_class");
		Assertions.assertEquals(2, t.positionToGet());		
		t.setMetricName("LOC_method");
		Assertions.assertEquals(3, t.positionToGet());
		t.setMetricName("CYCLO_method");
		Assertions.assertEquals(4, t.positionToGet());
		t.setMetricName("erro");
//		Assertions.assertNotEquals(4, t.positionToGet());
//		Assertions.assertThrows(IllegalArgumentException.class, t.positionToGet());
	}

	/**
	 * 
	 * Test method for {@link rules.Threshold#getLimit()}.
	 */
	@Test
	final void testGetLimit() {
		Assertions.assertEquals(limit, t.getLimit());
	}
	
	/**
	 * Test method for {@link rules.Threshold#getComparator()}.
	 */
	@Test
	final void testGetComparator() {
		Assertions.assertEquals(o, t.getComparator());
	}

	/**
	 * Test method for {@link rules.Threshold#getMetricName()}.
	 */
	@Test
	final void testGetMetricName() {
		t.setMetricName("LOC_class");
		Assertions.assertEquals("LOC_class", t.getMetricName());
	}

	/**
	 * Test method for {@link rules.Threshold#getResultados()}.
	 */
	@Test
	final void testGetResultados() {
		Assertions.assertEquals(resultados, t.getResultados());
	}
	
	/**
	 * Test method for {@link rules.Threshold#setMetricName()}.
	 */
	@Test
	final void testSetMetricName() {
		t.setMetricName("LOC_class");
		Assertions.assertEquals("LOC_class", t.getMetricName());
	}
	

	/**
	 * Test method for {@link rules.Threshold#main(java.lang.String[])}.
	 */
	@Test
	final void testMain() {
		fail("Not yet implemented"); // TODO
	}

}