package Excel;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maisTestes.Excel;

class ExcelTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testLerExcel() throws FileNotFoundException, IOException {
		Excel excel = new Excel();
		excel.lerExcel(new File("C:\\Users\\joao_\\Downloads\\Code_Smells.xlsx"));

		assertNotNull(excel.getList());
		assertEquals(247, excel.getList().size());
	}

	@Test
	void testGetList() throws FileNotFoundException, IOException {
		Excel excel = new Excel();
		excel.lerExcel(new File("C:\\Users\\joao_\\Downloads\\Code_Smells.xlsx"));

		assertNotNull(excel.getList());
		assertFalse(excel.getList().isEmpty());
	}

	@Test
	void testMain() throws FileNotFoundException, IOException {
		Excel excel = new Excel();
		excel.lerExcel(new File("C:\\Users\\joao_\\Downloads\\Code_Smells.xlsx"));

		assertNotNull(excel.getList());
		assertEquals(247, excel.getList().size());
	}

}
