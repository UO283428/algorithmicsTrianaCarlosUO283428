package algstudent.s3;

public class Subtraction2 {
	public static long rec2(int n)
	{
	 long cont = 0;
	 if (n<=0) cont++;
	 else 
	   { for (int i=0;i<n;i++) cont++; // O(n)
	     rec2(n-1);
	   }
	 return cont;   
	}
	
	public static void main (String arg []) 
	{
	 long t1,t2,cont = 0;
	 for (int n=1;n<=100000;n*=2)
	 {
	  t1 = System.currentTimeMillis ();
	   for (int j = 0; j < 100000000; j++) {
		   cont = rec2(n);
	   }
	  t2 = System.currentTimeMillis ();
	
	  System.out.println ("n="+n+ "**TIME="+(t2-t1)+"**cont="+cont);
	 }  // for
	} // main
} //class