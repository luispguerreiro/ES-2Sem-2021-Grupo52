/**
 * 
 */
package Rules;

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
import rules.GuiOutput.comparators;
import rules.Threshold;

/**
 * @author nmsid & @author henri
 *
 */
class ThresholdTest {
	static String FILE_PATH;
	static long serialVersionUID;
	static String metricName;
	static comparators o;
	static int limit;
	static ArrayList<Resultado> resultados;
	static Threshold t;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		serialVersionUID = 1L;
		o= comparators.BIGGER;
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
		Assertions.assertTrue(t.isBigger(smaller));
	}

	/**
	 * Test method for {@link rules.Threshold#isSmaller(int)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testIsSmaller() throws FileNotFoundException {
		int bigger= 10;
		Assertions.assertTrue(t.isSmaller(bigger));
	}

	/**
	 * Test method for {@link rules.Threshold#isEquals(int)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testIsEquals() throws FileNotFoundException {
		int equals= 20;
		Assertions.assertTrue(t.isEquals(equals));
	}

	/**
	 * Test method for {@link rules.Threshold#result(int)}.
	 */
	@Test
	final void testResult() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Threshold#setMetricBoolean(java.util.ArrayList)}.
	 */
	@Test
	final void testSetMetricBoolean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Threshold#positionToGet()}.
	 */
	@Test
	final void testPositionToGet() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Threshold#getLimit()}.
	 */
	@Test
	final void testGetLimit() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Threshold#getComparator()}.
	 */
	@Test
	final void testGetComparator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Threshold#getMetricName()}.
	 */
	@Test
	final void testGetMetricName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Threshold#getResultados()}.
	 */
	@Test
	final void testGetResultados() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Threshold#main(java.lang.String[])}.
	 */
	@Test
	final void testMain() {
		fail("Not yet implemented"); // TODO
	}

}
