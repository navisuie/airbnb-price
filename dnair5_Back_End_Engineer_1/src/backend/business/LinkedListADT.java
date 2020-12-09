package backend.business;
// --== CS400 File Header Information ==--
// Name: Dyuthi Nair
// Email: dnair5@wisc.edu 
// Team: BC
// TA: Bri Cochran
// Lecturer: Florian Heimerl	
// Notes to Grader: 

public class LinkedListADT { //consists of nodes and is added to each index of the array when a key, value pair is added
	private LinkedNode firstEntry;
	private int size;
	
	public LinkedListADT(LinkedNode firstEntry) {
		this.firstEntry = firstEntry;
		size = 1;
	}
	
	public void add(LinkedNode nextEntry) {
		firstEntry.setNext(nextEntry);
		size++;
	}
	
	public int getSize() {
		return size;
	}
	
	public LinkedNode get(int index) { //retrieves the node given the location within the LinkedList
		int count = 0;
		LinkedNode iterator = firstEntry;
		while(count != index) {
			iterator = iterator.getNext();
			count++;
		}
		return iterator;
	}
	
	public int findKey(Object key) { //finds the location of a key in the LinkedList, returns -1 if not found
		int count = 0;
		LinkedNode iterator = firstEntry;
		while(count != size) {
			if(iterator.getKey() == key) return count; 
			iterator = iterator.getNext();             
			count++;								   
		}
		return -1;
	}
	
	public void resetFirstEntry() { //rearrangement of LinkedList given removal of first element
		firstEntry = firstEntry.getNext();
		size--;
	}
	
	public void resetLastEntry() { //rearrangement of LinkedList given removal of last element
		size--;
		get(size).setNext(null);
	}
	
	public void resetMiddleEntry(int iteration) { //rearrangement of LinkedList given removal of a middle element
		get(iteration - 1).setNext(get(iteration).getNext());
		size--;
	}
}
