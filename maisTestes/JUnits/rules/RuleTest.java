/**
 * 
 */
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

import rules.Rule.comparator;
import rules.Rule.operator;

/**
 * @author nmsid
 *
 */
class RuleTest {
	static ArrayList<Threshold> thresholds;
	static ArrayList<String> metricName;
	static ArrayList<comparator> comp;
	static ArrayList<Integer> limits;
	static ArrayList<operator> oper;
	static String ruleName;
	static int ruleType;
	static Rule r;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		thresholds = new ArrayList<>();
		metricName = new ArrayList<>();
		comp = new ArrayList<>();
		limits = new ArrayList<>();
		oper = new ArrayList<>();
		ruleName= "Teste";
		ruleType= 1;
		metricName.add("NOM_class");
		metricName.add("LOC_class");
		comp.add(comparator.BIGGER);
		comp.add(comparator.SMALLER);
		oper.add(operator.AND);
		limits.add(20);
		limits.add(10);
		r= new Rule(ruleName, ruleType, metricName, comp,
				limits,  oper);
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
		Assertions.assertNotNull(r);
	}

	/**
	 * Test method for {@link rules.Rule#getThresholds()}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testGetThresholds() throws FileNotFoundException {
		r.createThresholds();
		thresholds= r.getThresholds();
		Assertions.assertEquals(thresholds, r.getThresholds());
	}

	/**
	 * Test method for {@link rules.Rule#createThresholds()}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testCreateThresholds() throws FileNotFoundException {
		Threshold t= new Threshold(metricName.get(0), comp.get(0), limits.get(0));
		r.createThresholds();
		Assertions.assertNotNull(t);
		Assertions.assertNotNull(thresholds);
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
		boolean one= true;
		boolean two= true;
		boolean three= false;
		boolean four= false;
		Assertions.assertTrue(r.and(one, two));
		Assertions.assertFalse(r.and(one, three));
		Assertions.assertFalse(r.and(four, three));
		
	}

	/**
	 * Test method for {@link rules.Rule#or(boolean, boolean)}.
	 */
	@Test
	final void testOr() {
		boolean one= true;
		boolean two= false;
		boolean three= false;
		boolean four= true;
		Assertions.assertTrue(r.or(one, two));
		Assertions.assertFalse(r.or(two, three));
		Assertions.assertTrue(r.or(one, four));
	}

	/**
	 * Test method for {@link rules.Rule#getRuleName()}.
	 */
	@Test
	final void testGetRuleName() {
		Assertions.assertEquals(ruleName, r.getRuleName());
	}

	/**
	 * Test method for {@link rules.Rule#getRuleType()}.
	 */
	@Test
	final void testGetRuleType() {
		Assertions.assertEquals(ruleType, r.getRuleType());
	}

	/**
	 * Test method for {@link rules.Rule#getComp()}.
	 */
	@Test
	final void testGetComp() {
		Assertions.assertEquals(comp, r.getComp());
	}

	/**
	 * Test method for {@link rules.Rule#getLimits()}.
	 */
	@Test
	final void testGetLimits() {
		r.setLimits(limits);
		Assertions.assertEquals(limits, r.getLimits());
	}

	/**
	 * Test method for {@link rules.Rule#getMetricName()}.
	 */
	@Test
	final void testGetMetricName() {
		Assertions.assertEquals(metricName, r.getMetricName());
	}

	/**
	 * Test method for {@link rules.Rule#getOper()}.
	 */
	@Test
	final void testGetOper() {
		Assertions.assertEquals(oper, r.getOper());
	}

	/**
	 * Test method for {@link rules.Rule#setRuleName(java.lang.String)}.
	 */
	@Test
	final void testSetRuleName() {
		ruleName= "teste2";
		r.setRuleName(ruleName);
		Assertions.assertEquals(ruleName, r.getRuleName());
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
		ArrayList<Integer> limits2= new ArrayList<>();
		limits2.add(15);
		limits2.add(25);
		r.setLimits(limits2);
		Assertions.assertEquals(limits2, r.getLimits());
	}

}
