import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class implements BSTInterface.
 * 
 * @author Jesse Chen
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
	
	private Node<T> root;
	private int size = 0;
	
	/**
     * The toString method is used for debugging purposes.
     *
     * @return A String representation of the BST.
     */
	public String toString() {
		if (root == null) {
		return "()";
		}
		return root.toString();
	}
    
    /**
     * Add data to the binary search tree.  All data to the left of
     * a node must be less than it, and all data to the right must be
     * greater than it. If the data is null throw IllegalArgumentException.
     * Do not add duplicate data to the tree.
     * 
     * @param data Data to be added to the tree.        
     */
    public void add(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	if (root == null) {
    		root = new Node<T>(data);
    		size++;
    	} else {
    		add(root, data);
    	}
    }
    
    /**
     * Recursive helper method for add(T data);
     * 
     * @param curr The current node.
     * @param data The data to be removed.
     * @return curr The next node to be checked.
     */
    private Node<T> add(Node<T> curr, T data) {
		if (curr == null) {
			curr = new Node<T>(data);
			size++;
		} else if (data.compareTo(curr.getData()) < 0) {
			curr.setLeft(add(curr.getLeft(), data));
		} else if (data.compareTo(curr.getData()) > 0) {
			curr.setRight(add(curr.getRight(), data));
		}
		
		return curr;
	}
    
    /**
     * Add the contents of the collection to the BST. 
     * If the data is null throw IllegalArgumentException.
     * 
     * @param collection A collection of data to be added to the tree.      
     */
    public void addAll(Collection<T> c) {
    	if (c == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	Iterator<T> it = c.iterator();
    	
    	while (it.hasNext()) {
    		add(it.next());
    	}
    }
    
    /**
     * Remove the data element from the tree.
     * 
     * In the case that a node you want to remove has two children
     * replace it with the successor. If the data is null throw 
     * IllegalArgumentException.
     * 
     * @param data The data you want to remove.
     * @return The data that was removed from the tree. Return null if
     *         the data doesn't exist.
     */
    public T remove(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}

    	T targetData = get(data);
    	
    	if (targetData != null) {
    		if (root.getData() == targetData) {
    			root = remove(root, data);
    		} else {
    			remove(root, data);
    		}
    	}
    	
    	return targetData;
    }
    
    /**
     * Recursive helper method for remove(T data);
     * 
     * @param curr The current node.
     * @param data The data to be removed.
     * @return curr The current mode after it has been replaced to reflect a
     * 				removal.
     */
    private Node<T> remove(Node<T> curr, T data) {
		if (curr == null) {
			return null;
		} else if (data.compareTo(curr.getData()) < 0) {
			curr.setLeft(remove(curr.getLeft(), data));
		} else if (data.compareTo(curr.getData()) > 0) {
			curr.setRight(remove(curr.getRight(), data));
		} else {		
			if (curr.getLeft() != null && curr.getRight() != null) {
				Node<T> successor = getMin(curr.getRight());
				curr.setData(successor.getData());
				curr.setRight(remove(curr.getRight(), curr.getData()));
			} else if (curr.getLeft() != null) {
				Node<T> leftChild = curr.getLeft();
				curr.setData(leftChild.getData());
				curr.setLeft(remove(leftChild, curr.getData()));
			} else if (curr.getRight() != null) {
				Node<T> rightChild = curr.getRight();
				curr.setData(rightChild.getData());
				curr.setRight(remove(rightChild, curr.getData()));
			} else {
				curr = null;
				size--;
			}
		}
		
		return curr;
	}
    
    /**
     * Finds the left-most node under a given node.
     * 
     * @param node The starting node.
     * @return The left-most node under the starting node.
     */
    private Node<T> getMin(Node<T> node) {
    	if (node.getLeft() == null) {
    		return node;
    	}
    	
    	return getMin(node.getLeft());
    }
    
    /**
     * Get the data from the tree.
     * 
     * This method simply returns the data that was stored in the tree.
     * If the data is null throw IllegalArgumentException.
     *
     * @param data The data you are searching for.
     * @return The data that was found in the tree. Return null if the data
     *         doesn't exist.
     */
    public T get(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	T target = get(root, data);
    	
    	return target;
    }
    
    /**
     * Recursive helper method for get(T data).
     * 
     * @param target The target node.
     * @param data The data to be searched for.
     * @return result The data of the retrieved node.
     */
    private T get(Node<T> target, T data) {
    	T result;
    	
    	if (target == null) {
    		return null;
    	} else if (data.compareTo(target.getData()) < 0) {
    		result = get(target.getLeft(), data);
		} else if (data.compareTo(target.getData()) > 0) {
			result = get(target.getRight(), data);
		} else {
			result = target.getData();
		}

    	return result;
    }
    
    /**
     * See if the tree contains the data.
     * If the data is null throw IllegalArgumentException. 
     * 
     * @param data The data to search for in the tree.        
     * @return Return true if the data is in the tree, false otherwise.
     */
    public boolean contains(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	return contains(root, data);
    }
    
    /**
     * Recursive helper method for contains(T data).
     * 
     * @param target The target node.
     * @param data The data to be searched for.
     * @return true If the data was found, otherwise false.
     */
    private boolean contains(Node<T> target, T data) {
    	boolean contained = false;

    	if (target == null) {
    		return false;
    	} else if (data.compareTo(target.getData()) < 0) {
			contained = contains(target.getLeft(), data);
		} else if (data.compareTo(target.getData()) > 0) {
			contained = contains(target.getRight(), data);
		} else {
			contained = true;
		}

    	return contained;
    }
    
    /**
     * Linearize the tree using the pre-order traversal.
     * 
     * @return A list that contains every element in pre-order.
     */
    public List<T> preOrder() {
    	List<T> linkedList = new LinkedList<T>();
		preOrder(root, linkedList);
		
		return linkedList;
    }
    
    /**
     * Recursive helper method for preOrder().
     * 
     * @param subRoot The root of the current subtree.
     * @param list The list of elements in pre-order.
     */
    private void preOrder(Node<T> subRoot, List<T> list) {
		if (subRoot == null) {
			return;
		}
		
		list.add(subRoot.getData());
		preOrder(subRoot.getLeft(), list);
		preOrder(subRoot.getRight(), list);
	}
    
    /**
     * Linearize the tree using the in-order traversal.
     * 
     * @return A list that contains every element in-order.
     */
    public List<T> inOrder() {
    	List<T> linkedList = new LinkedList<T>();
		inOrder(root, linkedList);
		
		return linkedList;
    }
    
    /**
     * Recursive helper method for inOrder().
     * 
     * @param subRoot The root of the current subtree.
     * @param list The list of elements in order.
     */
    private void inOrder(Node<T> subRoot, List<T> list) {
		if (subRoot == null) {
			return;
		}
		
		inOrder(subRoot.getLeft(), list);
		list.add(subRoot.getData());
		inOrder(subRoot.getRight(), list);
	}
    
    /**
     * Linearize the tree using the post-order traversal.
     * 
     * @return A list that contains every element in post-order.
     */
    public List<T> postOrder() {
    	List<T> linkedList = new LinkedList<T>();
		postOrder(root, linkedList);
		
		return linkedList;
    }
    
    /**
     * Recursive helper method for postOrder().
     * 
     * @param subRoot The root of the current subtree.
     * @param list The list of elements in post-order.
     */
    private void postOrder(Node<T> subRoot, List<T> list) {
		if (subRoot == null) {
			return;
		}
		
		postOrder(subRoot.getLeft(), list);
		postOrder(subRoot.getRight(), list);
		list.add(subRoot.getData());
	}
    
    /**
     * Linearize the tree using the level-order traversal.
     * 
     * @return A list that contains every element in level-order.
     */
    public List<T> levelOrder() {
    	List<T> linkedList = new LinkedList<T>();
		levelOrder(root, linkedList);
		
		return linkedList;
    }
    
    /**
     * Iterative helper method for levelOrder().
     * 
     * @param root The root.
     * @param list The list of elements in pre-order.
     */
    private void levelOrder(Node<T> root, List<T> list) {
		if (root != null) {
			LinkedList<Node<T>> q = new LinkedList<Node<T>>();
			q.add(root);
			while (!q.isEmpty()) {
				Node<T> curr = q.removeFirst();
				if (curr.getLeft() != null) {
					q.add(curr.getLeft());
				}
				if (curr.getRight() != null) {
					q.add(curr.getRight());
				}
				list.add(curr.getData());
			}
		}
	}
    
    /**
     * Test to see if the tree is empty.
     * 
     * @return Return true if the tree is empty, false otherwise.
     */
    public boolean isEmpty() {
    	return root == null;
    }
    
    /**
     * Returns the size of the BST.
     * 
     * @return Return the number of elements in the tree.
     */
    public int size() {
    	return size;
    }
    
    /**
     * Clears the tree.
     */
    public void clear() {
    	root = null;
    	size = 0;
    }
}
