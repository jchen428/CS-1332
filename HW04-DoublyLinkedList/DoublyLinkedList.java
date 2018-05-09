import java.util.Iterator;

/**
 * This is a Doubly LinkedList that has both a head an tail pointer.
 * 
 * @author Jesse Chen
 */

public class DoublyLinkedList<E> implements LinkedListInterface<E>, Iterable<E>{

	private Node<E> head;
	private Node<E> tail;
	private int size;

	@Override
	/**
	 * Appends element before the element at the specified index.
	 * 
	 * @param index Index being appended to.  If the index is equal to the size of the list
	 * 			then append to end of list.
	 * @param data Element being appended
	 * @return whether add was successful, null data or invalid indexes should return false.
	 */
	public boolean add(int index, E data) {
		Node<E> curr;
		Node<E> newNode = new Node<E>(data);
		boolean added = false;
		
		if (index >= 0 && index <= size / 2) {
			curr = head;
			
			if (head == null && tail == null && index == 0) {
				head = newNode;
				tail = newNode;
			} else if (index == 0) {
				newNode.setNext(curr);
				curr.setPrev(newNode);
				head = newNode;
			} else {
				for (int i = 0; i < index; i++) {
					curr = curr.getNext();
				}
				
				Node<E> prev = curr.getPrev();
				prev.setNext(newNode);
				newNode.setPrev(prev);
				newNode.setNext(curr);
				curr.setPrev(newNode);
			}
			size++;
			added = true;
		} else if (index >= size / 2 && index <= size) {
			curr = tail;
			
			if (index == size) {
				newNode.setPrev(curr);
				curr.setNext(newNode);
				tail = newNode;
			} else {
				for (int i = size - 1; i > index; i--) {
					curr = curr.getPrev();
				}
				
				Node<E> prev = curr.getPrev();
				prev.setNext(newNode);
				newNode.setPrev(prev);
				newNode.setNext(curr);
				curr.setPrev(newNode);
			}
			size++;
			added = true;
		}

		return added;
	}
	
	@Override
	/**
	 * Clears list
	 */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	/**
	 * Checks if specified element is within the List
	 * 
	 * @param o Object being searched
	 * @return whether Object element is found
	 */
	public boolean contains(Object o) {
		boolean found = false;
		Node<E> curr = head;
		
		for (int i = 0; i < size && !found; i++) {
			if (curr.getData().equals(o)) {
				found = true;
			}
			curr = curr.getNext();
		}
		
		return found;
	}
	
	@Override
	/**
	 * Gets element at specified index
	 * 
	 * @param index Index of Element
	 * @return element at index, invalid index and not found element returns null.
	 */
	public E get(int index) {
		E elem = null;
		Node<E> curr;
		
		if (index >= 0 && index <= size / 2) {
			curr = head;
			
			for (int i = 0; i < index; i++) {
				curr = curr.getNext();
			}
			
			elem = curr.getData();
		} else if (index >= size / 2 && index <= size) {
			curr = tail;
			
			for (int i = size - 1; i > index; i--) {
				curr = curr.getPrev();
			}
			
			elem = curr.getData();
		}
		
		return elem;
	}
	
	@Override
	/**
	 * Returns the index of the first occurrence of the given element.
	 * 
	 * @param data The element to search for
	 * @return The first occurrence of the given element, or -1 if it is not in
	 *         the list
	 */
	public int indexOf(E data) {
		boolean found = false;
		int index = -1;
		Node<E> curr = head;
		
		for (int i = 0; i < size && !found; i++) {
			if (curr.getData().equals(data)) {
				index = i;
				found = true;
			} else {
				curr = curr.getNext();
			}
		}
		
		return index;
	}
	
