package central;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SecureCacheResponse;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Metrics.CYCLO_method;
import Metrics.Loc_Class;
import Metrics.Loc_Method;
import Metrics.Metrics;
import Metrics.NOM_Class;
import Metrics.WMC_Class;

public class Central {

	private String SRC_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\miniJasml";
	private static final String FILE_PATH = "C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\SourceCodeParser.java";
	File file = new File("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx"); // vai ser o nome
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

		
		System.out.println(locMethod.getResultados().size() + " " + cycloMethod.getResultados().size());
	}
	

	public void writeExcel(Sheet sheet, XSSFWorkbook workBook) throws IOException {
		sheet.setDefaultColumnWidth(20);
		cabecalho(sheet);
		int rowCount = 0;
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
//			cell4.setCellValue(nomClass.getResultados().get(i).getLinhas());
//			cell5.setCellValue(locClass.getResultados().get(i).getLinhas());
//			cell6.setCellValue(wmcClass.getResultados().get(i).getLinhas());
			cell7.setCellValue(locMethod.getResultados().get(i).getLinhas());
			cell8.setCellValue(cycloMethod.getResultados().get(i).getLinhas());
			
			
//			Row r = sheet.createRow(++rowCount);
			
			Cell methodID = row.createCell(0);
			methodID.setCellValue(separador);

		}

	}

	public void cabecalho(Sheet sheet) {
		String[] c = { "MethodID", "Package", "Class", "Method", "NOM_class", "LOC_class", "WMC_class", "LOC_method",
				"CYCLO_method" };
		Row cabecalho = sheet.createRow(0);
		int cellCount = 0;
		for (String s : c) {
			Cell cell = cabecalho.createCell(cellCount++);
			cell.setCellValue(s);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Central c = new Central();
	}

}
