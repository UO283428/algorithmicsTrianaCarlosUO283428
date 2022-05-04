package algstudent.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class TrominoForkJoin extends RecursiveAction{
	private static final long serialVersionUID = 1L;
	
	
	int color = 1;
	int[][] matrix;

	private int startx;
	private int starty;
	private int n;
	private int emptyx;
	private int emptyy;

	public TrominoForkJoin(int[][] matrix, int startx, int starty, int n, int emptyx, int emptyy, int color, boolean print) {
		this.matrix = matrix;
		this.startx = startx;
		this.starty = starty;
		this.n = n;
		this.emptyx = emptyx;
		this.emptyy = emptyy;
		this.color = color;
		//if (print)
			//printMatrix();
	}
	
	
	public static void main(String[] args) {		
		int n = Integer.parseInt(args[0]);
		//int size = 16;
		int[][] matrix = new int[n][n];

		int emptyx = Integer.parseInt(args[1]);
		int emptyy = Integer.parseInt(args[2]);
		//emptyx = 13;
		//emptyy = 9;
		int startx = 0;
		int starty = 0;

		matrix[emptyy][emptyx] = -1;
		//printMatrix();
		int color = 1;

		//printMatrix();
	}

	@SuppressWarnings("unused")
	public void printMatrix() {
		int n = matrix.length;
		for (int i=0; i<n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + "\t");
				}
			System.out.println();
			}
		System.out.println();
	}

	@Override
	public void compute() {
		//printMatrix();
		boolean sequential = false;
//		if(n <= 16) {
//			sequential = true;
//		}
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
			if (sequential) {
				
			}else {
				
			}
			invokeAll(new TrominoForkJoin(matrix, startx, starty, half, emptyx, emptyy, color, false), // q
					new TrominoForkJoin(matrix, startx + half, starty, half, startx + (half), starty + (half-1), color*2, false), // q2
					new TrominoForkJoin(matrix, startx, starty + half, half, startx + (half-1), starty + (half), color*3, false), // q3
					new TrominoForkJoin(matrix, startx + half, starty + half, half, startx + (half), starty + (half), color*4, false) // q4
					);			
			break;
			
		case 2:

			matrix[starty + (half-1)][startx + (half-1)] = color; // q1
			matrix[starty + (half)][startx + (half-1)] = color;  // q3
			matrix[starty + (half)][startx + (half)] = color;	// q4
			color++;

			invokeAll(new TrominoForkJoin(matrix, startx, starty, half, startx + (half-1), starty + (half-1), color, false), 
					new TrominoForkJoin(matrix, startx + half, starty, half, emptyx, emptyy, color*2, false),
					new TrominoForkJoin(matrix, startx, starty + half, half, startx + (half-1), starty + (half), color*3, false),
					new TrominoForkJoin(matrix, startx + half, starty + half, half, startx + (half), starty + (half), color*4, false)
					);
//			compute(startx, starty, half, startx + (half-1), starty + (half-1)); // q1
//			compute(startx + half, starty, half, emptyx, emptyy); // q
//			compute(startx, starty + half, half, startx + (half-1), starty + (half)); // q3
//			compute(startx + half, starty + half, half, startx + (half), starty + (half)); // q4
			break;
			
		case 3:
			
			matrix[starty + (half-1)][startx + (half-1)] = color; // q1
			matrix[starty + (half-1)][startx + (half)] = color; // q2
			matrix[starty + (half)][startx + (half)] = color;	// q4
			color++;
			
			invokeAll(new TrominoForkJoin(matrix, startx, starty, half, startx + (half-1), starty + (half-1), color, false),
					new TrominoForkJoin(matrix, startx + half, starty, half, startx + (half), starty + (half-1), color*2, false),
					new TrominoForkJoin(matrix, startx, starty + half, half, emptyx, emptyy, color*3, false),
					new TrominoForkJoin(matrix, startx + half, starty + half, half, startx + (half), starty + (half), color*4, false)
					);
//			compute(startx, starty, half, startx + (half-1), starty + (half-1)); // q1
//			compute(startx + half, starty, half, startx + (half), starty + (half-1)); // q2
//			compute(startx, starty + half, half, emptyx, emptyy); // q
//			compute(startx + half, starty + half, half, startx + (half), starty + (half)); // q4
			break;
			
		case 4:
			matrix[starty + (half-1)][startx + (half-1)] = color; // q1
			matrix[starty + (half-1)][startx + (half)] = color; // q2
			matrix[starty + (half)][startx + (half-1)] = color;  // q3
			color++;
			
			invokeAll(new TrominoForkJoin(matrix, startx, starty, half, startx + (half-1), starty + (half-1), color, false),
					new TrominoForkJoin(matrix, startx + half, starty, half, startx + (half), starty + (half-1), color*2, false), // q2
					new TrominoForkJoin(matrix, startx, starty + half, half, startx + (half-1), starty + (half), color*3, false), // q3
					new TrominoForkJoin(matrix, startx + half, starty + half, half, emptyx, emptyy, color*4, false) // q
					);
//			compute(startx, starty, half, startx + (half-1), starty + (half-1)); // q1
//			compute(startx + half, starty, half, startx + (half), starty + (half-1)); // q2
//			compute(startx, starty + half, half, startx + (half-1), starty + (half)); // q3
//			compute(startx + half, starty + half, half, emptyx, emptyy); // q
			break;
		}
		}
	}

}
