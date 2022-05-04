package algstudent.test;

public class Tromino {

	static int color = 1;
	static int[][] matrix;

	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		//int size = 16;
		matrix = new int[size][size];

		int emptyx = Integer.parseInt(args[1]);
		int emptyy = Integer.parseInt(args[2]);
		//int emptyx = 13;
		//int emptyy = 9;

		matrix[emptyy][emptyx] = -1;
		//printMatrix();
		fillIn(0, 0, size, emptyx, emptyy);
		//printMatrix();

	}

	@SuppressWarnings("unused")
	private static void printMatrix() {
		int n = matrix.length;
		for (int i=0; i<n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + "\t");
				}
			System.out.println();
			}
		System.out.println();
	}

	public static void fillIn(int startx, int starty, int n, int emptyx, int emptyy) {
		//printMatrix();
		int quadrant = 1;
		int half = n/2;
		
		if ((emptyx-startx) >= half && (emptyy-startx) >= half)
			quadrant = 4;
		if ((emptyx-startx) >= half && (emptyy-starty) < half)
			quadrant = 2;
		if ((emptyx-startx) < half && (emptyy-starty) >= half)
			quadrant = 3;
		
		if (n == 2) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (matrix[starty + i][startx + j] == 0) {
						matrix[starty + i][startx + j] = color;
					}
				}
			}
			color++;
		}else {
		
		switch(quadrant) {
		case 1:

			matrix[starty + (half-1)][startx + (half)] = color; // q2
			matrix[starty + (half)][startx + (half-1)] = color;  // q3
			matrix[starty + (half)][startx + (half)] = color;	// q4
			color++;
			
			fillIn(startx, starty, half, emptyx, emptyy); // q
			fillIn(startx + half, starty, half, startx + (half), starty + (half-1)); // q2
			fillIn(startx, starty + half, half, startx + (half-1), starty + (half)); // q3
			fillIn(startx + half, starty + half, half, startx + (half), starty + (half)); // q4
			
			break;
			
		case 2:

			matrix[starty + (half-1)][startx + (half-1)] = color; // q1
			matrix[starty + (half)][startx + (half-1)] = color;  // q3
			matrix[starty + (half)][startx + (half)] = color;	// q4
			color++;

			fillIn(startx, starty, half, startx + (half-1), starty + (half-1)); // q1
			fillIn(startx + half, starty, half, emptyx, emptyy); // q
			fillIn(startx, starty + half, half, startx + (half-1), starty + (half)); // q3
			fillIn(startx + half, starty + half, half, startx + (half), starty + (half)); // q4

			break;
			
		case 3:
			
			matrix[starty + (half-1)][startx + (half-1)] = color; // q1
			matrix[starty + (half-1)][startx + (half)] = color; // q2
			matrix[starty + (half)][startx + (half)] = color;	// q4
			color++;
			
			fillIn(startx, starty, half, startx + (half-1), starty + (half-1)); // q1
			fillIn(startx + half, starty, half, startx + (half), starty + (half-1)); // q2
			fillIn(startx, starty + half, half, emptyx, emptyy); // q
			fillIn(startx + half, starty + half, half, startx + (half), starty + (half)); // q4

			break;
			
		case 4:
			matrix[starty + (half-1)][startx + (half-1)] = color; // q1
			matrix[starty + (half-1)][startx + (half)] = color; // q2
			matrix[starty + (half)][startx + (half-1)] = color;  // q3
			color++;
			
			fillIn(startx, starty, half, startx + (half-1), starty + (half-1)); // q1
			fillIn(startx + half, starty, half, startx + (half), starty + (half-1)); // q2
			fillIn(startx, starty + half, half, startx + (half-1), starty + (half)); // q3
			fillIn(startx + half, starty + half, half, emptyx, emptyy); // q

			break;
		}
		}
	}
}