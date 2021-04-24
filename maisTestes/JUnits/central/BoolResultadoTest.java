/**
 * 
 */
package central;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author nmsid
 *
 */

class BoolResultadoTest {
	static String classes;
	static String metodo;
	static String pack;
	static boolean verificacao;
	static BoolResultado b;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		b= new BoolResultado(pack, classes, metodo, verificacao);
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
	 * {@link central.BoolResultado#BoolResultado(java.lang.String, java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	final void testBoolResultado() {
		Assertions.assertNotNull(b);

	}

	/**
	 * Test method for {@link central.BoolResultado#setVerificacao(boolean)}.
	 */
	@Test
	final void testSetVerificacao() {
		
		boolean f= false;
		b.setVerificacao(f);
		Assertions.assertFalse(b.getVerificacao());

	}

	/**
	 * Test method for {@link central.BoolResultado#getPackage()}.
	 */
	@Test
	final void testGetPackage(){
		Assertions.assertEquals(b.getPackage(), pack);
	}

	/**
	 * Test method for {@link central.BoolResultado#getClasses()}.
	 */
	@Test
	final void testGetClasses() {
		Assertions.assertEquals(b.getClasses(), classes);
	}

	/**
	 * Test method for {@link central.BoolResultado#getMetodo()}.
	 */
	@Test
	final void testGetMetodo() {
		Assertions.assertEquals(b.getMetodo(), metodo);
	}

	/**
	 * Test method for {@link central.BoolResultado#getVerificacao()}.
	 */
	@Test
	final void testGetVerificacao() {
		Assertions.assertEquals(b.getVerificacao(), verificacao);
	}



}
