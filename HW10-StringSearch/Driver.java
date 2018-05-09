import java.util.List;
import java.util.Scanner;

/**
 * Driver for the program.
 * 
 * @author Jesse
 */
public class Driver {

	private static Scanner keyboard = new Scanner(System.in);
	private static StringSearchInterface search = new StringSearch();
	
	/**
	 * Main method. Executes program.
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		boolean exit = false;
		
		while (!exit) {
			System.out.println("Enter some text (\"exit\" to quit): ");
			String text = keyboard.nextLine();
			
			if (text.equals("exit")) {
				exit = true;
			} else {
				System.out.println("Enter pattern to find: ");
				String pattern = keyboard.nextLine();
				
				long start, end;
		    	double elapsed;
				
				start = System.nanoTime();
		    	List<Integer> boyerMoore = search.boyerMoore(pattern, text);
		    	end = System.nanoTime();
		    	elapsed = ((double) (end - start)) / 1000000000;
				System.out.println("\nBoyer-Moore:");
				System.out.println("Pattern found in text at indices: " + boyerMoore);
				System.out.println("Took " + elapsed + "  seconds to final all occurences\n");
				
				start = System.nanoTime();
		    	List<Integer> rabinKarp = search.rabinKarp(pattern, text);
		    	end = System.nanoTime();
		    	elapsed = ((double) (end - start)) / 1000000000;
				System.out.println("Rabin-Karp:");
				System.out.println("Pattern found in text at indices: " + rabinKarp.toString());
				System.out.println("Took " + elapsed + "  seconds to final all occurences\n");
			}
		}
	}
}
