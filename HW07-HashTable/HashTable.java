import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import java.util.Set;

/**
 * Implement the hash table interface here.
 * 
 * @author Jesse Chen
 * @author Julia Ting
 * 
 */
public class HashTable<K, V> implements HashTableInterface<K, V> {

	/**
	 * DO NOT CHANGE THIS NUMBER.
	 * 
	 * This is the constant determining max load factor, or when you will
	 * have to regrow the table.
	 */
	private static final double MAX_LOAD_FACTOR = .71;
	
	/**
	 * DO NOT CHANGE THIS NUMBER.
	 * 
	 * This is the constant determining what size you will initialize your
	 * table array to.
	 */
	private static final int INITIAL_CAPACITY = 11;
	
	/**
	 * The number of entries in the table.
	 */
	private int size;
	
	/**
	 * The backing array of your hash table.
	 */
	private MapEntry<K, V>[] table;
	
	/**
	 * Initialize the backing array to the initial capacity and the size
	 * to the appropriate starting size.
	 */
	public HashTable() {
		table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
		size = 0;
	}
	
	/**
	 * Add the key value pair in the form of a MapEntry<K, V>. If the key
	 * already exists in the table then override the current value with the
	 * new value. Don't forget to regrow the table at the appropriate time.
	 * 
	 * Throw an IllegalArgumentException if input is null.
	 * 
	 * @param key The key of this entry in the hash table.
	 * @param value The value that you would like to add to the hash table.
	 * @return the value that previously corresponded to the specified key
	 * if it existed, null if it did not have one.
	 */
	public V put(K key, V value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException();
		}
		
		if (size + 1 > table.length * MAX_LOAD_FACTOR) {
			regrow();
		}
		
		int hashCode = Math.abs(key.hashCode()) % table.length;
		MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
		
		if (table[hashCode] == null || table[hashCode].isRemoved()) {
			table[hashCode] = newEntry;
			size++;
		} else {
			MapEntry<K, V> existingEntry = table[hashCode];
			
			while (existingEntry != null && !existingEntry.isRemoved()) {
				if (existingEntry.getKey().equals(key)) {
					V existingValue = existingEntry.getValue();
					existingEntry.setValue(value);
					
					return existingValue;
				}
				
				hashCode++;
				
				if (hashCode > table.length - 1) {
					hashCode = 0;
				}
				
				existingEntry = table[hashCode];
			}
			
			if (existingEntry == null) {
				size++;
			}
			
			table[hashCode] = newEntry;
		}
		
