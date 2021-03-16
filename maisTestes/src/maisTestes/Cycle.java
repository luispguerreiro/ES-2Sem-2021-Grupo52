/**
 * 
 */
package maisTestes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author henri
 *
 */
public class Cycle {

	private String fileName;
	private Scanner s;

	public int numCycles(String fileName) throws IOException {
		this.fileName = fileName;
		File file = new File(fileName);
		s = new Scanner(file);
		int num = 0;
		while (s.hasNext()) {
			String line = "";
			line = s.nextLine();
			String l = line.replaceAll("\\s+","");
			if ((l.startsWith("for(") || l.startsWith("if(") || l.startsWith("while(") || l.startsWith("case") || l.startsWith("}elseif") )
					|| l.contains("||") || l.startsWith("exception")) {
				num++;
				System.out.println(l);
			}
		}
		return num;
	}

	public static void main(String[] args) throws IOException {
		Cycle c = new Cycle();
//		System.out.println(c.numCycles("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\SourceCodeParser.java"));
		File f = new File("C:\\Users\\henri\\OneDrive\\Ambiente de Trabalho\\SourceCodeParser.java");
		System.out.println(f.getName());
	}

}
