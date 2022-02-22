package algstudent.s1;

import java.util.Random;

import algstudent.s0.FileUtil;
import algstudent.s0.MatrixOperations;

public class MatrixOperationsTimes {

	int Matrix[][];
	int n = 0;
	MatrixOperationsTimes(int n, int min, int max){
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
	
	MatrixOperationsTimes(String fname){
		Matrix = FileUtil.loadMatrixFromFile(fname);
		this.n = Matrix[0].length;
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		
		long t1, t2, t3;
		MatrixOperationsTimes op = new MatrixOperationsTimes(n, 0, 10);
		
		for(int i = n; i < Integer.MAX_VALUE; i += 3) {
			op = new MatrixOperationsTimes(i, 0, 10);
			t1 = System.currentTimeMillis();
			int sum = op.sumDiagonal1();
			t2 = System.currentTimeMillis();
			t3 = t2 -t1;
			System.out.printf("Size=%d TIME=%d milliseconds SUM=%d\n",
					i, t3, sum);
		}
		
		System.out.println("Diagonal2");
		for(int i = n; i < Integer.MAX_VALUE; i += 3) {
			op = new MatrixOperationsTimes(i, 0, 10);
			t1 = System.currentTimeMillis();
			int sum = op.sumDiagonal2();
			t2 = System.currentTimeMillis();
			t3 = t2 -t1;
			System.out.printf("Size=%d TIME=%d milliseconds SUM=%d\n",
					i, t3, sum);
		}
		
	}
	
	public int getSize() {
		return n;
	}
	
	public void write() {
		for (int i = 0; i < Matrix[0].length; i++) {
			for (int j = 0; j < Matrix[0].length; j++) {
				System.out.print(Matrix[i][j] + "\t");
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
	public void travelPath(int i, int j) {
		int num = Matrix[i][j];
		System.out.println(i + ", " + j + "-->" + num);
		Matrix[i][j] = -1;
		switch(num) {
		case 1:
			if (j <= 0)
				break;
			travelPath(i-1, j);
			break;
		case 2:
			if (i >= n-1)
				break;
			travelPath(i, j+1);
			break;
		case 3:
			if (i >= n-1)
				break;
			travelPath(i+1, j);
			break;
		case 4:
			if (j <= 0)
				break;
			travelPath(i, j-1);
			break;
		case -1:
			break;
		default:
			break;
		}
	}
}