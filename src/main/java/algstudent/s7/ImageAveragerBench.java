package algstudent.s7;


public class ImageAveragerBench {
	// Benchmarking settings
	private static String REAL_IMG = "src/main/java/algstudent/s6/einstein_1_256.png";
	private static String BAD_IMG = "src/main/java/algstudent/s6/einstein_1_256.png";
	private static String OUT_DIR_G = "src/main/java/algstudent/s6/out_g/";
	private static String OUT_DIR_B = "src/main/java/algstudent/s6/out_bt";
	private static String OUT_DIR_BNB = "src/main/java/algstudent/s7/out_bnb";
	private static int N_IMGS = 13; 
	private static double PERCENTAGE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Noise level - Gaussian sigma
		
	public static void main(String[] args) {
		
		int n_real, n_bad;
		ImageAverager img_avger;
		
		for (int n = 2; n <= 14; n++) {
			System.out.println("Testing Image averager with " + n + " samples.");
			// Generating and testing a single dataset instance
			n_bad = (int) ((PERCENTAGE_BAD/100.)*n);
			n_real = n - n_bad;
			System.out.println(REAL_IMG);
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			
			System.out.println("TESTING BRANCH AND BOUND\n");
			NodeAvg node = new NodeAvg(img_avger);
			BranchAndBoundAvg problem = new BranchAndBoundAvg();
			
			long t, t1, t2;
			t = 0;
			t1 = System.currentTimeMillis();
			problem.branchAndBound(node);
			t2 = System.currentTimeMillis();
			t = t2-t1;
//			problem.printSolutionTrace();
			NodeAvg bestNode = (NodeAvg) problem.getBestNode();
//			System.out.println("The Heuristic of the best node is: " + bestNode.getHeuristicValue() + 
//					" The depth is : " + bestNode.getDepth() + bestNode.getSol(bestNode)
//					+ "\n The Processed nodes are: " + problem.PNodes + " Generated: " + problem.GNodes + " Trimmed: " + problem.TNodes );
//			System.out.println("**TIME=" + t);
//			System.out.println();
			img_avger = (bestNode).getImageAverager();
			img_avger.saveResults(OUT_DIR_BNB);			
			
			
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			node = new NodeAvg(img_avger);
			problem = new BranchAndBoundAvg();
			System.out.println("Testing with pruning");
			
			t = 0;
			t1 = System.currentTimeMillis();
			problem.branchAndBoundPrune(node);
			t2 = System.currentTimeMillis();
			t = t2-t1;
			problem.printSolutionTrace();
			bestNode = (NodeAvg) problem.getBestNode();
			System.out.println("The Heuristic of the best node is: " + bestNode.getHeuristicValue() + 
					" The depth is : " + bestNode.getDepth() + bestNode.getSol(bestNode)
					+ "\n The Processed nodes are: " + problem.PNodes + " Generated: " + problem.GNodes + " Trimmed: " + problem.TNodes );
			System.out.println("**TIME=" + t);
			System.out.println();
			
			img_avger = (bestNode).getImageAverager();
			img_avger.saveResults(OUT_DIR_BNB);
		}
	}
}
