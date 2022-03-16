package algstudent.s4;

import java.util.ArrayList;

public class Color {

	private String color;
	public ArrayList<String> countries;

	public Color(String color) {
		this.color = color;
		countries = new ArrayList<String>();
	}

	public void printColor() {
		System.out.println(color);
	}
	
	public void add(String country) {
		countries.add(country);
	}

}
