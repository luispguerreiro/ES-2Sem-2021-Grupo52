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
<<<<<<< HEAD
		int smaller= 10;
		int bigger= 30;
=======
		int smaller = 10;
>>>>>>> refs/heads/Central
		Assertions.assertFalse(t.isBigger(smaller));
<<<<<<< HEAD
		Assertions.assertTrue(t.isBigger(bigger));
=======
		Assertions.assertTrue(t.isBigger(40));
>>>>>>> refs/heads/Central
	}

	/**
	 * Test method for {@link rules.Threshold#isSmaller(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testIsSmaller() throws FileNotFoundException {
<<<<<<< HEAD
		int smaller= 10;
		int bigger= 30;
		Assertions.assertTrue(t.isSmaller(smaller));
		Assertions.assertFalse(t.isSmaller(bigger));
=======
		int bigger = 10;
		Assertions.assertTrue(t.isSmaller(bigger));
		Assertions.assertFalse(t.isSmaller(40));
>>>>>>> refs/heads/Central
	}

	/**
	 * Test method for {@link rules.Threshold#isEquals(int)}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	final void testIsEquals() throws FileNotFoundException {
<<<<<<< HEAD
		int smaller= 10;
		int bigger= 30;
		int equals= 20;
		Assertions.assertFalse(t.isEquals(smaller));
		Assertions.assertFalse(t.isEquals(bigger));
=======
		int equals = 20;
>>>>>>> refs/heads/Central
		Assertions.assertTrue(t.isEquals(equals));
<<<<<<< HEAD
		
=======
		Assertions.assertFalse(t.isEquals(19));
>>>>>>> refs/heads/Central
	}

	/**
	 * Test method for {@link rules.Threshold#result(int)}.
<<<<<<< HEAD
	 * @throws FileNotFoundException 
=======
	 * 
	 * @throws FileNotFoundException
>>>>>>> refs/heads/Central
	 */
	@Test
	final void testResult() throws FileNotFoundException {
<<<<<<< HEAD
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
=======
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
	
>>>>>>> refs/heads/Central
	
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
<<<<<<< HEAD
		metricName= "NOM_class";
		int position;
		t.setMetricName(metricName);
		ArrayList<BoolResultado> r= new ArrayList<>();
		t.positionToGet();
//		Assertions.assertNotNull(t.setMetricBoolean(r));
//		Assertions.assertEquals(position, t.positionToGet());	
		fail("Not yet implemented"); // TODO
		
=======
		ArrayList<BoolResultado> r= new ArrayList<>();
		Assertions.assertNotNull(r);
>>>>>>> refs/heads/Central
	}

	/**
	 * Test method for {@link rules.Threshold#positionToGet()}.
<<<<<<< HEAD
	 * 	 * @throws IllegalArgumentException 
=======
	 * @throws IllegalArgumentException
>>>>>>> refs/heads/Central
	 */
	@Test
	final void testPositionToGet() {
<<<<<<< HEAD
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
=======
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

>>>>>>> refs/heads/Central
	}

	/**
	 * 
	 * Test method for {@link rules.Threshold#getLimit()}.
	 */
	@Test
	final void testGetLimit() {
<<<<<<< HEAD
		Assertions.assertEquals(limit, t.getLimit());
=======
		Assertions.assertEquals(t.getLimit(), limit);
>>>>>>> refs/heads/Central
	}
	
	/**
	 * Test method for {@link rules.Threshold#getComparator()}.
	 */
	@Test
	final void testGetComparator() {
<<<<<<< HEAD
		Assertions.assertEquals(o, t.getComparator());
=======
		Assertions.assertEquals(t.getComparator(), o);
>>>>>>> refs/heads/Central
	}

	/**
	 * Test method for {@link rules.Threshold#getMetricName()}.
	 */
	@Test
	final void testGetMetricName() {
<<<<<<< HEAD
		t.setMetricName("LOC_class");
		Assertions.assertEquals("LOC_class", t.getMetricName());
=======
		Assertions.assertEquals(t.getMetricName(), "NOM_class");
		
	}

	/**
	 * Test method for {@link rules.Threshold#setMetricName()}.
	 */
	@Test
	final void testsetMetricName() {
		t.setMetricName("NOM_class");
		Assertions.assertEquals(t.getMetricName(), "NOM_class");
>>>>>>> refs/heads/Central
	}

	/**
	 * Test method for {@link rules.Threshold#getResultados()}.
	 */
	@Test
	final void testGetResultados() {
<<<<<<< HEAD
		Assertions.assertEquals(resultados, t.getResultados());
=======
		Assertions.assertNotNull(resultados);
		Assertions.assertEquals(t.getResultados(), resultados);
>>>>>>> refs/heads/Central
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