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
		int smaller= 10;
		int bigger= 30;
		Assertions.assertFalse(t.isBigger(smaller));
		Assertions.assertTrue(t.isBigger(bigger));
		Assertions.assertTrue(t.isBigger(40));
	}

	/**
	 * Test method for {@link rules.Threshold#isSmaller(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testIsSmaller() throws FileNotFoundException {
		int smaller= 10;
		int bigger= 30;
		Assertions.assertTrue(t.isSmaller(smaller));
		Assertions.assertFalse(t.isSmaller(bigger));
		Assertions.assertTrue(t.isSmaller(smaller));
		Assertions.assertFalse(t.isSmaller(40));
	}

	/**
	 * Test method for {@link rules.Threshold#isEquals(int)}.
	 * 
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
		
		Assertions.assertFalse(t.isEquals(19));
	}
	
	/**
	 * Test method for {@link rules.Threshold#isBiggerEquals(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testIsBiggerEquals() throws FileNotFoundException {
		int smaller= 10;
		int bigger= 30;
		int equals= 20;
		Assertions.assertFalse(t.isBiggerEquals(smaller));
		Assertions.assertTrue(t.isBiggerEquals(bigger));
		Assertions.assertTrue(t.isBiggerEquals(equals));
		
		Assertions.assertFalse(t.isBiggerEquals(19));
	}
	
	/**
	 * Test method for {@link rules.Threshold#isSmallerEquals(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testIsSmallerEquals() throws FileNotFoundException {
		int smaller= 10;
		int bigger= 30;
		int equals= 20;
		Assertions.assertTrue(t.isSmallerEquals(smaller));
		Assertions.assertFalse(t.isSmallerEquals(bigger));
		Assertions.assertTrue(t.isSmallerEquals(equals));
		
		Assertions.assertTrue(t.isSmallerEquals(19));
	}

	/**
	 * Test method for {@link rules.Threshold#result(int)}.
	 * @throws FileNotFoundException 
	 * 
	 * @throws FileNotFoundException
	 */
	
	@Test
	final void testResult() throws FileNotFoundException {
		int smaller= 10;
		int bigger= 30;
		int equals= 20;
		t.setComparator(comparator.BIGGER);
		Assertions.assertFalse(t.result(smaller));
		Assertions.assertTrue(t.result(bigger));
		t.setComparator(comparator.SMALLER);
		Assertions.assertFalse(t.result(bigger));
		Assertions.assertTrue(t.result(smaller));
		t.setComparator(comparator.EQUALS);
		Assertions.assertFalse(t.result(smaller));
		Assertions.assertTrue(t.result(equals));
		t.setComparator(comparator.BIGGEREQUALS);
		Assertions.assertFalse(t.result(smaller));
		Assertions.assertTrue(t.result(equals));
		Assertions.assertTrue(t.result(bigger));
		t.setComparator(comparator.SMALLEREQUALS);
		Assertions.assertFalse(t.result(bigger));
		Assertions.assertTrue(t.result(equals));
		Assertions.assertTrue(t.result(smaller));
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
		metricName= "NOM_class";
		int position;
		t.setMetricName(metricName);
		ArrayList<BoolResultado> r= new ArrayList<>();
		t.positionToGet();
		t.setMetricBoolean(r);
//		Assertions.assertEquals(position, t.positionToGet());	
		
		Assertions.assertNotNull(r);
	}

	/**
	 * Test method for {@link rules.Threshold#positionToGet()}.
	 * @throws IllegalArgumentException
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

	}

	/**
	 * 
	 * Test method for {@link rules.Threshold#getLimit()}.
	 */
	@Test
	final void testGetLimit() {
		Assertions.assertEquals(limit, t.getLimit());
		Assertions.assertEquals(t.getLimit(), limit);
	}
	
	/**
	 * Test method for {@link rules.Threshold#getComparator()}.
	 */
	@Test
	final void testGetComparator() {
		Assertions.assertEquals(o, t.getComparator());
		Assertions.assertEquals(t.getComparator(), o);
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
		Assertions.assertEquals(resultados, t.getResultados());
		Assertions.assertNotNull(resultados);
		Assertions.assertEquals(t.getResultados(), resultados);
	}
	
	/**
	 * Test method for {@link rules.Threshold#setMetricName()}.
	 */
	@Test
	final void testSetMetricName() {
		t.setMetricName("LOC_class");
		Assertions.assertEquals("LOC_class", t.getMetricName());
	}
	


}