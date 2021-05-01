package maisTestes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Grupo 52
 */

public class Excel {

	ArrayList<Linha> list;
	
	/**
	 * Reads an Excel file and transforms it into an Array List
	 * 
	 * 
	 * @param file	the Excel file to be converted into an Array List
	 * 			
	 */
	
	public void lerExcel(File file) throws FileNotFoundException, IOException {

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		Iterator<Row> itr = sheet.iterator();
		itr.next();

		list = new ArrayList<Linha>();
		Linha linha;

		while (itr.hasNext()) {
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			linha = new Linha();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getColumnIndex()) {
				case 1:
					linha.setPacote(cell.getStringCellValue());
					break;
				case 2:
					linha.setClasse(cell.getStringCellValue());
					break;
				case 3:
					linha.setMetodo(cell.getStringCellValue());
					break;
				case 7:
					linha.setIs_God_Class(cell.getBooleanCellValue());
					break;
				case 10:
					if (cell.getCellType() == 4) {
						linha.setIs_Long_Method(cell.getBooleanCellValue());
//						System.out.print(cell.getBooleanCellValue() + " ");
					} else {
						linha.setIs_Long_Method(false);
					}
					break;
				default:
				}
			}
			list.add(linha);
			System.out.println(linha);
		}

	}
	
	/**
	 * getter for the Array List of linhas
	 * 
	 * @return	the list itself			
	 */

	public ArrayList<Linha> getList() {
		return list;
	}
}