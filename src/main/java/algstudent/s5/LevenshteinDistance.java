package algstudent.s5;

public class LevenshteinDistance {

	public static String str1 = "BARCAZAS";
	public static String str2 = "ABRACADABRA";
	
	public static int[][] lT;
	
	public static void main(String[] args) {
		calculateDistance(args[0], args[1]);
	}
	
	public static int calculateDistance(String str1, String str2) {

		lT = calculateLevenshteinTable(str1, str2);
		return lT[lT.length-1][lT[0].length-1];
	}

	public static int[][] calculateLevenshteinTable(String str12, String str22) {
		lT = new int[str2.length()+1][str1.length()+1];
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		
		for(int i = 0; i < lT[0].length; i++) {
			for (int j = 0; j < lT.length; j++) {
				if(i == 0 || j == 0) {
					if (i == 0 && j == 0) {
						lT[j][i] = 0;
					}else {
						if (j == 0) {
							lT[j][i] = lT[j][i-1] +1;
						}else {
							lT[j][i] = lT[j-1][i] +1;
						}
					}
				}else {
					if (s1[i-1] == s2[j-1]) {
						lT[j][i] = lT[j-1][i-1];
					}else {
						lT[j][i] = 1 + min(lT[j-1][i-1], lT[j][i-1], lT[j-1][i]);
					}
					
				}				
			}
		}
		printMatrix(lT);
		return (lT);
	}

	private static int min(int i, int j, int k) {
		int min = i;
		if (j < min) {
			min = j;
		}
		if (k < min)
			return k;
		return min;
	}

	private static void printMatrix(int[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			System.out.println();
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j]);
			}
		}
		System.out.println();
	}

}
