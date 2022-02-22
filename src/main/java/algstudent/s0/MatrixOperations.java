package algstudent.s0;

import java.util.Random;

public class MatrixOperations {

	int Matrix[][];
	int n = 0;
	MatrixOperations(int n, int min, int max){
		this.n = n;
		if ( n < 0)
			throw new IllegalArgumentException("No negative values allowed for the size.");
		if (min >= max)
			throw new IllegalArgumentException("The minimun value cannot be grater than the maximun. There must be a difference in between the range");
			
		Matrix = new int[n][n];
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Matrix[i][j] = rnd.nextInt(max - min +1) + min;
			}
		}
	}
	
	MatrixOperations(String fname){
		Matrix = FileUtil.loadMatrixFromFile(fname);
		this.n = Matrix[0].length;
	}
	
	public int getSize() {
		return n;
	}
	
	public void write() {
		for (int i = 0; i < Matrix[0].length; i++) {
			for (int j = 0; j < Matrix[0].length; j++) {
				System.out.print(Matrix[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public int sumDiagonal1() {
		int sum = 0;
		for (int i = 0; i < Matrix[0].length; i++) {
			for (int j = 0; j < Matrix[0].length; j++) {
				if (i == j)
					sum += Matrix[i][j];
			}
		}
		return sum;
	}

	public int sumDiagonal2() {
		int sum = 0;
		for (int i = 0; i < Matrix[0].length; i++) {
			sum = Matrix[i][i];
		}
		return sum;
	}

}