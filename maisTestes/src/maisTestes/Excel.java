package maisTestes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws FileNotFoundException {
		try {
			File file = new File("Code_Smells.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			ArrayList<Linha> list = new ArrayList<Linha>();
			Linha linha;
			itr.next();
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				linha = new Linha();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
					case 1:
						linha.setPacote(cell.getStringCellValue());
//						System.out.print(cell.getStringCellValue() + " ");
						break;
					case 2:
						linha.setClasse(cell.getStringCellValue());
//						System.out.print(cell.getStringCellValue() + " ");
						break;
					case 3:
						linha.setMetodo(cell.getStringCellValue());
//						System.out.print(cell.getStringCellValue() + " ");
						break;
					case 7:
						linha.setIs_God_Class(cell.getBooleanCellValue());
//						System.out.print(cell.getBooleanCellValue() + " ");
						break;
					case 10:
						if (cell.getCellType() == 4) {
							linha.setIs_Long_Method(cell.getBooleanCellValue());
//						System.out.print(cell.getBooleanCellValue() + " ");
						} else {
							linha.setIs_Long_Method(false);
//							System.out.print(cell.getStringCellValue() + " ");
						}
						break;
					default:
					}
				}
				list.add(linha);
			}
//			for (Linha l : list)
//				System.out.println(l.toString());
			Procura p = new Procura();
			p.getProcura(list, "GrammerException");
			
			for(Linha l : p.getLista())
				System.out.println(l.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}