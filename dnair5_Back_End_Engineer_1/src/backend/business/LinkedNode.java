package backend.business;
// --== CS400 File Header Information ==--
// Name: Dyuthi Nair
// Email: dnair5@wisc.edu 
// Team: BC
// TA: Bri Cochran
// Lecturer: Florian Heimerl	
// Notes to Grader: 

public class LinkedNode { // the nodes for the LinkListADT that I built
	
	private Object key;
	private Object value;
	private LinkedNode next;
	
	public LinkedNode(Object key, Object value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public Object getKey() {
		return this.key;
	}
	
	public LinkedNode getNext() {
		return this.next;
	}
	
	public void setNext(LinkedNode next) {
		this.next = next;
	}

}
