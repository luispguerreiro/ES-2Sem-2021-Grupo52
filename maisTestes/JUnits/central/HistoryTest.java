/**
 * 
 */
package central;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rules.Rule;

/**
 * @author nmsid
 *
 */
class HistoryTest {
	static ArrayList<Rule> rules= new ArrayList<>();

	static String folderPathToSave= "save";
	static String folderPathToRead = "read";
	static String ruleName= "name";
	static String string_date = "01/04/2021 13:00:00";
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	static History h;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

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
//		h.setRuleName(ruleName);
//		h.setFolderPathToRead(folderPathToRead);
//		h.setFolderPathToSave(folderPathToSave);
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
		FileOutputStream f = new FileOutputStream(new File(folderPathToSave + "\\" + ruleName));

		ObjectOutputStream o = new ObjectOutputStream(f);
		h.writeFile(rules);
	}

	/**
	 * Test method for {@link central.History#readFile(java.lang.String)}.
	 */
	@Test
	final void testReadFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link central.History#deleteOlderFiles(java.lang.String, java.lang.String)}.
	 */
	@Test
	final void testDeleteOlderFiles() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link central.History#getRecentFiles(java.lang.String, java.lang.String)}.
	 */
	@Test
	final void testGetRecentFiles() {
		fail("Not yet implemented"); // TODO
	}
}
