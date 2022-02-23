package algstudent.s2;


/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion extends Vector {	
	public Insertion(int nElements) {
		super(nElements);
	}

	@Override
	public void sort(){
		int lower;
		
		for (int i = 1; i < elements.length; i++) {
			lower = elements[i];
			int j = i-1;			for (; j >= 0; j--) {
				if (lower < elements[j]) {
					elements[j+1] = elements[j];
				}
			}
			elements[j+1] = lower;
		}

	} 
	
	@Override
	public String getName() {
		return "Insertion";
	} 
} 
