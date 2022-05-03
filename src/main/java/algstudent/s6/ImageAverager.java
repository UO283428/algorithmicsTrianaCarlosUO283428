package algstudent.s6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ImageAverager {
	
	private Image real_img, bad_img; //to store the main good and main bad image
	private Image avg_img, half1_img, half2_img; //to store the final tests to see if we improve the previous results
	private Image[] dataset; //dataset to store all the images (good and bad ones)
	private int[] sol; //to store the partial results (where I am putting the pictures? 0->not assigned, 1->first half, 2->second half
	private int[] bestSol; //to store the best solution
	private int width, height; //to store the width and height of the image
	//backtracking variables
	private int counter; //to store the number of times we assign an image to half1, half2 or no group
	private double max_zncc; //to store the best ZNCC
	public int TNodes, GNodes;
	
	
	/** Constructor
	* @real_path  path to the real image (pattern to find) on disk
	* @bad_path  path to the bad image on disk
	* @n_real  number of real images in the dataset (>= 1)
	* @n_bad  number of bad images in the dataset 
	* @s_noise  standard deviation for noise 
	*/
	public ImageAverager(String real_path, String bad_path, int n_real, int n_bad, double s_noise) {
		assert (n_real >= 1) && (n_bad < n_real);//assert at least one reference image
		TNodes = 0;
		GNodes = 0;
		
		//load reference and bad images
		this.real_img = new Image(real_path);
		this.bad_img = new Image(bad_path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();
		
		//create the dataset as an array of unordered randomly chosen real and bad images
		int total_imgs = n_real + n_bad; //the total number of images are the good + the bad ones
		this.dataset = new Image[total_imgs]; //the data set for the total of images
		this.sol = new int[total_imgs]; //we will use this variable during the process 0->not assigned, 1->first half, 2->second half 
		this.bestSol = new int[total_imgs]; //we will use this variable to store the best results
		int[] rand_index = this.randomIndexes(total_imgs); //random array of positions to mix images
		Image hold_img; //temp images
		int region = 0; // 0-up, 1-down, 2-left, 3-right
		for (int i=0; i<n_real; i++) { //to save good images
			hold_img = new Image(this.width, this.height); //generate image
			hold_img.addSignal(this.real_img); //save the image
			hold_img.suppressRegion(region); //a half part of the image is deleted
			hold_img.addNoise(s_noise); //add some noise 
			this.dataset[rand_index[i]] = hold_img; //save image
			if (region == 3) region = 0;
			else region++;
		}
		region = 0;
		for (int i=n_real; i<n_real+n_bad; i++) { //to save bad images
			hold_img = new Image(this.width, this.height); //generate image
			hold_img.addSignal(this.bad_img); //save the image
			hold_img.invertSignal(); //corrupt the image
			hold_img.suppressRegion(region); //the fourth part of the image is deleted
			hold_img.addNoise(s_noise); //add some noise  
			this.dataset[rand_index[i]] = hold_img; //save image
			if (region == 3) region = 0;
			else region++;
		}
	}
	
	/**
	 * To generate a random array of positions
	 * @param n Length of the array
	 * @return 
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}
	
	/**
	 * Store resulting images for testing
	 * @out_dir directory save the output images
	 */
	public void saveResults(String out_dir) {
		this.avg_img.save(out_dir + "/img_avg.png");
		this.half1_img.save(out_dir + "/img_half1_avg.png");
		this.half2_img.save(out_dir + "/img_half2_avg.png");
		for(int i=0; i<this.dataset.length; i++) {
			this.dataset[i].save(out_dir + "/img_" + i + "_klass_" + this.bestSol[i] + ".png");
		}
	}
	
	/**
	 * @return the number of steps carried out by the algorithm to solve the problem
	 */
	public int getCounter() {
		return counter;
	}
	
	/** Computes the ZNCC between both image dataset halves
	 * @return the computed ZNCC
	 */
	public double zncc() {
		return this.half1_img.zncc(this.half2_img);
	}
	
	public double getMaxZncc() {
		return max_zncc;
	}
	
	/**
	 * Greedy algorithm: random instances for each half, the best one is the final solution    
	 * @n_tries number of random tries     
	 */
	public void splitSubsetsGreedy(int n_tries) {
		double minAvg = -1;
		Image[] g1Sol = null, g2Sol = null;
		
		for (int i = 1; i <= n_tries; i++) {
			int[] indexes = new int[dataset.length];
			Random rdn = new Random();
			int indx = 0, g1L = 0, g2L = 0;
			
			for (int j = 0; j < dataset.length; j++) {
				indx = rdn.nextInt(2)+1;
				indexes[j] = indx;
				
				if (indx == 1) {
					g1L++;
				}else if (indx == 2) {
					g2L++;
				}
			}
			
			Image[] g1 = new Image[g1L];
			Image[] g2 = new Image[g2L];
			int i1 = 0, i2 = 0;
			
			half1_img = new Image(this.width, this.height);
			half2_img = new Image(this.width, this.height);
			
			for (int j = 0; j < indexes.length; j++) {
				if (indexes[j] == 1) {
					g1[i1] = dataset[j];
					half1_img.addSignal(dataset[j]);
					i1++;
				}else if (indexes[j] == 2) {
					g2[i2] = dataset[j];
					half2_img.addSignal(dataset[j]);
					i2++;
				}
			}
			if (g1.length > 0 || g2.length > 0) {
				if (zncc() > minAvg) {
					minAvg = zncc();
					g1Sol = g1.clone();
					g2Sol = g2.clone();
				}
			}
		}

		half1_img = new Image(this.width, this.height);
		half2_img = new Image(this.width, this.height);
		
		for (int i = 0; i < g1Sol.length; i++) {
			half1_img.addSignal(g1Sol[i]);
		}
		
		for (int i = 0; i < g2Sol.length; i++) {
			half2_img.addSignal(g2Sol[i]);
		}
		
		avg_img = new Image(this.width, this.height);
		avg_img.addSignal(half1_img);
		avg_img.addSignal(half2_img);
		
	}

	/**
	 * Backtracking algorithm 
	 * @max_unbalancing: (pruning condition) determines the maximum difference between 
	 * the number of images on each half set               
	 */
	public void splitSubsetsBacktracking(int max_unbalancing) {
			half1_img = new Image(this.width, this.height);
			half2_img = new Image(this.width, this.height);
			avg_img = new Image(this.width, this.height);
			
			max_zncc = -1;
			splitSubsetsBacktracking(0, max_unbalancing);
		}
		
	public void splitSubsetsBacktracking(int level, int max_unbalancing) {
		if (level == dataset.length) { //Sol stop
			if (zncc() > max_zncc) {
				max_zncc = zncc();
				//System.out.println(max_zncc);
				bestSol = copyOf(sol);
				avg_img = new Image(this.width, this.height);
				avg_img.addSignal(half1_img);
				avg_img.addSignal(half2_img);
			}
			
		}else {
			Image auxG1 = half1_img.copy();
			Image auxG2 = half2_img.copy();
				
			int leftBalance = getLeftBalance();
			int rightBalance = getRightBalance();
			//Add image to group 1
			if (!((leftBalance +1 ) - rightBalance > max_unbalancing)) {
				GNodes++;
				half1_img.addSignal(dataset[level]);
				sol[level] = 1;
				
				splitSubsetsBacktracking(level+1, max_unbalancing);
				half1_img = auxG1.copy();
			}else {
				TNodes++;
			}
	
			//Add image to group 2
			if (!((rightBalance +1 ) - leftBalance > max_unbalancing)) {
				GNodes++;
				half2_img.addSignal(dataset[level]);
				sol[level] = 2;
				
				splitSubsetsBacktracking(level+1, max_unbalancing);
				half2_img = auxG2.copy();
			}else {
				TNodes++;
			}
				
			//Add image to group 3
			sol[level] = 0;
			splitSubsetsBacktracking(level+1, max_unbalancing);
		}
	}

	private int getLeftBalance() {
		int balance = 0;
		for (int i = 0; i < sol.length; i++) {
				if (sol[i] == 1)
					balance++;
			}
		return balance;
	}

	private int getRightBalance() {
		int balance = 0;
		for (int i = 0; i < sol.length; i++) {
				if (sol[i] == 2)
					balance++;
			}
		return balance;
	}

	/**
	 * Backtracking algorithm without balancing. Using a larger than the number of images in the dataset ensures no prunning          
	 */
	public void splitSubsetsBacktracking() {
		half1_img = new Image(this.width, this.height);
		half2_img = new Image(this.width, this.height);
		avg_img = new Image(this.width, this.height);
		
		max_zncc = -1;
		Backtracking(0);
	}
	
	public void Backtracking(int level) {
		if (level == dataset.length) { //Sol stop
			if (zncc() > max_zncc) {
				max_zncc = zncc();
				//System.out.println(max_zncc);
				bestSol = copyOf(sol);
				avg_img = new Image(this.width, this.height);
				avg_img.addSignal(half1_img);
				avg_img.addSignal(half2_img);
			}
			
		}else {
			Image auxG1 = half1_img.copy();
			Image auxG2 = half2_img.copy();
			//Add image to group 1
			half1_img.addSignal(dataset[level]);
			sol[level] = 1;
			
			Backtracking(level+1);
			half1_img = auxG1.copy();
			
			
			//Add image to group 2
			half2_img.addSignal(dataset[level]);
			sol[level] = 2;
			
			Backtracking(level+1);
			half2_img = auxG2.copy();
			
			
			//Add image to group 3
			sol[level] = 0;
			Backtracking(level+1);
		}
	}

	private int[] copyOf(int[] sol2) {
		int[] ret = new int[sol2.length];
		for (int i = 0; i < sol2.length; i++) {
			ret[i] = sol2[i];
		}
		return ret;
	}

}