package algstudent.s1;

public class Vector3 {
	static int[] v;

	public static void main(String[] args) {
		
		long t1, t2, t3;
		
		for (int n = 5; n < Integer.MAX_VALUE; n *= 5) {
			v = new int[n];
			Vector1.fillIn(v);
			
			t1 = System.currentTimeMillis();
			int sum = Vector1.sum(v);
			t2 = System.currentTimeMillis();
			
			t3 = t2 -t1;
			System.out.printf("Size=%d TIME=%d milliseconds SUM=%d\n",
				n, t3, sum);
		}
		
		
		
		
		
	}

}