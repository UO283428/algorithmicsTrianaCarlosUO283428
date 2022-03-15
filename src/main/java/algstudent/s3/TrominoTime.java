package algstudent.s3;

import java.util.Random;

public class TrominoTime {

	public static void main(String[] args) {
		int reps = Integer.parseInt(args[0]);
		
		Random rd = new Random();
		
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
		}
	}
	
}