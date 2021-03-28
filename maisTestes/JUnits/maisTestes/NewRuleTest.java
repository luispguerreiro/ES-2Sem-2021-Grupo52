/**
 * 
 */
package maisTestes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maisTestes.GuiOutput.comparators;
import maisTestes.GuiOutput.operators;

/**
 * @author henry&nmsidique
 *
 */
class NewRuleTest {
	static NewRule nr1;
	static ArrayList<String> metricName = new ArrayList<>();
	static ArrayList<comparators> comp = new ArrayList<>();
	static ArrayList<Integer> limits = new ArrayList<>();
	static ArrayList<operators> oper = new ArrayList<>();
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		nr1= new NewRule("Regra1", metricName, comp, limits, oper);  
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
	 * Test method for {@link maisTestes.NewRule#NewRule(java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)}.
	 */
	@Test
	final void testNewRule() {
		Assertions.assertNotNull(nr1);
	}

	/**
	 * Test method for {@link maisTestes.NewRule#main(java.lang.String[])}.
	 */
	@Test
	final void testMain() {
		fail("Not yet implemented"); // TODO
	}

}
