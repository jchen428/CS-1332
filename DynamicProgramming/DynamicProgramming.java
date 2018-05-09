/**
 * Assignment to teach dynamic programming using 3 simple example problems:
 * 1. Fibonacci numbers
 * 2. Longest common subsequence
 * 3. Edit distance
 * 
 * @author Jesse Chen
 */
public class DynamicProgramming {

	/**
	 * Calculates the nth fibonacci number: fib(n) = fib(n-1) + fib(n-2).
	 * Remember that fib(0) = 0 and fib(1) = 1.
	 * 
	 * This should NOT be done recursively - instead, use a 1 dimensional
	 * array so that intermediate fibonacci values are not re-calculated.
	 * 
	 * The running time of this function should be O(n).
	 * 
	 * @param n A number 
	 * @return The nth fibonacci number
	 */
	public static int fib(int n) {
		int[] fib = new int[n + 2];
		fib[0] = 0;
		fib[1] = 1;
		
		for (int i = 2; i < n + 1; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		
		return fib[n];
	}

	/**
	 * Calculates the length of the longest common subsequence between a and b.
	 * 
	 * @param a
	 * @param b
	 * @return The length of the longest common subsequence between a and b
	 */
	public static int lcs(String a, String b) {
		int[][] lcs = new int[a.length() + 1][b.length() + 1];
		int i = 0, j = 0;
		
		for (i = 0; i < a.length(); i++) {
			for (j = 0; j < b.length(); j++) {
				if (a.charAt(i) == b.charAt(j)) {
					lcs[i + 1][j + 1] = lcs[i][j] + 1;
				} else {
					lcs[i + 1][j + 1] = Math.max(lcs[i][j + 1], lcs[i + 1][j]);
				}
			}
		}
		
		return lcs[i][j];
	}

	/**
	 * Calculates the edit distance between two strings.
	 * 
	 * @param a A string
	 * @param b A string
	 * @return The edit distance between a and b
	 */
	public static int edit(String a, String b) {
		int[][] edit = new int[a.length() + 1][b.length() + 1];
		int i = 0, j = 0;
		
		for (i = 0; i < a.length() + 1; i++) {
			edit[i][0] = i;
		}
		for (j = 0; j < b.length() + 1; j++) {
			edit[0][j] = j;
		}
		
		for (i = 1; i < a.length() + 1; i++) {
			for (j = 1; j < b.length() + 1; j++) {
				int tempMin = Math.min(edit[i - 1][j], edit[i][j - 1]) + 1;
				int min;
				
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					min = 0;
				} else {
					min = 1;
				}
				edit[i][j] = Math.min(tempMin, edit[i - 1][j - 1] + min);
			}
		}
		
		return edit[i - 1][j - 1];
	}
}