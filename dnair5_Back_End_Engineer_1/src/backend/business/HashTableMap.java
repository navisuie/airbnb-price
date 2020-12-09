package backend.business;
// --== CS400 File Header Information ==--
// Name: Dyuthi Nair
// Email: dnair5@wisc.edu 
// Team: BC
// TA: Bri Cochran
// Lecturer: Florian Heimerl	
// Notes to Grader: 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HashTableMap implements MapADT<Object, Object>{

	private LinkedListADT[] map = null;
	private int load = 0;
	
	public HashTableMap(int capacity) {
		map = new LinkedListADT[capacity];
	}
	
	public HashTableMap() {
		map = new LinkedListADT[10];
	}
	
	public static void fill(HashTableMap Database, String fileName) { //this is the new method that takes the 
		for (int i = 0; i < 91; i++) {								  //data and puts it into the hashtable
			Scanner reader = new Scanner(fileName);
			Database.put(reader.nextInt(), reader.nextLine());
		}
		return;
	}
	
	@Override
	public boolean put(Object key, Object value) {
		if(containsKey(key)) return false;
		int index = Math.abs(key.hashCode()) % map.length;
		if(map[index] == null) { //first key-value pair to be put in this index of the array
			map[index] = new LinkedListADT(new LinkedNode(key, value));
		} else {
			map[index].add(new LinkedNode(key, value)); //adds key-value pair to a LinkedList
		}	
		load++;
		if(atLoadCapacity()) {
			rehash();
		}
		return true;
	}
	
	private boolean atLoadCapacity(){
		return ((double) (load / map.length) >= 0.8);
	}
	
	private void rehash() {
		LinkedListADT[] newMap = map;
		map = new LinkedListADT[map.length * 2]; //recreates HashMap with doubled capacity
		load = 0;
		for(int i = 0; i < newMap.length; i++) {
			if(newMap[i] != null) {
				for(int j = 0; j < newMap[i].getSize(); j++) {
					put(newMap[i].get(j).getKey(), newMap[i].get(j).getValue()); //rehashes all values from old map into new one
				}
			}
		}
	}

	@Override
	public Object get(Object key) throws NoSuchElementException {
		if(!containsKey(key)) throw new NoSuchElementException("Key not found in map");
		int index = Math.abs(key.hashCode()) % map.length;
		int iteration = map[index].findKey(key); //finds where in the LinkedList the key is located
		return map[index].get(iteration).getValue(); //retrieves key's value from location found on previous line
	}

	@Override
	public int size() {
		return load;
	}

	@Override
	public boolean containsKey(Object key) {
		int index = Math.abs(key.hashCode()) % map.length;
		if(map[index] == null) return false;
		else {
			if(map[index].findKey(key) < 0) return false;
			return true;
		}
	}

	@Override
	public Object remove(Object key) {
		if(!containsKey(key)) throw new NoSuchElementException("Key not found in map");
		int index = Math.abs(key.hashCode()) % map.length;
		LinkedListADT current = map[index];
		int iteration = current.findKey(key);
		Object beingRemoved = current.get(iteration);
		if(iteration == 0) { //removing pair from the front of a LinkedList
			current.resetFirstEntry();
		} else if(iteration == current.getSize()) { //removing pair from the end of a LinkedList
			current.resetLastEntry();
		} else { //removing a pair from middle of a LinkedList
			current.resetMiddleEntry(iteration);
		}
		return beingRemoved;
		
	}

	@Override
	public void clear() {
		map = new LinkedListADT[map.length];		
		load = 0;
	}
	
	public int find(Object key) {
	    int index = (key.hashCode() < 0 ? 0 - key.hashCode() : key.hashCode()) % map.length;
	    LinkedListADT temp = map[index];
	    if (temp != null) {
	      for (int i = 0; i < temp.getSize(); i++) {
	        if (temp.get(i).getKey().equals(key)) {
	          return i;
	        }
	      }
	    }
	    return -1;
	  }
}
