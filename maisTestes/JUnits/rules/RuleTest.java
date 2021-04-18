/**
 * 
 */
package rules;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import central.BoolResultado;
import rules.Rule.comparator;
import rules.Rule.operator;

/**
 * @author nmsid e henri
 *
 */
class RuleTest {
	
	static ArrayList<Threshold> thresholds;

	static ArrayList<String> metricName;
	static ArrayList<comparator> comp;
	static ArrayList<Integer> limits;
	static ArrayList<operator> oper;
	static String ruleName;
	static ArrayList<BoolResultado> ruleResults;
	static int ruleType;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ruleName= "TestRule";
		ruleType= 0;
		
		thresholds = new ArrayList<>();
		comp = new ArrayList<>();
		limits = new ArrayList<>();
		oper = new ArrayList<>();
		ruleResults = new ArrayList<>();
//		ruleResults.add();
		Rule rule= new Rule(ruleName, ruleType, metricName, comp, limits, oper);
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
	 * Test method for {@link rules.Rule#Rule(java.lang.String, int, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)}.
	 */
	@Test
	final void testRule() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#getThresholds()}.
	 */
	@Test
	final void testGetThresholds() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#createThresholds()}.
	 */
	@Test
	final void testCreateThresholds() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#calculateThresholds(java.util.ArrayList, java.util.ArrayList)}.
	 */
	@Test
	final void testCalculateThresholds() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#logic1(rules.Threshold, int)}.
	 */
	@Test
	final void testLogic1() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#logic2(rules.Threshold, rules.Threshold, int, int)}.
	 */
	@Test
	final void testLogic2() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#logic3(rules.Threshold, rules.Threshold, rules.Threshold, int, int, int)}.
	 */
	@Test
	final void testLogic3() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#and(boolean, boolean)}.
	 */
	@Test
	final void testAnd() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#or(boolean, boolean)}.
	 */
	@Test
	final void testOr() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#getRuleName()}.
	 */
	@Test
	final void testGetRuleName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#getRuleType()}.
	 */
	@Test
	final void testGetRuleType() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#getComp()}.
	 */
	@Test
	final void testGetComp() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#getLimits()}.
	 */
	@Test
	final void testGetLimits() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#getMetricName()}.
	 */
	@Test
	final void testGetMetricName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#getOper()}.
	 */
	@Test
	final void testGetOper() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#setRuleName(java.lang.String)}.
	 */
	@Test
	final void testSetRuleName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#fuelArrays()}.
	 */
	@Test
	final void testFuelArrays() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#check()}.
	 */
	@Test
	final void testCheck() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link rules.Rule#setLimits(java.util.ArrayList)}.
	 */
	@Test
	final void testSetLimits() {
		fail("Not yet implemented"); // TODO
	}

}
