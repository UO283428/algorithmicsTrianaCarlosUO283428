package algstudent.s3;

public class Tromino {

	static int color = 1;
	static int[][] matrix;
	
	public static void main(String[] args) {
		int size = 8;
		matrix = new int[size][size];

		int emptyx = 3;
		int emptyy = 5;
		
		matrix[emptyy][emptyx] = -1;
		printMatrix();
		fillIn(0, 0, size, emptyx, emptyy);
		printMatrix();
		
	}
	
	private static void printMatrix() {
		int n = matrix.length;
		for (int i=0; i<n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + "\t");
				}
			System.out.println();
			}
		System.out.println("");
	}

	public static void fillIn(int startx, int starty, int n, int emptyx, int emptyy) {
		int quadrant = 1;
		int half = n/2;
		
		if ((emptyx-startx) >= half && emptyy >= half)
			quadrant = 4;
		if (emptyx >= half && emptyy < half)
			quadrant = 2;
		if (emptyx < half && emptyy >= half)
			quadrant = 3;
		
		if (n == 2) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (matrix[starty + i][startx + j] == 0) {
						matrix[starty + i][startx + j] = color;
					}
				}
				color++;
			}
		}else {
		
		switch(quadrant) {
		case 1:
			fillIn(startx, starty, half, emptyx, emptyy);
			
			matrix[half][half] = color;
			fillIn(half, half, half, half, half);
			
			matrix[half][half-1] = color;
			fillIn(startx, half, half, half-1, half);
			
			matrix[half-1][half] = color;
			fillIn(half, starty, half, half, half -1);
		
			color++;
			break;
			
		case 2:
			fillIn(half, starty, half, emptyx, emptyy);
			
			matrix[half-1][half-1] = color;
			fillIn(startx, starty, half, half-1, half-1);
			
			matrix[half][half-1] = color;
			fillIn(startx, half, half, half-1, half);
			
			matrix[half][half] = color;
			fillIn(half, half, half, half, half);
			
			color++;
			break;
			
		case 3:
			fillIn(startx, half, half, emptyx, emptyy);
			
			matrix[half-1][half-1] = color;
			fillIn(startx, starty, half, half-1, half-1);
			
			matrix[half-1][half] = color;
			fillIn(half, starty, half, half, half-1);
			
			matrix[half][half] = color;
			fillIn(half, half, half, half, half);
			
			color++;
			break;
			
		case 4:
			fillIn(half, half, half, emptyx, emptyy);
			
			matrix[half-1][half-1] = color;
			fillIn(startx, starty, half, half-1, half-1);
			
			matrix[half-1][half] = color;
			fillIn(half, starty, half, half, half-1);
			
			matrix[half][half-1] = color;
			fillIn(startx, half, half, half-1, half);
			
			color++;
			break;
		}
		}
	}
}
