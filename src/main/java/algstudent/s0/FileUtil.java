package algstudent.s0;

import java.io.*;
import java.util.*;

public abstract class FileUtil {
	
public static int[][] loadMatrixFromFile (String fileName) {
		
	    String line;
	    String[] row= null;
	    int n;
	    int[][] Matrix;
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader("src/main/"+fileName));
	    	   line = file.readLine();
	    	   n = Integer.parseInt(line);
	    	   System.out.println("The size of m is: " + n);
	    	   System.out.println();
	    	   Matrix = new int[n][n];
	    	   int theRow = 0;
	    	   while (file.ready()) {
	    		   line = file.readLine();
	    			row = line.split("\t");
	    			for (int i = 0; i < row.length; i++) {
	    				Matrix[theRow][i] = Integer.parseInt(row[i]);
	    			}
	    			theRow++;
	    		}
	    		file.close();
	    		return Matrix;
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("File not found.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    }
	    return null;
	  }
	
}
