/**
 * This class implements QueueInterface.
 * 
 * @author Jesse Chen
 *
 */
public class Queue<T> implements QueueInterface<T> {

	private LinkedListInterface<T> queue = new LinkedList<T>();

	@Override
	/**
	 * Add a node with the given data to the back of your queue.
	 * 
	 * @param t The data to add.
	 */
	public void enqueue(T t) {
		queue.addToBack(t);
	}

	@Override
	/**
	 * Dequeue from the front of your queue.
	 * 
	 * @return The data from the front node or null.
	 */
	public T dequeue() {
		return queue.removeFromFront();
	}

	@Override
	/**
	 * Return the size of the queue.
	 * 
	 * @return int size
	 */
	public int size() {
		return queue.size();
	}

	@Override
	/**
	 * Return true if empty. False otherwise.
	 * 
	 * @return boolean result
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}	
}