		return null;
	}
	
	/**
	 * Regrows the hash table.
	 * 
	 * @return The regrown hash table.
	 */
	private void regrow() {
		MapEntry<K, V>[] copy = table;
		table = (MapEntry<K, V>[]) new MapEntry[2 * table.length + 1];
		size = 0;
			
		for (int i = 0; i < copy.length; i++) {
			if (copy[i] != null && !copy[i].isRemoved()) {
				put(copy[i].getKey(), copy[i].getValue());
			}
		}
	}
	
	/**
	 * Searches through the hash table to find the value corresponding
	 * to the specified key. 
	 * 
	 * Throw an IllegalArgumentException if input is null.
	 * 
	 * @param key The Key of the entry that you are looking to retrieve.
	 * @return the value of the entry indicated by the key, null if not
	 * in the table.
	 */
	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		V target = null;
		int hashCode = Math.abs(key.hashCode()) % table.length;
		boolean found = false;
		
		while (table[hashCode] != null && !found) {
			if (!table[hashCode].isRemoved() && table[hashCode].getKey().equals(key)) {
				found = true;
				target = table[hashCode].getValue();
			}
			else {
				hashCode++;
				
				if (hashCode > table.length - 1) {
					hashCode = 0;
				}
			}
		}
		
		return target;
	}
	
	/**
	 * Remove the MapEntry specified by the key from the hash table.
	 * 
 	 * Throw an IllegalArgumentException if input is null.
	 * 
	 * @param key The key of the entry that you are looking to remove.
	 * @return the value that corresponds with the removed key.
	 */
	public V remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		V target = null;
		int hashCode = Math.abs(key.hashCode()) % table.length;
		boolean found = false;
		
		while (table[hashCode] != null && !found) {
			if (!table[hashCode].isRemoved() && table[hashCode].getKey().equals(key)) {
				found = true;
				target = table[hashCode].getValue();
				table[hashCode].setValue(null);
				table[hashCode].setRemoved(true);
				size--;
			}
			else {
				hashCode++;
				
				if (hashCode > table.length - 1) {
					hashCode = 0;
				}
			}
		}

		return target;
	}
	
	/**
	 * Determine whether the hash table contains the specified value.
	 * 
	 * Throw an IllegalArgumentException if input is null.
	 * 
	 * @param value The value that you are looking for.
	 * @return true if the value is in the hash table, false if not.
	 */
	public boolean contains(V value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		
		boolean contained = false;
		
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && !table[i].isRemoved()  && table[i].getValue().equals(value)) {
				contained = true;
			}
		}
		
		return contained;
	}
	
	/**
	 * Determine whether the hash table contains the specified key.
	 * 
	 * Throw an IllegalArgumentException if input is null.
	 * 
	 * @param key The key that you are looking for.
	 * @return true if the key is in the hash table, false if not.
	 */
	public boolean containsKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		int hashCode = Math.abs(key.hashCode()) % table.length;
		boolean contained = false;
		
		while (table[hashCode] != null && !contained) {
			if (!table[hashCode].isRemoved() && table[hashCode].getKey().equals(key)) {
				contained = true;
			}
			else {
				hashCode++;
				
				if (hashCode > table.length - 1) {
					hashCode = 0;
				}
			}
		}
		
		return contained;
	}
	
	/**
	 * Return the unique set of keys in the hash table. If there are no keys
	 * return an empty set. You may use java.util.HashSet. 
	 * 
	 * @return the set of unique keys for the hash table.
	 */
	public Set<K> keySet() {
		Set<K> keySet = new HashSet<K>();
		
		for (int i = 0; i < table.length; i++) {
			MapEntry<K, V> currEntry = table[i];
			
			if (currEntry != null && !currEntry.isRemoved()) {
				keySet.add(currEntry.getKey());
			}
		}
		
		return keySet;
	}
	
	/**
	 * Return all the values in the hash table. If there are no values return
	 * an empty collection (you may use java.util.ArrayList or LinkedList).
	 * 
	 * @return a collection of all the values in the hash table.
	 */
	public Collection<V> values() {
		Collection<V> values = new ArrayList<V>();
		
		for (int i = 0; i < table.length; i++) {
			MapEntry<K, V> currEntry = table[i];
			
			if (currEntry != null && !currEntry.isRemoved()) {
				values.add(currEntry.getValue());
			}
		}
		
		return values;
	}
	
	/**
	 * Returns the set of distinct map entries. If there are no entries 
	 * return an empty set. You may use java.util.HashSet.
	 * 
	 * @return set of all the entries in the hash table.
	 */
	public Set<MapEntry<K, V>> entrySet() {
		Set<MapEntry<K, V>> entrySet = new HashSet<MapEntry<K, V>>();
		
		for (int i = 0; i < table.length; i++) {
			MapEntry<K, V> currEntry = table[i];
			
			if (currEntry != null && !currEntry.isRemoved()) {
				entrySet.add(currEntry);
			}
		}
		
		return entrySet;
	}
	
	/**
	 * Return the number of elements in the hash table.
	 * 
	 * @return the number of elements in the hash table.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return if the hash table is empty or not.
	 * 
	 * @return true if the hash table is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Clear all entries in the hash table. This method should also reset
	 * the backing array to the initial capacity. 
	 */
	public void clear() {
		table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
		size = 0;
	}
}
