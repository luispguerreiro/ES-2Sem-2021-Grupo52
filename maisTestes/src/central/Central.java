package central;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Metrics.CYCLO_method;
import Metrics.Loc_Class;
import Metrics.Loc_Method;
import Metrics.Metrics;
import Metrics.NOM_Class;
import Metrics.Resultado;
import Metrics.WMC_Class;

public class Central {

	// private String SRC_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de
	// Trabalho\\miniJasml";
	// private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente
	// de Trabalho\\SourceCodeParser.java";
	// File file = new File("C:\\Users\\henri\\OneDrive\\Ambiente de
	// Trabalho\\jasml_metrics.xlsx"); // vai ser o nome
	private String SRC_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10\\src\\com\\jasml\\classes";
	private String FILE_PATH = "C:\\Users\\nmsid\\Downloads\\jasml_0.10\\src\\com\\jasml\\classes\\SourceCodeParser.java";
	private File file = new File("C:\\Users\\nmsid\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx"); // vai ser o
																											// nome
	// da
	// pasta"_metric"
	private Loc_Method locMethod;
	private CYCLO_method cycloMethod;
	private Loc_Class locClass;
	private NOM_Class nomClass;
	private WMC_Class wmcClass;
	int separador = 0;

	private Metrics metric;

	public Central() throws IOException {
		File dir = new File(SRC_PATH);
		File[] files = dir.listFiles();
		XSSFWorkbook workBook = new XSSFWorkbook();
		Sheet sheet = workBook.createSheet("aaa");
		for (int i = 0; i < files.length; i++) {

			metric = new Metrics(files[i].getAbsolutePath());

			locMethod = new Loc_Method(metric);
			cycloMethod = new CYCLO_method(metric);
			locClass = new Loc_Class(metric);
			nomClass = new NOM_Class(metric);
			wmcClass = new WMC_Class(metric);

			writeExcel(sheet, workBook);
		}
		OutputStream fileOut = new FileOutputStream(file);
		workBook.write(fileOut);
		fileOut.flush();
		fileOut.close();

	}

	public void writeExcel(Sheet sheet, XSSFWorkbook workBook) throws IOException {
		sheet.setDefaultColumnWidth(20);
		cabecalho(sheet, workBook);
		int rowCount = 1;
		int k = 0;
		for (int i = 0; i < cycloMethod.getResultados().size(); i++) {

			Row row = sheet.createRow(++separador);
			int colCount = 0;
			Cell pack = row.createCell(++colCount);
			Cell classes = row.createCell(++colCount);
			Cell methods = row.createCell(++colCount);
			Cell cell4 = row.createCell(++colCount);
			Cell cell5 = row.createCell(++colCount);
			Cell cell6 = row.createCell(++colCount);
			Cell cell7 = row.createCell(++colCount);
			Cell cell8 = row.createCell(++colCount);
			pack.setCellValue(locMethod.getResultados().get(i).getPackage());
			classes.setCellValue(locMethod.getResultados().get(i).getClasses());
			methods.setCellValue(locMethod.getResultados().get(i).getMethodNames());

			if (!(cycloMethod.getResultados().get(i).getClasses()
					.equals(cycloMethod.getResultados().get(k).getClasses()))
					&& k < nomClass.getResultados().size() - 1) {
				System.out.println(i + " " + k);
				k++;
			}

			cell4.setCellValue(nomClass.getResultados().get(k).getLinhas());
			cell5.setCellValue(locClass.getResultados().get(k).getLinhas());
			cell6.setCellValue(wmcClass.getResultados().get(k).getLinhas());

			cell7.setCellValue(locMethod.getResultados().get(i).getLinhas());
			cell8.setCellValue(cycloMethod.getResultados().get(i).getLinhas());

			Cell methodID = row.createCell(0);
			methodID.setCellValue(separador);
			rowCount++;

		}

	}

	public void writeClassExcel(int colCount, Row row, Cell cell) {
		Cell cell4 = row.createCell(++colCount);
		Cell cell5 = row.createCell(++colCount);
		Cell cell6 = row.createCell(++colCount);
		while (cell.getStringCellValue().equals(locClass.getResultados().get(0).getClasses())) {
			cell4.setCellValue(nomClass.getResultados().get(0).getLinhas());
			cell5.setCellValue(locClass.getResultados().get(0).getLinhas());
			cell6.setCellValue(wmcClass.getResultados().get(0).getLinhas());
		}

	}

	public void cabecalho(Sheet sheet, XSSFWorkbook workBook) {
		String[] c = { "MethodID", "Package", "Class", "Method", "NOM_class", "LOC_class", "WMC_class", "LOC_method",
				"CYCLO_method" };
		Row cabecalho = sheet.createRow(0);
		int cellCount = 0;
		XSSFCellStyle style = workBook.createCellStyle();
		XSSFFont font = workBook.createFont();
		font.setFontHeightInPoints((short) 15);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setBold(true);
		style.setFont(font);
		for (String s : c) {
			Cell cell = cabecalho.createCell(cellCount++);
			cell.setCellValue(s);
			cell.setCellStyle(style);
		}
	}

	public String getSourcePath() {
		return SRC_PATH;
	}

	public String getFlePath() {
		return FILE_PATH;
	}

	public File getFile() {
		return file;
	}

	public void setSourcePath(String SRC_PATH) {
		this.SRC_PATH = SRC_PATH;
	}

	public void setFilePath(String FILE_PATH) {
		this.FILE_PATH = FILE_PATH;
	}

	public void setFile(File f) {
		this.file = f;
	}

	public static void main(String[] args) throws IOException {
		Central c = new Central();
	}

}
