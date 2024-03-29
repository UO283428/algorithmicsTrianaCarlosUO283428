package algstudent.s3;

public class Subtraction1{
	public static long rec1(int n){
	 long cont = 0;
	 if (n<=0) cont++;
	 else 
	   { cont++;  // O(1)=O(n^0)
	     rec1(n-1);
	   }
	 return cont;   
	}
	
	public static void main(String arg []) {
	 long t1,t2,cont = 0;
	 for (int n=1;n<=100000;n*=2)
	 {
	  t1 = System.currentTimeMillis();
	   for (int j = 0; j < 100000000; j++) {
		   cont = rec1(n);
	   }
	  t2 = System.currentTimeMillis();
	
	  System.out.println("n="+n+ "**TIME="+(t2-t1)+"**cont="+cont);
	 }  // for
	} // main
} //class