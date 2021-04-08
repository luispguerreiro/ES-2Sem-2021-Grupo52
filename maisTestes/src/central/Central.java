package central;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import luis.Metrics;

public class Central {

	private String SRC_PATH = "C:\\Users\\luisg\\Desktop\\Faculdade\\3º Ano\\ES\\jasml_0.10";

	File file = new File("C:\\Users\\luisg\\Desktop\\jasml_metrics.xlsx"); // vai ser o nome
	private List<Metrics> metrics = new ArrayList<>();
	int separador = 0;

	public Central() throws IOException {
		File[] v = extracted();
		XSSFWorkbook workBook = new XSSFWorkbook();
		Sheet sheet = workBook.createSheet("aaa");
		for (int i = 0; i < v.length; i++) {
			new Metrics(v[i].getAbsolutePath()).getClassMetrics().forEach(m -> metrics.add(m));
		}
		writeExcel(sheet, workBook);	
		OutputStream fileOut = new FileOutputStream(file);
		workBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public File[] extracted() throws IOException {
		File dir = new File(SRC_PATH);
		ArrayList<File> lista = new ArrayList<File>();
		File[] v = new File[0];
		if (dir.isDirectory()) {
			Path path = Paths.get(dir.getAbsolutePath());
			List<Path> paths = listFiles(path);
			List<File> files = pathsToFiles(paths);
			for (int i = 0; i < paths.size(); i++) {
				if (files.get(i).isFile() && files.get(i).getPath().endsWith(".java")) {
					lista.add(files.get(i));
				}
			}
			v = new File[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				v[i] = lista.get(i);
			}
		}
		return v;
	}

	public void writeExcel(Sheet sheet, XSSFWorkbook workBook) throws IOException {
		sheet.setDefaultColumnWidth(20);
		cabecalho(sheet, workBook);
		for (Metrics metric : metrics) {
			for (int i = 0; i < metric.getNumOfMethods(); i++) {
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
				pack.setCellValue(metric.getClassPackage());
				classes.setCellValue(metric.getClassName());
				methods.setCellValue(metric.getMethodsName().get(i));
				cell4.setCellValue(metric.getNumOfMethods());
				cell5.setCellValue(metric.getLOC_Class());
				cell6.setCellValue(metric.getCYCLO_Class());
				cell7.setCellValue(metric.getLOC_Method_Results().get(i));
				cell8.setCellValue(metric.getCYCLO_Method_Results().get(i));
				Cell methodID = row.createCell(0);
				methodID.setCellValue(separador);
//				rowCount++;
			}
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

	public List<Path> listFiles(Path path) throws IOException {
		List<Path> result;
		try (Stream<Path> walk = Files.walk(path)) {
			result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
		}
		return result;
	}

	public List<File> pathsToFiles(List<Path> path) {
		List<File> files = new ArrayList<File>();
		for (int i = 0; i < path.size(); i++) {
			files.add(path.get(i).toFile());
		}
		return files;
	}

	public String getSourcePath() {
		return SRC_PATH;
	}

	public File getFile() {
		return file;
	}

	public void setSourcePath(String SRC_PATH) {
		this.SRC_PATH = SRC_PATH;
	}

	public void setFile(File f) {
		this.file = f;
	}

	public static void main(String[] args) throws IOException {
		Central c = new Central();
	}
}