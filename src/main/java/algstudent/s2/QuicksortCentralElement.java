package algstudent.s2;


/* This program can be used to sort n elements with 
 * the best algorithm of this lab. It is the QUICKSORT */
public class QuicksortCentralElement extends Vector {
	
	public QuicksortCentralElement(int nElements) {
		super(nElements);
	}
	
	private void quickSort(int left, int right) {
		int pivotindx = (left + right)/2;
		int pivot = elements[pivotindx];
		
		int i = left;
		int j = right;
		while(i < j) {
			while( elements[i] < pivot) {
				i++;
			}
			
			while(elements[j] > pivot) {
				j--;
			}
			
			interchange(i, j);
		}
	}

	@Override
	public void sort() {
		quickSort(0, elements.length-1);		
	}
	
	@Override
	public String getName() {
		return "Quicksort - Central element";
	} 
} 
