/**
 * 
 */
package central;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Metrics.Resultado;
import rules.Rule;
import rules.Rule.comparator;
import rules.Rule.operator;
import rules.Threshold;

/**
 * @author Nazif Sidique & Henrique Marques
 *
 */
class HistoryTest {
	static ArrayList<Rule> rules= new ArrayList<>();
	static ArrayList<Threshold> thresholds;
	static ArrayList<String> metricName;
	static ArrayList<comparator> comp;
	static ArrayList<Integer> limits;
	static ArrayList<operator> oper;
	static ArrayList<operator> oper2;
	static ArrayList<Resultado> result; 
	static ArrayList<BoolResultado> boolresult;
	
	static int ruleType;
	static Rule r;
	static String folderPathToSave= "save";
	static String folderPathToRead = "read";
	static String ruleName= "name";
	static History h;

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
		oper2 = new ArrayList<>();
		result = new ArrayList<>();
		boolresult = new ArrayList<>();
		ruleName = "Teste";
		ruleType = 1;
		metricName.add("NOM_class");
		metricName.add("LOC_class");
		metricName.add("WMC_class");
		comp.add(comparator.BIGGER);
		comp.add(comparator.SMALLER);
		comp.add(comparator.BIGGER);
		oper.add(operator.AND);
		oper.add(operator.AND);
		oper2.add(operator.OR);
		oper2.add(operator.OR);
		limits.add(20);
		limits.add(10);
		limits.add(30);
		r = new Rule(ruleName, ruleType, metricName, comp, limits, oper);
		rules.add(r);
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
	 * Test method for {@link central.History#History()}.
	 */
	@Test
	final void testHistory() {
		h = new History();
	}

	/**
	 * Test method for
	 * {@link central.History#setFolderPathToSave(java.lang.String)}.
	 */
	@Test
	final void testSetFolderPathToSave() {
		String folder = "folder";
		h.setFolderPathToSave(folder);
		Assertions.assertEquals(h.getFolderPathToSave(), folder);
	}
	
	/**
	 * Test method for
	 * {@link central.History#setFolderPathToRead(java.lang.String)}.
	 */
	@Test
	final void testSetFolderPathToRead() {
		String folder = "folder";
		h.setFolderPathToRead(folder);
		Assertions.assertEquals(h.getFolderPathToRead(), folder);
	}


	/**
	 * Test method for {@link central.History#setRuleName(java.lang.String)}.
	 */
	@Test
	final void testSetRuleName() {
		String folder = "folder";
		h.setRuleName(folder);
		Assertions.assertEquals(h.getRuleName(), folder);
	}


	/**
	 * Test method for {@link central.History#writeFile(java.util.ArrayList)}.
	 * @throws IOException 
	 */
	@Test
	final void testWriteFile() throws IOException {
		History h1= new History();
		h1.setFolderPathToSave("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\");
		h1.setRuleName("regra");
		h1.writeFile(rules);
		Assertions.assertNotNull(rules);
	}

	/**
	 * Test method for {@link central.History#readFile(java.lang.String)}.
	 */
	@Test
	final void testReadFile() {
		
		History h1= new History();
		h1.setFolderPathToSave("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\");
		h1.setRuleName("regra");
		h1.writeFile(rules);
		ArrayList<Rule> rules1= h1.readFile("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\regra");
		Assertions.assertFalse(rules1.isEmpty());
	}

}
