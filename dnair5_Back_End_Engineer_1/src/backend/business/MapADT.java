package backend.business;
// --== CS400 File Header Information ==--
// Name: Dyuthi Nair
// Email: dnair5@wisc.edu 
// Team: BC
// TA: Bri Cochran
// Lecturer: Florian Heimerl	
// Notes to Grader: 

import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {

	public boolean put(KeyType key, ValueType value);
	public ValueType get(KeyType key) throws NoSuchElementException;
	public int size();
	public boolean containsKey(KeyType key);
	public ValueType remove(KeyType key);
	public void clear();
	
}