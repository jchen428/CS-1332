/**
 * This class implements StackInterface.
 * 
 * @author Jesse Chen
 *
 */
public class Stack<T> implements StackInterface<T> {

	private LinkedListInterface<T> stack = new LinkedList<T>();

	@Override
	/**
	 * Push a new node onto the stack with the given data.
	 * 
	 * @param t The data to push.
	 */
	public void push(T t) {
		stack.addToFront(t);
	}

	@Override
	/**
	 * Should pop from the stack.
	 * 
	 * @return Data from node or null.
	 */
	public T pop() {
		return stack.removeFromFront();
	}

	@Override
	/**
	 * Return the size of the stack.
	 * 
	 * @return int size
	 */
	public int size() {
		return stack.size();
	}

	@Override
	/**
	 * Return true if empty. False otherwise.
	 * 
	 * @return boolean result
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
}