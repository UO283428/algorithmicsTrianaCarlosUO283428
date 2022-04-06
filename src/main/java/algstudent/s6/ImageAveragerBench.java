package algstudent.s6;

import java.nio.file.Paths;

public class ImageAveragerBench {
	
	// Benchmarking settings
	private static String REAL_IMG = "src/main/java/algstudent/s6/einstein_1_256.png";
	private static String BAD_IMG = "src/main/java/algstudent/s6/einstein_1_256.png";
	private static String OUT_DIR_G = "src/main/java/algstudent/s6/out_g/";
	private static String OUT_DIR_B = "src/main/java/algstudent/s6/out_bt";
	private static int N_IMGS = 13; 
	private static double PERCENTAGE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Noise level - Gaussian sigma
		
	public static void main(String[] args) {
		
		int n_real, n_bad;
		ImageAverager img_avger;
		
		// Generating and testing a single dataset instance
		n_bad = (int) ((PERCENTAGE_BAD/100.)*N_IMGS);
		n_real = N_IMGS - n_bad;
		System.out.println(REAL_IMG);
		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
				
		System.out.print("TESTING GREEDY:\n");
		//img_avger.splitSubsetsGreedy(10000);
		//System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		//System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
		//img_avger.saveResults(OUT_DIR_G);
			
		System.out.print("TESTING BACKTRACKING:\n");
		img_avger.Backtracking();
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);

		// Measurements
		// TODO
	}

}