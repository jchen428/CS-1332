import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * This class contains BFS, DFS, and Djikstra graph search algorithms.
 * 
 * @author Jesse Chen
 */
public class GraphSearch {

	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using General Graph Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be added to the Structure.
	 *
	 * The structure(struct) passed in is an empty structure may behave as a Stack or Queue and the
	 * correspondingly search function should execute DFS(Stack) or BFS(Queue) on the graph.
	 *
	 * @param start		The start node.
	 * @param struct	The data structure used to implement the search.
	 * @param adjList	The adjacency list of the given graph.
	 * @param goal		The goal node.
	 * @return true 	if path exists false otherwise
	 */
	public static <T> boolean generalGraphSearch(T start, Structure<T> struct, Map<T, List<T>> adjList, T goal) {
		if (start == null || struct == null || adjList == null || goal == null) {
			throw new IllegalArgumentException();
		} else if (adjList.isEmpty() || !adjList.containsKey(start) || !adjList.containsKey(goal)) {
			return false;
		}
		
		HashSet<T> visited = new HashSet<T>();

		struct.add(start);
		
		while (!struct.isEmpty()) {
			T curr = struct.remove();

			if (visited.add(curr)) {
				for (T t : adjList.get(curr)) {
					if (t == goal || t.equals(goal)) {
						return true;
					}
					
					if (!visited.contains(t)) {
						struct.add(t);
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using Bredth First Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be added to the Structure.
	 *
	 * @param start		The start node.
	 * @param adjList	The adjacency list of the given graph.
	 * @param goal		The goal node.
	 * @return true 	if path exists false otherwise
	 */
	public static <T> boolean breadthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
		return generalGraphSearch(start, new StructureQueue<T>(), adjList, goal);
	}
	
	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using Depth First Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be added to the Structure.
	 *
	 * @param start		The start node.
	 * @param adjList	The adjacency list of the given graph.
	 * @param goal		The goal node.
	 * @return true 	if path exists false otherwise
	 */
	public static <T> boolean depthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
		return generalGraphSearch(start, new StructureStack<T>(), adjList, goal);
	}
	
	/**
	 * Find the shortest distance between the start node and the goal node in the given a weighted graph
	 * in the form of an adjacency list where the edges only have positive weights
	 * Return the aforementioned shortest distance if there exists a path between the start and goal,-1
	 * otherwise.
	 *
	 * There are no negative edge weights in the graph.
	 *
	 * @param start		The start node.
	 * @param adjList	The adjacency list of the given weighted graph.
	 * @param goal		The goal node.
	 * @return the shortest distance between the start and the goal node
	 */
	public static <T> int djikstraShortestPathAlgorithm(T start, Map<T, List<Pair<T, Integer>>> adjList, T goal) {
		if (start == null || adjList == null || goal == null) {
			throw new IllegalArgumentException();
		} else if (adjList.isEmpty() || !adjList.containsKey(start) || !adjList.containsKey(goal)) {
			return -1;
		}
		
		Comparator<Pair<T, Integer>> comparator = new Comparator<Pair<T, Integer>>() {
			
			public int compare(Pair<T, Integer> pair1, Pair<T, Integer> pair2) {
				return Integer.compare(pair1.b, pair2.b);
			}
		};
		
		PriorityQueue<Pair<T, Integer>> heap = new PriorityQueue<Pair<T, Integer>>(1, comparator);
		Hashtable<T, Integer> vertices = new Hashtable<T, Integer>();
		
		for (T t : adjList.keySet()) {
			if (t == start) {
				vertices.put(t, 0);
			} else {
				vertices.put(t, Integer.MAX_VALUE);
			}
			
			heap.add(new Pair<T, Integer>(t, vertices.get(t)));
		}
		
		while (!heap.isEmpty()) {
			Pair<T, Integer> curr = heap.poll();
			
			for (Pair<T, Integer> adj : adjList.get(curr.a)) {
				int dist = vertices.get(curr.a) + adj.b;
				
				if (dist < vertices.get(adj.a)) {
					int newDist = dist;
					vertices.remove(adj);
					vertices.put(adj.a, newDist);
					heap.add(new Pair<T, Integer>(adj.a, dist));
				}
			}
		}
		
		return vertices.get(goal);
	}
}