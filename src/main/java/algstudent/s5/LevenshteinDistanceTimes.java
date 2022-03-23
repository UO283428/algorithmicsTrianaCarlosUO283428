package algstudent.s5;

import java.util.Random;

import algstudent.s3.Tromino;

public class LevenshteinDistanceTimes {

	public static char A = 'A';
	
	public static void main(String[] args) {
		int reps = Integer.parseInt(args[0]);

		Random rd = new Random();

		for (int i = 100; i < Integer.MAX_VALUE; i *= 2) {
			String str1 = "";
			String str2 = "";
			for (int j  = 0; j < i ; j++) {
				str1 += (A + rd.nextInt(27));
				str2 += (A + rd.nextInt(27));
			}

			String[] params = {str1, str2};

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

}
