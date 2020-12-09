
// --== CS400 File Header Information ==--
// Name: <Navpreet Singh>
// Email: <nsingh49@wisc.edu>
// Team: <>
// TA: <>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {
	public boolean put(KeyType key, ValueType value);

	public ValueType get(KeyType key) throws NoSuchElementException;

	public int size();

	public boolean containsKey(KeyType key);

	public ValueType remove(KeyType key);

	public void clear();
}