	@Override
	/**
	 * Returns whether list is empty or not.
	 * 
	 * @return whether list is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	/**
	 * Removes the first instance of specified element
	 * from the list.
	 * 
	 * @param o Object element being removed
	 * @return Element being removed, null if not found
	 */
	public E remove(Object o) {
		E elem = null;
		boolean found = false;
		Node<E> curr = head;
		
		for (int i = 0; i < size && !found; i++) {
			if (curr.getData().equals(o)) {
				elem = curr.getData();
				found = true;
				Node<E> prev = curr.getPrev();
				Node<E> next = curr.getNext();
				
				if (curr == head) {
					head = next;
				} else {
					prev.setNext(next);
				}
				
				if (curr == tail) {
					tail = prev;
				} else {
					next.setPrev(prev);
				}
				
				size--;
			} else {
				curr = curr.getNext();
			}
		}

		return elem;
	}
	
	@Override
	/**
	 * Removes element at specified index
	 * 
	 * @param index Index of element being removed
	 * @return Element being removed, invalid index returns null
	 */
	public E remove(int index) {
		E elem = null;
		Node<E> curr;
		
		if (head != null && tail != null) {
			if (index >= 0 && index <= size / 2) {
				curr = head;
				
				for (int i = 0; i < index; i++) {
					curr = curr.getNext();
				}
				
				elem = curr.getData();
				Node<E> prev = curr.getPrev();
				Node<E> next = curr.getNext();
					
				if (curr == head) {
					head = next;
				} else {
					prev.setNext(next);
				}
				
				if (curr == tail) {
					tail = prev;
				} else {
					next.setPrev(prev);
				}
				
				size--;
			} else if (index >= size / 2 && index < size) {
				curr = tail;
				
				for (int i = size - 1; i > index; i--) {
					curr = curr.getPrev();
				}
				
				elem = curr.getData();
				Node<E> prev = curr.getPrev();
				Node<E> next = curr.getNext();
				
				prev.setNext(next);
				
				if (curr == tail) {
					tail = prev;
				} else {
					next.setPrev(prev);
				}
				
				size--;
			}
		}

		return elem;
	}
	
	@Override
	/**
	 * Replaces the element at the given index.
	 * 
	 * @param index Index of the element to be replace
	 * @param data Data of new element
	 * @return The old element, invalid index or null data returns null
	 */
	public E replace(int index, E data) {
		add(index + 1, data);
		E oldElem = remove(index);

		return oldElem;
	}
	
	@Override
	/**
	 * Returns the size of the List.
	 * 
	 * @return size of List
	 */
	public int size() {
		return size;
	}
	
	@Override
	/**
	 * Getter for head
	 * NOTE: This is purely for our testing purposes, in real life you would not want
	 * someone to have access to your head
	 * 
	 * @return Head of the list
	 */
	public Node<E> getHead() {
		return head;
	}
	
	@Override
	/**
	 * Reverses the current linked list
	 * NOTE: Do not copy the list to an external datastructure i.e. ArrayList, or create
	 * new object nodes.  It's possible to do this with Node references and reference 
	 * manipulation.
	 */
	public void reverseList() {
		Node<E> temp = head;
		head = tail;
		tail = temp;
		Node<E> curr = head;
		
		while (curr != null) {
			temp = curr.getNext();
			curr.setNext(curr.getPrev());
			curr.setPrev(temp);
			curr = curr.getNext();
		}
	}

	@Override
	/**
	 * Creates an iterator for the List.
	 * 
	 * @return DLLIterator
	 */
	public Iterator<E> iterator() {
		return new DLLIterator();
	}
	
	/**
	 * Implements Iterator.
	 * 
	 * @author Jesse Chen
	 */
	private class DLLIterator implements Iterator<E> {

		public Node<E> curr;
		
		/**
		 * Constructor for DLLIterator that starts at the head of the list.
		 */
		public DLLIterator() {
			curr = head;
		}
		
		@Override
		/**
		 * Determines if the current Node exists.
		 * 
		 * @return Whether the current Node is null
		 */
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		/**
		 * Gets the data from the current Node.
		 * 
		 * @return The data from the current Node
		 */
		public E next() {
			E result;
			
            if (hasNext()) {
                result = curr.getData();
                curr = curr.getNext();
            }
            else {
                throw new java.util.NoSuchElementException();
            }
            
            return result;
        }

		@Override
		/**
		 * Not used.
		 */
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}
}