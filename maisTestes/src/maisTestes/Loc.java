package maisTestes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loc {
	String FileName;

	public Loc(String FileName) throws FileNotFoundException {
		this.FileName = FileName;
		File file = new File(FileName);
		Scanner iterate = new Scanner(file);
		int numLines = 0;
		while (iterate.hasNext()) {
			iterate.nextLine();
			numLines++;
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("Loc.java");
		Scanner iterate = new Scanner(file);
		int numLines = 0;
		while (iterate.hasNext()) {
			iterate.nextLine();
			numLines++;
		}
		System.out.println(numLines);
	}
}
