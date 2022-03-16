package algstudent.s4;

public class Country {

	private String name;
	public String[] borders;

	public Country(String countryName, String[] borders) {
		name = countryName;
		this.borders = borders;
	}

	public void PrintCountry() {
		System.out.print("\n" + name + ": ");
		for (int i = 0; i < borders.length; i++) {
			System.out.print(borders[i]);
		}
		System.out.println();
	}
}