import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * This class implements the SortingInterface.
 * 
 * @author Jesse Chen
 */
public class Sorting implements SortingInterface {

	/**
	 * Implement bubble sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr The array to be sorted.
	 */
	public <T extends Comparable<T>> void bubblesort(T[] arr) {
		int i = 0;
		boolean swapped = true;
		
		while (i < arr.length - 1 || swapped) {
			swapped = false;
			
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					T temp = arr[j];
					
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}
			
			i++;
		}
	}

	/**
	 * Implement insertion sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr The array to be sorted.
	 */
	public <T extends Comparable<T>> void insertionsort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int j = i;
			T temp = arr[i];
			
			while (j > 0 && arr[j].compareTo(arr[j - 1]) < 0) {
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
				j--;
			}
		}
	}

	/**
	 * Implement selection sort.
	 * 
	 * It should be:
	 *  inplace
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n^2)
	 * 
	 * @param arr The array to be sorted.
	 */
	public <T extends Comparable<T>> void selectionsort(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			
			for (int j = i + 1; j < arr.length; j++) {	
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			
			if (minIndex != i) {
				T temp = arr[i];
				
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
	}

	/**
	 * Implement quick sort. 
	 * 
	 * Use the provided random object to select your pivots.
	 * For example if you need a pivot between a (inclusive)
	 * and b (exclusive) where b > a, use the following code:
	 * 
	 * int pivotIndex = r.nextInt(b - a) + a;
	 * 
	 * It should be:
	 *  inplace
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 * 
	 * @param arr The array to be sorted.
	 * @param r RNG for partitioning.
	 */
	public <T extends Comparable<T>> void quicksort(T[] arr, Random rng) {
		quicksort(arr, rng, 0, arr.length - 1);	
	}
	
	/**
	 * Recursive helper method for quicksort.
	 * 
	 * @param arr The array to be sorted.
	 * @param r RNG for partitioning.
	 * @param left The minimum index of a partition.
	 * @param right The maximum index of a partition.
	 */
	private <T extends Comparable<T>> void quicksort(T[] arr, Random rng, int left, int right) {
		if (left < right) {
			int pivotIndex = rng.nextInt(right - left) + left;
			int pivot = partition(arr, left, right, pivotIndex);
			
			quicksort(arr, rng, left, pivot - 1);
			quicksort(arr, rng, pivot, right);
		}
	}
	
	/**
	 * Partitions the given array.
	 * 
	 * @param arr The array to be sorted.
	 * @param left The minimum index of a partition.
	 * @param right The maximum index of a partition.
	 * @param pivotIndex The index of the pivot to be used.
	 * @return left The index of the next pivot.
	 */
	private <T extends Comparable<T>> int partition(T[] arr, int left, int right, int pivotIndex) {
		T pivot = arr[pivotIndex];
		
		while (left <= right) {
			while (arr[left].compareTo(pivot) < 0) {
				left++;
			}
			while (pivot.compareTo(arr[right]) < 0) {
				right--;
			}
			
			if (left <= right) {
				T temp = arr[left];
				
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}
		
		return left;
	}

	/**
	 * Implement merge sort.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n log n)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 *  
	 * @param arr
	 * @return
	 */
	public <T extends Comparable<T>> T[] mergesort(T[] arr) {
		if (arr.length == 1) {
			return arr;
		}
		
		Comparable[] left = new Comparable[arr.length / 2];
		Comparable[] right = new Comparable[arr.length - left.length];
		
		for (int i = 0; i < left.length; i++) {
			left[i] = arr[i];
		}
		
		for (int i = 0; i < right.length; i++) {
			right[i] = arr[left.length + i];
		}
		
		return merge(mergesort((T[]) left), mergesort((T[]) right));
	}

	private <T extends Comparable<T>> T[] merge(T[] left, T[] right) {
		int leftIndex = 0;
		int rightIndex = 0;
		int destIndex = 0;
		Comparable[] dest = new Comparable[left.length + right.length];
		
		while (leftIndex < left.length && rightIndex < right.length) {
			if (left[leftIndex].compareTo(right[rightIndex]) < 0) {
				dest[destIndex] = left[leftIndex];
				leftIndex++;
			} else {
				dest[destIndex] = right[rightIndex];
				rightIndex++;
			}
			
			destIndex++;
		}
		
		if (leftIndex >= left.length) {
			for (int i = rightIndex; i < right.length; i++) {
				dest[destIndex] = right[i];
				destIndex++;
			}
		} else if (rightIndex >= right.length) {
			for (int i = leftIndex; i < left.length; i++) {
				dest[destIndex] = left[i];
				destIndex++;
			}
		}
		
		return (T[]) dest;
	}
	
	/**
	 * Implement radix sort
	 * 
	 * Remember you CAN'T convert the ints to strings.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(kn)
	 *  
	 * And a best case running time of:
	 *  O(kn)
	 * 
	 * @param arr
	 * @return
	 */
	public int[] radixsort(int[] arr) {
		boolean flag = true;
		int divisor = 1;
		Queue[] buckets = new Queue[10];
		
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList();
		}
		
		while (flag) {
			flag = false;
			
			for (int i = 0; i < arr.length; i++) {
				int bucketIndex = (arr[i] / divisor) % 10;
				
				if (bucketIndex > 0) {
					flag = true;
				}
				
				buckets[bucketIndex].add(arr[i]);
			}
			
			int arrIndex = 0;
			
			for (int i = 0; i < buckets.length; i++) {
				while (!buckets[i].isEmpty()) {
					int value = (int) buckets[i].poll();
					arr[arrIndex++] = value;
				}
			}
			
			divisor *= 10;
		}
		
		return arr;
	}
}
