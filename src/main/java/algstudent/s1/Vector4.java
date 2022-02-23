package algstudent.s1;

public class Vector4 {
	static int[] v;
	static int[] w;

	public static void main(String[] args) {
		int repetitions = Integer.parseInt(args[0]);
		long t1, t2, t3;
		
		for (int n = repetitions; n < Integer.MAX_VALUE; n *= 3) {
			v = new int[n];
			Vector1.fillIn(v);
			w = new int[n];
			Vector1.fillIn(w);
			
			t1 = System.currentTimeMillis();
			var sum = 0;
			for (int repetition = 1; repetition < n; repetition++) {
				Vector1.maximum(v, w);
			}
			t2 = System.currentTimeMillis();
			
			t3 = t2 -t1;
			//t3 /= repetitions;
			System.out.printf("Size=%d TIME=%d milliseconds SUM=%d\n",
				n, t3, sum);
		}	
	}
}