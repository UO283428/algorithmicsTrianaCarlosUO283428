package algstudent.s5;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LevenshteinDistanceTest {

	int[][] lT;
	
	private String bTest1;
	private String bTest2;
	
	@Before
	public void setUp() throws Exception {		
		bTest1 = "ABCDE";
		bTest2 = "A";
	}

	@Test
	public void basicTest() {
		lT = LevenshteinDistance.calculateLevenshteinTable(bTest1, bTest2);
		int[][] res = new int[][]{{0, 1, 2, 3, 4, 5}, {1, 0, 1, 2, 3, 4}};
		Assert.assertArrayEquals(lT, res);
	}
	
	@Test
	public void basicTestInv() {
		lT = LevenshteinDistance.calculateLevenshteinTable(bTest2, bTest1);
		int[][] res = new int[][]{{0, 1}, {1, 0}, {2, 1}, {3, 2}, {4, 3}, {5, 4}};
		Assert.assertArrayEquals(lT, res);
	}
	

	@Test
	public void mainTest() {
		lT = LevenshteinDistance.calculateLevenshteinTable(LevenshteinDistance.str1, LevenshteinDistance.str2);
		int[][] res = new int[][] {{0, 1, 2, 3, 4, 5, 6, 7, 8}, 
			{1, 1, 1, 2, 3, 4, 5, 6, 7}, 
			{2, 1, 2, 2, 3, 4, 5, 6, 7}, 
			{3, 2, 2, 2, 3, 4, 5, 6, 7}, 
			{4, 3, 2, 3, 3, 3, 4, 5, 6}, 
			{5, 4, 3, 3, 3, 4, 4, 5, 6}, 
			{6, 5, 4, 4, 4, 3, 4, 4, 5}, 
			{7, 6, 5, 5, 5, 4, 4, 5, 5}, 
			{8, 7, 6, 6, 6, 5, 5, 4, 5}, 
			{9, 8, 7, 7, 7, 6, 6, 5, 5}, 
			{10, 9, 8, 7, 8, 7, 7, 6, 6}, 
			{11, 10, 9, 8, 8, 8, 8, 7, 7}};

		Assert.assertArrayEquals(lT, res);
	}

}
