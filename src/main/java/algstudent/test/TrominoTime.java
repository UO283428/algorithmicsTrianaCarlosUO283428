package algstudent.test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class TrominoTime {

	public static void main(String[] args) {
		int reps = Integer.parseInt(args[0]);
		ForkJoinPool pool = new ForkJoinPool();
		
		Random rd = new Random();
		
		//for (int i = 8; i < 16; i *= 2) {
		for (int i = 4; i < Integer.MAX_VALUE; i *= 2) {
					int emptyx = rd.nextInt(i);
			int emptyy = rd.nextInt(i);

			String[] params = {String.valueOf(i), String.valueOf(emptyx), String.valueOf(emptyy)};
			
			long t,t1,t2;
			System.out.println("\n\nTime Measurement: " + i);
			t = 0;
			for (int repetition = 1; repetition <= reps; repetition++) {
					t1 = System.currentTimeMillis();
					Tromino.main(params);
					t2 = System.currentTimeMillis();
					t += t2-t1;
				}
			System.out.println("n=" + i + "**TIME=" + t);
			
			System.out.println("\n\nTime Measurement: " + i);
			t = 0;
			
	
			int startx = 0;
			int starty = 0;
			int color = 1;
			
			for (int repetition = 1; repetition <= reps; repetition++) {
				int[][] matrix = new int[i][i];		
				matrix[emptyy][emptyx] = -1;
				TrominoForkJoin task = new TrominoForkJoin(matrix, startx, starty, i, emptyx, emptyy, color, true);
				//task.printMatrix();
				t1 = System.currentTimeMillis();
				TrominoForkJoin.main(params);
				pool.invoke(task);
				t2 = System.currentTimeMillis();
				t += t2-t1;
				//task.printMatrix();
			}
			System.out.println("n=" + i + "**TIME=" + t);
		}
	}
}