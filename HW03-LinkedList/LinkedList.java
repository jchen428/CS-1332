/**
 * This class implements LinkedListInterface.
 * 
 * @author Jesse Chen
 */
public class LinkedList<T> implements LinkedListInterface<T> {

	private Node<T> head = null;
	private Node<T> tail = null;
	private int size = 0;
	
	@Override
	/**
	 * Add a new node to the front of the linked list 
	 * that holds the given data.
	 * 
	 * @param t The data that the new node should hold.
	 */
	public void addToFront(T t) {
		if (head == null) {
			head = new Node<T>(t, null);
			tail = head;
		} else {
			head = new Node<T>(t, head);
		}
		
		size++;
	}

	@Override
	/**
	 * Add a new node to the back of the linked list
	 * that holds the given data.
	 * 
	 * @param t The data that the new node should hold.
	 */
	public void addToBack(T t) {
		if (head == null) {
			head = new Node<T>(t, null);
			tail = head;
		} else {
			tail.next = new Node<T>(t, null);
			tail = tail.next;
		}
		
		size++;
	}

	@Override
	/**
	 * Remove the front node from the list and return the
	 * data from it.
	 * 
	 * @return The data from the front node or null.
	 */
	public T removeFromFront() {
		T data = head.data;
		head = head.next;
		size--;
		
		return data;
	}

	@Override
	/**
	 * Remove the back node from the list and return the 
	 * data from it.
	 * 
	 * @return The data from the last node or null.
	 */
	public T removeFromBack() {
		T data = tail.data;
		Node<T> temp = head;
		
		while (temp != tail && temp.next != tail) {
			temp = temp.next;
		}
		
		temp.next = null;
		tail = temp;
		size--;
		
		return data;
	}

	@Override
	/**
	 * Return the linked list represented as a list of objects.
	 * 
	 * @return A copy of the linked list data as a list.
	 */
	public T[] toList() {
		Object[] arr = new Object[size];
		Node<T> temp = head;
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (T) temp.data;
			
			if (temp.next != null) {
				temp = temp.next;
			}
		}

		return (T[]) arr;
	}

	@Override
	/**
	 * Return a boolean value representing whether or not
	 * the list is empty.
	 * 
	 * @return True if empty. False otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	/**
	 * Return the size of the list as an integer.
	 * 
	 * @return The size of the list.
	 */
	public int size() {
		return size;
	}

	@Override
	/**
	 * Clear the list.
	 */
	public void clear() {
		head = null;
		tail = null;
	}

	/**
	 * The node class. Remember that this is a singularly linked list.
	 * 
	 * @author Jesse Chen
	 */
	private class Node<T> {
		private T data;
		private Node<T> next;
		
		private Node(T d, Node<T> n) {
			data = d;
			next = n;
		}
	}

}