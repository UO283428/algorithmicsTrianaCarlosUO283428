package algstudent.s4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsColoring {

	public static String CountriesFile = "borders.txt";
	public static String ColorsFile = "colors.txt";
	public static String src = "src/main/java/algstudent/s4/";
	
	public static Map<String, List<String>> world;	//list of countries and its list of frontiers
	public static List<String> colors;					//List of available Colors
	public static Map<String, String> map;		//List of countries with its color
	
	public static void main(String[] args) {
		world = loadCountries(src + CountriesFile);
		colors = loadColors(src + ColorsFile);
		map = new HashMap<String, String>();
		
		color();
	}
	
	
	
	private static void color() {
		for (Map.Entry<String, List<String>> set : world.entrySet()) {
			for(String color : colors) {
				boolean valid = true;
				for (String frontier : set.getValue()) {
					String usedColor = get(frontier.trim(), map);
					if (usedColor != null && usedColor.equals(color)) {
						valid = false;
					}
				}
				if (valid) {
					map.put(set.getKey(), color);
					break;
				}
			}
		 }
		
		System.out.print(map.toString());
		boolean valid = true;
		for (Map.Entry<String, List<String>> set : world.entrySet()) {
			for (String frontier : set.getValue()) {
				String usedColor = get(frontier.trim(), map);
				String colored = get(set.getKey(), map);
				if (usedColor != null && usedColor.equals(colored)) {
						valid = false;
					}
				}
		 }
		System.out.println(valid);
	}

	private static String get(String frontier, Map<String, String> map) {
		String ret = null;
		for (Map.Entry<String, String> set : map.entrySet()) {
			if (set.getKey().equals(frontier)) {
				return set.getValue();
			}
		}
		return ret;
	}



	private static List<String> loadColors(String fileName) {
		List<String> colors = new ArrayList<String>();
		
	    String line;
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    	   
	    	   while (file.ready()) {
	    		   line = file.readLine();

	    		   colors.add(line);
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

	private static HashMap<String, List<String>> loadCountries(String fileName) {
		HashMap<String, List<String>> countries = new HashMap<String, List<String>>();
		
	    String line;
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    	   
	    	   while (file.ready()) {
	    		   line = file.readLine();
	    		   
	    		   String[] country = line.split(":");
	    		   String countryName = country[0];
	    		   
	    		   String[] borders = country[1].split(",");
	    		   
	    		   countries.put(countryName, Arrays.asList(borders));
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
