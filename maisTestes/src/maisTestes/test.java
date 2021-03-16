package maisTestes;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
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
		int chavetas = 0;
		boolean livre = false;
		
		while (iterate.hasNext()) {
			if (iterate.nextLine().toLowerCase().contains(MethodName + "(")) {
				// System.out.println("encontrei o metodo");
				while (livre != true) {
					String s = iterate.nextLine();
					if (s.toLowerCase().contains("{")) {
						chavetas++;
						// System.out.println("tem chaveta");
					} else {
						if (s.toLowerCase().contains("}")) {
							chavetas--;
							// System.out.println("tira chaveta");
						}
					if (chavetas < 0) {
						// System.out.println(numLines);
						livre = true;
						iterate.close();
						return numLines;
					}
					}
					numLines++;
					

				}
			}
			iterate.next();
		}
		// System.out.println(numLines);
		return numLines;

	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Rui, deixa de ser do benfica!");
		System.out.println(Loc_class("Loc.java"));
		System.out.println(Loc_method("Loc.java", "loc"));
	   
	        Class className=null;
	       try{
	          className= Class.forName("Teste");
	          Method[] methods= className.getDeclaredMethods();
	           System.out.println("Number of methods in "+className+" = "+methods.length);
	           for(int i=0;i<methods.length;i++){
	               System.out.println(methods[i]);
	           }
	       }catch(ClassNotFoundException classNotFoundException){
	           System.out.println("Class "+className+" could not be found");
	       }

	    
	}

}