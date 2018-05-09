/**
 * Node class for the Doubly Linked List.
 * 
 * @author Jesse Chen
 */
public class Node<E> {
	
	private E data;
	private Node<E> next;
	private Node<E> prev;
	
	/**
	 * Constructor for Node.
	 * 
	 * @param data The data that the Node will contain
	 */
	public Node(E data) {
		this.data = data;
	}
	
	@Override
	/**
	 * Returns a string representation of the Node's data
	 * 
	 * @return A string representation of the Node's data
	 */
	public String toString() {
		return data.toString();
	}
	
	/**
	 * Gets the data in the Node.
	 * 
	 * @return data The data in the Node
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Gets what this Node's next pointer points to.
	 * @return next The Node that this Node's next pointer points to
	 */
	public Node<E> getNext() {
		return next;
	}
	
	/**
	 * Sets the Node's next pointer.
	 * 
	 * @param next The next Node
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	/**
	 * Gets what this Node's prev pointer points to.
	 * 
	 * @return prev The Node that this Node's prev pointer points to
	 */
	public Node<E> getPrev() {
		return prev;
	}
	
	/**
	 * Sets the Node's prev pointer.
	 * 
	 * @param prev The prev Node
	 */
	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}
}