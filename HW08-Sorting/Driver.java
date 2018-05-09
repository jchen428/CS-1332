import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

/**
 * This class runs the program.
 * 
 * @author Jesse
 */
public class Driver {
	
	private static Scanner keyboard = new Scanner(System.in);
	private static Random rng = new Random();
	
	/**
	 * Main method. Executes program.
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		boolean exit = false;
		
		while (!exit) {
			System.out.print("Please enter the size of the array you would like to sort or enter 0 to exit: ");
            int size = keyboard.nextInt();
            
            if (size == 0) {
            	exit = true;
            } else if (size > 0) {
            	Integer[] unsorted = new Integer[size];
            	Integer[] copy = new Integer[size];
            	
            	for (int i = 0; i < size; i++) {
            		unsorted[i] = Math.abs(rng.nextInt(1000000000));
            		copy[i] = unsorted[i];
            	}
            	
            	System.out.println("The unsorted array is: " + Arrays.toString(copy));
            	
            	Sorting sorter = new Sorting();
            	Comparable[] sorted = sorter.mergesort(copy);
            	System.out.println("The sorted array is: " + Arrays.toString(sorted));
            	
            	long start, end;
            	double elapsed;
            	
            	for (int i = 0; i < size; i++) {
            		copy[i] = unsorted[i];
            	}
            	start = System.nanoTime();
            	sorter.bubblesort(copy);
            	end = System.nanoTime();
            	elapsed = ((double) (end - start)) / 1000000000;
            	System.out.println("\nIt took bubble sort " + elapsed + " seconds.");
            	
            	for (int i = 0; i < size; i++) {
            		copy[i] = unsorted[i];
            	}
            	start = System.nanoTime();
            	sorter.insertionsort(copy);
            	end = System.nanoTime();
            	elapsed = ((double) (end - start)) / 1000000000;
            	System.out.println("It took insertion sort " + elapsed + " seconds.");
            	
            	for (int i = 0; i < size; i++) {
            		copy[i] = unsorted[i];
            	}
            	start = System.nanoTime();
            	sorter.selectionsort(copy);
            	end = System.nanoTime();
            	elapsed = ((double) (end - start)) / 1000000000;
            	System.out.println("It took selection sort " + elapsed + " seconds.");
            	
            	for (int i = 0; i < size; i++) {
            		copy[i] = unsorted[i];
            	}
            	start = System.nanoTime();
            	sorter.quicksort(copy, rng);
            	end = System.nanoTime();
            	elapsed = ((double) (end - start)) / 1000000000;
            	System.out.println("It took quick sort " + elapsed + " seconds.");
            	
            	for (int i = 0; i < size; i++) {
            		copy[i] = unsorted[i];
            	}
            	start = System.nanoTime();
            	sorter.mergesort(copy);
            	end = System.nanoTime();
            	elapsed = ((double) (end - start)) / 1000000000;
            	System.out.println("It took merge sort " + elapsed + " seconds.");
            	
            	int[] intClone = new int[size];
            	for (int i = 0; i < size; i++) {
            		intClone[i] = copy[i];
            	}
            	start = System.nanoTime();
            	sorter.radixsort(intClone);
            	end = System.nanoTime();
            	elapsed = ((double) (end - start)) / 1000000000;
            	System.out.println("It took radix sort " + elapsed + " seconds.\n");
            }
		}
	}
}
