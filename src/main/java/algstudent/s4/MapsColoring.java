package algstudent.s4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class MapsColoring {

	public static String CountriesFile = "borders.txt";
	public static String ColorsFile = "colors.txt";
	public static String src = "src/main/java/algstudent/s4/";
	
	public static List<Country> world;
	public static List<Color> colors;
	public static Dictionary<Country, Color> map;
	
	public static void main(String[] args) {
		world = loadCountries(src + CountriesFile);
		colors = loadColors(src + ColorsFile);
		
		color();
	}

	private static void color() {
		for (Country country : world) {
			for (Color color : colors) {
				boolean isValid = true;
				for (String usedIn : country.borders) {
					if (color.countries.contains(usedIn)) {
						isValid = false;
						break;
					}
					if (isValid) {
						
					}
				}
			}
		}
	}

	private static List<Color> loadColors(String fileName) {
		HashMap<Color, ArrayList<String>> colors = new HashMap<Color, String[]>();
		
	    String line;
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    	   
	    	   while (file.ready()) {
	    		   line = file.readLine();
	    		   
	    		   Color newColor = new Color(line);
	    		   newColor.printColor();
	    		   colors.add(newColor, );
	    	   }
	    	   file.close();
	    	return colors;
	    }catch (FileNotFoundException fnfe) {
	      System.out.println("File not found.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    }
	    return null;
	}

	private static List<Country> loadCountries(String fileName) {
		List<Country> countries = new ArrayList<Country>();
		
	    String line;
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    	   
	    	   while (file.ready()) {
	    		   line = file.readLine();
	    		   
	    		   String[] country = line.split(":");
	    		   String countryName = country[0];
	    		   
	    		   String[] borders = country[1].split(",");
	    		   
	    		   Country newCountry = new Country(countryName, borders);
	    		   newCountry.PrintCountry();
	    		   countries.add(newCountry);
	    	   }
	    	   file.close();
	    		return countries;
	    }catch (FileNotFoundException fnfe) {
	      System.out.println("File not found.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    }
	    return null;
	  
	}

}
