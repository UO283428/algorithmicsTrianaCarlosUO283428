package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}
	
	@Override
	public void sort() {
		for (int i = 0; i < elements.length; i++) {
			int min = elements[i];
			int minind = i;
			for (int j = i; j < elements.length; j++) {
				if (elements[j] < min) {
					min = elements[j];
					minind = j;
				}
				this.interchange(minind, i);
			}
			
		}
	}  
	
	@Override
	public String getName() {
		return "Selection";
	} 
} 
