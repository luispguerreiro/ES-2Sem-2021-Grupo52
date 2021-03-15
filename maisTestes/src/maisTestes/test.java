package maisTestes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {
//rui
//teste

	public static int Loc_class(String FileName) throws FileNotFoundException {

		File file = new File(FileName);
		Scanner iterate = new Scanner(file);
		int numLines = 0;
		while (iterate.hasNext()) {
			iterate.nextLine();
			numLines++;
		}

		return numLines;

	}

	public static int Loc_method(String FileName, String MethodName) throws FileNotFoundException {
		File file = new File(FileName);
		Scanner iterate = new Scanner(file);
		int numLines = 1;
		int chavetas = 1;
		boolean livre = false;
		while (iterate.hasNext()) {

			if (iterate.nextLine().toLowerCase().contains("loc(")) {
				//System.out.println("encontrei o metodo");
				while (livre != true) {
					numLines++;
					

					if (iterate.nextLine().toLowerCase().contains("{")) {
						chavetas++;
						//System.out.println("tem chaveta");
					}
					if (iterate.nextLine().toLowerCase().contains("}")) {
						chavetas--;
						//System.out.println("tira chaveta");
					}
						if (chavetas == 0) {
							//System.out.println(numLines);
							iterate.close();
							return numLines;
						}
						
					
				}
			}
			iterate.next();
		}
		//System.out.println(numLines);
		return numLines;

	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Rui, deixa de ser do benfica!");
		System.out.println(Loc_class("Loc.java"));
		System.out.println(Loc_method("Loc.java", "loc(string fileName)"));
	}

}