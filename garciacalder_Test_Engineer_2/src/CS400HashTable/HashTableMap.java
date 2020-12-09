// --== CS400 File Header Information ==--
// Name: Leonardo Garcia Calderon
// Email: garciacalder@wisc.edu
// Team: BC
// TA: Bri Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: 

import java.util.NoSuchElementException;
import java.util.LinkedList;

// hash table class that holds generic key and value type using chained linked lists
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	// I use this private class as an object that I put in my linked lists in order to hold two values
	private class Node {
		KeyType key;
		ValueType value;
	}

	private int capacity;  // length of array
	private LinkedList[] table;  // array of linked lists that hold my Nodes
	private int size;   // # of pairs total in my hash table

	// constructor with capacity as a parameter
	public HashTableMap(int capacity) {
		this.capacity = capacity;
		size = 0;
		table = new LinkedList[capacity];
	}

	// constructor with no parameter. default capacity is 10
	public HashTableMap() {
		capacity = 10;
		size = 0;
		table = new LinkedList[capacity];
	}

	// this method adds data pairs into the hash table
	@Override
	public boolean put(KeyType key, ValueType value) {
		// getting hash code and index of table
		int index = Math.abs(key.hashCode() % capacity);
		LinkedList<Node> list = table[index];
		// if the list is empty, then a new linked list is created
		if (list == null) {
			list = new LinkedList<Node>();
			Node node = new Node();
			node.key = key;
			node.value = value;
			list.add(node);
			table[index] = list;
			++size;
			// if load capacity is over 80%, double the capacity and rehash
			if (size  >= capacity * 0.8) {
				increaseCapacity();
				rehash();
			}
			return true;
		}
		// checking to see if key is already in hash table
		int i = 0;
		while (i < list.size()) {
			if (list.get(i).key.equals(key)) {
				return false;
			}
			++i;
		}
		// link list already created and key is unique, so new data pair is added to linked list
		Node node = new Node();
		node.key = key;
		node.value = value;
		list.add(node);
		++size;
		
		// if load capacity is over 80%, double the capacity and rehash
				if (size  >= capacity * 0.8) {
					increaseCapacity();
					rehash();
				}

		return true;
	}

	// helper method: double capacity of hash table
	private void increaseCapacity() {
		capacity = capacity * 2;
	}

	// helper method: rehash hash table
	private void rehash() {
		// copying table in order to make a new one
		LinkedList[] oldTable = table;
		// new one made with new capacity
		table = new LinkedList[capacity];
		size = 0;
		// two loops in order to go through old table and rehash into new table
		for (int i = 0; i < (capacity / 2); ++i) {
			LinkedList<Node> list = oldTable[i];
			if (list != null) {
				int j = 0;
				while (j < list.size()) {
					put(list.get(j).key, list.get(j).value);
					++j;
				}
			}
		}
	}

	// this method has key as parameter and returns the value that is paired with it
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		// checking hash code and index of linkedlist
		int index = Math.abs(key.hashCode() % capacity);
		LinkedList<Node> list = table[index];
		// if linked list isn't null, then must go through lists and check if key exists
		if (list != null) {
		int i = 0;
		while (i < list.size()) {
			// if true, then key exists
			if (list.get(i).key.equals(key)) {
				return list.get(i).value;
			}
			++i;
		}
		}
		// exception thrown because key doesn't exist
		throw new NoSuchElementException("The key does not exist.");
	}

	// this method returns the # of data pairs in hash table
	@Override
	public int size() {
		return this.size;
	}
	
	// helper method that returns current capacity of hash table
	public int capacity() {
		return this.capacity;
	}

	// this method checks whether or not the hash table contains the key
	@Override
	public boolean containsKey(KeyType key) {
		// checks hash code and index of table
		int index = Math.abs(key.hashCode() % capacity);
		LinkedList<Node> list = table[index];
		// if list isn't null, must go through list to check if key exists
		if (list != null) {
			int i = 0;
			while (i < list.size()) {
				// if true, then key exists
				if (list.get(i).key.equals(key)) {
					return true;
				}
				++i;
			}
		}
		return false;
	}

	// this method removes the data pair corresponding with the key, and returns the value
	// that was paired with it. returns null is key doesn't exist
	@Override
	public ValueType remove(KeyType key) {
		// checks hash code and table index
		int index = Math.abs(key.hashCode() % capacity);
		LinkedList<Node> list = table[index];
		// if linked list doesn't exist yet
		if (list == null) {
			return null;
		}
		// checking to see if key exists
		int i = 0;
		while (i < list.size()) {
			// if key exists, decrement size, remove pair, and return value
			if (list.get(i).key.equals(key)) {
					--size;
				return list.remove(i).value;
			}
			++i;
		}
		// return null if key doesn't exist
		return null;
	}

	// this method clears the entire hash table
	@Override
	public void clear() {
		for (int i = 0; i < capacity; ++i) {
			table[i] = null;
		}
		size = 0;
	}

}
