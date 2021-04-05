package maisTestes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	final void testMain() throws FileNotFoundException {
		Excel excel= new Excel();
		excel.lerExcel(new File("C:\\Users\\Vasco\\Downloads\\Code_Smells.xlsx"));
		
		assertNotNull(excel.getList());
		assertEquals(247, excel.getList().size());
	}

}
