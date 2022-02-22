package algstudent.s0;

public class Main {

	public static void main(String[] args) {
		
		MatrixOperations op1 = new MatrixOperations("matrix01.txt");
		MatrixOperations op2 = new MatrixOperations("matrix02.txt");
		
		op1.write();
		op2.write();
		
		op2.travelPath(3, 0);
		
		op2.write();
		
	}

}
