package algstudent.s1;

public class Vector4 {
	static int[] v;

	public static void main(String[] args) {
		int repetitions = Integer.parseInt(args[0]);
		long t1, t2, t3;
		
		for (int n = 10; n < Integer.MAX_VALUE; n *= 5) {
			v = new int[n];
			Vector1.fillIn(v);
			
			t1 = System.currentTimeMillis();
			var sum = 0;
			for (int repetition = 1; repetition < repetitions; repetition++) {
				Vector1.fillIn(v);
			}
			t2 = System.currentTimeMillis();
			
			t3 = t2 -t1;
			//t3 /= repetitions;
			System.out.printf("Size=%d TIME=%d microseconds SUM=%d NUMBERREPETITIONS=%d\n",
				n, t3, sum, repetitions);
		}
		
		
		
		
		
	}

}