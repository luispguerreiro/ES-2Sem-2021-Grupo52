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

import Metrics.CYCLO_method;
import Metrics.Loc_Class;
import Metrics.Loc_Method;
import Metrics.Metrics;
import Metrics.NOM_Class;
import Metrics.Resultado;
import Metrics.WMC_Class;
import rules.GodClass;
import rules.GuiOutput.comparators;
import rules.GuiOutput.operators;
import rules.Threshold;

public class Central {

	private String SRC_PATH = "C:\\Users\\henri\\Downloads\\jasml_0.10";

	File file = new File("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\jasml_metrics.xlsx"); //mudar nome
	private Loc_Method locMethod;
	private CYCLO_method cycloMethod;
	private Loc_Class locClass;
	private NOM_Class nomClass;
	private WMC_Class wmcClass;
	int separador = 0;

	ArrayList<Resultado> all = new ArrayList<>();
	
	ArrayList<BoolResultado> boolResult = new ArrayList<>();

	private Metrics metric;
	

	public Central(GodClass g) throws IOException {

		File[] v = extracted();

		XSSFWorkbook workBook = new XSSFWorkbook();
		Sheet sheet = workBook.createSheet(file.getName().replaceFirst("[.][^.]+$", ""));
		for (int i = 0; i < v.length; i++) {

			metric = new Metrics(v[i].getAbsolutePath());

			locMethod = new Loc_Method(metric);
			cycloMethod = new CYCLO_method(metric);
			locClass = new Loc_Class(metric);
			nomClass = new NOM_Class(metric);
			wmcClass = new WMC_Class(metric);

			writeExcel(sheet, workBook);

			fuelAll();
		}
		putMethodID();
		if(!(file.canWrite())){
				System.out.println("Feche o ficheiro primeiro!\n");
				throw new IllegalStateException();
		}
				OutputStream fileOut = new FileOutputStream(file);
		workBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		System.out.println("\nExportação para Excel concluída!\n");
		sys();
		g.calculateThresholds(all, boolResult);
	}

	public void sys(){
		for(int i=0; i<all.size(); i++){
			for(int j=0;j<all.get(i).getAllInts().length; j++){
			System.out.println(all.get(i).getAllInts()[j]);
			System.out.println("size  ->" + all.get(i).getAllInts().length);
			}
			}
	}
	
	public void fuelAll() {
		all.addAll(cycloMethod.getResultados());
		int k = 0;
		for (int i = 0; i < cycloMethod.getResultados().size(); i++) {

			if (!(cycloMethod.getResultados().get(i).getClasses()
					.equals(cycloMethod.getResultados().get(k).getClasses()))
					&& k < nomClass.getResultados().size() - 1) {
				k++;
			}
			all.get(i).setAllIntsNomClass(nomClass.getResultados().get(k).getLinhas());
			all.get(i).setAllIntsLocClass(locClass.getResultados().get(k).getLinhas());
			all.get(i).setAllIntsWmcClass(wmcClass.getResultados().get(k).getLinhas());
			all.get(i).setAllIntsLocMethod(locMethod.getResultados().get(i).getLinhas());
			all.get(i).setAllIntsCycloMethod(cycloMethod.getResultados().get(i).getLinhas());
			
			boolResult.add(new BoolResultado(all.get(i).getPath(), false));

//			System.out.println("nomClass: "+all.get(i).getAllInts().get(0));
//			System.out.println("  locClass: "+all.get(i).getAllInts().get(1));
//			System.out.println("    wmcClass: "+all.get(i).getAllInts().get(2));
//			System.out.println("      locMethod: "+all.get(i).getAllInts().get(3));
//			System.out.println("        cycloMethod: "+all.get(i).getAllInts().get(4));
		}
//		System.out.println("nomClass: "+all.get(0).getAllInts().get(10));
	}

	public void putMethodID() {
		for (int i = 0; i < all.size(); i++)
			all.get(i).setMethodID(i + 1);
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

	public void verificacao(ArrayList<Resultado> res, Threshold t) {

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

	public ArrayList<Resultado> getAll() {
		return all;
	}

	public static void main(String[] args) throws IOException {
		String ruleName = "Regra2";
		int ruleType = 0;
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparators> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operators> oper = new ArrayList<>();
		GodClass r = new GodClass(ruleName, metricName, comp, limits, oper);
		
		Central c = new Central(r);
//		for (int i = 0; i < c.getAll().size(); i++) {
//			System.out.println("ID--> " + c.getAll().get(i).getMethodID());
//			System.out.println(c.getAll().get(i).getMetrica());
//
//		}
	}

}
