package maisTestes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {
//ffdsa

//rui
//teste
	
	public static int Loc (String FileName) throws FileNotFoundException {
		
		File file = new File(FileName);
		Scanner iterate = new Scanner (file);
		int numLines = 0;
		while (iterate.hasNext()) {
			iterate.nextLine();
			numLines ++;
		}
		return numLines; 
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Rui, deixa de ser do benfica!");
		System.out.println(Loc("Loc.java"));

	}

}