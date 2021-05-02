package Metrics;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

class MetricsTest {
	private static String FILE_PATH = "SourceCodeParser.java";
	static Metrics metric;
	static CompilationUnit cu;
	private static List<ClassOrInterfaceDeclaration> clazz;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		metric = new Metrics(FILE_PATH);
		cu = StaticJavaParser.parse(new File(FILE_PATH));
		clazz = cu.findAll(ClassOrInterfaceDeclaration.class);

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
	final void testMetrics() throws FileNotFoundException {
		Metrics m = new Metrics(FILE_PATH);
		assertNotNull(m);
	}

	@Test
	final void testGetMainClass() {
		assertNotNull(metric.getMainClass());
		
	}

	@Test
	final void testGetNestedClasses() {
		assertNotNull(metric.getNestedClasses());
		
	}

	@Test
	final void testGetCu() {
		assertNotNull(metric.getCu());
		assertEquals(metric.getCu(), cu);
	}

	@Test
	final void testGetClazz() {
		assertNotNull(metric.getClazz());
		assertEquals(metric.getClazz(), clazz);
	}

	


	@Test
	final void testGetFilePath() {
		assertNotNull(metric.getFilePath());
		assertEquals(metric.getFilePath(), FILE_PATH);
	}

}
